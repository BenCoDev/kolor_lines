package src.controller;

import src.model.*;
import src.view.BoardPanel;
import src.view.KolorLinesFrame;
import src.view.SquarePanel;

import javax.swing.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;

import static src.controller.Utils.flatten;

public class BoardController {

    public BoardController(KolorLinesFrame frame, Board board) {
        this.frame = frame;
        this.board = board;
    }

    /**
     * TODO Describe
     * @param squarePanel
     */
    public void run(SquarePanel squarePanel){  // Find better name
        BoardPanel boardPanel = this.frame.getBoardPanel();
        ArrayList<SquarePanel> selectedSquarePanels = boardPanel.getSelectedSquarePanels();

        Position[] lastUserPositions = this.selectSquare(squarePanel);

        // If 2 selected, swap
        if (selectedSquarePanels.size() == 2) {

            this.swap();

            boardPanel.emptySelectedSquarePanels();

            // Check if alignment
            LinkedList<LinkedList<Square>> validUserAlignments = board.processPositions(lastUserPositions);

            KolorLines.processValidAlignments(board, validUserAlignments, this.frame.getUser());
            frame.updateBoardPanel(flatten(validUserAlignments));  // pass lastUserPositions
            // TODO: highlight alignment


            // TODO: add some delay
            // System user to play
            this.frame.updateMessagePanel("System user is playing");
            Position[] lastSystemPositions = this.frame.getSystemUser().play();

            // TODO: add some delay
            frame.updateBoardPanel(lastSystemPositions);  // pass lastSystemPositions

            LinkedList<LinkedList<Square>> validSystemAlignments = board.processPositions(lastSystemPositions);

            KolorLines.processValidAlignments(board, validSystemAlignments, this.frame.getUser());
            frame.updateBoardPanel(flatten(validSystemAlignments));

            // TODO Check if board is full / if yes display gameover

            // TODO increment score



            this.frame.updateMessagePanel("Your turn !");
        }
    }

    public Position[] selectSquare(SquarePanel squarePanel) {
        Position[] lastPositions = new Position[1];
        String type;
        BoardPanel boardPanel = this.frame.getBoardPanel();
        ArrayList<SquarePanel> selectedSquarePanels = boardPanel.getSelectedSquarePanels();

        try {
            switch (selectedSquarePanels.size()){
                case 0:
                    type = "origin";
                    break;
                case 1:
                    type = "target";
                    if (squarePanel == selectedSquarePanels.get(0)){  // Is sel same as origin ? If so untoggle and return
                        squarePanel.toggleSelect();
                        selectedSquarePanels.remove(0);
                        return null;
                    }
                    break;
                default:
                    throw new Exception("Unexpected error");
            }

            this.board.selectSquare(squarePanel.getSquare().getPosition(), type);
            squarePanel.toggleSelect();
            boardPanel.addToSelectedSquarePanels(squarePanel);  // OK, so add to selected
            this.frame.updateMessagePanel("Square selected");
        }
        catch (Exception e){
            this.frame.popWarning(e.getMessage());
        }

        lastPositions[0] = squarePanel.getSquare().getPosition();

        return lastPositions;
    }

    private void swap(){
        BoardPanel boardPanel = this.frame.getBoardPanel();
        ArrayList<SquarePanel> selectedSquarePanels = boardPanel.getSelectedSquarePanels();
        Color originColor = selectedSquarePanels.get(0).getSquare().getColor();
        selectedSquarePanels.get(0).getSquare().unsetColor();
        selectedSquarePanels.get(1).getSquare().setColor(originColor);
        selectedSquarePanels.forEach(SquarePanel::toggleSelect);  // Unselect both
    }

    private KolorLinesFrame frame;
    private Board board;
}
