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

                LinkedList<LinkedList<Square>> validSystemAlignments = processPositions(board, lastSystemPositions);

                processValidAlignments(board, validSystemAlignments, humanUser);

            } while (board.isEmpty());  // If board is empty, continue playing

            if  (board.isFull()) {
                break;
            }

            // src.model.User plays
            System.out.println("src.model.User is playing");

            Position[] lastUserPositions = humanUser.play();
            LinkedList<LinkedList<Square>> validUserAlignments = processPositions(board, lastUserPositions);

            board.display();

            processValidAlignments(board, validUserAlignments, humanUser);

            System.out.println("End of turn");
        }

        // Display score
        System.out.println(humanUser.getScore());
    }

    /**
     * Check if positions triggered a valid alignments
     *
     *
     * Includes business logic here
     *
     * @param board
     * @param positions
     * @return validAlignments
     */
    protected static LinkedList<LinkedList<Square>> processPositions(Board board, Position[] positions){
        LinkedList<LinkedList<Square>> validAlignments = new LinkedList();

        // Iterate over positions
        for (Position position : positions) {

            if (position != null){  // Can be null (System positions for example)

                // For each position, get valid alignments as a list of lists
                LinkedList<LinkedList<Square>> alignments = board.getAlignments(position, MIN_CONSECUTIVE_ALIGNMENT);  // FIXME: CHECK can it be null

                for (LinkedList<Square> alignment : alignments) {  // For each alignment, check not already as alignment of a position

                    if (!validAlignments.contains(alignment)) {
                        validAlignments.add(alignment);
                    }
                }

            }
        }

        return validAlignments;
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
                board.unsetSquare(validAlignmentSquare.getPosition());
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

    private static int MIN_CONSECUTIVE_ALIGNMENT = 3;
}