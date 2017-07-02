package src.view;

import src.model.Board;
import src.model.HumanUser;

import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;

public class ScorePanel extends JPanel {

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

    private void draw(){

        this.currentScoreField.setText(String.valueOf(this.user.getScore()));

    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        draw();
    }

    private HumanUser user;

    private JTextField currentScoreField;
}
