package Vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import DAO.UtilisateurDAO;
import DAO.UtilisateurDAOImpl;
import Modele.Utilisateur;

public class InscriptionPanel extends JPanel {
    public InscriptionPanel(CardLayout cardLayout, JPanel cardPanel) {
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
        motDePasse.setEchoChar('*');
        ajouterFocusListener(motDePasse, "Mot de passe");
        add(motDePasse);

        JButton boutonInscription = new JButton("S'inscrire");
        boutonInscription.setBounds(385, 230, 160, 30);
        styliserBouton(boutonInscription);
        add(boutonInscription);

        // Clic sur le bouton "S'inscrire"
        boutonInscription.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomComplet = nom.getText();
                String adresseMail = email.getText();
                String motDePasseTexte = new String(motDePasse.getPassword());

                if (nomComplet.isEmpty() || adresseMail.isEmpty() || motDePasseTexte.isEmpty()
                        || nomComplet.equals("Nom complet")
                        || adresseMail.equals("Email")
                        || motDePasseTexte.equals("Mot de passe")) {
                    JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Créer un nouvel utilisateur avec ton constructeur
                Utilisateur nouvelUtilisateur = new Utilisateur(
                        0,                    // id (0 car il sera généré automatiquement par la BDD)
                        nomComplet,
                        motDePasseTexte,
                        adresseMail,
                        true,                 // sexe : ici je mets true par défaut (on pourra demander à l'utilisateur plus tard)
                        false                 // admin : par défaut un inscrit n'est pas admin
                );

                UtilisateurDAO utilisateurDAO = new UtilisateurDAOImpl();
                boolean success = utilisateurDAO.save(nouvelUtilisateur);

                if (success) {
                    JOptionPane.showMessageDialog(null, "Inscription réussie !");

                    // Redirection vers la fenêtre de connexion (CardLayout)
                    cardLayout.show(cardPanel, "Connexion");
                } else {
                    JOptionPane.showMessageDialog(null, "Erreur lors de l'inscription.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
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
