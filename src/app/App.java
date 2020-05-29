package app;

import javax.swing.*;

public class App {
    public static void main(String[] args) throws Exception {
        
        Principal frame = new Principal();
        frame.setSize(800,600);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setVisible(true);
    }
}