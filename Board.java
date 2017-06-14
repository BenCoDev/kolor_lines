import java.util.LinkedList;
import java.util.List;

/**
 * Represents the Board for Kolor Lines games
 *
 * The Board is composed of squares and has a defined size
 */
public class Board {

    public Board(int size){
        Position.setMaxWidth(size);
        Position.setMaxHeight(size);

        this.size = size;
        this.squares = new Square[size][size];
    }

    /**
     * TODO
     * @return
     */
    public boolean isFull(){
        for (int i = 0; i < this.squares.length; i++)

            for (int j = 0; j < this.squares[i].length; j++) {
                Square curSquare;
                try {
                    curSquare = this.getSquare(new Position(i, j));
                }
                catch (Exception e) {
                    curSquare = null;
                }

                if (curSquare == null){
                    return false;
                }
            }
        return true;
    }

    public Square getSquare(Position pos){
        return this.squares[pos.getOrd()][pos.getAbs()];
    }

    public void setSquare(Position pos){
//        TODO: add tests
        this.squares[pos.getOrd()][pos.getAbs()] = new Square(pos);
    }

    public void setSquare(Position pos, Color color){
//        TODO: add tests
        this.squares[pos.getOrd()][pos.getAbs()] = new Square(pos, color);
    }

    public void unsetSquare(Position pos){
//        TODO: add tests
        this.squares[pos.getOrd()][pos.getAbs()] = null;
    }

    /**
     * Check if Board has alignment starting a Position
     *
     * Direction is optional
     */
    public LinkedList<Square>[] fetchAlignments(Position pos, LinkedList<Square>[] alignmentsByDirection){
        //  Means it is the first square
        //  If no directions
        //  List neighbours
        Square curSquare = getSquare(pos);
        Square[] neighbours;

        neighbours = this.listNeighbours(pos);

        for (int i = 0; i < neighbours.length; i++){

            if (neighbours[i] != null &&
                    (neighbours[i].getColor() == curSquare.getColor() ||
                            (neighbours[i].getColor() == Color.RAINBOW || curSquare.getColor() == Color.RAINBOW))){
                try {
                    alignmentsByDirection[i].add(neighbours[i]);
                }
                catch (java.lang.NullPointerException e){
                    alignmentsByDirection[i] = new LinkedList<Square>();
                    alignmentsByDirection[i].add(neighbours[i]);
                }

                fetchAlignments(neighbours[i].getPosition(), alignmentsByDirection, Board.Direction.values()[i]);
            }
        }

        return alignmentsByDirection;
    }

    public LinkedList<Square>[] fetchAlignments(Position pos, LinkedList<Square>[] alignmentsByDirection, Direction direction){

        // TODO: Describe alignmensByDirection
        // Same structure with same 8 slots
        Square curSquare = getSquare(pos);

        Square nextSquare;
        try {
            nextSquare = getSquare(pos.getNextPosition(direction));
        } catch (Exception e) {
            System.out.println("No more squares on this direction");
            nextSquare = null;
        }

        if (nextSquare != null) {
            if (curSquare.getColor() == nextSquare.getColor() ||
                    (nextSquare.getColor() == Color.RAINBOW || curSquare.getColor() == Color.RAINBOW)) {
                alignmentsByDirection[direction.ordinal()].add(nextSquare);
            }

            fetchAlignments(nextSquare.getPosition(), alignmentsByDirection, direction);
        }

        return alignmentsByDirection;
    }

    /**
     * mergedAlignmentsByDirection
     * 0: NWSE
     * 1: WE
     * 2: SWNE
     * 3: SN
     * @param alignmentsByDirection
     * @return
     */
    public static LinkedList<Square>[] mergeDirectionAligments(LinkedList<Square>[] alignmentsByDirection){
        LinkedList<Square>[] mergedAlignmentsByDirection = new LinkedList[4];

        // Make more sense to decrement
        // i from 7 to 4 included
        int outputArrayIndex;
        for (int i = alignmentsByDirection.length - 1; i >= mergedAlignmentsByDirection.length; i--){
            outputArrayIndex = -(i - (alignmentsByDirection.length - 1));

            if (alignmentsByDirection[i] != null){
                try {
                    mergedAlignmentsByDirection[outputArrayIndex].addAll(alignmentsByDirection[i]);
                }
                catch (java.lang.NullPointerException e){
                    mergedAlignmentsByDirection[outputArrayIndex] = new LinkedList<Square>();
                    mergedAlignmentsByDirection[outputArrayIndex].addAll(alignmentsByDirection[i]);
                }
            }

            if (alignmentsByDirection[i - mergedAlignmentsByDirection.length] != null){
                try {
                    mergedAlignmentsByDirection[outputArrayIndex].addAll(alignmentsByDirection[i - mergedAlignmentsByDirection.length]);
                }
                catch (java.lang.NullPointerException e){
                    mergedAlignmentsByDirection[outputArrayIndex] = new LinkedList<Square>();
                    mergedAlignmentsByDirection[outputArrayIndex].addAll(alignmentsByDirection[i - mergedAlignmentsByDirection.length]);
                }
            }
        }
        return mergedAlignmentsByDirection;
    }

    public Square[] listNeighbours(Position pos){
        Square[] neighbours = new Square[Board.Direction.values().length];

        // Get N, NE, E, SE , S, SW, W, NW and append in this order; null if empty
        for (int i = 0; i < Board.Direction.values().length; i ++){
            Direction curDirection = Board.Direction.values()[i];
            try {
                neighbours[i] = this.getSquare(pos.getNextPosition(curDirection));
            }
            catch (Exception e) {
                System.out.println("No more squares on this direction");
                neighbours[i] = null;
            }
        }

        return neighbours;
    }

    public void display(){
        String formattedTable = "";

        formattedTable += String.format("%10s", "/");
        for (int colIndex=0; colIndex < this.squares[0].length; colIndex++){
            formattedTable += String.format("%10s", colIndex);
        }
        formattedTable += "\n";

        // Print row by row
        for (int rowIndex=0; rowIndex < this.squares.length; rowIndex++){

            for (int colIndex=0; colIndex < this.squares[rowIndex].length; colIndex++){
                if (colIndex == 0){
                    formattedTable += String.format("%10s", rowIndex);
                }

                if (Square.class.isInstance(this.squares[rowIndex][colIndex])){
                    formattedTable += String.format("%10s", this.squares[rowIndex][colIndex].getColor());
                }
                else {
                    formattedTable += String.format("%10s", " ");
                }

            }
            formattedTable += "\n";
        }

        System.out.print(formattedTable);
    }

    private int size;
    private Square[][] squares;
    public enum Direction { N, NE, E, SE, S, SW, W, NW };
}
