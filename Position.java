public class Position {
    /**
     * a Position object represents the coordinates in a cartesian plane
     *
     * The origin Position(0, 0) of the plane is the top left corner
     *
     * @param abs   int     Abscise of the position
     * @param ord   int     Ordinate of the position
     */
    Position(int abs, int ord) throws Exception {
        this.abs = abs;
        this.ord = ord;
        this.validate();
    }

    private void validate()  throws Exception {
        if (this.abs >= maxWidth){
            throw new Exception(String.format("Abscise not permitted: should be lower than: %s", maxWidth));
        }
        if (this.ord >= maxLength){
            throw new Exception(String.format("Ordinate not permitted: should be lower than: %s", maxLength));
        }
    }

    public static void setMaxWidth(int maxWidth) {
        Position.maxWidth = maxWidth;
    }

    public static void setMaxLength(int maxLength) {
        Position.maxLength = maxLength;
    }

    private static int maxWidth = 10;
    private static int maxLength = 10;
    private int abs;
    private int ord;
}
