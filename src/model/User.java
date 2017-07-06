package model;

import java.util.LinkedList;

/**
 * Abstract class representing a User
 */
abstract public class User {

    /**
     * Set a Board to a User
     */
    public void setBoard(Board board){
        this.board = board;
    }

    public Board getBoard() {
        return this.board;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int updateScore(int addedValue) {
        this.score += addedValue;
        return this.score;
    }

    /**
     * Compute the added value to the score given alignments
     *
     * Add the size of each alignment
     * If more than one alignment (COMBO)
     * Will raise the computed value to the power of square root of the alignments
     *
     * @param alignments: LinkedList<LinkedList<Square>>
     * @return
     */
    public static int computeValue(LinkedList<LinkedList<Square>> alignments) {
        int value = 0;

        for (LinkedList<Square> alignment : alignments) {
            value += alignment.size();
        }

        if (alignments.size() > 0) {
            value = (int) Math.pow(value, Math.sqrt(alignments.size()));
        }

        return value;
    }

    private int score = 0;
    private Board board; // aggregation
}
