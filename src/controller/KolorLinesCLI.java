package controller;

import model.*;

import java.util.LinkedList;

/**
 * KolorLinesCLI represents the controller which wrap the interactions of the user via CLI
 * and the model
 */
public class KolorLinesCLI {
    public static void main(String[] args){

        Board board = Board.prompt();  // Prompt to get Board size

        SystemUser systemUser = new SystemUser();
        HumanUser humanUser = new HumanUser();

        systemUser.setBoard(board);
        humanUser.setBoard(board);

        board.display();

        while (!board.isFull()) {  // Start of a turn

            // System plays
            do {

                System.out.println("System is playing");

                Position[] lastSystemPositions = SystemUserController.play(board);

                board.display();

                LinkedList<LinkedList<Square>> validSystemAlignments = board.processPositions(lastSystemPositions);

                int addedValue = Board.processValidAlignments(validSystemAlignments);
                humanUser.updateScore(addedValue);

                if (addedValue > 0){
                    System.out.println("Youhou! You just got " + addedValue + " points");
                }

                System.out.println("Score is " + (int) humanUser.getScore());

            } while (board.isEmpty());  // If board is empty, continue playing

            if  (board.isFull()) {  // If the board is full, exit the while loop
                System.out.println("Game Over");
                break;
            }

            // User plays
            System.out.println("User is playing");

            Position[] lastUserPositions = HumanUserController.play(board);

            LinkedList<LinkedList<Square>> validUserAlignments = board.processPositions(lastUserPositions);

            board.display();

            int addedValue = Board.processValidAlignments(validUserAlignments);
            humanUser.updateScore(addedValue);
            if (addedValue > 0){
                System.out.println("Youhou! You just got " + addedValue + " points");
            }

            System.out.println("Score is " + humanUser.getScore());

            System.out.println("End of turn");
        }

        // Display score
        System.out.println(humanUser.getScore());
    }
}