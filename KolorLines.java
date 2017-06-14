import javafx.geometry.Pos;
import sun.jvm.hotspot.debugger.linux.LinuxDebugger;

import java.util.LinkedList;

public class KolorLines {
    public static void main(String[] args) throws Exception {
        // Prompt to get Board size

        Board board = new Board(3);

        SystemUser systemUser = new SystemUser();
        HumanUser humanUser = new HumanUser();

        systemUser.setBoard(board);
        humanUser.setBoard(board);

        // Games start
        // Automatic play from the computer
        while (!board.isFull()){
            systemUser.play();  // use lastPosition
            board.display();

            Position lastPosition = humanUser.play();
            board.display();

            // Should remove if valid
            // Should increment score


            // Maybe hide/ internalize in getValidAlignments
            LinkedList validAlignments = board.getMinLengthAlignments(lastPosition, 3);  // FIXME: refactor
            System.out.println(validAlignments);
            // TODO Should remove if valid
            // TODO Should increment score
        }

        // Games end
        // Display score
    }
}