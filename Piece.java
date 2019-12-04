public class Piece {
    enum PieceOption {
        blankPiece(new int[][] {{0,0}}, new short[] {1}),
        linePiece(new int[][] {{0,0}, {1,0}, {2,0}, {-1,0}}, new short[] {4}),
        zPiece(new int[][] {{0,0}, {0,-1}, {0,1}, {-1,-1}}, new short[] {4}),
        reverseZPiece(new int[][]{{0,0}, {-1,0}, {0,-1}, {1,-1}}, new short[] {4}),
        lPiece(new int[][] {{0,0}, {1,0}, {-1,0}, {-1,-1}}, new short[] {4}),
        reverseLPiece(new int[][] {{0,0}, {1,0}, {1,-1}, {0,-1}}, new short[] {4}),
        squarePiece(new int[][] {{0,0}, {1,0}, {0,-1}, {-1,-1}}, new short[] {4}),
        tPiece(new int[][] {{0,0}, {1,0}, {-1,0}, {0,-1}}, new short[] {4});

        public int [][] relativeCoordinates;
        public short[] blockAmmount;

        private PieceOption(int [][] coordinates, short[] size) {
            this.relativeCoordinates = coordinates;
            this.blockAmmount = size;
        }

    }

    private int[] baseCoordinates;
    private int[][] shapeCoordinates;
    private int[] reomoved;
    private PieceOption shape;
    short size;

    public Piece(int[] startCoord, PieceOption type) {
        baseCoordinates = startCoord;
        shape = type;
        size = type.blockAmmount[0];
        setShape(type);
    }

    private void setShape(PieceOption type){
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < 2; ++j) {
                shapeCoordinates[i][j] = type.relativeCoordinates[i][j];
            }
        }
    }

    public void removeAt(int[] coord){
        int [] tempCoord;
        for (int i = 0; i < size; i++) {
            if (coord == shapeCoordinates[i]){
                shapeCoordinates[i][0] = -1;
                shapeCoordinates[i][1] = -1;
            }
        }
    }

    public void moveDown(){
        this.baseCoordinates[1]--;
    }

}