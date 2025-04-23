package Controleur;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Interface Shopping");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(800, 600));
        frame.setLayout(new BorderLayout());

        Connexion menuBar = new Connexion(frame);
        frame.setJMenuBar(menuBar);

        frame.pack();
        frame.setVisible(true);
    }
}
