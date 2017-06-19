public class SystemUser extends User {

    /**
     * Should be ok to play one position at a time given that
     * if one position creates a valid aligned
     * then it keeps true for all new position introduces
     * @return
     * @throws Exception
     */
    public Position[] play() throws PositionException {
        int i = 0;
        Position[] lastPositions = new Position[CONSECUTIVE_MOVES];

        while(i < SystemUser.CONSECUTIVE_MOVES && ! this.getBoard().isFull()){

            Position position = Position.randomPosition();

            if (this.getBoard().getSquare(position) == null){
                this.getBoard().setSquare(position);

                lastPositions[i] = position;
                i += 1;
            }
        }

        return lastPositions;
    }

    private static int CONSECUTIVE_MOVES = 3;
}
