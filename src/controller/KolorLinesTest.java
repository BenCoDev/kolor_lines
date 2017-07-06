package controller;

import org.junit.Test;
import model.Board;
import model.Position;
import model.Square;

import java.awt.*;
import java.util.LinkedList;

import static org.junit.Assert.*;

public class KolorLinesTest {

    @Test
    public void processPosition() throws Exception {
        int SIZE = 3;
        Board board = new Board(new Dimension(SIZE, SIZE));

        // System placed 3 squares valid for alignment
        String[] boardSerialized = {
                null, "RAINBOW", null,
                null, "RAINBOW", null,
                "BLEU", "BLEU", "BLEU"
        };

        board.load(boardSerialized);

        // System positions
        Position[] positions = new Position[3];
        positions[0] = new Position(1, 0);
        positions[1] = new Position(1, 1);
        positions[2] = new Position(1, 2);

        LinkedList<LinkedList<Square>> validAlignments = board.processPositions(positions);
        assertEquals("Size should be 2 - avoid duplicate counting", validAlignments.size(), 2);
    }

}
