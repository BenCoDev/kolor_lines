package src.controller;

import src.model.Board;
import src.model.HumanUser;
import src.model.Position;
import src.model.SystemUser;
import src.view.KolorLinesFrame;
import src.view.MessagePanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlController implements ActionListener{

    private KolorLinesFrame frame;

    private Board board;

    public ControlController(KolorLinesFrame frame, Board board) {
        this.frame = frame;
        this.board = board;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        board.resetSquares();
        frame.getUser().setScore(0);
        frame.repaintBoardPanel();
        frame.updateMessagePanel(MessagePanel.DEFAULT_MESSAGE);

        this.frame.getSystemUser().setBoard(board);
        this.frame.getUser().setBoard(board);

        try {
            Position[] lastSystemPositions = this.frame.getSystemUser().play();
            frame.updateBoardPanel(lastSystemPositions);
        }
        catch (Exception e){
            // should not happen
        }
    }
}
