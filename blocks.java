enum Pieces {
    blankPiece(new int[][] {{0,0}, {0,0}, {0,0}, {0,0} }),
    linePiece(new int[][] {{0,0}, {1,0}, {2,0}, {-1,0}}),
    zPiece(new int[][] {{0,0}, {0,-1}, {0,1}, {-1,-1}}),
    reverseZPiece(new int[][]{{0,0}, {-1,0}, {0,-1}, {1,-1}}),
    lPiece(new int[][] {{0,0}, {1,0}, {-1,0}, {-1,-1}}),
    reverseLPiece(new int[][] {{0,0}, {1,0}, {1,-1}, {0,-1}}),
    squarePiece(new int[][] {{0,0}, {1,0}, {0,-1}, {-1,-1}),
    tPiece(new int[][] {{0,0}, {1,0}, {-1,0}, {0,-1}});



}