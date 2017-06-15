public class SystemUser extends User {

    public Position[] play() throws Exception {
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
