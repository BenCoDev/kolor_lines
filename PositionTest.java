import org.junit.Test;

import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.containsString;

public class PositionTest {
    /**
     * src: https://www.mkyong.com/unittest/junit-4-tutorial-2-expected-exception-test/
     */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
     * Case: test validate raise Ordinate exception when entering an invalid ordinate
     * @throws Exception    Exception containt Ordinate in the msg
     */
    @Test()
    public void validate_InvalidOrdinate_ExceptionThrown() throws Exception {
        thrown.expect(Exception.class);
        thrown.expectMessage(containsString("Ordinate"));
        new Position(1, 20);
    }

    @Test()
    public void validate_InvalidAbscise_ExceptionThrown() throws Exception {
        thrown.expect(Exception.class);
        thrown.expectMessage(containsString("Abscise"));
        new Position(20, 1);
    }

    @Test()
    public void validate_InvalidAbsciseInvalidOrdinate_ExceptionThrown() throws Exception {
        thrown.expect(Exception.class);
        thrown.expectMessage(containsString("Abscise"));
        new Position(20, 20);
    }

    @Test
    public void setMaxWidth() throws Exception {
// TODO
    }

    @Test
    public void setMaxHeight() throws Exception {
// TODO
    }

    @Test
    public void getNextPosition_Basic() throws Exception {
        Position.setMaxHeight(3);
        Position.setMaxWidth(3);
        Position curPosition = new Position(1, 1);

        assertEquals(curPosition.getNextPosition(Board.Direction.N).getAbs(), curPosition.getAbs());
        assertEquals(curPosition.getNextPosition(Board.Direction.N).getOrd(), curPosition.getOrd() - 1);

        assertEquals(curPosition.getNextPosition(Board.Direction.NE).getAbs(), curPosition.getAbs() + 1);
        assertEquals(curPosition.getNextPosition(Board.Direction.NE).getOrd(), curPosition.getOrd() - 1);

        assertEquals(curPosition.getNextPosition(Board.Direction.E).getAbs(), curPosition.getAbs() + 1);
        assertEquals(curPosition.getNextPosition(Board.Direction.E).getOrd(), curPosition.getOrd());

        assertEquals(curPosition.getNextPosition(Board.Direction.SE).getAbs(), curPosition.getAbs() + 1);
        assertEquals(curPosition.getNextPosition(Board.Direction.SE).getOrd(), curPosition.getOrd() + 1);

        assertEquals(curPosition.getNextPosition(Board.Direction.S).getAbs(), curPosition.getAbs());
        assertEquals(curPosition.getNextPosition(Board.Direction.S).getOrd(), curPosition.getOrd() + 1);

        assertEquals(curPosition.getNextPosition(Board.Direction.SW).getAbs(), curPosition.getAbs() - 1);
        assertEquals(curPosition.getNextPosition(Board.Direction.SW).getOrd(), curPosition.getOrd() + 1);

        assertEquals(curPosition.getNextPosition(Board.Direction.W).getAbs(), curPosition.getAbs() - 1);
        assertEquals(curPosition.getNextPosition(Board.Direction.W).getOrd(), curPosition.getOrd());

        assertEquals(curPosition.getNextPosition(Board.Direction.NW).getAbs(), curPosition.getAbs() - 1);
        assertEquals(curPosition.getNextPosition(Board.Direction.NW).getOrd(), curPosition.getOrd() - 1);
    }

    @Test
    public void getNextPosition_InvalidOrdinate_ExceptionThrown() throws Exception {
        thrown.expect(Exception.class);
        thrown.expectMessage(containsString("Ordinate"));

        Position.setMaxHeight(3);
        Position.setMaxWidth(3);
        Position curPosition = new Position(1,0);

        curPosition.getNextPosition(Board.Direction.N);
    }

    @Test
    public void randomPosition_BasicCase() throws Exception {
        Position.setMaxHeight(3);
        Position.setMaxWidth(3);
        Position position = Position.randomPosition();

        assertNotNull(position);
    }
}