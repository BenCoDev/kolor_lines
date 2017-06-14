import java.util.Scanner;

/**
 * Created by benoitcotte on 14/06/2017.
 */
public class HumanUser extends User {

    public Position play() throws Exception {
        // Turn for the Human user
        // Aggregation ?? User has a board, or a board has a User ==> more User has a board
        Scanner sc1 = new Scanner(System.in);
        int abs = sc1.nextInt();
        Scanner sc2 = new Scanner(System.in);
        int ord = sc2.nextInt();

        Scanner sc3 = new Scanner(System.in);
        int absTarget = sc3.nextInt();
        Scanner sc4 = new Scanner(System.in);
        int ordTarget = sc4.nextInt();

        Position position = new Position(abs, ord);
        Position targetPosition = new Position(absTarget, ordTarget);

        if (this.getBoard().getSquare(position) != null) {
            if (this.getBoard().getSquare(targetPosition) == null){
                this.getBoard().setSquare(targetPosition, this.getBoard().getSquare(position).getColor());
                this.getBoard().unsetSquare(position);
            }
            else {
                // TODO raise exception
            }
        }
        else {
            // TODO raise exception nothing there
        }

        return targetPosition;
    }

}
