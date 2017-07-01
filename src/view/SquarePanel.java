package src.view;

import src.controller.BoardController;
import src.controller.SquareController;
import src.model.Square;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class SquarePanel extends JPanel {

    public SquarePanel(KolorLinesFrame frame, Square square) {
        this.square = square;
        this.setPreferredSize(new Dimension(this.SQUARE_WIDTH, this.SQUARE_WIDTH));

        if (square.getColor() == null){
            this.setBackground(this.DEFAUlT_COLOR);
        }
        else {
            this.setBackground(square.getColor().getAwtColor());
        }

        SquareController squareController = new SquareController(frame, square);

        this.addMouseListener(squareController);
    }

    public void draw(){
        if (square.getColor() == null){
            this.setBackground(Color.DARK_GRAY);
        }
        else {
            this.setBackground(square.getColor().getAwtColor());
        }

        if (this.isSelected){
            this.setBorder(selectedBorder);
        }
        else {
            this.setBorder(null);
        }
    }

    public void toggleSelect(){
        this.isSelected = !this.isSelected;
        this.repaint();
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        draw();
    }

    public static int getSquareWidth() {
        return SQUARE_WIDTH;
    }

    public Square getSquare() {
        return square;
    }

    private Square square;

    private boolean isSelected = false;

    private static int SQUARE_WIDTH = 100;
    private static java.awt.Color DEFAUlT_COLOR = Color.DARK_GRAY;


    private static Border selectedBorder = BorderFactory.createLineBorder(Color.LIGHT_GRAY, 5);

}
