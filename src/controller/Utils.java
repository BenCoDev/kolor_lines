package src.controller;

import src.model.Position;
import src.model.Square;

import java.util.LinkedList;
import java.util.Scanner;

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

    // TODO: change position
    public static Position[] flatten(LinkedList<LinkedList<Square>> inputListofList){
        int len = 0;
        for (LinkedList<Square> list: inputListofList){
            len += list.size();
        }

        Position outputPositions[] = new Position[len];

        int counter = 0;
        for (int i = 0; i < inputListofList.size(); i ++) {
            for (int j = 0; j < inputListofList.get(i).size(); j++){

                outputPositions[counter] = inputListofList.get(i).get(j).getPosition();
                counter += 1;

            }
        }

        return outputPositions;
    }

}
