package com.mirka.tetristry;

// Diferent options for pieces
public enum PieceOption {
    blankPiece(new int[][] {{0,0},{0,0},{0,0},{0,0}}, new short[] {4}),
    linePiece(new int[][] {{0,0}, {1,0}, {2,0}, {-1,0}}, new short[] {4}),
    zPiece(new int[][] {{0,0}, {0,-1}, {0,1}, {-1,-1}}, new short[] {4}),
    reverseZPiece(new int[][]{{0,0}, {-1,0}, {0,-1}, {1,-1}}, new short[] {4}),
    lPiece(new int[][] {{0,0}, {1,0}, {-1,0}, {-1,-1}}, new short[] {4}),
    reverseLPiece(new int[][] {{0,0}, {1,0}, {1,-1}, {0,-1}}, new short[] {4}),
    squarePiece(new int[][] {{0,0}, {1,0}, {0,-1}, {-1,-1}}, new short[] {4}),
    tPiece(new int[][] {{0,0}, {1,0}, {-1,0}, {0,-1}}, new short[] {4});

    public int [][] relativeCoordinates;
    public short blockAmmount;

    private PieceOption(int [][] coordinates, short[] size) {
        this.relativeCoordinates = coordinates;
        this.blockAmmount = size[0];
    }


}
