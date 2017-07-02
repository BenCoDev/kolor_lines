package model;

import java.util.LinkedList;

abstract public class User {

//    public .model.User() {
//    }

    // Play
    public void setBoard(Board board){
        this.board = board;
    }

    public Board getBoard() {
        return this.board;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public double updateScore(double addedValue) {
        this.score += addedValue;
        return this.score;
    }

    public static double computeValue(LinkedList<LinkedList<Square>> alignments) {
        double value = 0;

        for (LinkedList<Square> alignment : alignments) {
            value += alignment.size();
        }

        if (alignments.size() > 0) {
            value = Math.pow(value, Math.sqrt(alignments.size()));
        }

        return value;
    }

    private double score = 0;
    private Board board; // aggregation
}
