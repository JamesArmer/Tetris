package com.mirka.tetristry;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

import static com.mirka.tetristry.PieceOption.blankPiece;

public class Board extends JPanel implements ActionListener {
    private static final int BOARD_WIDTH = 10;
    private static final int BOARD_HEIGHT = 22;
    private PieceOption[][] board;
    private boolean doneFall = true;
    private Piece fallingPiece;
    private Random randomise = new Random();
    private int[] currentCoord = {0,0};

    private Timer timer;


    // start the program and setup the board
    public Board(){
        setFocusable(true);
        board = new PieceOption[BOARD_WIDTH][BOARD_HEIGHT];
        clearBoard();
        timer = new Timer(400, this); // timer for lines down
        timer.start();
        addKeyListener(new ControlsAdapter());
    }

    // reset board to nothing
    private void clearBoard() {
        for (int i = 0; i < BOARD_WIDTH; i++){
            for (int j = 0; j < BOARD_HEIGHT; j++) {
                board[i][j] = blankPiece;
            }
        }
    }

    // generate the next piece
    private void nextPiece() {
        PieceOption[] allOptions = PieceOption.values();
        int r;
        do {
            r = randomise.nextInt(allOptions.length);
        } while(allOptions[r] == blankPiece);
        fallingPiece = new Piece(allOptions[r]);
        currentCoord[0] = BOARD_WIDTH/2;
        currentCoord[1] = BOARD_HEIGHT - fallingPiece.minY()[1];
    }

    // Check if a square is occupied
    private boolean isOccupied(int[] coord) {
        if (coord[0] < 0 || coord[0] >= BOARD_WIDTH || coord[1] >= BOARD_HEIGHT) {
            return false;
        }
        if (coord[1] < 0 || board[coord[0]][coord[1]] != blankPiece) {
            return true;
        }
        return false;
    }

    // Remove the completed lines
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
                board[i][j] = blankPiece;
            }
        }
        // addScore(numFull);
        repaint();
    }

    // stop the block from moving down if it can't
    private void stopDown(){
        doneFall = true;
        int yCoord = currentCoord[1];
        int xCoord = currentCoord[0];
        int blockY = 0;

        for (int i = 0; i < fallingPiece.shape.blockAmmount; i++) {
            blockY = yCoord + fallingPiece.shapeCoordinates[i][1];
            if (blockY < BOARD_HEIGHT) {
                board[xCoord + fallingPiece.shapeCoordinates[i][0]][blockY] = fallingPiece.shape;
            } else {
                // gameOver();
            }
        }
        removeFull();
    }

    // Move the piece down by one
    private void moveDown() {
        boolean broken = false;
        int newYCoord = currentCoord[1]-1;
        int xCoord = currentCoord[0];
        for (int i = 0; i < fallingPiece.shape.blockAmmount; i++) {
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

    private void moveHorizontal(int direction) {
        boolean broken = false;
        int yCoord = currentCoord[1];
        int newXCoord = currentCoord[0] + direction;
        for (int i = 0; i < fallingPiece.shape.blockAmmount; i++) {
            if (isOccupied(new int[]{newXCoord + fallingPiece.shapeCoordinates[i][0], yCoord + fallingPiece.shapeCoordinates[i][1]})) {
                stopDown();
                broken = true;
                break;
            }
        }
        if (!broken){
            currentCoord[0] = newXCoord;
        }
    }

    private void turnPiece(int direction){
        Piece testPiece = fallingPiece;
        if (direction == 1) {
            testPiece.rotateClock();
        } else if (direction == 0) {
            testPiece.rotateCounter();
        }

        boolean broken = false;
        int yCoord = currentCoord[1];
        int xCoord = currentCoord[0];

        for (int i = 0; i < testPiece.shape.blockAmmount; i++) {
            if (isOccupied(new int[]{xCoord + testPiece.shapeCoordinates[i][0], yCoord + testPiece.shapeCoordinates[i][1]})) {
                stopDown();
                broken = true;
                break;
            }
        }
        if (!broken){
            fallingPiece = testPiece;
        }
    }

    // Do this every few seconds
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (doneFall) {
            doneFall = false;
            nextPiece();
        } else {
            moveDown();
        }
        repaint();
    }

    class ControlsAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent ke) {
            int keyCode = ke.getKeyCode();

            switch (keyCode) {
                case 'a':
                case 'A':
                case KeyEvent.VK_LEFT:
                    moveHorizontal(-1);
                    break;
                case 'd':
                case 'D':
                case KeyEvent.VK_RIGHT:
                    moveHorizontal(1);
                    break;
                case 'w':
                case 'W':
                case KeyEvent.VK_UP:
                    turnPiece(-1);
                    break;
                case 's':
                case 'S':
                case KeyEvent.VK_DOWN:
                    turnPiece(1);
                    break;

            }
        }
    }

    // PLAGIARISED CODE TO TEST, DO NOT USE FOR FINAL PROJECT
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

                if (shape != blankPiece) {
                    drawSquare(g, j * squareWidth(), boardTop + i * squareHeight(), shape);
                }
            }
        }
        if (fallingPiece.shape != blankPiece) {
            for (int i = 0; i < fallingPiece.size; ++i) {
                int x = currentCoord[0] + fallingPiece.shapeCoordinates[i][0];
                int y = currentCoord[1] + fallingPiece.shapeCoordinates[i][1];
                drawSquare(g, x * squareWidth(), boardTop + (BOARD_HEIGHT - y - 1) * squareHeight(), fallingPiece.shape);
            }
        }
    }
}
