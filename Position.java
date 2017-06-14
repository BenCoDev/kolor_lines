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
        if (Position.maxWidth != 0 || Position.maxLength != 0){
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

        if (this.ord >= Position.maxLength){
            throw new Exception(String.format("Ordinate not permitted: should be lower than: %s", maxLength));
        }
        else if (this.ord < 0){
            throw new Exception(String.format("Ordinate not permitted: should be upper than: %s", minLength));
        }
    }

    public static void setMaxWidth(int maxWidth) {
        Position.maxWidth = maxWidth;
    }

    public static void setMaxLength(int maxLength) {
        Position.maxLength = maxLength;
    }

    public int getAbs() {
        return this.abs;
    }

    public int getOrd() {
        return this.ord;
    }

    /**
     * TODO
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

    private static int maxWidth;
    private static int maxLength;
    private static int minLength = 0;
    private static int minWidth = 0;
    private int abs;
    private int ord;
}
