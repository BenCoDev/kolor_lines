/**
 * Represents a square of the Board (composition)
 *
 * a Square object has a position on the Board
 * and a color if the square is filled (i.e. if there is a pawn of the corresponding color at the position)
 */
public class Square {

    /**
     * Instantiates a Square given a position
     * Will set a random color for this square
     *
     * @param pos   Position    Position of the Square
     */
    public Square(Position pos){
        this.position = pos;
        this.color = Color.randomColor();
    }

    /**
     * Instantiates a Square given a position and a color
     * Will set a random color for this square
     *
     * @param pos   Position    Position of the Square
     * @param color   Color     Color of the Square
     */
    public Square(Position pos, Color color) {
        this.position = pos;
        this.color = color;
    }

    public Color getColor() {
        return this.color;
    }

    public Position getPosition() {
        return position;
    }

    public void unsetColor() {
        this.color = null;
    }

    private Position position;
    private Color color;
}