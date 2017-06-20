package src;

/**
 * Represents a square of the src.Board (composition)
 *
 * a src.Square object has a position on the src.Board
 * and a color if the square is filled (i.e. if there is a pawn of the corresponding color at the position)
 */
public class Square {
    // TODO: check if can be set as abstract see if initialized other place than src.Board
    // TODO: should enable square to have no colors


    /**
     * Instantiates a src.Square given a position
     * Will set a random color for this square
     *
     * @param pos   src.Position    src.Position of the src.Square
     */
    public Square(Position pos){
        this.position = pos;
        this.color = Color.randomColor();
    }

    /**
     * Instantiates a src.Square given a position and a color
     * Will set a random color for this square
     *
     * @param pos   src.Position    src.Position of the src.Square
     * @param color   src.Color     src.Color of the src.Square
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

    private Position position;
    private Color color;
}
