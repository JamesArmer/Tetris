package com.mirka.tetristry;


// COPPIED CODE DO NOT USE FOR FINAL PROJECT
import javax.swing.*;
import java.awt.*;

public class Tetris extends JFrame {


    public Tetris() {
        Board board = new Board();
        add(board);
        setSize(200, 400);
        setTitle("My Tetris");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        Tetris myTetris = new Tetris();
        myTetris.setLocationRelativeTo(null);
        myTetris.setVisible(true);
    }
}
