package view;

import model.Board;
import model.Position;
import model.PositionException;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BoardPanel extends JPanel {

    /**
     * Represents a BoardPanel responsible for displaying the board
     * placed into the frame given a board instance
     *
     * @param frame KolorLinesFrame
     * @param board Board
     */
    public BoardPanel(KolorLinesFrame frame, Board board) {
        this.frame = frame;
        this.board = board;
        this.squarePanels = new SquarePanel[this.board.getSize().width][this.board.getSize().width];
        this.selectedSquarePanels = new ArrayList<SquarePanel>(2);  // Reinit when BoardPanel init == composition

        this.setPreferredSize(this.getPreferredSize());
        this.setLayout(new GridLayout(this.board.getSize().width, this.board.getSize().width, this.GUTTER, this.GUTTER));
        this.setBorder(BorderFactory.createEmptyBorder(this.GUTTER, this.GUTTER, this.GUTTER, this.GUTTER));
    }

    /**
     * Initialize the grid of square panels
     */
    public void initGrid(){
        for (int ord = 0; ord < board.getSize().width; ord++) {
            for (int abs = 0; abs < board.getSize().width; abs++) {
                try {
                    this.squarePanels[abs][ord] = new SquarePanel(this.frame, board.getSquare(new Position(abs, ord)));
                    this.add(this.squarePanels[abs][ord]);
                }
                catch (PositionException e){
                    e.printStackTrace();  // not likely to happen
                }
            }
        }
    }

    /**
     * Update the JPanel by only repainting the positions given
     * @param positions Position[]
     */
    public void update(Position[] positions){
        for (Position position : positions) {
            if (position!= null){
                this.squarePanels[position.getAbs()][position.getOrd()].repaint();
            }
        }
    }

    public Dimension getPreferredSize() {
        return new Dimension(DEFAULT_WIDTH, DEFAULT_WIDTH);
    }

    public void addToSelectedSquarePanels(SquarePanel squarePanel){
        selectedSquarePanels.add(squarePanel);
    }

    public void emptySelectedSquarePanels(){
        selectedSquarePanels.clear();
    }

    public ArrayList<SquarePanel> getSelectedSquarePanels() {
        return selectedSquarePanels;
    }

    public void setIsListening(boolean isListening){
        this.isListening = isListening;
    }

    public boolean getIsListening(){
        return this.isListening;
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        draw();
    }

    private void draw(){
        for (int x = 0; x < board.getSize().width; x++) {
            for (int y = 0; y < board.getSize().width; y++) {
                this.squarePanels[x][y].repaint();
            }
        }

    }

    private boolean isListening = true;  // If false, lock the BoardPanel from listening to events
    private Board board;
    private KolorLinesFrame frame;  // Main frame
    private SquarePanel[][] squarePanels;  // Represents the squarePanels which makes the BoardPanel
    private ArrayList<SquarePanel> selectedSquarePanels;  // Currently selected SquarePanels
    private static int GUTTER = 5;  // Space between SquarePanel
    private static int DEFAULT_WIDTH = 635;
}
