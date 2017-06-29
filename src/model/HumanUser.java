package src.model;

public class HumanUser extends User {

    public Position[] play() {
        // Turn for the Human user
        // Aggregation ?? src.model.User has a board, or a board has a src.model.User ==> more src.model.User has a board
        Position[] lastPositions = new Position[1];

        Square originalSquare = this.getBoard().promptSquare();
        Color originalColor = originalSquare.getColor();
        this.getBoard().unsetSquare(originalSquare.getPosition());

        Position targetPosition = this.getBoard().promptPosition();
        this.getBoard().setSquare(targetPosition, originalColor);

        lastPositions[0] = targetPosition;

        return lastPositions;
    }
}
