package src.controller;

import src.model.BoardException;
import src.model.HumanUser;
import src.model.Board;
import src.view.KolorLinesFrame;

import javax.swing.*;
import java.awt.*;

public class KolorLinesRunnable implements Runnable {

    @Override
    public void run(){

        // TODO: implement prompt for Board size
        try {
            new KolorLinesFrame(new Board(new Dimension(3, 3)), new HumanUser());
        } catch (BoardException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new KolorLinesRunnable());
    }
}
