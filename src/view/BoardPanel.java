package view;

import model.Board;
import model.Position;
import model.PositionException;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BoardPanel extends JPanel {

    public BoardPanel(KolorLinesFrame frame, Board board) {
        this.frame = frame;
        this.board = board;
        this.squarePanels = new SquarePanel[this.board.getSize().width][this.board.getSize().width];
        this.selectedSquarePanels = new ArrayList<SquarePanel>(2);  // Reinit when BoardPanel init == composition

        this.setPreferredSize(this.getPreferredSize());
        this.setMinimumSize(this.getPreferredSize());
        this.setMaximumSize(this.getPreferredSize());
        this.setLayout(new GridLayout(this.board.getSize().width, this.board.getSize().width, this.GUTTER, this.GUTTER));
        this.setBorder(BorderFactory.createEmptyBorder(this.GUTTER, this.GUTTER, this.GUTTER, this.GUTTER));


        for (int ord = 0; ord < board.getSize().width; ord++) {
            for (int abs = 0; abs < board.getSize().width; abs++) {
                try {
                    this.squarePanels[abs][ord] = new SquarePanel(this.frame, board.getSquare(new Position(abs, ord)));
                    this.add(this.squarePanels[abs][ord]);
                }
                catch (PositionException e){
                    // not likely to happen
                }
            }
        }

    }

    private void draw(){
        for (int x = 0; x < board.getSize().width; x++) {
            for (int y = 0; y < board.getSize().width; y++) {
                this.squarePanels[x][y].repaint();
            }
        }

    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        draw();
    }

    public void update(Position[] positions){
        for (Position position : positions) {
            if (position!= null){
                this.squarePanels[position.getAbs()][position.getOrd()].repaint();
            }
        }
    }

    public Dimension getPreferredSize() {
        int width = this.board.getSize().width * SquarePanel.getSquareWidth();
        return new Dimension(width, width);
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

    private boolean isListening = true;


    private Board board;
    private KolorLinesFrame frame;
    private SquarePanel[][] squarePanels;
    private ArrayList<SquarePanel> selectedSquarePanels;
    private static int GUTTER = 5;
}
