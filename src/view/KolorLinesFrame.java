package src.view;

import src.model.Board;
import src.model.HumanUser;
import src.model.Position;
import src.model.SystemUser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class KolorLinesFrame extends JFrame {

    public KolorLinesFrame(Board board, HumanUser user, SystemUser systemUser) {
        this.board = board;
        this.user = user;
        this.systemUser = systemUser;

        this.setTitle("Kolor Lines");
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event){
                exitProcedure();
            }
        });

        this.createMainFrame();
    }

    private void createMainFrame(){
        boardPanel = new BoardPanel(this, this.board);
        messagePanel = new MessagePanel(this);
        scorePanel = new ScorePanel(this.user);
        controlPanel = new ControlPanel(this, this.board);

        JPanel sidePanel = new JPanel();
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.PAGE_AXIS));
        sidePanel.add(this.scorePanel);
        sidePanel.add(this.controlPanel);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        mainPanel.add(messagePanel, BorderLayout.NORTH);
        mainPanel.add(boardPanel, BorderLayout.CENTER);
        mainPanel.add(sidePanel, BorderLayout.EAST);

        this.updateMessagePanel("Start playing");

        this.add(mainPanel);
        this.setLocationByPlatform(true);
        this.pack();
        this.setVisible(true);
    }

    public void repaintBoardPanel() {
        this.boardPanel.repaint();
    }

    public void updateBoardPanel(Position[] positions){
        this.boardPanel.update(positions);
    }


    public void popWarning(String warning){
        this.messagePanel.popWarning(warning);
        this.messagePanel.repaint();
    }

    public void updateMessagePanel(String message){
        this.messagePanel.setMessage(message);
        this.messagePanel.repaint();
    }

    public void exitProcedure(){
        this.dispose();
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

    private Board board;
    private HumanUser user;
    private SystemUser systemUser;
}
