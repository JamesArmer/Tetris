package test;

import com.mirka.tetristry.Piece;
import com.mirka.tetristry.PieceOption;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PieceTest {

    private Piece blankPiece;
    private Piece linePiece;
    private Piece zPiece;
    private Piece reverseZPiece;
    private Piece lPiece;
    private Piece reverseLPiece;
    private Piece squarePiece;
    private Piece tPiece;
    private List<Piece> allPieces;

    @BeforeEach
    void setUp() {
        allPieces = new ArrayList<>();
        blankPiece = new Piece(PieceOption.blankPiece);
        allPieces.add(blankPiece);
        linePiece = new Piece(PieceOption.linePiece);
        allPieces.add(linePiece);
        zPiece = new Piece(PieceOption.zPiece);
        allPieces.add(zPiece);
        reverseZPiece = new Piece(PieceOption.reverseZPiece);
        allPieces.add(reverseZPiece);
        lPiece = new Piece(PieceOption.lPiece);
        allPieces.add(lPiece);
        reverseLPiece = new Piece(PieceOption.reverseLPiece);
        allPieces.add(reverseLPiece);
        squarePiece = new Piece(PieceOption.squarePiece);
        allPieces.add(squarePiece);
        tPiece = new Piece(PieceOption.tPiece);
        allPieces.add(tPiece);
    }

    @Test
    void testInitialisedPieces(){
        // Test shapes are initialised to their said shapes
        assertEquals(blankPiece.shape, PieceOption.blankPiece);
        assertEquals(linePiece.shape, PieceOption.linePiece);
        assertEquals(zPiece.shape, PieceOption.zPiece);
        assertEquals(reverseZPiece.shape, PieceOption.reverseZPiece);
        assertEquals(lPiece.shape, PieceOption.lPiece);
        assertEquals(reverseLPiece.shape, PieceOption.reverseLPiece);
        assertEquals(squarePiece.shape, PieceOption.squarePiece);
        assertEquals(tPiece.shape, PieceOption.tPiece);

        // Check all of the pieces have size == 4
        for(Piece p : allPieces){
            assertEquals(4, p.size);
        }

        // Test the pieces have their coordinates initalised properly
        int[][] expected;
        int[][] actual;
        // Blank piece
        expected = new int[][] {{0,0},{0,0},{0,0},{0,0}};
        actual = blankPiece.shapeCoordinates;
        assertArrayEquals(expected, actual);
        // Line piece
        expected = new int[][] {{0,0}, {1,0}, {2,0}, {-1,0}};
        actual = linePiece.shapeCoordinates;
        assertArrayEquals(expected, actual);
        // Z shaped piece
        expected = new int[][] {{0,0}, {0,-1}, {0,1}, {-1,-1}};
        actual = zPiece.shapeCoordinates;
        assertArrayEquals(expected, actual);
        // Inverted z shaped piece
        expected = new int[][] {{0,0}, {-1,0}, {0,-1}, {1,-1}};
        actual = reverseZPiece.shapeCoordinates;
        assertArrayEquals(expected, actual);
        // L shaped piece
        expected = new int[][] {{0,0}, {1,0}, {-1,0}, {-1,-1}};
        actual = lPiece.shapeCoordinates;
        assertArrayEquals(expected, actual);
        // Inverted L shaped piece
        expected = new int[][] {{0,0}, {1,0}, {1,-1}, {0,-1}};
        actual = reverseLPiece.shapeCoordinates;
        assertArrayEquals(expected, actual);
        // Square shaped piece
        expected = new int[][] {{0,0}, {1,0}, {0,-1}, {-1,-1}};
        actual = squarePiece.shapeCoordinates;
        assertArrayEquals(expected, actual);
        // T shaped piece
        expected = new int[][] {{0,0}, {1,0}, {-1,0}, {0,-1}};
        actual = tPiece.shapeCoordinates;
        assertArrayEquals(expected, actual);
    }

    @Test
    void rotateClock() {
        // Check this works for each piece
        int[][] expected;
        int[][] actual;

        expected = new int[][]{{0, 0}, {0, 0}, {0, 0}, {0, 0}};
        actual = blankPiece.rotateClock().shapeCoordinates;
        assertArrayEquals(expected, actual);

        expected = new int[][]{{0, 0}, {0, 1}, {0, 2}, {0, -1}};
        actual = linePiece.rotateClock().shapeCoordinates;
        assertArrayEquals(expected, actual);

        expected = new int[][]{{0,0}, {1,0}, {-1,0}, {1,-1}};
        actual = zPiece.rotateClock().shapeCoordinates;
        assertArrayEquals(expected, actual);

        expected = new int[][]{{0,0}, {0,-1}, {1,0}, {1,1}};
        actual = reverseZPiece.rotateClock().shapeCoordinates;
        assertArrayEquals(expected, actual);

        expected = new int[][]{{0,0}, {0,1}, {0,-1}, {1,-1}};
        actual = lPiece.rotateClock().shapeCoordinates;
        assertArrayEquals(expected, actual);

        expected = new int[][]{{0,0}, {0,1}, {1,1}, {1,0}};
        actual = reverseLPiece.rotateClock().shapeCoordinates;
        assertArrayEquals(expected, actual);

        squarePiece.rotateClock();
        expected = new int[][]{{0, 0}, {1, 0}, {0, -1}, {-1, -1}};
        actual = squarePiece.shapeCoordinates;
        assertArrayEquals(expected, actual);

        expected = new int[][]{{0,0}, {0,1}, {0,-1}, {1,0}};
        actual = tPiece.rotateClock().shapeCoordinates;
        assertArrayEquals(expected, actual);
    }

    @Test
    void rotateCounter() {
        int[][] expected;
        int[][] actual;

        expected = new int[][]{{0, 0}, {0, 0}, {0, 0}, {0, 0}};
        actual = blankPiece.rotateCounter().shapeCoordinates;
        assertArrayEquals(expected, actual);

        expected = new int[][]{{0, 0}, {0, 1}, {0, 2}, {0, -1}};
        actual = linePiece.rotateCounter().shapeCoordinates;
        assertArrayEquals(expected, actual);

        expected = new int[][]{{0,0}, {1,0}, {-1,0}, {1,-1}};
        actual = zPiece.rotateCounter().shapeCoordinates;
        assertArrayEquals(expected, actual);

        expected = new int[][]{{0,0}, {0,-1}, {1,0}, {1,1}};
        actual = reverseZPiece.rotateCounter().shapeCoordinates;
        assertArrayEquals(expected, actual);

        expected = new int[][]{{0,0}, {0,1}, {0,-1}, {1,-1}};
        actual = lPiece.rotateCounter().shapeCoordinates;
        assertArrayEquals(expected, actual);

        expected = new int[][]{{0,0}, {0,1}, {1,1}, {1,0}};
        actual = reverseLPiece.rotateCounter().shapeCoordinates;
        assertArrayEquals(expected, actual);

        expected = new int[][]{{0, 0}, {1, 0}, {0, -1}, {-1, -1}};
        actual = squarePiece.rotateCounter().shapeCoordinates;
        assertArrayEquals(expected, actual);

        expected = new int[][]{{0,0}, {0,1}, {0,-1}, {1,0}};
        actual = tPiece.rotateCounter().shapeCoordinates;
        assertArrayEquals(expected, actual);
    }

    @Test
    void minY() {
        for(Piece p : allPieces){
            int[][] sCoordinates = p.shapeCoordinates;
            int test;

            // supply test data: populate all y=0
            for(int first=0; first<p.size; first++){
                p.shapeCoordinates[first][1] = 0;
            }
            // supply correct data to test against: set the test case to = 1
            test = 1;
            // line below only checks the 1st coordinate as that is the only coordinate ever used in the game
            assertTrue(p.minY()[1] < test);

            // set the coordinates back for other tests
            p.shapeCoordinates = sCoordinates;
        }
    }

    @Test
    void minX() {
        for(Piece p : allPieces){
            int[][] sCoordinates = p.shapeCoordinates;
            int test;

            // supply test data:  populate all x=0
            for(int first=0; first<p.size; first++){
                p.shapeCoordinates[first][0] = 0;
            }
            // supply correct data to test against: set the test case to = 1
            test = 1;
            assertTrue(p.minX() < test);

            // set the coordinates back for other tests
            p.shapeCoordinates = sCoordinates;
        }
    }

    @Test
    void maxX() {
        for(Piece p : allPieces){
            int[][] sCoordinates = p.shapeCoordinates;
            int test;

            // supply test data:  populate all x=1
            for(int first=0; first<p.size; first++){
                p.shapeCoordinates[first][0] = 1;
            }
            // supply correct data to test against: set the test case to = 0
            test = 0;
            assertTrue(p.maxX() > test);

            // set the coordinates back for other tests
            p.shapeCoordinates = sCoordinates;
        }
    }
}