package com.mirka.tetristry;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Board extends JPanel implements ActionListener {
    private static final int BOARD_WIDTH = 10;
    private static final int BOARD_HEIGHT = 22;
    private PieceOption[][] board;
    private boolean doneFall;
    private Piece fallingPiece;
    private Random randomise = new Random();
    private int[] currentCoord = {0,0};


    public Board(){
        board = new PieceOption[BOARD_WIDTH][BOARD_HEIGHT];
        clearBoard();
    }

    private void clearBoard() {
        for (int i = 0; i < BOARD_WIDTH; i++){
            for (int j = 0; j < BOARD_HEIGHT; j++) {
                board[i][j] = PieceOption.blankPiece;
            }
        }
    }

    private void nextPiece() {
        PieceOption[] allOptions = PieceOption.values();
        int r = randomise.nextInt(allOptions.length);
        fallingPiece = new Piece(allOptions[r]);
        currentCoord[0] = BOARD_WIDTH/2;
        currentCoord[1] = BOARD_HEIGHT - fallingPiece.minY()[1];
    }

    private boolean isOccupied(int[] coord) {
        if (board[coord[0]][coord[1]] != PieceOption.blankPiece) {
            return true;
        }
        return false;
    }

    private void stopDown(){
        doneFall = true;
        int yCoord = currentCoord[1]-1;
        int xCoord = currentCoord[0];
        int blockY = 0;

        for (int i = 0; i < fallingPiece.shape.blockAmmount[0]; i++) {
            blockY = xCoord + yCoord +fallingPiece.shapeCoordinates[i][1];
            if (blockY < BOARD_HEIGHT) {
                board[xCoord + fallingPiece.shapeCoordinates[i][0]][blockY] = fallingPiece.shape;
            } else {
                gameOver();
            }
        }
        removeFull();
    }

    private void moveDown() {
        boolean broken = false;
        int newYCoord = currentCoord[1]-1;
        int xCoord = currentCoord[0];
        for (int i = 0; i < fallingPiece.shape.blockAmmount[0]; i++) {
            if (isOccupied(new int[]{xCoord + fallingPiece.shapeCoordinates[i][0], newYCoord + fallingPiece.shapeCoordinates[i][1]})) {
                stopDown();
                broken = true;
                break;
            }
        }
        if (!broken){
            currentCoord[1]--;
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (doneFall) {
            doneFall = false;
            nextPiece();
        } else {
            moveDown();
        }
    }
}
