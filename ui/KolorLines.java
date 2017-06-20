//package ui;
//
//import ui.model.KolorLinesModel;
//import ui.view.KolorLinesFrame;
//
//import java.awt.*;
//import javax.swing.*;
//import javax.swing.table.AbstractTableModel;
//import javax.swing.table.TableModel;
//
//public class KolorLines implements Runnable {
//
//    @Override
//    public void run() {
//        new KolorLinesFrame(new KolorLinesModel());
//    }
//
//    public void show() {
////        JFrame frame = new JFrame("Kolor Lines");
////        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
////
////        //Créer la JMenuBar
////        JMenuBar greenMenuBar = new JMenuBar();
////        greenMenuBar.setOpaque(true);
////        greenMenuBar.setBackground(new src.Color(0, 200, 0));
////        greenMenuBar.setPreferredSize(new Dimension(200, 20));
////
////        //Créer le MessageCenter
////        JLabel messageCenter = new JLabel();
////        messageCenter.setOpaque(true);
////        messageCenter.setBackground(new src.Color(250, 250, 0));
////        messageCenter.setPreferredSize(new Dimension(200, 45));
////
////        //Créer le src.Board
////        JTable board = new JTable();
////
////
////
////        TableModel dataModel = new AbstractTableModel() {
////            public int getColumnCount() { return 10; }
//////            public void set
////            public int getRowCount() { return 10;}
////            public Object getValueAt(int row, int col) { return new Integer(row*col); }
////        };
////
////        JTable table = new JTable(dataModel);
////        JScrollPane scrollpane = new JScrollPane(table);
//////        Jgrid
////
//////        board.setOpaque(true);
//////        board.setBackground(new src.Color(21, 250, 85));
//////        board.setPreferredSize(new Dimension(200, 90));
////
//////        JLabel firstSquare = new JLabel();
//////        firstSquare.setBackground(new src.Color(0, 0, 0));
//////        firstSquare.setOpaque(true);
//////        firstSquare.setPreferredSize(new Dimension(20, 20));
//////        board.add(firstSquare);
//////
//////        JLabel secondSquare = new JLabel();
//////        secondSquare.setBackground(new src.Color(0, 0, 0));
//////        secondSquare.setPreferredSize(new Dimension(10, 10));
//////        board.add(secondSquare);
////
////        //mettre la JmenuBar et position le JLabel
////        frame.setJMenuBar(greenMenuBar);
////        frame.getContentPane().add(messageCenter, BorderLayout.NORTH);
//////        frame.getContentPane().add(board, BorderLayout.CENTER);
////        frame.getContentPane().add(scrollpane, BorderLayout.CENTER);
////
////        //afficher...
////        frame.pack();
////        frame.setVisible(true);
//    }
//
//    public static void main(final String[] args) {
//        // Schedule a job for the event-dispatching thread:
//        // creating and showing this application's GUI.
//        SwingUtilities.invokeLater(new KolorLines());
//    }
//}