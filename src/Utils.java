package src;

import java.util.Scanner;

/**
 * Created by benoitcotte on 19/06/2017.
 */
public class Utils {
    public static int promptInt(String valueName){
        System.out.println("Enter " + valueName);
        Scanner scanner = new Scanner(System.in);

        try {
            int abs = scanner.nextInt();
            return abs;
        } catch (java.util.InputMismatchException e) {
            System.out.println("You should enter an integer");
            return promptInt(valueName);
        }
    }

}
