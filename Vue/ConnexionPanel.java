package Vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import DAO.UtilisateurDAO;
import DAO.UtilisateurDAOImpl;
import Modele.Utilisateur;

public class ConnexionPanel extends JPanel {
    private CardLayout cardLayout;
    private JPanel contenuCentral;
    private Connexion fenetreConnexion;


    public ConnexionPanel(CardLayout cardLayout, JPanel contenuCentral, Connexion fenetreConnexion) {
        this.cardLayout = cardLayout;
        this.contenuCentral = contenuCentral;
        this.fenetreConnexion = fenetreConnexion;

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

        // 👉 Code pour gérer le clic sur "Connexion"
        boutonConnexion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String identifiant = email.getText();
                String mdp = new String(motDePasse.getPassword());

                UtilisateurDAO utilisateurDAO = new UtilisateurDAOImpl();
                List<Utilisateur> utilisateurs = utilisateurDAO.getAll();

                for (Utilisateur u : utilisateurs) {
                    if (u.getMail().equals(identifiant) && u.getMot_de_passe().equals(mdp)) {
                        JOptionPane.showMessageDialog(null, "Connexion réussie !");
                        fenetreConnexion.setUtilisateurConnecte(u);
                        fenetreConnexion.mettreAJourMenu();
                        cardLayout.show(contenuCentral, "VentesFlash");
                        return;
                    }
                }

                JOptionPane.showMessageDialog(null, "Identifiant ou mot de passe incorrect.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });
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
