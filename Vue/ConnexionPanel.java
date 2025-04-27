package Vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import Controleur.ConnexionController;

public class ConnexionPanel extends JPanel {
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private ConnexionController controller;

    public ConnexionPanel(CardLayout cardLayout, JPanel contenuCentral, Connexion fenetreConnexion) {
        setLayout(new BorderLayout());

        // Initialise le contrôleur
        controller = new ConnexionController(this, cardLayout, contenuCentral, fenetreConnexion);

        // Bande bleue en haut
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(Color.decode("#4682A9"));
        headerPanel.setPreferredSize(new Dimension(0, 80));
        headerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20));

        JLabel titre = new JLabel("Page de connexion");
        titre.setFont(new Font("SansSerif", Font.BOLD, 20));
        titre.setForeground(Color.WHITE);
        headerPanel.add(titre);

        add(headerPanel, BorderLayout.NORTH);

        // Panel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setLayout(null);

        JLabel logo = chargerLogo();
        logo.setBounds(20, 20, 100, 60);
        mainPanel.add(logo);

        emailField = new JTextField("Identifiant");
        emailField.setBounds(315, 102, 160, 25);
        emailField.setForeground(Color.GRAY);
        ajouterFocusListener(emailField, "Identifiant");
        mainPanel.add(emailField);

        passwordField = new JPasswordField("Mot de passe");
        passwordField.setBounds(315, 152, 160, 25);
        passwordField.setEchoChar((char) 0);
        passwordField.setForeground(Color.GRAY);
        ajouterFocusListener(passwordField, "Mot de passe");
        mainPanel.add(passwordField);

        loginButton = new JButton("Connexion");
        loginButton.setBounds(315, 200, 160, 30);
        styliserBouton(loginButton);
        mainPanel.add(loginButton);

        add(mainPanel, BorderLayout.CENTER);

        // Délègue l'action au contrôleur
        loginButton.addActionListener(e -> controller.attemptLogin());
    }

    private JLabel chargerLogo() {
        ImageIcon icon = new ImageIcon("Logo Vulpixia.jpg");
        Image img = icon.getImage().getScaledInstance(100, 60, Image.SCALE_SMOOTH);
        return new JLabel(new ImageIcon(img));
    }

    private void styliserBouton(JButton bouton) {
        bouton.setBackground(Color.decode("#4682A9"));
        bouton.setForeground(Color.WHITE);
        bouton.setFocusPainted(false);
        bouton.setFont(bouton.getFont().deriveFont(Font.BOLD, 14f));
    }

    private void ajouterFocusListener(JTextField champ, String texteParDefaut) {
        champ.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (champ.getText().equals(texteParDefaut)) {
                    champ.setText("");
                    champ.setForeground(Color.BLACK);
                    if (champ instanceof JPasswordField) {
                        ((JPasswordField) champ).setEchoChar('•');
                    }
                }
            }

            public void focusLost(FocusEvent e) {
                if (champ.getText().isEmpty()) {
                    champ.setText(texteParDefaut);
                    champ.setForeground(Color.GRAY);
                    if (champ instanceof JPasswordField) {
                        ((JPasswordField) champ).setEchoChar((char) 0);
                    }
                }
            }
        });
    }

    // --- Getters pour que le contrôleur puisse accéder aux champs ---
    public String getEmail() {
        return emailField.getText();
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }
}
