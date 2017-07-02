package model;

/**
 * Represents a square of the .model.Board (composition)
 *
 * a .model.Square object has a position on the .model.Board
 * and a color if the square is filled (i.e. if there is a pawn of the corresponding color at the position)
 */
public class Square {
    /**
     * Instantiates a .model.Square given a position
     * Will set a random color for this square
     *
     * @param pos   .model.Position    .model.Position of the .model.Square
     */
    public Square(Position pos){
        this.position = pos;
    }

    /**
     * Instantiates a .model.Square given a position and a color
     * Will set a random color for this square
     *
     * @param pos   .model.Position    .model.Position of the .model.Square
     * @param color   .model.Color     .model.Color of the .model.Square
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
