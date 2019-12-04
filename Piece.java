import PieceOption;

public class Piece {
    private int[] baseCoordinates;
    private int[][] shapeCoordinates;
    private int[] reomoved;
    private PieceOption shape;
    int size;

    public Piece(int[] startCoord, PieceOption type = blankPiece){
        baseCoordinates = startCoord;
        shape = type;
        size = type.blockAmmount;
        removed = new int[];
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