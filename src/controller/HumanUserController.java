package controller;

import model.BoardException;
import model.Color;
import model.Board;
import model.Position;
import model.Square;

public class HumanUserController {
    /**
     * Defines the actions of a user
     *
     * @return Position[] - positions of the square played
     */
    public static Position[] play(Board board) {
        // Turn for the Human user
        Position[] lastPositions = new Position[1];

        // Prompt position first
        Square originSquare = null;
        do {
            try {
                Position originPosition = Position.prompt("Source");
                originSquare = board.selectSquare(originPosition, "origin");
            }
            catch (BoardException e){
                System.out.print(e.getMessage());
            }

        } while (originSquare == null);

        Color originColor = originSquare.getColor();
        originSquare.unsetColor();

        Square targetSquare = null;
        do {
            try {
                Position targetPosition = Position.prompt("Target");
                targetSquare = board.selectSquare(targetPosition, "target");
            }
            catch (BoardException e){
                System.out.print(e.getMessage());
            }

        } while (targetSquare == null);

        targetSquare.setColor(originColor);

        lastPositions[0] = targetSquare.getPosition();

        return lastPositions;
    }
}
