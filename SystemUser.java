/**
 * Created by benoitcotte on 14/06/2017.
 */
public class SystemUser extends User {

    public void play() throws Exception {
        int i = 0;
        while(i < SystemUser.CONSECUTIVE_MOVES && ! this.getBoard().isFull()){

            Position position = Position.randomPosition();
            if (this.getBoard().getSquare(position) == null){
                this.getBoard().setSquare(position);

                i += 1;
            }
        }
    }

    private static int CONSECUTIVE_MOVES = 3;
}
