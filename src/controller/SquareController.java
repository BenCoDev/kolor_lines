package controller;

import view.KolorLinesFrame;
import view.SquarePanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SquareController implements MouseListener {


    /**
     * Square controller glues together interactions with the SquarePanel and the model
     *
     * @param frame: KolorlinesFrame - represents centralized access to other part of the view
     */
    public SquareController(KolorLinesFrame frame) {
        this.frame = frame;
    }

    /**
     * From a MouseEvent on a SquarePanel,
     * delegates action to the BoardController
     *
     * @param e MouseEvent
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        SquarePanel curSquarePanel = (SquarePanel) e.getComponent();

        if (! this.frame.getBoardPanel().getIsListening()){  // If the BoardPanel is locked, early return
            return;
        }

        this.frame.getBoardPanel();

        BoardController boardController = new BoardController(this.frame, this.frame.getBoard());

        boardController.run(curSquarePanel);
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
}
