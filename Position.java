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

        if (Position.maxWidth != 0 || Position.maxLength != 0){  // Only validates if maxLength and maxWidth have been set
            this.validate();
        }

    }

    private void validate()  throws Exception {
        if (this.abs >= Position.maxWidth){
            throw new Exception(String.format("Abscise not permitted: should be lower than: %s", maxWidth));
        }
        if (this.ord >= Position.maxLength){
            throw new Exception(String.format("Ordinate not permitted: should be lower than: %s", maxLength));
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

    private static int maxWidth;
    private static int maxLength;
    private int abs;
    private int ord;
}
