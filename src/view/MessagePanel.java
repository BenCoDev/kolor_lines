package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MessagePanel extends JPanel {

    /**
     * Represents a MessagePanel responsible for displaying messages to the user
     * placed into the frame
     *
     * @param frame KolorLinesFrame
     */
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

    /**
     * Pop a notification on the messagePanel for a short amount of time (STICKY_DELAY)
     * @param notification
     * @param type
     */
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

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        draw();
    }

    private void draw(){
        this.messageLabel.setText(this.message);
    }

    private String message = DEFAULT_MESSAGE;
    private static int STICKY_DELAY = 4000;  // Delay of a notification
    private JLabel messageLabel = new JLabel(this.message);
    private java.net.URL imgURL = getClass().getResource("assets/loader.gif");  // Image of the loader icon
    private ImageIcon loaderIcon = new ImageIcon(imgURL);
    private JLabel loaderLabel = new JLabel("Loading ", loaderIcon, JLabel.LEFT);  // Label of the loader
    private JLabel notificationLabel = new JLabel();
    private static final Font font = new Font("Monospaced", Font.BOLD, 12);
    private KolorLinesFrame frame;
    public static enum NotificationType {  // Type of notification
        WARNING,
        SUCCESS
    };
    public static String DEFAULT_MESSAGE = "<html>" +
            "Align 5 squares to increment your score" +
            "<br>Get the highest score" +
            "<br>Click on <em>Start Game</em> to <font color='green'>BEGIN</font>" +
            "</html>";
}
