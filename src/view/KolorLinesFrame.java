package src.view;

import src.model.Board;
import src.model.HumanUser;
import src.model.Position;
import src.model.SystemUser;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class KolorLinesFrame {

    public KolorLinesFrame(Board board, HumanUser user, SystemUser systemUser) {
        this.board = board;
        this.user = user;
        this.systemUser = systemUser;

        this.createMainFrame();
    }

    private void createMainFrame(){
        boardPanel = new BoardPanel(this, this.board);
        messagePanel = new MessagePanel(this);
        scorePanel = new ScorePanel(this.board, this.user);
        controlPanel = new ControlPanel(this, this.board);

        frame = new JFrame();
        frame.setTitle("Kolor Lines");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event){
                exitProcedure();
            }
        });

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));

        mainPanel.add(messagePanel);
        mainPanel.add(boardPanel);
        mainPanel.add(createSidePanel());

        frame.add(mainPanel);
        frame.setLocationByPlatform(true);
        frame.pack();
        frame.setVisible(true);
    }


    private JPanel createSidePanel(){
        JPanel sidePanel = new JPanel();
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.PAGE_AXIS));
        sidePanel.add(scorePanel.getPanel());
        sidePanel.add(Box.createVerticalStrut(30));
        sidePanel.add(controlPanel.getPanel());
        sidePanel.add(Box.createVerticalStrut(30));
        return sidePanel;
    }

    public void repaintBoardPanel() {
        this.boardPanel.repaint();
    }

    public void updateBoardPanel(Position[] positions){
        this.boardPanel.update(positions);
    }

    public void updateMessagePanel(String message){
        this.messagePanel.setMessage(message);
        this.messagePanel.repaint();
    }

    public void exitProcedure(){
        this.frame.dispose();
        System.exit(0);
    }

    public BoardPanel getBoardPanel() {
        return boardPanel;
    }

    public Board getBoard() {
        return board;
    }

    public HumanUser getUser() {
        return user;
    }

    public SystemUser getSystemUser() {
        return systemUser;
    }

    private MessagePanel messagePanel;
    private BoardPanel boardPanel;
    private ScorePanel scorePanel;
    private ControlPanel controlPanel;
    private JFrame frame;

    private Board board;
    private HumanUser user;
    private SystemUser systemUser;
}
