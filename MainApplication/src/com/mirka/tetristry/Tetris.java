package com.mirka.tetristry;


// PLAGIARISED CODE DO NOT USE FOR FINAL PROJECT
import javax.swing.*;
import java.awt.*;

public class Tetris extends JFrame {

    private JLabel scoreboard;
    private JLabel upcoming;
    private JLabel gameStatus;


    public Tetris() {
        scoreboard = new JLabel("Score: 0");
        upcoming = new JLabel("Upcoming Pieces: ");
        gameStatus = new JLabel("<html>S<br>O<br>P<br>P<br>E<br>D</html");

        add(scoreboard, BorderLayout.PAGE_END);
        add(upcoming, BorderLayout.PAGE_START);
        add(gameStatus, BorderLayout.LINE_END);
        setSize(400, 800);
        setTitle("Tetris Company Tetris");
        Board board = new Board(this);
        add(board);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        board.start();
    }

    public JLabel[] getJLabels() {
        return new JLabel[]{scoreboard, upcoming, gameStatus};
    }

    public static void main(String[] args) {
        Tetris myTetris = new Tetris();
        myTetris.setLocationRelativeTo(null);
        myTetris.setVisible(true);
    }
}
