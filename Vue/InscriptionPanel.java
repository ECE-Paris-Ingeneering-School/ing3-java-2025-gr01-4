package Vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class InscriptionPanel extends JPanel {
    public InscriptionPanel() {
        setLayout(null);
        setBackground(Color.decode("#87bcd6"));

        JLabel logo = chargerLogo();
        add(logo);

        JLabel titre = new JLabel("Page d'inscription");
        titre.setBounds(350, 20, 250, 33);
        titre.setFont(new Font("SansSerif", Font.BOLD, 20));
        titre.setForeground(Color.BLACK);
        add(titre);

        JTextField nom = new JTextField("Nom complet");
        nom.setBounds(392, 102, 160, 25);
        nom.setForeground(Color.GRAY);
        ajouterFocusListener(nom, "Nom complet");
        add(nom);

        JTextField email = new JTextField("Email");
        email.setBounds(392, 142, 160, 25);
        email.setForeground(Color.GRAY);
        ajouterFocusListener(email, "Email");
        add(email);

        JPasswordField motDePasse = new JPasswordField("Mot de passe");
        motDePasse.setBounds(392, 182, 160, 25);
        motDePasse.setForeground(Color.GRAY);
        motDePasse.setEchoChar((char) 0);
        ajouterFocusListener(motDePasse, "Mot de passe");
        add(motDePasse);

        JButton boutonInscription = new JButton("S'inscrire");
        boutonInscription.setBounds(385, 230, 160, 30);
        styliserBouton(boutonInscription);
        add(boutonInscription);
    }

    // Les méthodes utilitaires (chargerLogo, styliserBouton, ajouterFocusListener)
    // sont identiques à celles de ConnexionPanel et peuvent être déplacées
    // dans une classe utilitaire commune si nécessaire
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