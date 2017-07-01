package src.controller;

import src.model.BoardException;
import src.model.HumanUser;
import src.model.Board;
import src.model.SystemUser;
import src.view.KolorLinesFrame;

import javax.swing.*;
import java.awt.*;

public class KolorLinesRunnable implements Runnable {

    @Override
    public void run(){

        // TODO: implement prompt for Board size
        try {
            HumanUser user = new HumanUser();
            SystemUser systemUser = new SystemUser();
            new KolorLinesFrame(new Board(new Dimension(5, 5)), user, systemUser);
        } catch (BoardException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new KolorLinesRunnable());
    }
}
