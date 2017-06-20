package src;

public class HumanUser extends User {

    public Position[] play() {
        // Turn for the Human user
        // Aggregation ?? src.User has a board, or a board has a src.User ==> more src.User has a board
        Position[] lastPositions = new Position[1];

        Square originalSquare = this.getBoard().promptSquare();
        this.getBoard().unsetSquare(originalSquare.getPosition());

        Position targetPosition = this.getBoard().promptPosition();
        this.getBoard().setSquare(targetPosition, originalSquare.getColor());

        lastPositions[0] = targetPosition;

        return lastPositions;
    }
}
