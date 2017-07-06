package view;

import controller.SquareController;
import model.Square;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;


public class SquarePanel extends JPanel {

    /**
     * Represents a SquarePanel responsible for displaying a SquarePanel in a BoardPanel
     * given the Square model
     * @param square - Square
     */
    public SquarePanel(KolorLinesFrame frame, Square square) {
        this.frame = frame;
        this.square = square;
        int squareWidth = (int) (this.frame.getBoardPanel().getPreferredSize().getWidth() / this.frame.getBoard().getSize().getWidth());

        this.setPreferredSize(new Dimension(squareWidth, squareWidth));

        SquareController squareController = new SquareController(frame);

        this.addMouseListener(squareController);
    }

    public void toggleSelect(){
        this.isSelected = !this.isSelected;
        this.repaint();
    }

    public Square getSquare() {
        return square;
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }

    private void draw(Graphics g){

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

    private KolorLinesFrame frame;
    private Square square;
    private boolean isSelected = false;
    private static int SQUARE_WIDTH = 100;  // Boardwidth
    private static Border selectedBorder = BorderFactory.createLineBorder(java.awt.Color.LIGHT_GRAY, 5);

}
