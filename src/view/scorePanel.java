package view;

import model.HumanUser;

import javax.swing.*;
import java.awt.*;

public class ScorePanel extends JPanel {

    /**
     * Represents a ScorePanel responsible for displaying the score of a user to the user
     * given this user
     * @param user - HumanUser
     */
    public ScorePanel(HumanUser user) {
        this.user = user;

        this.setLayout(new GridBagLayout());

        JLabel currentScoreLabel = new JLabel("Current Score:");
        this.add(currentScoreLabel);

        this.currentScoreField = new JTextField(6);
        this.currentScoreField.setEditable(false);
        this.currentScoreField.setHorizontalAlignment(JTextField.RIGHT);
        this.add(this.currentScoreField);

    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        draw();
    }

    private void draw(){
        this.currentScoreField.setText(String.valueOf(this.user.getScore()));
    }

    private HumanUser user;
    private JTextField currentScoreField;
}
