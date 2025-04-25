package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Modele.UserModel;
import Vue.LoginView;
import Vue.SuccessView;

public class LoginController {
    private UserModel model;
    private LoginView view;

    public LoginController(UserModel model, LoginView view) {
        this.model = model;
        this.view = view;

        this.view.addLoginListener(new LoginListener());
    }

    class LoginListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String username = view.getUsername();
            String password = view.getPassword();

            if (model.validateUser(username, password)) {
                view.dispose(); // Ferme la fenêtre de login
                SuccessView successView = new SuccessView();
                successView.setVisible(true);
            } else {
                view.displayErrorMessage("Identifiants invalides !");
            }
        }
    }
}
