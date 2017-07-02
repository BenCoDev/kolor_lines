package controller;

import model.HumanUser;
import model.SystemUser;
import view.KolorLinesFrame;

import javax.swing.*;

public class KolorLinesRunnable implements Runnable {

    @Override
    public void run(){
        HumanUser user = new HumanUser();
        SystemUser systemUser = new SystemUser();
        new KolorLinesFrame(user, systemUser);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new KolorLinesRunnable());
    }
}
