package Vue;

import javax.swing.*;
import java.awt.*;
import Modele.Utilisateur;

public class ComptePanel extends JPanel {
    private CardLayout cardLayout;
    private JPanel contenuCentral;
    private Connexion fenetreConnexion;

    public ComptePanel(CardLayout cardLayout, JPanel contenuCentral, Connexion fenetreConnexion) {
        this.cardLayout = cardLayout;
        this.contenuCentral = contenuCentral;
        this.fenetreConnexion = fenetreConnexion;

        affichageInfo();
    }

    private void affichageInfo() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.decode("#87bcd6"));

        JLabel logo = chargerLogo();
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(logo);
        add(Box.createRigidArea(new Dimension(0, 20)));

        //Prend les informations de l'utilisateur
        //S'il n'est pas connecté on affiche un message, sinon on affiche les informations
        Utilisateur utilisateur = Utilisateur.getUtilisateurConnecte();
        if (utilisateur != null) {
            ajouterInfosUtilisateur(utilisateur);
            ajouterBoutonDeconnexion();
        } else {
            JLabel nonConnecte = new JLabel("Vous n'êtes pas connecté");
            nonConnecte.setAlignmentX(Component.CENTER_ALIGNMENT);
            nonConnecte.setFont(new Font("SansSerif", Font.PLAIN, 16));
            add(nonConnecte);
        }
    }

    private void ajouterInfosUtilisateur(Utilisateur utilisateur) {
        String[] infos = {
                "Nom: " + utilisateur.getNom(),
                "Email: " + utilisateur.getMail(),
                "Mot de passe: " + utilisateur.getMot_de_passe(),
                "Sexe: " + (utilisateur.getSexe() ? "Homme" : "Femme"),
                "Admin: " + (utilisateur.isAdmin() ? "Oui" : "Non")
        };

        for (String info : infos) {
            JLabel label = new JLabel(info);
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            label.setFont(new Font("SansSerif", Font.PLAIN, 16));
            add(Box.createRigidArea(new Dimension(0, 10)));
            add(label);
        }
    }

    private void ajouterBoutonDeconnexion() {
        JButton boutonDeconnexion = new JButton("Déconnexion");
        boutonDeconnexion.setAlignmentX(Component.CENTER_ALIGNMENT);
        styliserBouton(boutonDeconnexion);

        add(Box.createRigidArea(new Dimension(0, 30)));
        add(boutonDeconnexion);

        boutonDeconnexion.addActionListener(e -> {
            Utilisateur.setUtilisateurConnecte(null);
            JOptionPane.showMessageDialog(this, "Vous êtes déconnecté !");
            fenetreConnexion.mettreAJourMenuDeconnexion(); // 🔥 Mettre à jour la barre de menu
            cardLayout.show(contenuCentral, "Connexion"); // 🔥 Revenir à la page de connexion
        });
    }

    private void styliserBouton(JButton bouton) {
        bouton.setBackground(Color.decode("#4682A9"));
        bouton.setForeground(Color.WHITE);
        bouton.setFocusPainted(false);
    }

    private JLabel chargerLogo() {
        ImageIcon icon = new ImageIcon("Logo Vulpixia.jpg");
        Image img = icon.getImage().getScaledInstance(100, 60, Image.SCALE_SMOOTH);
        return new JLabel(new ImageIcon(img));
    }
}
