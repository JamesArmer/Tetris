package com.mirka.tetristry;


// PLAGIARISED CODE DO NOT USE FOR FINAL PROJECT
import javax.swing.*;
import java.awt.*;

public class Tetris extends JFrame {


    public Tetris() {
        setSize(400, 800);
        setTitle("My Tetris");
        Board board = new Board();
        add(board);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        Tetris myTetris = new Tetris();
        myTetris.setLocationRelativeTo(null);
        myTetris.setVisible(true);
    }
}
