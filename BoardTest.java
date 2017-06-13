import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

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

}