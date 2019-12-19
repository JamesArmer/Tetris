package com.mirka.tetristry;

public class Piece {
    public int[][] shapeCoordinates;
    public PieceOption shape;
    public short size;
    private int test;

    // Base start
    public Piece(PieceOption type) {
        shape = type;
        size = type.blockAmmount;
        shapeCoordinates = new int[size][2];
        setShape(type);
    }

    // copy over the coordinates
    private void setShape(PieceOption type) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < 2; ++j) {
                shapeCoordinates[i][j] = type.relativeCoordinates[i][j];
            }
        }
    }

    // Rotate piece clockwise
    public Piece rotateClock () {
        if (shape == PieceOption.squarePiece) {
            return this;
        }

        Piece rotated = new Piece(this.shape);
        for (int i = 0; i < size; i++) {
            rotated.shapeCoordinates[i][0] = -this.shapeCoordinates[i][1];
            rotated.shapeCoordinates[i][1] = this.shapeCoordinates[i][0];
        }
        return rotated;
    }

    // Rotate piece counter clockwise
    public Piece rotateCounter() {
        if (shape == PieceOption.squarePiece) {
            return this;
        }

        Piece rotated = new Piece(this.shape);
        for (int i = 0; i < size; i++) {
            rotated.shapeCoordinates[i][0] = this.shapeCoordinates[i][1];
            rotated.shapeCoordinates[i][1] = -this.shapeCoordinates[i][0];
        }
        return rotated;
    }

    // Get the minimum Y value
    public int[] minY() {
        int[] m = {0,0};

        for (int i = 0; i < size; i++) {
            if (this.shapeCoordinates[i][1] < m[1]) {
                m[1] = this.shapeCoordinates[i][1];
            }
        }

        return m;
    }

    // Get the minimum X value
    public int minX() {
        int m = 0;

        for (int i = 0; i < size; i++) {
            if (this.shapeCoordinates[i][0] < m) {
                m = this.shapeCoordinates[i][0];
            }
        }

        return m;
    }

    // Get the maximum X value
    public int maxX() {
        int m = 0;

        for (int i = 0; i < size; i++) {
            if (this.shapeCoordinates[i][0] > m) {
                m = this.shapeCoordinates[i][0];
            }
        }
        return m;
    }
}
