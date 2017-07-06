package controller;

import model.*;
import view.BoardPanel;
import view.KolorLinesFrame;
import view.MessagePanel;
import view.SquarePanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class BoardController {

    /**
     * Board controller glues together interactions of the user on BoardPanel with the model.
     * More specifically, the interactions happen via the SquareController level
     * which then delegates to the BoardController
     *
     * @param frame: KolorlinesFrame - represents centralized access to other part of the view
     * @param board: Board - instance of the current board represented
     */
    public BoardController(KolorLinesFrame frame, Board board) {
        this.frame = frame;
        this.board = board;
    }

    /**
     * Main method of the controller given a squarePanel the user interacted with
     *
     * Process the selection of the HumanUser and executes the SystemUser turn
     *
     * @param squarePanel: SquarePanel - instance of the SquarePanel the user interacted with
     */
    public void run(SquarePanel squarePanel) {

        BoardPanel boardPanel = this.frame.getBoardPanel();
        ArrayList<SquarePanel> selectedSquarePanels = boardPanel.getSelectedSquarePanels();
        Position[] lastUserPositions = this.selectSquare(squarePanel);

        // If 2 selected, swap and get the System user to play
        if (selectedSquarePanels.size() == 2) {

            board.display();  // CLI display to have a debug print if needed

            this.swap();

            board.display();  // CLI display to have a debug print if needed

            boardPanel.emptySelectedSquarePanels();

            // Check if alignment
            LinkedList<LinkedList<Square>> validUserAlignments = board.processPositions(lastUserPositions);

            int addedValue = Board.processValidAlignments(validUserAlignments);
            if (addedValue > 0){
                frame.popNotification("Youhou! You just got " + addedValue + " points", MessagePanel.NotificationType.SUCCESS);
            }

            frame.getUser().updateScore(addedValue);

            // TODO: highlight alignment and add time before removing
            frame.updateBoardPanel(Square.flatten(validUserAlignments));

            board.display();

            // System user to play
            //  TODO: handle while (board.isEmpty()); case

            frame.updateMessagePanel("System user is playing...");
            frame.getBoardPanel().setIsListening(false);  // Lock the BoardPanel until the SystemUser finished playing

            frame.getMessagePanel().showLoading();

            javax.swing.Timer displayTimer = new javax.swing.Timer(SystemUser.getSpeed(), new DelayedActionListener());

            displayTimer.setRepeats(false);
            displayTimer.start();
        }
    }

    /**
     * Wrapper around the selection of a squarePanel
     *
     * Given a squarePanel and the current selectedSquarePanels, it will
     * - unselect the squarePanel if the selectedSquarePanels already have 1 SquarePanel and
     *      selected squarePanel is the same as the current selected SquarePanel
     * - select the squarePanel otherwise and update selectedSquarePanels accordingly
     * - update the messagePanel with "selected" if so
     * - update the messagePanel with BoardException message if so
     *
     * @param squarePanel - SquarePanel
     * @return Position[] - Array of Position played by the user - Represented as an array to harmonize with SystemUser
     *      moves
     */
    private Position[] selectSquare(SquarePanel squarePanel) {
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
                    if (squarePanel == selectedSquarePanels.get(0)){
                        // Is selectedPanel is the same as origin - unselect it and return
                        squarePanel.toggleSelect();
                        selectedSquarePanels.remove(0);
                        return null;
                    }
                    break;
                default:
                    throw new BoardException("Unknown type provided");
            }

            this.board.selectSquare(squarePanel.getSquare().getPosition(), type);
            squarePanel.toggleSelect();
            boardPanel.addToSelectedSquarePanels(squarePanel);  // OK, so add to selected

            this.frame.updateMessagePanel("Square selected");
        }
        catch (BoardException e){
            this.frame.popNotification(e.getMessage(), MessagePanel.NotificationType.WARNING);
        }

        lastPositions[0] = squarePanel.getSquare().getPosition();

        return lastPositions;
    }

    /**
     * Wrapper around swapping a target SquarePanel color with an origin SquarePanel color
     * given the current selectedSquarePanels
     *
     * Responsible to swap colors
     * Responsible for unselecting SquarePanels
     */
    private void swap(){
        BoardPanel boardPanel = this.frame.getBoardPanel();
        ArrayList<SquarePanel> selectedSquarePanels = boardPanel.getSelectedSquarePanels();
        Color originColor = selectedSquarePanels.get(0).getSquare().getColor();
        selectedSquarePanels.get(0).getSquare().unsetColor();
        selectedSquarePanels.get(1).getSquare().setColor(originColor);
        selectedSquarePanels.forEach(SquarePanel::toggleSelect);  // Unselect both
    }

    /**
     * Wrapper to simulate delay
     */
    private class DelayedActionListener implements ActionListener {


        /**
         * Represents the action performed by the System user
         *
         * Responsible for make the System user to play
         * Updating message panels accordingly
         *
         * @param e - ActionEvent
         */
        public void actionPerformed(ActionEvent e) {
            Position[] lastSystemPositions = SystemUserController.play(board);

            board.display();

            frame.updateBoardPanel(lastSystemPositions);  // pass lastSystemPositions

            LinkedList<LinkedList<Square>> validSystemAlignments = board.processPositions(lastSystemPositions);

            int addedValue = Board.processValidAlignments(validSystemAlignments);
            if (addedValue > 0){
                frame.popNotification("Youhou! You just got " + addedValue + " points", MessagePanel.NotificationType.SUCCESS);
            }
            frame.getUser().updateScore(addedValue);

            frame.updateBoardPanel(Square.flatten(validSystemAlignments));

            if (board.isFull()){
                frame.updateMessagePanel("Game Over");
            }
            else {
                frame.getBoardPanel().setIsListening(true);  // Release the lock on the BoardPanel
                frame.updateMessagePanel("Your turn !");
            }

            frame.getMessagePanel().hideLoading();

            board.display();
        }
    }

    private KolorLinesFrame frame;
    private Board board;
}
