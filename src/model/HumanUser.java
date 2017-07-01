package src.model;

public class HumanUser extends User {

    public Position[] play() {
        // Turn for the Human user
        // Aggregation ?? src.model.User has a board, or a board has a src.model.User ==> more src.model.User has a board
        Position[] lastPositions = new Position[1];

        // Prompt position first

        Square originSquare = null;
        do {
            try {
                Position originPosition = Position.prompt("Source");
                originSquare = this.getBoard().selectSquare(originPosition, "origin");
            }
            catch (Exception e){
                System.out.print(e);
            }

        } while (originSquare == null);

        Color originColor = originSquare.getColor();
        originSquare.unsetColor();

        Square targetSquare = null;
        do {
            try {
                Position targetPosition = Position.prompt("Target");
                targetSquare = this.getBoard().selectSquare(targetPosition, "target");
            }
            catch (Exception e){  // TODO: specify which exception
                System.out.print(e);
            }

        } while (targetSquare == null);

        targetSquare.setColor(originColor);

        lastPositions[0] = targetSquare.getPosition();

        return lastPositions;
    }
}
