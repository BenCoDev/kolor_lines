package src.controller;

import src.model.Square;
import src.view.KolorLinesFrame;
import src.view.SquarePanel;

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
        curSquarePanel.toggleSelect();
        e.getComponent().repaint();
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
