package com.java.astar;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AstarClassTest {

	private String mapFilePath = "testmap.txt";


    @Before
    public void setup() throws FileNotFoundException, UnsupportedEncodingException {
        String[][] worldMapArrays = new String[5][5];
        String worldMapString = "@*^^^~~*~.**...^..*~~~*~X";
        for (int i = 0; i < worldMapString.length(); i++) {
            worldMapArrays[i / 5][i % 5] = String.valueOf(worldMapString.charAt(i));
        }

        PrintWriter writer = new PrintWriter(mapFilePath, "UTF-8");
        for (int i = 0; i < worldMapArrays.length; i++) {
            for (int j = 0; j < worldMapArrays[i].length; j++) {
                writer.print(worldMapArrays[i][j]);
            }
            writer.println();
        }
        writer.close();
    }

    @Test
    public void TestMain() throws IOException {
        //Run the main method and produce an output map using the map printed in the setup
        String[] args = new String[1];
        args[0] = mapFilePath;
        AstarClass.main(args);

        //Using the provided solution create a new terrain matrix that will be used to test the approximate correctness
        String[][] testOutputMap = new String[5][5];
        String worldMapString = "##^^^~~#~.**.#.^..#~~~*~#";
        for (int i = 0; i < worldMapString.length(); i++) {
            testOutputMap[i / 5][i % 5] = String.valueOf(worldMapString.charAt(i));
        }

        //Put all the data from the output file into an array
        BufferedReader reader = new BufferedReader(new FileReader("test.txt"));
        String line = reader.readLine();
        String[][] outputMap = new String[5][5];
        int lineNumber = 0;
        while(line != null){
            String[] terrain = new String[line.length()];
            for (int i = 0; i < line.length(); i++) {
                terrain[i] = String.valueOf(line.charAt(i));
            }
            outputMap[lineNumber++] = terrain;
            line = reader.readLine();
        }

        //Test that all the entries are equal
        for (int i = 0; i < testOutputMap.length; i++) {
            for (int j = 0; j < testOutputMap[i].length; j++) {
                assertEquals(testOutputMap[i][j], outputMap[i][j]);
            }
        }
    }

    @After
    public void cleanFiles(){
        File[] fileList = new File[2];
        fileList[0] = new File(mapFilePath);
        fileList[1] = new File("output.txt");
        for (File file : fileList) {
            file.delete();
        }
    }
}
