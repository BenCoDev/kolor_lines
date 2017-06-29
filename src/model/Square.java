package src.model;

import java.awt.*;

/**
 * Represents a square of the src.model.Board (composition)
 *
 * a src.model.Square object has a position on the src.model.Board
 * and a color if the square is filled (i.e. if there is a pawn of the corresponding color at the position)
 */
public class Square {
    // TODO: check if can be set as abstract see if initialized other place than src.model.Board
    // TODO: should enable square to have no colors


    /**
     * Instantiates a src.model.Square given a position
     * Will set a random color for this square
     *
     * @param pos   src.model.Position    src.model.Position of the src.model.Square
     */
    public Square(Position pos){
        this.position = pos;
    }

    /**
     * Instantiates a src.model.Square given a position and a color
     * Will set a random color for this square
     *
     * @param pos   src.model.Position    src.model.Position of the src.model.Square
     * @param color   src.model.Color     src.model.Color of the src.model.Square
     */
    public Square(Position pos, Color color) {
        this.position = pos;
        this.color = color;
    }

    public Color getColor() {
        return this.color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Position getPosition() {
        return position;
    }

    /**
     * Explain why not in view (bc not a JPanel)
     * @param g
     */
    public void draw(Graphics g){

        if (this.color == null) {
            g.setColor(java.awt.Color.RED);
            g.fillRect(
                    this.position.getAbs() * WIDTH + GUTTER,
                    this.position.getOrd() * WIDTH + GUTTER,
                    WIDTH - GUTTER,
                    WIDTH - GUTTER);
        }
        else {
            g.setColor(java.awt.Color.BLACK);
            g.fillRect(this.position.getAbs(), this.position.getOrd(), WIDTH, WIDTH);
        }
    }

    public static int getWidth() {
        return WIDTH;
    }

    public static int getGutter() {
        return GUTTER;
    }

    private static int WIDTH = 100;
    private static int GUTTER = 5;

    private Position position;
    private Color color;
}
