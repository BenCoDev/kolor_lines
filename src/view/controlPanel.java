package view;

import controller.ControlController;
import model.Board;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ControlPanel extends JPanel {

    /**
     * Represents a ControlPanel responsible for starting the game
     * placed into the frame given a board instance
     *
     * @param frame KolorLinesFrame
     * @param board Board
     */
    public ControlPanel(KolorLinesFrame frame, Board board) {
        ControlController listener = new ControlController(frame, board);

        this.setLayout(new GridBagLayout());

        JButton startGameButton = new JButton("Start Game");
        startGameButton.addActionListener(listener);

        GridBagConstraints gbc = new GridBagConstraints(0, 1,
                1, 1, 1.0D, 1.0D, GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL, regularInsets, 0, 0);

        this.add(startGameButton, gbc);
    }

    private static final Insets regularInsets = new Insets(10, 10, 0, 10);
}