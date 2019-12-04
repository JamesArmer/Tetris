package com.mirka.tetristry;

import javax.swing.*;
import java.awt.event.ActionListener;

public class Board extends JPanel implements ActionListener {
    private static final int BOARD_WIDTH = 10;
    private static final int BOARD_HEIGHT = 22;
    private PieceOption[][] board;

    public Board(){
        board = new PieceOption[BOARD_WIDTH][BOARD_HEIGHT];
    }

    private void clearBoard() {
        for (int i = 0; i < BOARD_WIDTH; i++){
            for (int j = 0; j < BOARD_HEIGHT; j++) {
                board[i][j] = PieceOption.blankPiece;
            }
        }
    }
}
