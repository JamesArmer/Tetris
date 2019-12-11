package com.mirka.tetristry;

// Diferent options for pieces
public enum PieceOption {
    blankPiece(new int[][] {{0,0},{0,0},{0,0},{0,0}}, new short[] {4}, "BP"),
    linePiece(new int[][] {{0,0}, {1,0}, {2,0}, {-1,0}}, new short[] {4}, "LI"),
    reverseLPiece(new int[][] {{0,0}, {0,-1}, {0,1}, {-1,-1}}, new short[] {4}, "Rl"),
    zPiece(new int[][]{{0,0}, {-1,0}, {0,-1}, {1,-1}}, new short[] {4}, " Z"),
    lPiece(new int[][] {{0,0}, {1,0}, {-1,0}, {-1,-1}}, new short[] {4}, " L"),
    squarePiece(new int[][] {{0,0}, {1,0}, {1,-1}, {0,-1}}, new short[] {4}, " S"),
    reverseZPiece(new int[][] {{0,0}, {1,0}, {0,-1}, {-1,-1}}, new short[] {4}, "RZ"),
    tPiece(new int[][] {{0,0}, {1,0}, {-1,0}, {0,-1}}, new short[] {4}, " T");

    public int [][] relativeCoordinates;
    public short blockAmmount;
    public String name;

    private PieceOption(int [][] coordinates, short[] size, String pieceName) {
        this.relativeCoordinates = coordinates;
        this.blockAmmount = size[0];
        this.name = pieceName;
    }


}
