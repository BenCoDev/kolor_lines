package src.model;

import org.junit.Test;

import static org.junit.Assert.*;


public class SquareTest {
    @Test
    public void getColor() throws Exception {
        Square square = new Square(new Position(1, 1));
        assertNotNull("src.model.Color should be set randomly", square.getColor());

        Square squareBlue = new Square(new Position(1, 1), Color.BLEU);
        assertEquals("src.model.Color should be set to BLEU", squareBlue.getColor(), Color.BLEU);
    }

    @Test
    public void getPosition() throws Exception {
//        TODO

    }

    @Test
    public void unsetColor() throws Exception {
//        TODO
    }
}