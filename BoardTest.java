import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by benoitcotte on 13/06/2017.
 */
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

        LinkedList<Square>[] alignmentsByDirection = new LinkedList[8];

        assertArrayEquals(b.fetchAlignments(originalPosition, alignmentsByDirection), new LinkedList[8]);
    }

    @Test
    public void fetchAlignments_MultipleDirectionAlignments() throws Exception {
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


        LinkedList<Square>[] alignmentsByDirection = new LinkedList[8];
        LinkedList<Square>[] expectedArray = new LinkedList[8];

        expectedArray[Board.Direction.valueOf("NE").ordinal()] = new LinkedList<Square>();
        expectedArray[Board.Direction.valueOf("NE").ordinal()].add(squareNE);
        expectedArray[Board.Direction.valueOf("SW").ordinal()] = new LinkedList<Square>();
        expectedArray[Board.Direction.valueOf("SW").ordinal()].add(squareSW);

        assertArrayEquals(expectedArray, b.fetchAlignments(originalPosition, alignmentsByDirection));
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

        LinkedList<Square>[] alignmentsByDirection = new LinkedList[8];
        LinkedList<Square>[] expectedArray = new LinkedList[8];

        expectedArray[Board.Direction.valueOf("SE").ordinal()] = new LinkedList<Square>();
        expectedArray[Board.Direction.valueOf("SE").ordinal()].add(nextSquare);
        expectedArray[Board.Direction.valueOf("SE").ordinal()].add(nextNextSquare);

        assertArrayEquals(expectedArray, b.fetchAlignments(originalPosition, alignmentsByDirection));
    }

    @Test
    public void mergeDirectionAligments() throws Exception {
        Square WSquare = new Square(new Position(0, 1));
        Square ESquare = new Square(new Position(2, 1));

        LinkedList<Square>[] alignmentsByDirection = new LinkedList[8];
        alignmentsByDirection[Board.Direction.valueOf("W").ordinal()] = new LinkedList<Square>();
        alignmentsByDirection[Board.Direction.valueOf("W").ordinal()].add(WSquare);

        alignmentsByDirection[Board.Direction.valueOf("E").ordinal()] = new LinkedList<Square>();
        alignmentsByDirection[Board.Direction.valueOf("E").ordinal()].add(ESquare);


        LinkedList<Square>[] expectedArray = new LinkedList[4];
        expectedArray[1] = new LinkedList<Square>();
        expectedArray[1].add(WSquare);
        expectedArray[1].add(ESquare);

        assertArrayEquals(expectedArray, Board.mergeDirectionAligments(alignmentsByDirection));

    }

}