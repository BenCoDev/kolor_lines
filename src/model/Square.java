package src.model;

/**
 * Represents a square of the src.model.Board (composition)
 *
 * a src.model.Square object has a position on the src.model.Board
 * and a color if the square is filled (i.e. if there is a pawn of the corresponding color at the position)
 */
public class Square {
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

    private Position position;
    private Color color;
}
