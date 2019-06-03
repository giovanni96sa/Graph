package com.example.graph.giorgio.graph.stuffs;

public class Nodo {
    private int x;
    private int y;

    public Nodo(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    @Override
    public String toString() {
        return "x = " + x + "; y = " + y;
    }
}
