package src.controller;

import src.model.Board;
import src.view.KolorLinesFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoardController implements ActionListener {

    public BoardController(KolorLinesFrame frame, Board board) {
        this.frame = frame;
        this.board = board;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        System.out.println("KIKOU");
    }

    private KolorLinesFrame frame;
    private Board board;
}
