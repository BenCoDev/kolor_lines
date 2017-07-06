package controller;

import model.Board;
import model.Position;
import view.KolorLinesFrame;
import view.MessagePanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlController implements ActionListener{

    /**
     * Control controller glues together interactions with the ControlPanel and the model
     *
     * @param frame: KolorlinesFrame - represents centralized access to other part of the view
     * @param board: Board - instance of the current board represented
     */
    public ControlController(KolorLinesFrame frame, Board board) {
        this.frame = frame;
        this.board = board;
    }

    /**
     * ActionPerformed consists in resetting the game for the user to play a new game
     * @param event - ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        board.resetSquares();
        frame.getUser().setScore(0);
        frame.repaintBoardPanel();
        frame.updateMessagePanel(MessagePanel.USER_TURN_TEXT);

        this.frame.getSystemUser().setBoard(board);
        this.frame.getUser().setBoard(board);

        Position[] lastSystemPositions = SystemUserController.play(board);
        frame.updateBoardPanel(lastSystemPositions);
    }

    private KolorLinesFrame frame;
    private Board board;
}
