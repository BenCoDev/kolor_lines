public class Position {
    /**
     * a Position object represents the coordinates in a cartesian plane.
     *
     * The origin Position(0, 0) of the plane is the top left corner.
     *
     * Validate data according maxLength and maxWidth if have been set
     * If normal flow, should be set at the Board instantiation (Aggregation)
     * Otherwise, enable to instantiate a Position free of any context.
     *
     * @param abs   int     Abscise of the position
     * @param ord   int     Ordinate of the position
     */
    Position(int abs, int ord) throws Exception {
        this.abs = abs;
        this.ord = ord;

        // Only validates if maxLength and maxWidth have been set
        if (Position.maxWidth != 0 || Position.maxHeight != 0){
            this.validate();
        }

    }

    /**
     * TODO
     * @throws Exception
     */
    private void validate()  throws Exception {
        if (this.abs >= Position.maxWidth){
            throw new Exception(String.format("Abscise not permitted: should be lower than: %s", maxWidth));
        }
        else if (this.abs < 0){
            throw new Exception(String.format("Abscise not permitted: should be upper than: %s", minWidth));
        }

        if (this.ord >= Position.maxHeight){
            throw new Exception(String.format("Ordinate not permitted: should be lower than: %s", maxHeight));
        }
        else if (this.ord < 0){
            throw new Exception(String.format("Ordinate not permitted: should be upper than: %s", minHeight));
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
     * TODOthis.squares[rowIndex][colIndex].getPosition().getAbs()
     * @param direction
     * @return
     * @throws Exception
     */
    public Position getNextPosition(Board.Direction direction) throws Exception{
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

    public static Position randomPosition() throws Exception {
//        TODO: distribute over open positions

        int abs = (int) (Math.random() * Position.maxWidth);  // If not set: will be 0
        int ord = (int) (Math.random() * Position.maxHeight);  // If not set: will be 0

        return new Position(abs, ord);
    }

    private static int minWidth = 0;
    private static int minHeight = 0;

    private static int maxWidth;  // Will be set at Board instantiation
    private static int maxHeight; // Will be set at Board instantiation

    private int abs;
    private int ord;
}
