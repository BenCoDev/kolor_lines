package controller;

import model.Position;
import model.Board;
import model.Square;
import model.SystemUser;

public class SystemUserController {
    /**
     * Defines the actions of a System User aka the computer
     *
     * @return
     */
    public static Position[] play(Board board) {  // should not raise exception
        Position[] lastPositions = new Position[SystemUser.getConsecutiveMoves()];

        int i = 0;
        while(i < SystemUser.getConsecutiveMoves() && ! board.isFull()){

            Position position = Position.randomPosition();

            Square selectedSquare = board.getSquare(position);

            if (selectedSquare.getColor() == null){
                selectedSquare.setColor();
                lastPositions[i] = position;
                i += 1;
            }
        }

        return lastPositions;
    }
}
