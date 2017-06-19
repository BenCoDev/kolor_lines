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

    /**
     * Check if positions triggered a valid alignments
     *
     *
     * Includes business logic here
     *
     * @param board
     * @param positions
     * @return validAlignments
     */
    protected static LinkedList<LinkedList<Square>> processPositions(Board board, Position[] positions){
        LinkedList<LinkedList<Square>> validAlignments = new LinkedList();

        // Iterate over positions
        for (Position position : positions) {


            // For each position, get valid alignments as a list of lists
            LinkedList<LinkedList<Square>> alignments = board.getAlignments(position, MIN_CONSECUTIVE_ALIGNMENT);  // FIXME: CHECK can it be null

            for (LinkedList<Square> alignment : alignments) {  // For each alignment, check not already as alignment of a position

                if (!validAlignments.contains(alignment)) {
                    validAlignments.add(alignment);  // FIXME: test not already in DUPLICATES
                }
            }


        }

        return validAlignments;
    }

    private static int MIN_CONSECUTIVE_ALIGNMENT = 3;
}