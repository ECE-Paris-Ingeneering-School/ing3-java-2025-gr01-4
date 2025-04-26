package Vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import Modele.Utilisateur;

public class ComptePanel extends JPanel {
    private CardLayout cardLayout;
    private JPanel contenuCentral;
    private Connexion fenetreConnexion;
    private Component Nom;

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

        Utilisateur utilisateur = Utilisateur.getUtilisateurConnecte();
        if (utilisateur != null) {
            ajouterInfosUtilisateur(utilisateur);

            add(Box.createRigidArea(new Dimension(0, 30)));

            ajouterBoutonModifier();

            add(Box.createRigidArea(new Dimension(0, 30)));

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

    private void ajouterBoutonModifier() {
        JButton boutonModifier = new JButton("Modifier mes informations");
        styliserBouton(boutonModifier);
        boutonModifier.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(boutonModifier);

        //On rafraichit la page pour avoir la page de modification
        boutonModifier.addActionListener(e -> {
            removeAll(); // Enlève tout
            revalidate();
            repaint();
            affichageInfoAvecModification();
        });
    }

    private void affichageInfoAvecModification() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.decode("#87bcd6"));

        JLabel logo = chargerLogo();
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(logo);
        add(Box.createRigidArea(new Dimension(0, 20)));

        Utilisateur utilisateur = Utilisateur.getUtilisateurConnecte();
        if (utilisateur != null) {
            ajouterZoneModification();
            add(Box.createRigidArea(new Dimension(0, 20)));
            ajouterBoutonAnnuler();
            add(Box.createRigidArea(new Dimension(0, 20)));
            ajouterBoutonDeconnexion();
        } else {
            JLabel nonConnecte = new JLabel("Vous n'êtes pas connecté");
            nonConnecte.setAlignmentX(Component.CENTER_ALIGNMENT);
            nonConnecte.setFont(new Font("SansSerif", Font.PLAIN, 16));
            add(nonConnecte);
        }
    }
    private void ajouterBoutonAnnuler() {
        JButton boutonAnnuler = new JButton("Annuler");
        styliserBouton(boutonAnnuler);
        boutonAnnuler.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(boutonAnnuler);
        //Rafraichit la page pour revenir à la page d'information de compte
        boutonAnnuler.addActionListener(e -> {
            removeAll();
            revalidate();
            repaint();
            affichageInfo(); // Revenir à l'affichage des infos
        });
    }

    private void ajouterZoneModification() {
        JPanel panelModifications = new JPanel();
        panelModifications.setLayout(new BoxLayout(panelModifications, BoxLayout.Y_AXIS));
        panelModifications.setOpaque(false);
        panelModifications.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel titre = new JLabel("Modifier mes informations");
        titre.setFont(new Font("SansSerif", Font.BOLD, 16));
        titre.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelModifications.add(titre);

        Utilisateur utilisateur = Utilisateur.getUtilisateurConnecte();

        JTextField nom = new JTextField(utilisateur.getNom());
        ajouterFocusListener(nom, "Nom complet");

        JTextField email = new JTextField(utilisateur.getMail());
        ajouterFocusListener(email, "Email");

        JPasswordField MotDePasse = new JPasswordField(utilisateur.getMot_de_passe());
        ajouterFocusListener(MotDePasse, "Nouveau MotDePasse");

        Dimension fieldSize = new Dimension(200, 25);
        nom.setMaximumSize(fieldSize);
        email.setMaximumSize(fieldSize);
        MotDePasse.setMaximumSize(fieldSize);

        panelModifications.add(Box.createRigidArea(new Dimension(0, 10)));
        panelModifications.add(nom);
        panelModifications.add(Box.createRigidArea(new Dimension(0, 10)));
        panelModifications.add(email);
        panelModifications.add(Box.createRigidArea(new Dimension(0, 10)));
        panelModifications.add(MotDePasse);
        panelModifications.add(Box.createRigidArea(new Dimension(0, 20)));

        JButton boutonValider = new JButton("Valider les modifications");
        styliserBouton(boutonValider);
        boutonValider.setAlignmentX(Component.CENTER_ALIGNMENT);

        panelModifications.add(boutonValider);

        add(panelModifications);

        //Validation des changements
        boutonValider.addActionListener(e -> {
            String nouveauNom = nom.getText();
            String nouvelEmail = email.getText();
            String nouveauMotDePasse = new String(MotDePasse.getPassword());

            if (nouveauNom.isEmpty() || nouvelEmail.isEmpty() || nouveauMotDePasse.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Tous les champs doivent être remplis.", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }

            utilisateur.setNom(nouveauNom);
            utilisateur.setMail(nouvelEmail);
            utilisateur.setMot_De_Passe(nouveauMotDePasse);

            // Mise à jour en BDD
            DAO.UtilisateurDAO utilisateurDAO = new DAO.UtilisateurDAOImpl();
            utilisateurDAO.modifier(utilisateur);

            JOptionPane.showMessageDialog(this, "Informations mises à jour avec succès !");
        });
    }

    //le bouton déconnexion est présent sur les deux pages
    private void ajouterBoutonDeconnexion() {
        JButton boutonDeconnexion = new JButton("Déconnexion");
        boutonDeconnexion.setAlignmentX(Component.CENTER_ALIGNMENT);
        styliserBouton(boutonDeconnexion);

        add(Box.createRigidArea(new Dimension(0, 20)));
        add(boutonDeconnexion);

        boutonDeconnexion.addActionListener(e -> {
            Utilisateur.setUtilisateurConnecte(null);
            JOptionPane.showMessageDialog(this, "Vous êtes déconnecté !");
            fenetreConnexion.mettreAJourMenuDeconnexion();
            cardLayout.show(contenuCentral, "Connexion");
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

    private void ajouterFocusListener(JTextField champ, String texteParDefaut) {
        champ.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (champ.getText().equals(texteParDefaut)) {
                    champ.setText("");
                    champ.setForeground(Color.BLACK);
                }
            }
        });
    }
}
