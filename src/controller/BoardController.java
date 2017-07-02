package src.controller;

import src.model.*;
import src.view.BoardPanel;
import src.view.KolorLinesFrame;
import src.view.MessagePanel;
import src.view.SquarePanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

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
    public void run(SquarePanel squarePanel) throws InterruptedException {  // Find better name
        BoardPanel boardPanel = this.frame.getBoardPanel();
        ArrayList<SquarePanel> selectedSquarePanels = boardPanel.getSelectedSquarePanels();

        Position[] lastUserPositions = this.selectSquare(squarePanel);

        // If 2 selected, swap
        if (selectedSquarePanels.size() == 2) {

            board.display();

            this.swap();

            board.display();

            boardPanel.emptySelectedSquarePanels();

            // Check if alignment
            LinkedList<LinkedList<Square>> validUserAlignments = board.processPositions(lastUserPositions);

            double addedValue = board.processValidAlignments(validUserAlignments);
            if (addedValue > 0){
                frame.popNotification("Youhou! You just got " + (int) addedValue + " points", MessagePanel.NotificationType.SUCCESS);
            }

            frame.getUser().updateScore(addedValue);

            // TODO: highlight alignment
            // TODO: add time before removing
            frame.updateBoardPanel(flatten(validUserAlignments));  // pass lastUserPositions

            board.display();

            // System user to play
            //  TODO: handle while (board.isEmpty()); case

            frame.updateMessagePanel("System user is playing...");
            frame.getBoardPanel().setIsListening(false);

            frame.getMessagePanel().showLoading();

            javax.swing.Timer displayTimer = new javax.swing.Timer(SystemUser.getSpeed(), new DelayedActionListener());

            displayTimer.setRepeats(false);
            displayTimer.start();

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
            this.frame.popNotification(e.getMessage(), MessagePanel.NotificationType.WARNING);
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

    private class DelayedActionListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            Position[] lastSystemPositions = frame.getSystemUser().play();  // TODO: can be null ! be careful

            board.display();

            frame.updateBoardPanel(lastSystemPositions);  // pass lastSystemPositions

            LinkedList<LinkedList<Square>> validSystemAlignments = board.processPositions(lastSystemPositions);

            double addedValue = board.processValidAlignments(validSystemAlignments);
            if (addedValue > 0){
                frame.popNotification("Youhou! You just got " + (int) addedValue + " points", MessagePanel.NotificationType.SUCCESS);
            }
            frame.getUser().updateScore(addedValue);

            frame.updateBoardPanel(flatten(validSystemAlignments));

            if (board.isFull()){
                frame.updateMessagePanel("Game Over");
            }
            else {
                frame.getBoardPanel().setIsListening(true);
                frame.updateMessagePanel("Your turn !");
            }

            frame.getMessagePanel().hideLoading();
            board.display();
        }
    }


    private KolorLinesFrame frame;
    private Board board;
}
