package src.controller;

import src.model.Board;
import src.model.Color;
import src.model.Position;
import src.model.Square;
import src.view.BoardPanel;
import src.view.KolorLinesFrame;
import src.view.SquarePanel;

import javax.swing.*;
import java.util.ArrayList;

public class BoardController {

    public BoardController(KolorLinesFrame frame, Board board) {
        this.frame = frame;
        this.board = board;
    }

    public void run(SquarePanel squarePanel){  // Find better name
        BoardPanel boardPanel = this.frame.getBoardPanel();
        ArrayList<SquarePanel> selectedSquarePanels = boardPanel.getSelectedSquarePanels();

        this.selectSquare(squarePanel);

        // If 2 selected, swap
        if (selectedSquarePanels.size() == 2) {

            this.swap();

            boardPanel.emptySelectedSquarePanels();

            // Check if alignment


            // System user to play
            try {
                Position[] lastSystemPositions = this.frame.getSystemUser().play();
                frame.updateBoardPanel(lastSystemPositions);
            } catch (Exception e) {
                // should not happen
            }
        }
    }

    public void selectSquare(SquarePanel squarePanel) {
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
                        return;
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
//            this.frame.updateMessagePanel(e.getMessage());
        }
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
