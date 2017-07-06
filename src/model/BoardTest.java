package model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
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
        Board b = new Board(new Dimension(1, 1));
        Position position = new Position(0, 0);

        b.setSquare(position);

        assertTrue(b.isFull());
    }

    @Test
    public void isFull_NotFullCase() throws Exception {
        Board b = new Board(new Dimension(3, 3));
        Position position = new Position(1, 1);

        b.setSquare(position);

        assertFalse(b.isFull());
    }

    @Test
    public void display_EmptyBoard() throws Exception {
        Board b = new Board(new Dimension(1, 1));
        b.display();
        assertEquals("Should have displayed empty board", outContent.toString(),
                "         /         0\n" +
                        "         0          \n"
        );
    }

    @Test
    public void display_FilledBoard() throws Exception {
        Board b = new Board(new Dimension(1, 1));
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

        Board b = new Board(new Dimension(2, 2));
        Position originalPosition = new Position(0, 0);
        b.setSquare(originalPosition);

        Position position = new Position(1, 0);
        b.listNeighbours(position);
        b.display();
    }

    @Test
    public void fetchAlignments_BasicCase() throws Exception {
        Board b = new Board(new Dimension(2, 2));
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
        Board b = new Board(new Dimension(3, 3));

        Position originalPosition = new Position(1, 1);
        b.setSquare(originalPosition, Color.BLUE);

        // Add NE with same color
        Position NEPosition = new Position(2, 0);
        b.setSquare(NEPosition, Color.BLUE);
        Square squareNE = b.getSquare(NEPosition);

        // Add E with different color
        Position EPosition = new Position(2, 1);
        b.setSquare(EPosition, Color.GREEN);
        Square squareE = b.getSquare(EPosition);

        // Add SW with same color
        Position SWPosition = new Position(0, 2);
        b.setSquare(SWPosition, Color.BLUE);
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
        Board b = new Board(new Dimension(3, 3));

        Position originalPosition = new Position(0, 0);
        b.setSquare(originalPosition, Color.BLUE);

        // Add nextPosition with same color
        Position nextPosition = new Position(1, 1);
        b.setSquare(nextPosition, Color.BLUE);
        Square nextSquare = b.getSquare(nextPosition);

        // Add nextNextPosition with same color
        Position nextNextPosition = new Position(2, 2);
        b.setSquare(nextNextPosition, Color.BLUE);
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
        Board b = new Board(new Dimension(3, 3));  // to avoid conflict with previous test cases with inferior value

        LinkedList<Square> curSquares = new LinkedList<Square>();
        curSquares.add(new Square(new Position(0, 0), Color.BLUE));

        assertTrue(Board.isColorValid(curSquares, new Square(new Position(1, 1), Color.BLUE)));
        assertTrue(Board.isColorValid(curSquares, new Square(new Position(1, 1), Color.RAINBOW)));
        assertFalse(Board.isColorValid(curSquares, new Square(new Position(1, 1), Color.RED)));

        curSquares.add(new Square(new Position(0, 0), Color.RAINBOW));
        assertTrue(Board.isColorValid(curSquares, new Square(new Position(1, 1), Color.BLUE)));
        assertTrue(Board.isColorValid(curSquares, new Square(new Position(1, 1), Color.RAINBOW)));
        assertFalse(Board.isColorValid(curSquares, new Square(new Position(1, 1), Color.RED)));

        LinkedList<Square> curSquaresStartingRainbow = new LinkedList<Square>();
        curSquaresStartingRainbow.add(new Square(new Position(0, 0), Color.RAINBOW));
        assertTrue(Board.isColorValid(curSquaresStartingRainbow, new Square(new Position(1, 1), Color.BLUE)));
        assertTrue(Board.isColorValid(curSquaresStartingRainbow, new Square(new Position(1, 1), Color.RAINBOW)));
        assertTrue(Board.isColorValid(curSquaresStartingRainbow, new Square(new Position(1, 1), Color.RED)));

        curSquaresStartingRainbow.add(new Square(new Position(0, 0), Color.BLUE));
        assertTrue(Board.isColorValid(curSquaresStartingRainbow, new Square(new Position(1, 1), Color.BLUE)));
        assertTrue(Board.isColorValid(curSquaresStartingRainbow, new Square(new Position(1, 1), Color.RAINBOW)));
        assertFalse(Board.isColorValid(curSquaresStartingRainbow, new Square(new Position(1, 1), Color.RED)));
    }

    @Test
    public void getAlignments_AlignmentWithRainbow_ReturnsAlignment() throws Exception {
        int SIZE = 3;
        Board board = new Board(new Dimension(SIZE, SIZE));

        String[] boardSerialized = {
                "BLUE", "RED", "RAINBOW",
                "RED", "BLUE", "BLUE",
                "BLUE", "RED", "GREEN"
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
        Board board = new Board(new Dimension(SIZE, SIZE));

        String[] boardSerialized = {
                null, "BLUE", null,
                "RED", "RAINBOW", "GREEN",
                "GREEN", null, "BLUE"
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
        Board board = new Board(new Dimension(SIZE, SIZE));

        String[] boardSerialized = {
                null, "RED", null, null, null, null, "GREEN", null, null, "GREEN",
                "BLUE", "RED", null, null, null, "RAINBOW", "RED", null, "BLUE", "RAINBOW",
                "BLUE", null, null, "GREEN", null, null, null, "GREEN", null, "RED",
                null, "GREEN", null, "RED", "RED", null, null, null, "RED", null,
                "BLUE", null, "RAINBOW", null, "GREEN", null, "RED", "BLUE", "RED", "GREEN",
                null, null, "GREEN", null, null, null, "RED", null, "GREEN", "GREEN",
                "BLUE", "RAINBOW", "BLUE", "GREEN", null, null, null, "RED", "GREEN", "GREEN",
                "BLUE", "RED", "GREEN", null, null, null, "RED", null, "GREEN", "GREEN",
                "RED", null, "RAINBOW", "BLUE", "RAINBOW", "GREEN", null, "RED", null, "BLUE",
                "RAINBOW", null, null, "RED", "GREEN", null, "RED", "RAINBOW", null, "GREEN",
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
//######################################################################
//        /         0         1         2         3         4         5
//        0      BLUE   RAINBOW     GREEN      BLUE                BLUE
//         1     GREEN             RAINBOW       RED      BLUE       RED
//         2                                    BLUE     GREEN     GREEN
//         3   RAINBOW             RAINBOW               GREEN     GREEN
//         4     GREEN                BLUE   RAINBOW      BLUE     GREEN
//         5      BLUE   RAINBOW      BLUE   RAINBOW     GREEN      BLUE
//######################################################################
//        ######################################################################
//        /         0         1         2         3         4         5
//        0      BLUE   RAINBOW     GREEN      BLUE                BLUE
//         1     GREEN      BLUE   RAINBOW       RED      BLUE       RED
//         2             RAINBOW                BLUE     GREEN     GREEN
//         3   RAINBOW   RAINBOW   RAINBOW               GREEN     GREEN
//         4     GREEN                BLUE   RAINBOW      BLUE     GREEN
//         5      BLUE   RAINBOW      BLUE   RAINBOW     GREEN      BLUE
//######################################################################
//        ######################################################################
//        /         0         1         2         3         4         5
//        0      BLUE               GREEN      BLUE                BLUE
//         1                       RAINBOW       RED      BLUE       RED
//         2                                    BLUE     GREEN     GREEN
//         3   RAINBOW                                   GREEN     GREEN
//         4     GREEN                BLUE                BLUE     GREEN
//         5      BLUE   RAINBOW      BLUE   RAINBOW                BLUE
//######################################################################
//}######################################################################
//        /         0         1         2         3         4         5
//        0      BLUE               GREEN      BLUE                BLUE
//        1   RAINBOW               GREEN       RED      BLUE       RED
//        2   RAINBOW      BLUE      BLUE      BLUE     GREEN     GREEN
//        3   RAINBOW   RAINBOW     GREEN               GREEN     GREEN
//        4     GREEN     GREEN      BLUE   RAINBOW      BLUE     GREEN
//        5      BLUE   RAINBOW      BLUE   RAINBOW     GREEN      BLUE
//        ######################################################################
//        ######################################################################
//        /         0         1         2         3         4         5
//        0      BLUE               GREEN      BLUE                BLUE
//        1   RAINBOW               GREEN       RED      BLUE       RED
//        2   RAINBOW      BLUE      BLUE      BLUE     GREEN     GREEN
//        3   RAINBOW   RAINBOW     GREEN     GREEN     GREEN     GREEN
//        4     GREEN                BLUE   RAINBOW      BLUE     GREEN
//        5      BLUE   RAINBOW      BLUE   RAINBOW     GREEN      BLUE
//        ######################################################################
