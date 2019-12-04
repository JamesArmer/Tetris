package com.mirka.tetristry;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Board extends JPanel implements ActionListener {
    private static final int BOARD_WIDTH = 10;
    private static final int BOARD_HEIGHT = 22;
    private PieceOption[][] board;
    private boolean doneFall = true;
    private Piece fallingPiece;
    private Random randomise = new Random();
    private int[] currentCoord = {0,0};

    private Timer timer;


    public Board(){
        board = new PieceOption[BOARD_WIDTH][BOARD_HEIGHT];
        clearBoard();
        timer = new Timer(400, this); // timer for lines down
        timer.start();
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

    private void removeFull() {
        int numFull = 0;
        for (int i = 0; i < BOARD_HEIGHT; i++){
            boolean fullLine = true;

            for (int j = 0; j < BOARD_WIDTH; j++){
                if (!isOccupied(new int[]{j, i})){
                    fullLine = false;
                    break;
                }
            }
            if (fullLine) {
                numFull++;
            } else if (numFull > 0) {
                for (int j = 0; j < BOARD_WIDTH; j++){
                    board[i-numFull][j] = board[i][j];
                }
            }
        }

        for (int i = BOARD_HEIGHT - numFull; i < BOARD_HEIGHT; i++){
            for (int j = 0; j < BOARD_WIDTH; j++){
                board[i][j] = PieceOption.blankPiece;
            }
        }
        // addScore(numFull);
        repaint();
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
                // gameOver();
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

    public int squareWidth() {
        return (int) getSize().getWidth() / BOARD_WIDTH;
    }

    public int squareHeight() {
        return (int) getSize().getHeight() / BOARD_HEIGHT;
    }

    private void drawSquare(Graphics g, int x, int y, PieceOption shape) {
        Color color = Color.BLUE;
        g.setColor(color);
        g.fillRect(x + 1, y + 1, squareWidth() - 2, squareHeight() - 2);
        g.setColor(color.brighter());
        g.drawLine(x, y + squareHeight() - 1, x, y);
        g.drawLine(x, y, x + squareWidth() - 1, y);
        g.setColor(color.darker());
        g.drawLine(x + 1, y + squareHeight() - 1, x + squareWidth() - 1, y + squareHeight() - 1);
        g.drawLine(x + squareWidth() - 1, y + squareHeight() - 1, x + squareWidth() - 1, y + 1);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Dimension size = getSize();

        int boardTop = (int) size.getHeight() - BOARD_HEIGHT * squareHeight();

        for (int i = 0; i < BOARD_HEIGHT; i++) {
            for (int j = 0; j < BOARD_WIDTH; ++j) {
                PieceOption shape = board[j][BOARD_HEIGHT - i - 1];

                if (shape != PieceOption.blankPiece) {
                    drawSquare(g, j * squareWidth(), boardTop + i * squareHeight(), shape);
                }
            }
        }
    }
}
