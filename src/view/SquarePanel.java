package view;

import controller.SquareController;
import model.Square;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class SquarePanel extends JPanel {

    public SquarePanel(KolorLinesFrame frame, Square square) {
        this.square = square;
        this.setPreferredSize(new Dimension(this.SQUARE_WIDTH, this.SQUARE_WIDTH));

        SquareController squareController = new SquareController(frame, square);

        this.addMouseListener(squareController);
    }

    public void draw(Graphics g){

        if (this.getSquare().getColor() == null){
            this.setBackground(java.awt.Color.DARK_GRAY);
        }
        else {
            Dimension d = getPreferredSize();
            java.awt.Color[] dipslayColors = this.square.getColor().getAwtColors();

            for (int i = 0; i < dipslayColors.length; i++){
                g.setColor(dipslayColors[i]);
                g.fillRect(0, i * (d.width / dipslayColors.length), d.width, d.width / dipslayColors.length);
            }
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
        draw(g);
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
    private static java.awt.Color DEFAUlT_COLOR = java.awt.Color.DARK_GRAY;


    private static Border selectedBorder = BorderFactory.createLineBorder(java.awt.Color.LIGHT_GRAY, 5);

}
