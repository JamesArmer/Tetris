package test;

import com.mirka.tetristry.Piece;
import com.mirka.tetristry.PieceOption;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

    @BeforeEach
    void setUp() {
        blankPiece = new Piece(PieceOption.blankPiece);
        linePiece = new Piece(PieceOption.linePiece);
        zPiece = new Piece(PieceOption.zPiece);
        reverseZPiece = new Piece(PieceOption.reverseZPiece);
        lPiece = new Piece(PieceOption.lPiece);
        reverseLPiece = new Piece(PieceOption.reverseLPiece);
        squarePiece = new Piece(PieceOption.squarePiece);
        tPiece = new Piece(PieceOption.tPiece);
    }

    @Test
    void testInitialisedPieces(){
        assertEquals(blankPiece.shape, PieceOption.blankPiece);
        assertEquals(linePiece.shape, PieceOption.linePiece);
        assertEquals(zPiece.shape, PieceOption.zPiece);
        assertEquals(reverseZPiece.shape, PieceOption.reverseZPiece);
        assertEquals(lPiece.shape, PieceOption.lPiece);
        assertEquals(reverseLPiece.shape, PieceOption.reverseLPiece);
        assertEquals(squarePiece.shape, PieceOption.squarePiece);
        assertEquals(tPiece.shape, PieceOption.tPiece);
    }

    @Test
    void rotateClock() {
        //Piece rotated = new rotateClock();
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