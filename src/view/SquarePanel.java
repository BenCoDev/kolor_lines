package src.view;

import src.model.Square;

import javax.swing.*;
import java.awt.*;

public class SquarePanel extends JPanel {

    public SquarePanel(Square square) {
        this.square = square;
        this.setPreferredSize(new Dimension(this.SQUARE_WIDTH, this.SQUARE_WIDTH));

        if (square.getColor() == null){
            this.setBackground(this.DEFAUlT_COLOR);
        }
        else {
            this.setBackground(square.getColor().getAwtColor());
        }
    }

    public void draw(){
        if (square.getColor() == null){
            this.setBackground(Color.DARK_GRAY);
        }
        else {
            this.setBackground(square.getColor().getAwtColor());
        }
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        draw();
    }

    public static int getSquareWidth() {
        return SQUARE_WIDTH;
    }

    private Square square;
    private static int SQUARE_WIDTH = 100;
    private static java.awt.Color DEFAUlT_COLOR = Color.DARK_GRAY;
}
