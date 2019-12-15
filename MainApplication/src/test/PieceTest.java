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

        blankPiece.rotateClock();
        expected = new int[][] {{0,0},{0,0},{0,0},{0,0}};
        actual = blankPiece.shapeCoordinates;
        assertArrayEquals(expected, actual);

        squarePiece.rotateClock();
        expected = new int[][] {{0,0}, {1,0}, {0,-1}, {-1,-1}};
        actual = squarePiece.shapeCoordinates;
        assertArrayEquals(expected, actual);


        expected = new int[][] {{0,0}, {0,1}, {0,2}, {0,-1}};

        actual = linePiece.rotateClock().shapeCoordinates;

        for(int[] i : actual){
            for(int j : i){
                System.out.print(j + ", ");
            }
            System.out.println();
        }
        assertArrayEquals(expected, actual);
    }

    @Test
    void rotateCounter() {

    }

    @Test
    void minY() {
    }

    @Test
    void minX() {
    }

    @Test
    void maxX() {
    }

    @AfterEach
    void tearDown() {
    }
}