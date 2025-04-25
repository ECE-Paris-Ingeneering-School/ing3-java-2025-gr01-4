package Vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ConnexionPanel extends JPanel {
    public ConnexionPanel() {
        setLayout(null);
        setBackground(Color.decode("#87bcd6"));

        JLabel logo = chargerLogo();
        add(logo);

        JLabel titre = new JLabel("Page de connexion");
        titre.setBounds(350, 20, 210, 33);
        titre.setFont(new Font("SansSerif", Font.BOLD, 20));
        titre.setForeground(Color.BLACK);
        add(titre);

        JTextField email = new JTextField("Identifiant");
        email.setBounds(392, 102, 160, 25);
        email.setForeground(Color.GRAY);
        ajouterFocusListener(email, "Identifiant");
        add(email);

        JPasswordField motDePasse = new JPasswordField("Mot de passe");
        motDePasse.setBounds(390, 152, 160, 25);
        motDePasse.setEchoChar((char) 0);
        motDePasse.setForeground(Color.GRAY);
        ajouterFocusListener(motDePasse, "Mot de passe");
        add(motDePasse);

        JButton boutonConnexion = new JButton("Connexion");
        boutonConnexion.setBounds(385, 200, 160, 30);
        styliserBouton(boutonConnexion);
        add(boutonConnexion);
    }

    private JLabel chargerLogo() {
        ImageIcon icon = new ImageIcon("src/Logo Vulpixia.png");
        Image img = icon.getImage().getScaledInstance(100, 60, Image.SCALE_SMOOTH);
        JLabel label = new JLabel(new ImageIcon(img));
        label.setBounds(20, 20, 100, 60);
        return label;
    }

    private void styliserBouton(JButton bouton) {
        bouton.setBackground(Color.decode("#4682A9"));
        bouton.setForeground(Color.WHITE);
        bouton.setFocusPainted(false);
    }

    private void ajouterFocusListener(JTextField champ, String texteParDefaut) {
        champ.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (champ.getText().equals(texteParDefaut)) {
                    champ.setText("");
                    champ.setForeground(Color.BLACK);
                }
            }

            public void focusLost(FocusEvent e) {
                if (champ.getText().isEmpty()) {
                    champ.setText(texteParDefaut);
                    champ.setForeground(Color.GRAY);
                }
            }
        });
    }
}