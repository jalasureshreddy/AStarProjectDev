package com.java.astar;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import com.java.astar.utils.Coordinates;
import com.java.astar.utils.MatrixPosition;
import com.java.astar.utils.MovementCosts;
import com.java.astar.utils.MapLoader;
import com.java.astar.utils.WorldMap;

public class AstarClass {	
	
	
	public static void main(String args[]) {
		
		//initializing MapLoader class
		MapLoader worldMapLoader = new MapLoader();
		//checking if AStarClass has got any input map file to check 
        if(args.length < 1){
            System.out.println("Please specify the full path of the map file that you would like to run A* on");
            System.exit(0);
        }
        //getting the mapPath 
        String mapPath = args[0];
        WorldMap worldMap = worldMapLoader.load(mapPath);

        WorldMap outputMap = worldMapLoader.load(mapPath);

        Comparator<MatrixPosition> comparator = new Comparator<MatrixPosition>() {
            @Override
            public int compare(MatrixPosition o1, MatrixPosition o2) {
                return (o1.getF() - o2.getF());
            }
        };

        PriorityQueue<MatrixPosition> openSet = new PriorityQueue<MatrixPosition>(10, comparator);
        List<MatrixPosition> closedSet = new ArrayList<MatrixPosition>();

        //Step 1: Start at @
        MatrixPosition current = worldMap.getStart();
        current.setG(MovementCosts.getMovementCosts().get(current.getValue()));
        current.setH(worldMap.getManhattanDistance(current));
        current.setF();
        openSet.add(current);

        while(!closedSet.contains(worldMap.getEnd()) || openSet.isEmpty()){
            //Step 5: Choose the best tile to move to based on the lowest total score
            current = openSet.remove();
            closedSet.add(current);

            for (Coordinates coordinates : worldMap.getNeighbours(current)) {
                //Step 2: Search the surrounding tiles for walkable choices
                MatrixPosition neighbour = worldMap.getIndex().get(new Coordinates(current.getRow() + coordinates.getX(), current.getColumn() + coordinates.getY()).toString());
                if(!closedSet.contains(neighbour) && !neighbour.getValue().equals("~")){
                    if(!openSet.contains(neighbour)){
                        neighbour.setParent(current);
                        //Step 3: Find the cost of movement to each of the tiles
                        neighbour.setG(current.getG() + MovementCosts.getMovementCosts().get(neighbour.getValue()));
                        //Step 4: Determine the distance from the choice tile to the goal using |x1 - x2| + |y1 - y2|
                        neighbour.setH(worldMap.getManhattanDistance(neighbour));
                        //This gives the tile's total cost of movement
                        neighbour.setF();
                        openSet.add(neighbour);
                    }
                    else{
                        if(neighbour.getG() < current.getG()){
                            neighbour.setParent(current);
                            neighbour.setG(current.getG() + MovementCosts.getMovementCosts().get(neighbour.getValue()));
                            neighbour.setH(worldMap.getManhattanDistance(neighbour));
                            neighbour.setF();
                            openSet.add(neighbour);
                        }
                    }
                }
            }
        }

        if(openSet.isEmpty()){
            System.out.println("Failed");
        }
        else{
            while(current != null){
                current.setValue("#");
                outputMap.setPosition(current);
                current = current.getParent();
            }

            try {
                PrintWriter writer = new PrintWriter(mapPath + ".out", "UTF-8");
                for (int i = 0; i < outputMap.getMap().length; i++) {
                    for (int j = 0; j < outputMap.getMap()[i].length; j++) {
                        writer.print(outputMap.getMap()[i][j]);
                    }
                    writer.println();
                }
                writer.close();
                System.out.println("Successfully created output file," + mapPath + ".out");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }

	}
