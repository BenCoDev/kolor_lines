package src.view;

import src.controller.KolorLines;
import src.model.Board;
import src.model.Position;
import src.model.PositionException;
import src.model.Square;

import javax.swing.*;
import java.awt.*;

public class BoardPanel extends JPanel {

    public BoardPanel(KolorLinesFrame frame, Board board) {
        this.frame = frame;
        this.board = board;
        this.squarePanels = new SquarePanel[this.board.getSize().width][this.board.getSize().width];
        this.setPreferredSize(this.getPreferredSize());
        this.setMinimumSize(this.getPreferredSize());
        this.setMaximumSize(this.getPreferredSize());
        this.setLayout(new GridLayout(this.board.getSize().width, this.board.getSize().width, this.GUTTER, this.GUTTER));

        for (int x = 0; x < board.getSize().width; x++) {
            for (int y = 0; y < board.getSize().width; y++) {
                try {
                    this.squarePanels[x][y] = new SquarePanel(this.frame, board.getSquare(new Position(x, y)));
                    this.add(this.squarePanels[x][y]);
                }
                catch (PositionException e){
                    // not likely to happen
                }
            }
        }

    }

    private void draw(){
        for (int x = 0; x < board.getSize().width; x++) {
            for (int y = 0; y < board.getSize().width; y++) {
                this.squarePanels[x][y].repaint();
            }
        }

    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        draw();

        // TODO: if game is over
    }

    public void update(Position[] positions){
        for (Position position : positions) {
            this.squarePanels[position.getOrd()][position.getAbs()].repaint();
        }
    }

    public Dimension getPreferredSize() {
        int width = this.board.getSize().width * SquarePanel.getSquareWidth();
        return new Dimension(width, width);
    }


    private Board board;
    private KolorLinesFrame frame;
    private SquarePanel[][] squarePanels;
    private static int GUTTER = 5;
}
