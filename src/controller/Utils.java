package controller;

import java.util.Scanner;

public class Utils {

    /**
     * Util function to prompt for an integer
     * Recursively calls itself until an integer is provided
     *
     * @param valueName - String - Name of the value to prompt for
     * @return int
     */
    public static int promptInt(String valueName){
        System.out.println("Enter " + valueName);
        Scanner scanner = new Scanner(System.in);

        try {
            int casted_value = scanner.nextInt();
            return casted_value;
        } catch (java.util.InputMismatchException e) {
            System.out.println("You should enter an integer");
            return promptInt(valueName);
        }
    }
}
