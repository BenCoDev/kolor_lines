package src.view;

import src.model.Board;
import src.model.HumanUser;

import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;

public class ScorePanel {

    private static final Insets regularInsets   =
            new Insets(10, 10, 0, 10);
    private static final Insets spaceInsets =
            new Insets(10, 10, 10, 10);

    private static final NumberFormat nf =
            NumberFormat.getInstance();

    private Board board;
    private HumanUser user;

    private JPanel panel;

    private JTextField highScoreField;
    private JTextField highCellField;
    private JTextField currentScoreField;
    private JTextField currentCellField;

    public ScorePanel(Board board, HumanUser user) {
        this.board = board;
        this.user = user;
        createPartControl();
//        updatePartControl();
    }

    private void createPartControl() {
        panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        int gridy = 0;

        JLabel currentScoreLabel = new JLabel("Current Score:");
        addComponent(panel, currentScoreLabel, 0, gridy, 1, 1,
                regularInsets, GridBagConstraints.LINE_START,
                GridBagConstraints.HORIZONTAL);

        currentScoreField = new JTextField(6);
        currentScoreField.setEditable(false);
        currentScoreField.setHorizontalAlignment(JTextField.RIGHT);
        addComponent(panel, currentScoreField, 1, gridy++, 1, 1,
                regularInsets, GridBagConstraints.LINE_START,
                GridBagConstraints.HORIZONTAL);
    }

    private void addComponent(Container container, Component component,
                              int gridx, int gridy, int gridwidth, int gridheight,
                              Insets insets, int anchor, int fill) {
        GridBagConstraints gbc = new GridBagConstraints(gridx, gridy,
                gridwidth, gridheight, 1.0D, 1.0D, anchor, fill,
                insets, 0, 0);
        container.add(component, gbc);
    }

//    public void updatePartControl() {
//        highScoreField.setText(nf.format(board.getHighScore()));
//        highCellField.setText(nf.format(board.getHighCell()));
//        currentScoreField.setText(nf.format(board.getCurrentScore()));
//        currentCellField.setText(nf.format(board.getCurrentCell()));
//    }

    public JPanel getPanel() {
        return panel;
    }
}
