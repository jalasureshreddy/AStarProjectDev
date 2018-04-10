package com.java.astar.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import org.junit.Test;


public class MapLoaderTest {

	private MapLoader mapLoader = new MapLoader();

    @Test
    public void testMapEquality() throws FileNotFoundException, UnsupportedEncodingException {
        //First we build a simple map
        String[][] worldMapArrays = new String[5][5];
        String worldMapString = "@*^^^~~*~.**...^..*~~~*~X";
        for (int i = 0; i < worldMapString.length(); i++) {
            worldMapArrays[i / 5][i % 5] = String.valueOf(worldMapString.charAt(i));
        }

        WorldMap worldMap = new WorldMap(5, 5);
        worldMap.buildMap(worldMapArrays);

        //Then use that same data to write to a temporary test file
        PrintWriter writer = null;
        writer = new PrintWriter("testmap.txt", "UTF-8");
        for (int i = 0; i < worldMapArrays.length; i++) {
            for (int j = 0; j < worldMapArrays[i].length; j++) {
                writer.print(worldMapArrays[i][j]);
            }
            writer.println();
        }
        writer.close();

        //Use that test file to create a map using our  maploader
        WorldMap loadedMap = mapLoader.load("testmap.txt");

        for (int i = 0; i < worldMap.getRowNum(); i++) {
            for (int j = 0; j < worldMap.getColumnNum(); j++) {
                assertEquals("Map positions are not equal", worldMap.getMap()[i][j], loadedMap.getMap()[i][j]);
            }
        }

        assertNotNull("Map loaded is null, map building failed", loadedMap.getIndex());

        assertEquals(worldMap.getIndex().size(), loadedMap.getIndex().size());

        for (String worldMapKey : worldMap.getIndex().keySet()) {
            assertEquals(worldMap.getIndex().get(worldMapKey), worldMap.getIndex().get(worldMapKey));
        }

        //Finally, delete the temporary file
        File file = new File("testmap.txt");
        file.delete();
    }
}
