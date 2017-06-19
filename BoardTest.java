import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.LinkedList;

import static org.junit.Assert.*;

public class BoardTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
        System.setErr(null);
    }

    @Test
    public void isFull_FullCase() throws Exception {
        Board b = new Board(1);
        Position position = new Position(0, 0);

        b.setSquare(position);

        assertTrue(b.isFull());
    }

    @Test
    public void isFull_NotFullCase() throws Exception {
        Board b = new Board(3);
        Position position = new Position(1, 1);

        b.setSquare(position);

        assertFalse(b.isFull());
    }

    @Test
    public void display_EmptyBoard() throws Exception {
        Board b = new Board(1);
        b.display();
        assertEquals("Should have displayed empty board", outContent.toString(),
                "         /         0\n" +
                "         0          \n"
        );
    }

    @Test
    public void display_FilledBoard() throws Exception {
        Board b = new Board(1);
        Position position = new Position(0, 0);

        b.setSquare(position);
        b.display();

        assertEquals("Should have displayed board with one filled value", outContent.toString(),
                "         /         0\n" +
                "         0" + String.format("%10s", b.getSquare(position).getColor()) + "\n"
        );
    }

    @Test
    public void listNeighbours() throws Exception {
//        TODO: refine

        Board b = new Board(2);
        Position originalPosition = new Position(0, 0);
        b.setSquare(originalPosition);

        Position position = new Position(1, 0);
        b.listNeighbours(position);
        b.display();
    }

    @Test
    public void fetchAlignments_BasicCase() throws Exception {
        Board b = new Board(2);
        Position originalPosition = new Position(1, 1);
        b.setSquare(originalPosition);

        LinkedList<Square>[] expectedArray = new LinkedList[8];
        for (int i = 0; i < Board.Direction.values().length; i++) {
            expectedArray[i] = new LinkedList<Square>();
            expectedArray[i].add(b.getSquare(originalPosition));
        }

        assertArrayEquals(b.fetchAlignments(originalPosition), expectedArray);
    }

    @Test
    public void fetchAlignments_WithNeighbours() throws Exception {
        Board b = new Board(3);

        Position originalPosition = new Position(1, 1);
        b.setSquare(originalPosition, Color.BLEU);

        // Add NE with same color
        Position NEPosition = new Position(2, 0);
        b.setSquare(NEPosition, Color.BLEU);
        Square squareNE = b.getSquare(NEPosition);

        // Add E with different color
        Position EPosition = new Position(2, 1);
        b.setSquare(EPosition, Color.VERT);
        Square squareE = b.getSquare(EPosition);

        // Add SW with same color
        Position SWPosition = new Position(0, 2);
        b.setSquare(SWPosition, Color.BLEU);
        Square squareSW = b.getSquare(SWPosition);


        LinkedList<Square>[] expectedArray = new LinkedList[8];
        for (int i = 0; i < Board.Direction.values().length; i++) {
            expectedArray[i] = new LinkedList<Square>();
            expectedArray[i].add(b.getSquare(originalPosition));
        }

        expectedArray[Board.Direction.valueOf("NE").ordinal()].add(squareNE);
        expectedArray[Board.Direction.valueOf("SW").ordinal()].add(squareSW);

        assertArrayEquals(expectedArray, b.fetchAlignments(originalPosition));
    }

    @Test
    public void fetchAlignments_MultipleSquareAlignments() throws Exception {
        Board b = new Board(3);

        Position originalPosition = new Position(0, 0);
        b.setSquare(originalPosition, Color.BLEU);

        // Add nextPosition with same color
        Position nextPosition = new Position(1, 1);
        b.setSquare(nextPosition, Color.BLEU);
        Square nextSquare = b.getSquare(nextPosition);

        // Add nextNextPosition with same color
        Position nextNextPosition = new Position(2, 2);
        b.setSquare(nextNextPosition, Color.BLEU);
        Square nextNextSquare = b.getSquare(nextNextPosition);

        LinkedList<Square>[] expectedArray = new LinkedList[8];
        for (int i = 0; i < Board.Direction.values().length; i++) {
            expectedArray[i] = new LinkedList<Square>();
            expectedArray[i].add(b.getSquare(originalPosition));
        }

        expectedArray[Board.Direction.valueOf("SE").ordinal()].add(nextSquare);
        expectedArray[Board.Direction.valueOf("SE").ordinal()].add(nextNextSquare);

        assertArrayEquals(expectedArray, b.fetchAlignments(originalPosition));
    }

    @Test
    public void mergeDirectionAlignments() throws Exception {
        Square curSquare = new Square(new Position(1, 1));
        Square WSquare = new Square(new Position(0, 1));
        Square ESquare = new Square(new Position(2, 1));

        LinkedList<Square>[] alignmentsByDirection = new LinkedList[8];
        for (int i = 0; i < Board.Direction.values().length; i++) {
            alignmentsByDirection[i] = new LinkedList<Square>();
            alignmentsByDirection[i].add(curSquare);
        }

        alignmentsByDirection[Board.Direction.valueOf("W").ordinal()].add(WSquare);
        alignmentsByDirection[Board.Direction.valueOf("E").ordinal()].add(ESquare);

        LinkedList<Square>[] expectedArray = new LinkedList[4];
        expectedArray[0] = new LinkedList<Square>();
        expectedArray[0].add(curSquare);

        expectedArray[1] = new LinkedList<Square>();
        expectedArray[1].add(WSquare);
        expectedArray[1].add(curSquare);
        expectedArray[1].add(ESquare);

        expectedArray[2] = new LinkedList<Square>();
        expectedArray[2].add(curSquare);

        expectedArray[3] = new LinkedList<Square>();
        expectedArray[3].add(curSquare);

        assertArrayEquals(expectedArray, Board.mergeAlignments(alignmentsByDirection));
    }

    @Test
    public void isColorValid() throws Exception {
        Board b = new Board(3);  // to avoid conflict with previous test cases with inferior value

        LinkedList<Square> curSquares = new LinkedList<Square>();
        curSquares.add(new Square(new Position(0, 0), Color.BLEU));

        assertTrue(Board.isColorValid(curSquares, new Square(new Position(1, 1), Color.BLEU)));
        assertTrue(Board.isColorValid(curSquares, new Square(new Position(1, 1), Color.RAINBOW)));
        assertFalse(Board.isColorValid(curSquares, new Square(new Position(1, 1), Color.ROUGE)));

        curSquares.add(new Square(new Position(0, 0), Color.RAINBOW));
        assertTrue(Board.isColorValid(curSquares, new Square(new Position(1, 1), Color.BLEU)));
        assertTrue(Board.isColorValid(curSquares, new Square(new Position(1, 1), Color.RAINBOW)));
        assertFalse(Board.isColorValid(curSquares, new Square(new Position(1, 1), Color.ROUGE)));

        LinkedList<Square> curSquaresStartingRainbow = new LinkedList<Square>();
        curSquaresStartingRainbow.add(new Square(new Position(0, 0), Color.RAINBOW));
        assertTrue(Board.isColorValid(curSquaresStartingRainbow, new Square(new Position(1, 1), Color.BLEU)));
        assertTrue(Board.isColorValid(curSquaresStartingRainbow, new Square(new Position(1, 1), Color.RAINBOW)));
        assertTrue(Board.isColorValid(curSquaresStartingRainbow, new Square(new Position(1, 1), Color.ROUGE)));

        curSquaresStartingRainbow.add(new Square(new Position(0, 0), Color.BLEU));
        assertTrue(Board.isColorValid(curSquaresStartingRainbow, new Square(new Position(1, 1), Color.BLEU)));
        assertTrue(Board.isColorValid(curSquaresStartingRainbow, new Square(new Position(1, 1), Color.RAINBOW)));
        assertFalse(Board.isColorValid(curSquaresStartingRainbow, new Square(new Position(1, 1), Color.ROUGE)));
    }

    @Test
    public void getAlignments_AlignmentWithRainbow_ReturnsAlignment() throws Exception {
        int SIZE = 3;
        Board board = new Board(SIZE);

        String[] boardSerialized = {
                "BLEU", "ROUGE", "RAINBOW",
                "ROUGE", "BLEU", "BLEU",
                "BLEU", "ROUGE", "VERT"
        };

        board.load(boardSerialized);

        // From position 0,2
        Position position1 = new Position(0, 2);
        LinkedList alignments = board.getAlignments(position1, 3);
        assertTrue(alignments.size() == 1);
    }

    @Test
    public void getAlignments_AlignmentWithRainbow_ReturnsNoAlignment() throws Exception {
        int SIZE = 3;
        Board board = new Board(SIZE);

        String[] boardSerialized = {
                null, "BLEU", null,
                "ROUGE", "RAINBOW", "VERT",
                "VERT", null, "BLEU"
        };

        board.load(boardSerialized);

        // From position (1, 1)
        Position position1 = new Position(1, 1);
        LinkedList alignments1 = board.getAlignments(position1, 3);
        assertTrue(alignments1.size() == 0);

        // From position (0, 1)
        Position position2 = new Position(0, 1);
        LinkedList alignments2 = board.getAlignments(position2, 3);
        assertTrue(alignments2.size() == 0);
    }

    @Test
    public void getAlignments_RandomAlignment_ReturnAlignments() throws Exception {
        int SIZE = 10;
        Board board = new Board(SIZE);

        String[] boardSerialized = {
                null, "ROUGE", null, null, null, null, "VERT", null, null, "VERT",
                "BLEU", "ROUGE", null, null, null, "RAINBOW", "ROUGE", null, "BLEU", "RAINBOW",
                "BLEU", null, null, "VERT", null, null, null, "VERT", null, "ROUGE",
                null, "VERT", null, "ROUGE", "ROUGE", null, null, null, "ROUGE", null,
                "BLEU", null, "RAINBOW", null, "VERT", null, "ROUGE", "BLEU", "ROUGE", "VERT",
                null, null, "VERT", null, null, null, "ROUGE", null, "VERT", "VERT",
                "BLEU", "RAINBOW", "BLEU", "VERT", null, null, null, "ROUGE", "VERT", "VERT",
                "BLEU", "ROUGE", "VERT", null, null, null, "ROUGE", null, "VERT", "VERT",
                "ROUGE", null, "RAINBOW", "BLEU", "RAINBOW", "VERT", null, "ROUGE", null, "BLEU",
                "RAINBOW", null, null, "ROUGE", "VERT", null, "ROUGE", "RAINBOW", null, "VERT",
        };

        board.load(boardSerialized);

        Position position1 = new Position(8, 5);
        LinkedList alignments1 = board.getAlignments(position1, 3);
        assertTrue(alignments1.size() == 1);

        Position position2 = new Position(2, 8);
        LinkedList alignments2 = board.getAlignments(position2, 3);
        assertTrue(alignments2.size() == 2);
    }

}
