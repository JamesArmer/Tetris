package com.mirka.tetristry;

public class Piece {
    public int[][] shapeCoordinates;
    public PieceOption shape;
    private short size;
    private int test;

    public Piece(PieceOption type) {
        shape = type;
        size = type.blockAmmount[0];
        setShape(type);
    }

    private void setShape(PieceOption type) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < 2; ++j) {
                shapeCoordinates[i][j] = type.relativeCoordinates[i][j];
            }
        }
    }

    public Piece rotateClock () {
        Piece rotated = new Piece(this.shape);

        for (int i = 0; i < size; i++) {
            rotated.shapeCoordinates[i][0] = this.shapeCoordinates[i][0];
            rotated.shapeCoordinates[i][1] = -this.shapeCoordinates[i][1];
        }
        return rotated;
    }

    public Piece rotateCounter() {
        Piece rotated = new Piece(this.shape);
        for (int i = 0; i < size; i++) {
            rotated.shapeCoordinates[i][0] = -this.shapeCoordinates[i][0];
            rotated.shapeCoordinates[i][1] = this.shapeCoordinates[i][1];
        }
        return rotated;
    }

    public int[] minY(){
        int[] m = {0,0};

        for (int i = 0; i < size; i++) {
            if (this.shapeCoordinates[i][1] < m[1]){
                m[1] = this.shapeCoordinates[i][1];
            }
        }

        return m;
    }

    public int minX(){
        int m = 0;

        for (int i = 0; i < size; i++) {
            if (this.shapeCoordinates[i][0] < m){
                m = this.shapeCoordinates[i][0];
            }
        }

        return m;
    }

    public int maxX(){
        int m = 0;

        for (int i = 0; i < size; i++) {
            if (this.shapeCoordinates[i][0] > m){
                m = this.shapeCoordinates[i][0];
            }
        }
        return m;
    }
}
