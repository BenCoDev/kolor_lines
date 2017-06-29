package src.controller;

import src.model.Board;
import src.model.HumanUser;
import src.model.Position;
import src.model.SystemUser;
import src.view.KolorLinesFrame;

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
        board.initSquares();

        SystemUser systemUser = new SystemUser();
        HumanUser humanUser = new HumanUser();

        systemUser.setBoard(board);
        humanUser.setBoard(board);

        try {
            Position[] lastSystemPositions = systemUser.play();
        }
        catch (Exception e){
            // should not happen
        }


        frame.repaintBoardPanel();
//        frame.updateScorePanel();
    }
}