package Vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LoginView extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel messageLabel;

    public LoginView() {
        setTitle("Connexion");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(4, 1));
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        loginButton = new JButton("Se connecter");
        messageLabel = new JLabel("", SwingConstants.CENTER);

        panel.add(new JLabel("Nom d'utilisateur:"));
        panel.add(usernameField);
        panel.add(new JLabel("Mot de passe:"));
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(messageLabel);

        add(panel);
    }

    public String getUsername() {
        return usernameField.getText();
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }

    public void addLoginListener(ActionListener listener) {
        loginButton.addActionListener(listener);
    }

    public void displayErrorMessage(String message) {
        messageLabel.setText(message);
        messageLabel.setForeground(Color.RED);
    }
}
