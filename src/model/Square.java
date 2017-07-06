package model;

import java.util.LinkedList;

/**
 * Represents a square of the .model.Board (composition)
 *
 * a Square object has a position on the .model.Board
 * and a color if the square is filled (i.e. if there is a pawn of the corresponding color at the position)
 */
public class Square {
    /**
     * Instantiates a Square given a position
     * Will set a random color for this square
     *
     * @param pos   Position  Position of the Square
     */
    public Square(Position pos){
        this.position = pos;
    }

    /**
     * Instantiates a Square given a position and a color
     * Will set a random color for this square
     *
     * @param pos   Position   Position of the Square
     * @param color Color     Color of the Square
     */
    public Square(Position pos, Color color) {
        this.position = pos;
        this.color = color;
    }

    public Color getColor() {
        return this.color;
    }

    public void setColor(){
        this.color = Color.randomColor();
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void unsetColor(){
        this.color = null;
    }

    public Position getPosition() {
        return position;
    }

    /**
     * Util function to flatten a LinkedList of LinkedList of Squares into an array of positions
     * @param nestedListOfSquares - nested LinkedList of Squares
     * @return: Positions[] - array of positions corresponding to the Squares
     */
    public static Position[] flatten(LinkedList<LinkedList<Square>> nestedListOfSquares){
        int len = 0;
        for (LinkedList<Square> list: nestedListOfSquares){
            len += list.size();
        }

        Position outputPositions[] = new Position[len];

        int counter = 0;
        for (LinkedList<Square> nestedListOfSquare : nestedListOfSquares) {
            for (Square aNestedListOfSquare : nestedListOfSquare) {
                outputPositions[counter] = aNestedListOfSquare.getPosition();
                counter += 1;
            }
        }

        return outputPositions;
    }

    private Position position;
    private Color color;
}
