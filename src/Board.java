package src;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Collections;

/**
 * Represents the src.Board for Kolor Lines games
 *
 * The src.Board is composed of squares and has a defined size
 */
public class Board {

    public Board(int size) throws BoardException {
        Position.setMaxWidth(size);
        Position.setMaxHeight(size);

        this.size = size;
        this.validate();  // valid after setting size

        this.squares = new Square[size][size];
        // composition
        // Lazy, instance square if nothing
        // means that a square can be
        // colored, or null ==> a square here must be colored

    }

    private void validate() throws BoardException {
        if (this.size <= 0){
            throw new BoardException("Size should be at least 1");
        }
    }

    public void load(String[] serializedBoard) throws Exception {
        for (int i = 0; i < (this.size * this.size); i++) {
            if (serializedBoard[i] != null) {
                this.setSquare(new Position(i % this.size, i / this.size), Color.valueOf(serializedBoard[i]));
            }
        }
    }

    public static Board prompt(){
        int size = Utils.promptInt("src.Board size");

        try {
            return new Board(size);

        } catch (BoardException e){
            System.out.println(e.getMessage());
            return Board.prompt();
        }
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

    /**
     * TODO
     * @return
     */
    public boolean isEmpty(){
        for (int i = 0; i < this.squares.length; i++) {
            for (int j = 0; j < this.squares[i].length; j++) {
                if (Square.class.isInstance(this.squares[i][j])) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Get Alignments bigger than given minimumLength which includes given position
     *
     * Will first compute all the alignments from given position according 8 directions
     * Will then merge found alignments according 4 directions and including the square at given position
     * Finally check length
     *
     * @param pos - src.Position - src.Position of the original point from where alignments are computed
     * @param minimumLength - int - Minimum length needed for an alignment to be returned
     * @return LinkedList - List of Lists of the squares in returned alignments
     */
    public LinkedList getAlignments(Position pos, int minimumLength){
        LinkedList<Square>[] alignmentsByDirection = this.fetchAlignments(pos);
        LinkedList<Square>[] mergedDirectionAlignments = Board.mergeAlignments(alignmentsByDirection);
        LinkedList<LinkedList<Square>> minimumLengthAlignments = new LinkedList<LinkedList<Square>>();

        for (LinkedList<Square> mergedDirectionAlignment : mergedDirectionAlignments) {
            if (mergedDirectionAlignment != null){
                if (Board.isAlignmentValid(mergedDirectionAlignment, minimumLength)) {
                    minimumLengthAlignments.add(mergedDirectionAlignment);
                }
            }
        }

        return minimumLengthAlignments;
    }

    public void display(){
        final int COL_WIDTH = 10;
        final String SEPARATOR = "#";

        String formattedTable = "";

        for (int i=0; i < (this.size + 1) * COL_WIDTH; i++){
            formattedTable += SEPARATOR;
        }

        formattedTable += "\n";

        formattedTable += String.format("%10s", "/");

        for (int colIndex=0; colIndex < this.squares[0].length; colIndex++){
            formattedTable += String.format("%" + COL_WIDTH + "s", colIndex);
        }

        formattedTable += "\n";

        // Print row by row
        for (int rowIndex=0; rowIndex < this.squares.length; rowIndex++){

            for (int colIndex=0; colIndex < this.squares[rowIndex].length; colIndex++){
                if (colIndex == 0){
                    formattedTable += String.format("%" + COL_WIDTH + "s", rowIndex);
                }

                if (Square.class.isInstance(this.squares[rowIndex][colIndex])){
                    formattedTable += String.format("%" + COL_WIDTH + "s", this.squares[rowIndex][colIndex].getColor());
                }
                else {
                    formattedTable += String.format("%" + COL_WIDTH + "s", " ");
                }

            }
            formattedTable += "\n";
        }

        for (int i=0; i < (this.size + 1) * COL_WIDTH; i++){
            formattedTable += SEPARATOR;
        }

        System.out.println(formattedTable);
    }

    public Square getSquare(Position pos){
        return this.squares[pos.getOrd()][pos.getAbs()];
    }

    public void setSquare(Position pos){
        // FIXME: is it more addSquare to board and should take square object?
        // If not (because composition: be sure that no other place instantiate src.Square)
        // TODO: add tests
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
     * @return
     */
    public Square promptSquare(){

        Position pos = Position.prompt("Original");

        if (this.getSquare(pos) != null){
            return this.getSquare(pos);
        }

        System.out.println("You can't prompt for an empty square on this board");

        return promptSquare();
    }

    /**
        Explain why here
        Why not on user ?
     */
    public Position promptPosition(){
        Position pos = Position.prompt("Target");

        if (this.getSquare(pos) == null){
            return pos;
        }

        System.out.println("You can't prompt for a filled position on this board");

        return promptPosition();

    }

    /**
     * List the squares surrounding a position
     *
     * @param pos - src.Position - src.Position at which we want to list the neighbours
     * @return - src.Square[] - List of squares for every direction listed
     *         Given in the order defined by the Direction enum
     *         null if empty
     */
    protected Square[] listNeighbours(Position pos){
        Square[] neighbours = new Square[Board.Direction.values().length];

        // Get N, NE, E, SE , S, SW, W, NW and append in this order; null if empty
        for (int i = 0; i < Board.Direction.values().length; i ++){
            Direction curDirection = Board.Direction.values()[i];
            try {
                neighbours[i] = this.getSquare(pos.getNextPosition(curDirection));
            }
            catch (Exception e) {  // Fixme enhance readibility of the exception
                neighbours[i] = null;
            }
        }

        return neighbours;
    }

    /**
     * Fetch alignments given a position by listing all the neighbours of a given position
     * includes curSquare and will extend alignments for each direction
     *
     *
     * @param pos - src.Position - src.Position of the original point from where alignments are computed
     * @return LinkedList - List of lists of aligned squares by direction
     *                      Given in the order of the Direction enum
     *                      Min size of 1 since it includes the curSquare
     */
    protected LinkedList<Square>[] fetchAlignments(Position pos){
        LinkedList<Square>[] alignmentsByDirection = new LinkedList[Direction.values().length];
        Square curSquare = getSquare(pos);
        Square[] neighbours;

        neighbours = this.listNeighbours(pos);

        for (int i = 0; i < neighbours.length; i++){

            // Init with curSquare
            alignmentsByDirection[i] = new LinkedList<Square>();
            alignmentsByDirection[i].add(curSquare);

            if (neighbours[i] != null) {

                extendAlignments(pos, alignmentsByDirection, Board.Direction.values()[i]);

            }
        }

        return alignmentsByDirection;
    }

    /**
     * Extend alignments given a position, an existing array of alignments by directions, and a given direction
     *
     * src.Square in the alignments are added in the order relative to the direction
     *
     * @param pos - src.Position - src.Position of the original point from where alignments are computed
     * @param alignmentsByDirection - Array of LinkedList of Squares - Array of alignments by directions -
     *                              will be extended in place
     * @param direction - Direction
     * @return alignmentsByDirection
     */
    protected LinkedList<Square>[] extendAlignments(Position pos, LinkedList<Square>[] alignmentsByDirection, Direction direction){
        Square nextSquare;

        try {
            nextSquare = getSquare(pos.getNextPosition(direction));
        }
        catch (Exception e) {  // Fixme enhance readibility of the exception
            nextSquare = null;
        }

        if (nextSquare != null) {
            if (isColorValid(alignmentsByDirection[direction.ordinal()], nextSquare)){
                alignmentsByDirection[direction.ordinal()].add(nextSquare);

                extendAlignments(nextSquare.getPosition(), alignmentsByDirection, direction);
            }
        }

        return alignmentsByDirection;
    }

    /**
     * Merge alignments from a given src.Square
     * by merging opposite directions 2 by 2
     *
     * Note: Case V V Rb B B, with Rb being the given src.Square ==> Rb will only count for leftSide
     * Controlled by shouldMergeRightSide boolean
     *
     * Also add the current square
     * @param alignmentsByDirection: LinkedList[8] - Alignments surrounding a src.Square by direction
     * @return LinkedList[4] - { alignments on NWSE, alignments on WE, alignments on SWNE, alignments on SN }
     */
    protected static LinkedList<Square>[] mergeAlignments(LinkedList<Square>[] alignmentsByDirection){
        LinkedList<Square>[] mergedAlignmentsByDirection = new LinkedList[4];

        // Make more sense to decrement
        // i from 7 to 4 included
        int outputArrayIndex;

        for (int i = alignmentsByDirection.length - 1; i >= mergedAlignmentsByDirection.length; i--){
            outputArrayIndex = -(i - (alignmentsByDirection.length - 1));
            LinkedList<Square> leftSideList = alignmentsByDirection[i];
            Collections.reverse(leftSideList); // Will reverse in place to get a left to right square alignment
            LinkedList<Square> rightSideList = alignmentsByDirection[i - mergedAlignmentsByDirection.length];
            boolean shouldMergeRightSide = true;

            mergedAlignmentsByDirection[outputArrayIndex] = new LinkedList<Square>(leftSideList);

            // Case V V Rb B B ==> Rb will only count for leftSide
            // Check the last of left (original square since list has been reversed)
            if (leftSideList.getLast().getColor() == Color.RAINBOW){
                Color leftColor = null;
                Color rightColor = null;

                if (leftSideList.size() > 0){
                    // Starts with first element of the LinkedList
                    leftColor = fetchStandardColor(leftSideList.listIterator(0));
                }

                if (rightSideList != null && rightSideList.size() > 0){
                    // Starts with first element of the LinkedList
                    rightColor = fetchStandardColor(rightSideList.listIterator(0));
                }

                if (rightColor != null && leftColor != null && rightColor != leftColor){
                    shouldMergeRightSide = false;
                }
            }

            if (shouldMergeRightSide){
                // Remove first of the list to avoid duplicates when merging
                // Won't raise error since every alignmentsByDirection has at least one element
                mergedAlignmentsByDirection[outputArrayIndex].addAll(rightSideList.subList(1, rightSideList.size()));
            }

        }
        return mergedAlignmentsByDirection;
    }

    private static boolean isAlignmentValid(LinkedList<Square> alignment, int minimumLength) {
        return (alignment.size() >= minimumLength);
    }

    /**
     * TODO
     * @param curSquares
     * @param nextSquare
     * @return
     */
    protected static boolean isColorValid(LinkedList<Square> curSquares, Square nextSquare){
        if (curSquares.size() == 0){
            // base case where has been going up while color was rainbow
            return true;
        }

        Square curSquare = curSquares.getLast();

        switch (curSquare.getColor()) {
            case RAINBOW:
                return isColorValid(new LinkedList<Square>(curSquares.subList(0, curSquares.size() - 1)), nextSquare);
            default:
                return nextSquare.getColor() == Color.RAINBOW || curSquare.getColor() == nextSquare.getColor();
        }

    }

    /**
     * Fetch a standard color (not equal to RAINBOW) given a linked list of src.Square
     * @param curSquare - ListIterator<src.Square> - current square
     * @return src.Color - standard color, otherwise returns None
     */
    protected static Color fetchStandardColor(ListIterator<Square> curSquare){
        if (curSquare.hasNext()) {
            Color curColor = curSquare.next().getColor();
            if (curColor != Color.RAINBOW) {
                return curColor;
            }
            else {
                return fetchStandardColor(curSquare);
            }
        }
        return null;
    }

    private int size;
    private Square[][] squares;  // TODO: describe
    public enum Direction { N, NE, E, SE, S, SW, W, NW };
}
