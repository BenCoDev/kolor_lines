package src.view;

import javax.swing.*;
import java.awt.*;

public class MessagePanel extends JPanel {

    public MessagePanel(KolorLinesFrame frame) {
        this.frame = frame;
        this.setPreferredSize(new Dimension(40, 40));
        this.add(this.messageLabel);
    }

    private void draw(){
        this.messageLabel.setText(this.message);
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        draw();
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    private String message;
    private JLabel messageLabel = new JLabel(this.message);

    private KolorLinesFrame frame;
}
