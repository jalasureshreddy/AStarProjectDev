package com.java.astar.utils;

public class Coordinates {
	
	private int x, y;

    public Coordinates() {
    }

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString(){
        return String.valueOf(x) + "."  +String.valueOf(y);
    }

}
