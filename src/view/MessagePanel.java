package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MessagePanel extends JPanel {

    public MessagePanel(KolorLinesFrame frame) {
        this.frame = frame;

        this.setPreferredSize(new Dimension(this.getPreferredSize().width, 80));
        this.setLayout(new BorderLayout());
        this.setBackground(Color.BLACK);

        messageLabel.setFont(font);
        loaderLabel.setFont(font);
        notificationLabel.setFont(font);

        messageLabel.setForeground(Color.WHITE);
        loaderLabel.setForeground(Color.WHITE);

        loaderLabel.setVisible(false);

        JPanel notificationPanel = new JPanel();
        notificationPanel.setPreferredSize(new Dimension(notificationPanel.getPreferredSize().width, 40));
        notificationPanel.setBackground(Color.BLACK);
        notificationPanel.add(this.notificationLabel);

        this.add(this.messageLabel, BorderLayout.CENTER);
        this.add(notificationPanel, BorderLayout.SOUTH);
        this.add(loaderLabel, BorderLayout.EAST);
    }

    private void draw(){
        this.messageLabel.setText(this.message);
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        draw();
    }

    public void popNotification(String notification, NotificationType type){  // Modify to popNotification

        this.notificationLabel.setVisible(true);
        this.notificationLabel.setText(notification);
        this.notificationLabel.setFont(font);

        switch (type){
            case WARNING: {
                this.notificationLabel.setForeground(Color.RED);
                break;
            }
            case SUCCESS: {
                this.notificationLabel.setForeground(Color.GREEN);
                break;
            }
            default: {
                this.notificationLabel.setForeground(Color.WHITE);
                break;
            }
        }

        ActionListener displayNotification = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Clear text or whatever you want
                notificationLabel.setVisible(false);
            }
        };

        Timer displayTimer = new Timer(STICKY_DELAY, displayNotification);
        displayTimer.setRepeats(false);
        displayTimer.start();
    }

    public void showLoading(){
        this.loaderLabel.setVisible(true);
    }

    public void hideLoading(){
        this.loaderLabel.setVisible(false);
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    private String message = DEFAULT_MESSAGE;

    private static int STICKY_DELAY = 4000;

    private JLabel messageLabel = new JLabel(this.message);

    private java.net.URL imgURL = getClass().getResource("assets/loader.gif");
    private ImageIcon loaderIcon = new ImageIcon(imgURL);
    private JLabel loaderLabel = new JLabel("Loading ", loaderIcon, JLabel.LEFT);

    private JLabel notificationLabel = new JLabel();

    private static final Font font = new Font("Monospaced", Font.BOLD, 12);

    private KolorLinesFrame frame;
    public static enum NotificationType {
        WARNING,
        SUCCESS
    };

    public static String DEFAULT_MESSAGE = "Start playing";
}
