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


    private Piece fallingPiece;
    private int[] currentCoord = {0,0};
    private boolean doneFall = true;

    private Random randomise = new Random();
    private PieceOption[] upcomingPieces = {blankPiece,blankPiece,blankPiece,blankPiece,blankPiece};

    private GameState gameState = GameState.STOPPED;
    private String stoppedMessage = "<html>G<br>A<br>M<br>E<br><br>O<br>V<br>E<br>R<br><br>" +
                                    "P<br>R<br>E<br>S<br>S<br><br>R<br><br>T<br>O<br><br>" +
                                    "R<br>E<br>S<br>T<br>A<br>R<br>T</html";
    private String startMessage = "<html>S<br>T<br>A<br>R<br>T<br>E<br>D</html";
    private String pauseMessage = "<html>P<br>A<br>U<br>S<br>E<br>D</html";

    private JLabel scoreBoard;
    private JLabel upcoming;
    private JLabel gameStatus;
    private Timer timer;
    private int score = 0;


    // start the program and setup the board
    public Board(Tetris parent){
        setFocusable(true);
        board = new PieceOption[BOARD_WIDTH][BOARD_HEIGHT];
        JLabel[] extras = parent.getJLabels();
        scoreBoard = extras[0];
        upcoming = extras[1];
        gameStatus = extras[2];
        addKeyListener(new ControlsAdapter());
        timer = new Timer(400, this); // timer for lines down
    }

    private void randomizedList(){
        for (int i = 0; i < upcomingPieces.length; i++) {
            PieceOption[] allOptions = PieceOption.values();
            int r;
            do {
                r = randomise.nextInt(allOptions.length);
            } while (allOptions[r] == blankPiece);
            upcomingPieces[i] = allOptions[r];
        }
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
        StringBuilder outputNext = new StringBuilder("Upcoming Pieces: ");
        PieceOption[] allOptions = PieceOption.values();
        fallingPiece = new Piece(upcomingPieces[0]);

        int r;
        do {
            r = randomise.nextInt(allOptions.length);
        } while(allOptions[r] == blankPiece);
        for (int i = 1; i < upcomingPieces.length; i++) {
            upcomingPieces[i-1] = upcomingPieces[i];
            String upcomingName = upcomingPieces[i - 1].name;
            outputNext.append(upcomingName);
            outputNext.append(" ");
        }
        upcomingPieces[upcomingPieces.length-1] = allOptions[r];
        String upcomingName = allOptions[r].name;
        outputNext.append(upcomingName);
        outputNext.append(" ");
        upcoming.setText(outputNext.toString());


        currentCoord[0] = BOARD_WIDTH/2;
        currentCoord[1] = BOARD_HEIGHT - fallingPiece.minY()[1];
    }

    // Check if a square is occupied
    private boolean isOccupied(int[] coord) {
        if ( coord[1] >= BOARD_HEIGHT) {
            return false;
        }
        return (coord[0] < 0 || coord[0] >= BOARD_WIDTH || coord[1] < 0 || board[coord[0]][coord[1]] != blankPiece);
    }

    private void addScore(int lines) {
        switch(lines) {
            case 1:
                score += 100;
                break;
            case 2:
                score += 250;
                break;
            case 3:
                score += 500;
                break;
            case 4:
                score += 800;
                break;
        }
        scoreBoard.setText("Score: " + score);
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
                    board[j][i-numFull] = board[j][i];
                }
            }
        }

        for (int i = BOARD_HEIGHT - numFull; i < BOARD_HEIGHT; i++){
            for (int j = 0; j < BOARD_WIDTH; j++){
                board[j][i] = blankPiece;
            }
        }
        addScore(numFull);
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
                gameOver();
            }
        }
        if(gameState != GameState.STOPPED) {
            removeFull();
        }
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
            repaint();
        }
    }

    private void moveDownUntilStopped() {
        boolean done = false;
        while(!done) {
            boolean broken = false;
            int newYCoord = currentCoord[1] - 1;
            int xCoord = currentCoord[0];
            for (int i = 0; i < fallingPiece.shape.blockAmmount; i++) {
                if (isOccupied(new int[]{xCoord + fallingPiece.shapeCoordinates[i][0], newYCoord + fallingPiece.shapeCoordinates[i][1]})) {
                    stopDown();
                    broken = true;
                    done = true;
                    break;
                }
            }
            if (!broken) {
                currentCoord[1]--;
                repaint();
            }
        }
    }

    private void moveHorizontal(int direction) {
        boolean broken = false;
        int yCoord = currentCoord[1];
        int newXCoord = currentCoord[0] + direction;
        for (int i = 0; i < fallingPiece.shape.blockAmmount; i++) {
            if (isOccupied(new int[]{newXCoord + fallingPiece.shapeCoordinates[i][0], yCoord + fallingPiece.shapeCoordinates[i][1]})) {
                broken = true;
                break;
            }
        }
        if (!broken){
            currentCoord[0] = newXCoord;
        }
        repaint();
    }

    private void turnPiece(int direction){
        Piece testPiece;
        if (direction == 1) {
            testPiece = fallingPiece.rotateClock();
        } else if (direction == -1) {
            testPiece = fallingPiece.rotateCounter();
        } else {
            return;
        }

        boolean broken = false;
        int yCoord = currentCoord[1];
        int xCoord = currentCoord[0];

        for (int i = 0; i < testPiece.shape.blockAmmount; i++) {
            if (isOccupied(new int[]{xCoord + testPiece.shapeCoordinates[i][0], yCoord + testPiece.shapeCoordinates[i][1]})) {
                broken = true;
                break;
            }
        }
        if (!broken){
            fallingPiece = testPiece;
        }
        repaint();
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

    // Get input
    class ControlsAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent ke) {
            int keyCode = ke.getKeyCode();

            if (keyCode == 'p' || keyCode == 'P' || keyCode == KeyEvent.VK_ESCAPE) {
                pause();
            }

            if (keyCode == 'r' || keyCode == 'R') {
                gameOver();
                start();
            }

            if(!(gameState == GameState.STARTED)) {
                return;
            }

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
                case KeyEvent.VK_SPACE:
                    moveDownUntilStopped();
                    break;
            }
        }
    }

    public void start() {
        if (gameState == GameState.STOPPED) {
            gameState = GameState.STARTED;
            clearBoard();
            timer.start();
            nextPiece();
            gameStatus.setText(startMessage);
            scoreBoard.setText("Score: " + score);
            randomizedList();
        }
    }

    public void pause() {
        if (gameState == GameState.STARTED) {
            gameState = GameState.PAUSED;
            timer.stop();
            gameStatus.setText(pauseMessage);
        } else if (gameState == GameState.PAUSED) {
            gameState = GameState.STARTED;
            timer.start();
            gameStatus.setText(startMessage);
        }
    }

    public void gameOver() {
        gameState = GameState.STOPPED;
        timer.stop();
        gameStatus.setText(stoppedMessage);
        score = 0;
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
