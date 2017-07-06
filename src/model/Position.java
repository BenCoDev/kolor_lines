package model;

import controller.Utils;

public class Position {
    /**
     * Position object represents the coordinates in a cartesian plane.
     *
     * The origin Position(0, 0) of the plane is the top left corner.
     *
     * Validate data according maxLength and maxWidth if have been set
     * If normal flow, should be set at the Board instantiation (Aggregation)
     * Otherwise, enable to instantiate a Position free of any context.
     *
     * @param abs   int     Abscissa of the position
     * @param ord   int     Ordinate of the position
     */
    public Position(int abs, int ord) throws PositionException {
        this.abs = abs;
        this.ord = ord;

        // Only validates if maxLength and maxWidth have been set
        if (Position.maxWidth != 0 || Position.maxHeight != 0){
            this.validate();
        }
    }

    /**
     * Validate the abscissa of a Position regarding its max width
     * @param abs - int
     * @throws PositionException
     */
    public static void validateAbs(int abs) throws PositionException {
        if (abs >= Position.maxWidth){
            throw new PositionException(String.format("Abscissa not permitted: should be lower than: %s", maxWidth));
        }
        else if (abs < 0){
            throw new PositionException(String.format("Abscissa not permitted: should be upper than: %s", minWidth));
        }
    }

    /**
     * Validate the ordinate of a Position regarding its max height
     * @param ord - int
     * @throws PositionException
     */
    public static void validateOrd(int ord) throws PositionException {
        if (ord >= Position.maxHeight){
            throw new PositionException(String.format("Ordinate not permitted: should be lower than: %s", maxHeight));
        }
        else if (ord < 0){
            throw new PositionException(String.format("Ordinate not permitted: should be upper than: %s", minHeight));
        }
    }

    public static void setMaxWidth(int maxWidth) {
        Position.maxWidth = maxWidth;
    }

    public static void setMaxHeight(int maxHeight) {
        Position.maxHeight = maxHeight;
    }

    public int getAbs() {
        return this.abs;
    }

    public int getOrd() {
        return this.ord;
    }

    /**
     * Get next position on a board given a direction
     * @param direction - direction
     * @return Position
     * @throws PositionException
     */
    public Position getNextPosition(Board.Direction direction) throws PositionException {
        switch (direction) {
            case N:
                return new Position(this.abs, this.ord - 1);
            case NE:
                return new Position(this.abs + 1, this.ord - 1);
            case E:
                return new Position(this.abs + 1, this.ord);
            case SE:
                return new Position(this.abs + 1, this.ord + 1);
            case S:
                return new Position(this.abs, this.ord + 1);
            case SW:
                return new Position(this.abs - 1, this.ord + 1);
            case W:
                return new Position(this.abs - 1, this.ord);
            case NW:
                return new Position(this.abs - 1, this.ord - 1);
        }

        return null;
    }

    public static Position prompt(String positionName){
        System.out.println("Position: " + positionName);

        int abs = promptCoord("Abscisse");
        int ord = promptCoord("Ordinate");

        try {
            return new Position(abs, ord);
        } catch (PositionException e){
            System.out.println(e.getMessage());
            return prompt(positionName);
        }
    }

    /**
     * Compute a random position in the constraint of the maximum width and maximum height
     * Will recursively call itself until a valid on is given
     *
     * TODO: Distribute over the available position to increase effectiveness of the method
     * @return Position
     */
    public static Position randomPosition() {
        int abs = (int) (Math.random() * Position.maxWidth);  // If not set: will be 0
        int ord = (int) (Math.random() * Position.maxHeight);  // If not set: will be 0

        try {
            Position pos = new Position(abs, ord);
            return pos;
        }
        catch (PositionException e){
            return randomPosition();  // should never happen
        }
    }

    /**
     * Validate if the provided coordinates of the object are valid
     * @throws PositionException
     */
    private void validate() throws PositionException {
        Position.validateAbs(this.abs);
        Position.validateOrd(this.ord);
    }

    private static int promptCoord(String coordName){

        int abs = Utils.promptInt(coordName);

        try {
            Position.validateAbs(abs);
            return abs;

        } catch (PositionException e){
            return promptCoord(coordName);
        }
    }

    private static int minWidth = 0;
    private static int minHeight = 0;
    private static int maxWidth;  // Will be set at Board instantiation
    private static int maxHeight; // Will be set at Board instantiation

    private int abs;
    private int ord;
}
