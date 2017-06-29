package src.view;

import src.model.Board;
import src.model.Position;
import src.model.PositionException;
import src.model.Square;

import javax.swing.*;
import java.awt.*;

public class BoardPanel extends JPanel {

    public BoardPanel(Board board) {
        this.board = board;
        this.setPreferredSize(this.getPreferredSize());
    }

    private void draw(Graphics g){

        g.setColor(Color.DARK_GRAY);

        g.fillRect(0, 0, this.getPreferredSize().width, this.getPreferredSize().height);

        for (int x = 0; x < board.getSize().width; x++) {
            for (int y = 0; y < board.getSize().width; y++) {
                try {
                    board.getSquare(new Position(x, y)).draw(g);
                }
                catch (PositionException e){
                    // not likely to happen
                }

            }
        }
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);

        // TODO: if game is over
    }

    public Dimension getPreferredSize() {
        int width = this.board.getSize().width * Square.getWidth() + Square.getGutter();
        return new Dimension(width, width);
    }

    private Board board;
}
