package controller;

import model.Square;
import view.KolorLinesFrame;
import view.SquarePanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SquareController implements MouseListener {

    public SquareController(KolorLinesFrame frame, Square square) {
        this.frame = frame;
        this.square = square;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        SquarePanel curSquarePanel = (SquarePanel) e.getComponent();

        if (! this.frame.getBoardPanel().getIsListening()){
            return;
        }

        // How to delegate to BoardController ?  this.frame ==> BoardPanel ==> Board
        // By using main object frame
        this.frame.getBoardPanel();

        BoardController boardController = new BoardController(this.frame, this.frame.getBoard());

        try {
            boardController.run(curSquarePanel);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    private KolorLinesFrame frame;
    private Square square;
}
