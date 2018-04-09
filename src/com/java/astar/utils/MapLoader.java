package com.java.astar.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MapLoader {

	//loading the map file 
	 public WorldMap load(String filePath) {
		 
		   //reading the file  
	        BufferedReader reader = null;
	        try {
	            reader = new BufferedReader(new FileReader(filePath));
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();  
	        }
	        //creating an object of WorldMap 
	        WorldMap worldMap = null;
	        try {
	            String line = reader.readLine();
	            List<String[]> terrains = new ArrayList<String[]>();

	            while(line != null){
	                String[] terrain = new String[line.length()];
	                for (int i = 0; i < line.length(); i++) {
	                    terrain[i] = String.valueOf(line.charAt(i));
	                }
	                terrains.add(terrain);
	                line = reader.readLine();
	            }

	            worldMap = new WorldMap(terrains.size(), terrains.get(0).length);
	            worldMap.buildMap(terrains.toArray(new String[terrains.size()][]));

	        } catch (IOException e) {
	            e.printStackTrace();  
	        }

	        return worldMap;
	    }
}
