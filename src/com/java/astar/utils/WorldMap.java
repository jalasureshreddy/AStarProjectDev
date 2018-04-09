package com.java.astar.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class WorldMap {

    	private int rowNum, columnNum;
	    private MatrixPosition end, start;

	    private String[][] map;
	    private Map<String, MatrixPosition> index;

	    public WorldMap(int rowNum, int columnNum){
	        this.rowNum = rowNum;
	        this.columnNum = columnNum;
	        map = new String[rowNum][columnNum];
	        index = new HashMap<String, MatrixPosition>();
	    }

	    public void buildMap(String[][] entries){
	        for (int i = 0; i < rowNum; i++) {
	            for (int j = 0; j < columnNum; j++) {
	                map[i][j] = entries[i][j];
	                index.put(String.valueOf(i) + "." + String.valueOf(j), new MatrixPosition(i, j, entries[i][j]));
	            }
	        }

	        for (int i = rowNum - 1; i >= 0; i--) {
	            for (int j = columnNum - 1; j >= 0; j--) {
	                if(entries[i][j].equals("X")){
	                    end = new MatrixPosition(i, j, entries[i][j]);
	                    break;
	                }
	            }
	            if(end != null){
	                break;
	            }
	        }

	        for (int i = 0; i < rowNum; i++) {
	            for (int j = 0; j < columnNum; j++) {
	                if(entries[i][j].equals("@")){
	                    start = new MatrixPosition(i, j, entries[i][j]);
	                    break;
	                }
	            }
	            if(start != null){
	                break;
	            }
	        }
	    }

	    public List<Coordinates> getNeighbours(MatrixPosition current ){
	        Coordinates currentCoordinates = new Coordinates(current.getRow(), current.getColumn());
	        List<Coordinates> neighbours = new ArrayList<Coordinates>();
	        for (int x = -1; x <= 1; x++) {
	            for (int y = -1; y <= 1; y++) {
	                if((x != 0 || y != 0)) {//This is the current entry and not its neighbors
	                    if((currentCoordinates.getX() + x >= 0 && currentCoordinates.getY() + y >= 0)
	                            && (currentCoordinates.getX() + x < rowNum && currentCoordinates.getY() + y < columnNum)){
	                        neighbours.add(new Coordinates(x, y));
	                    }
	                }
	            }
	        }

	        return neighbours;
	    }

	    public Map<String, MatrixPosition> getIndex() {
	        return index;
	    }

	    public int getRowNum() {
	        return rowNum;
	    }

	    public int getColumnNum() {
	        return columnNum;
	    }

	    public String[][] getMap() {
	        return map;
	    }

	    public MatrixPosition getStart() {
	        return start;
	    }

	    public MatrixPosition getEnd() {
	        return end;
	    }

	    public int getManhattanDistance(MatrixPosition position){
	        return Math.abs(position.getRow() - end.getRow()) + Math.abs(position.getColumn() - end.getColumn());
	    }

	    public void setPosition(MatrixPosition matrixPosition){
	        map[matrixPosition.getRow()][matrixPosition.getColumn()] = matrixPosition.getValue();
	        index.put(String.valueOf(matrixPosition.getRow()) + String.valueOf(matrixPosition.getColumn()), matrixPosition);
	    }
}
