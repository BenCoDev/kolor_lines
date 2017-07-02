package src.controller;

import src.model.*;

import java.util.LinkedList;

public class KolorLines {
    public static void main(String[] args) throws Exception {

        // Prompt to get src.model.Board size
        Board board = Board.prompt();

        SystemUser systemUser = new SystemUser();
        HumanUser humanUser = new HumanUser();

        systemUser.setBoard(board);
        humanUser.setBoard(board);

        board.display();

        while (!board.isFull()) {  // Start of a turn

            // System plays
            do {

                System.out.println("System is playing");

                Position[] lastSystemPositions = systemUser.play();

                board.display();

                LinkedList<LinkedList<Square>> validSystemAlignments = board.processPositions(lastSystemPositions);

                processValidAlignments(board, validSystemAlignments, humanUser);

            } while (board.isEmpty());  // If board is empty, continue playing

            if  (board.isFull()) {
                break;
            }

            // User plays
            System.out.println("User is playing");

            Position[] lastUserPositions = humanUser.play();

            LinkedList<LinkedList<Square>> validUserAlignments = board.processPositions(lastUserPositions);

            board.display();

            processValidAlignments(board, validUserAlignments, humanUser);

            System.out.println("End of turn");
        }

        // Display score
        System.out.println(humanUser.getScore());
    }

    /**
     *  Wrapper over refactored code
     * @param board
     * @param validAlignments
     * @param humanUser
     */
    protected static void processValidAlignments(Board board, LinkedList<LinkedList<Square>> validAlignments, HumanUser humanUser){

        for (LinkedList<Square> validAlignment : validAlignments) {
            for (Square validAlignmentSquare : validAlignment) {
                validAlignmentSquare.unsetColor();
            }
        }

        if (validAlignments.size() > 0) {

            double addedValue = HumanUser.computeValue(validAlignments);
            humanUser.updateScore(addedValue);

            board.display();
            System.out.println("Youhou! You just got " + (int) addedValue + " points");
            System.out.println("Score is " + (int) humanUser.getScore());
        }
    }
}