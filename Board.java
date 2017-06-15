import java.util.LinkedList;

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

    /**
     * Get Alignments bigger than given minimumLength which includes given position
     *
     * Will first compute all the alignments from given position according 8 directions
     * Will then merge found alignments according 4 directions and including the square at given position
     * Finally check length
     *
     * @param pos - Position - Position of the original point from where alignments are computed
     * @param minimumLength - int - Minimum length needed for an alignment to be returned
     * @return LinkedList - List of Lists of the squares in returned alignments
     */
    public LinkedList getAlignments(Position pos, int minimumLength){
        LinkedList<Square>[] alignmentsByDirection = this.fetchAlignments(pos);
        LinkedList<Square>[] mergedDirectionAlignments = Board.mergeAlignments(this.getSquare(pos), alignmentsByDirection);
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
     * List the squares surrounding a position
     *
     * @param pos - Position - Position at which we want to list the neighbours
     * @return - Square[] - List of squares for every direction listed
     *         Given in the order defined by the Direction enum
     *         null if empty
     */
    private Square[] listNeighbours(Position pos){
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

    /**
     * Fetch alignments given a position
     *
     * @param pos - Position - Position of the original point from where alignments are computed
     * @return LinkedList - List of lists of aligned squares by direction
     *                      Given in the order of the Direction enum
     */
    private LinkedList<Square>[] fetchAlignments(Position pos){
        LinkedList<Square>[] alignmentsByDirection = new LinkedList[Direction.values().length];
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

    /**
     * Fetch alignments given a position, an existing array of alignments by directions, and a given direction
     *
     * @param pos - Position - Position of the original point from where alignments are computed
     * @param alignmentsByDirection - Array of LinkedList of Squares - Array of alignments by directions -
     *                              will be extended in place
     * @param direction - Direction
     * @return alignmentsByDirection
     */
    private LinkedList<Square>[] fetchAlignments(Position pos, LinkedList<Square>[] alignmentsByDirection, Direction direction){
        Square curSquare = getSquare(pos);
        Square nextSquare;

        try {
            nextSquare = getSquare(pos.getNextPosition(direction));
        }
        catch (Exception e) {
            System.out.println("No more squares on this direction");
            nextSquare = null;
        }

        if (nextSquare != null) {
            // FIXME: problem on ROUGE RAINBOW BLEU: Check curSquare
            // If curSquare is rainbow go to previous until not rainbow
            // TODO: cover with tests

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
     *
     * Also add the current square
     * @param alignmentsByDirection
     * @return
     */
    private static LinkedList<Square>[] mergeAlignments(Square curSquare, LinkedList<Square>[] alignmentsByDirection){
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

            try {
                mergedAlignmentsByDirection[outputArrayIndex].add(curSquare);  // Add the current Square
            }
            catch (java.lang.NullPointerException e){
                mergedAlignmentsByDirection[outputArrayIndex] = new LinkedList<Square>();
                mergedAlignmentsByDirection[outputArrayIndex].add(curSquare);
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

    private static boolean isAlignmentValid(LinkedList<Square> alignment, int minimumLength) {
        return (alignment.size() >= minimumLength);
    }

    private int size;
    private Square[][] squares;
    public enum Direction { N, NE, E, SE, S, SW, W, NW };
}
