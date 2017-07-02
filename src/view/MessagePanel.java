package src.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MessagePanel extends JPanel {

    public MessagePanel(KolorLinesFrame frame) {
        this.frame = frame;

        this.setPreferredSize(new Dimension(this.getPreferredSize().width, 40));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(Color.BLACK);

        messageLabel.setFont(font);
        warningLabel.setFont(font);
        successLabel.setFont(font);

        messageLabel.setForeground(Color.WHITE);
        warningLabel.setForeground(Color.RED);
        successLabel.setForeground(Color.GREEN);

        this.add(this.messageLabel);
        this.add(this.warningLabel);
        this.add(this.successLabel);
    }

    private void draw(){
        this.messageLabel.setText(this.message);
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        draw();
    }

    public void popWarning(String warning){  // Modify to popNotification

        warningLabel.setVisible(true);
        warningLabel.setText(warning);

        ActionListener displayWarning = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Clear text or whatever you want
                warningLabel.setVisible(false);
            }
        };

        new Timer(STICKY_DELAY, displayWarning).start();
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    private String message;
    private String warning;
    private String success;
    private JLabel messageLabel = new JLabel(this.message);

    private static int STICKY_DELAY = 1000;
    private JLabel warningLabel = new JLabel(this.warning);
    private JLabel successLabel = new JLabel(this.success);

    private static final Font font = new Font("Monospaced", Font.BOLD, 12);

    private KolorLinesFrame frame;
}
