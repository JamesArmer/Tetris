import blocks.*;

public class Piece {
    private int[] baseCoordinates;
    private int[][] shapeCoordinates;
    private int[] reomoved;
    private Blocks shape;
    int size;

    public Piece(int[] startCoord, Blocks type = blankPiece){
        baseCoordinates = {0, 0};
        shape = type;
        size = type.blockAmmount;
        removed = new int[];
        setShape(type);
    }

    private void setShape(Blocks type){
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < 2; ++j) {
                shapeCoordinates[i][j] = shape.coords[i][j];
            }
        }
    }

    public void removeAt(int[] coord){
        int [] tempCoord
        for (int i = 0; i < size; i++) {
            if (coord == shapeCoordinates[i]){
                shapeCoordinates[i] = {-1,- 1};
            }
        }
    }

    public void moveDown(){
        this.baseCoordinates[1]--;
    }

}