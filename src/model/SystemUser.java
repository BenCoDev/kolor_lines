package model;

public class SystemUser extends User {
    public static int getSpeed() {
        return SPEED;
    }

    public static int getConsecutiveMoves() {
        return CONSECUTIVE_MOVES;
    }

    private static int CONSECUTIVE_MOVES = 3;  // Number of squares the system user should fill by turn
    private static int SPEED = 2000;  // Defines the speed of playing to simulate a delay
}
