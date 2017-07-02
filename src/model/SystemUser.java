package src.model;

public class SystemUser extends User {

    /**
     * Should be ok to play one position at a time given that
     * if one position creates a valid aligned
     * then it keeps true for all new position introduces
     * @return
     * @throws Exception
     */
    public Position[] play() {  // should not raise exception
        Position[] lastPositions = new Position[CONSECUTIVE_MOVES];

        int i = 0;
        while(i < SystemUser.CONSECUTIVE_MOVES && ! this.getBoard().isFull()){

            Position position = Position.randomPosition();

            Square selectedSquare = this.getBoard().getSquare(position);

            if (selectedSquare.getColor() == null){
                selectedSquare.setColor();
                lastPositions[i] = position;
                i += 1;
            }
        }

        return lastPositions;
    }

    private static int CONSECUTIVE_MOVES = 3;
}
