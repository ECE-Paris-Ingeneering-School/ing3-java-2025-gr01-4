/*package Controleur;

import Vue.Connexion;
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
}*/

package Controleur;

import Modele.UserModel;
import Vue.LoginView;

public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            UserModel model = new UserModel();
            LoginView view = new LoginView();
            new LoginController(model, view);

            view.setVisible(true);
        });
    }
}

