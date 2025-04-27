package Vue;

import Controleur.CompteController;
import Modele.Utilisateur;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class ComptePanel extends JPanel {
    private CardLayout cardLayout;
    private JPanel contenuCentral;
    private Connexion fenetreConnexion;
    private CompteController compteController; // Référence au contrôleur

    public ComptePanel(CardLayout cardLayout, JPanel contenuCentral, Connexion fenetreConnexion) {
        this.cardLayout = cardLayout;
        this.contenuCentral = contenuCentral;
        this.fenetreConnexion = fenetreConnexion;

        compteController = new CompteController(this); // Initialisation du contrôleur

        setLayout(new BorderLayout()); // Utilisation de BorderLayout
        setBackground(Color.WHITE);

        affichageInfo();
    }

    public CardLayout getCardLayout() {
        return cardLayout;
    }

    public JPanel getContenuCentral() {
        return contenuCentral;
    }

    public Connexion getFenetreConnexion() {
        return fenetreConnexion;
    }

    private void affichageInfo() {
        removeAll();
        setBackground(Color.WHITE);

        // Bande bleue en haut
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(Color.decode("#4682A9"));
        headerPanel.setPreferredSize(new Dimension(0, 80));
        headerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20));

        JLabel titre = new JLabel("Mon Compte");
        titre.setFont(new Font("SansSerif", Font.BOLD, 20));
        titre.setForeground(Color.WHITE);
        headerPanel.add(titre);

        add(headerPanel, BorderLayout.NORTH);

        // Panel principal en blanc
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        Utilisateur utilisateur = Utilisateur.getUtilisateurConnecte();
        if (utilisateur != null) {
            ajouterInfosUtilisateur(mainPanel, utilisateur);

            mainPanel.add(Box.createRigidArea(new Dimension(0, 30)));

            ajouterBoutonModifier(mainPanel);

            mainPanel.add(Box.createRigidArea(new Dimension(0, 30)));

            ajouterBoutonDeconnexion(mainPanel);

        } else {
            JLabel nonConnecte = new JLabel("Vous n'êtes pas connecté");
            nonConnecte.setAlignmentX(Component.CENTER_ALIGNMENT);
            nonConnecte.setFont(new Font("SansSerif", Font.PLAIN, 16));
            mainPanel.add(nonConnecte);
        }

        add(mainPanel, BorderLayout.CENTER);

        revalidate();
        repaint();
    }

    private void ajouterInfosUtilisateur(JPanel panel, Utilisateur utilisateur) {
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
            panel.add(Box.createRigidArea(new Dimension(0, 10)));
            panel.add(label);
        }
    }

    private void ajouterBoutonModifier(JPanel panel) {
        JButton boutonModifier = new JButton("Modifier mes informations");
        styliserBouton(boutonModifier);
        boutonModifier.setAlignmentX(Component.CENTER_ALIGNMENT);

        boutonModifier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeAll();
                revalidate();
                repaint();
                affichageInfoAvecModification();
            }
        });

        panel.add(boutonModifier);
    }

    private void affichageInfoAvecModification() {
        setBackground(Color.WHITE);
        setLayout(new BorderLayout());

        // Bande bleue
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(Color.decode("#4682A9"));
        headerPanel.setPreferredSize(new Dimension(0, 80));
        headerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20));

        JLabel titre = new JLabel("Modifier mes informations");
        titre.setFont(new Font("SansSerif", Font.BOLD, 20));
        titre.setForeground(Color.WHITE);
        headerPanel.add(titre);

        add(headerPanel, BorderLayout.NORTH);

        // Corps principal
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        Utilisateur utilisateur = Utilisateur.getUtilisateurConnecte();
        if (utilisateur != null) {
            ajouterZoneModification(mainPanel);
            mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
            ajouterBoutonAnnuler(mainPanel);
            mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
            ajouterBoutonDeconnexion(mainPanel);
        } else {
            JLabel nonConnecte = new JLabel("Vous n'êtes pas connecté");
            nonConnecte.setAlignmentX(Component.CENTER_ALIGNMENT);
            nonConnecte.setFont(new Font("SansSerif", Font.PLAIN, 16));
            mainPanel.add(nonConnecte);
        }

        add(mainPanel, BorderLayout.CENTER);

        revalidate();
        repaint();
    }

    private void ajouterZoneModification(JPanel panel) {
        Utilisateur utilisateur = Utilisateur.getUtilisateurConnecte();

        JTextField nom = new JTextField(utilisateur.getNom());
        JTextField email = new JTextField(utilisateur.getMail());
        JPasswordField motDePasse = new JPasswordField(utilisateur.getMot_de_passe());

        ajouterFocusListener(nom, "Nom complet");
        ajouterFocusListener(email, "Email");
        ajouterFocusListener(motDePasse, "Nouveau mot de passe");

        Dimension fieldSize = new Dimension(200, 25);
        nom.setMaximumSize(fieldSize);
        email.setMaximumSize(fieldSize);
        motDePasse.setMaximumSize(fieldSize);

        panel.add(nom);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(email);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(motDePasse);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        JButton boutonValider = new JButton("Valider les modifications");
        styliserBouton(boutonValider);
        boutonValider.setAlignmentX(Component.CENTER_ALIGNMENT);

        boutonValider.addActionListener(e -> {
            String nouveauNom = nom.getText();
            String nouvelEmail = email.getText();
            String nouveauMotDePasse = new String(motDePasse.getPassword());
            compteController.modifierInformations(nouveauNom, nouvelEmail, nouveauMotDePasse);
        });

        panel.add(boutonValider);
    }

    private void ajouterBoutonAnnuler(JPanel panel) {
        JButton boutonAnnuler = new JButton("Annuler");
        styliserBouton(boutonAnnuler);
        boutonAnnuler.setAlignmentX(Component.CENTER_ALIGNMENT);

        boutonAnnuler.addActionListener(e -> {
            removeAll();
            revalidate();
            repaint();
            affichageInfo();
        });

        panel.add(boutonAnnuler);
    }

    private void ajouterBoutonDeconnexion(JPanel panel) {
        JButton boutonDeconnexion = new JButton("Déconnexion");
        styliserBouton(boutonDeconnexion);
        boutonDeconnexion.setAlignmentX(Component.CENTER_ALIGNMENT);

        boutonDeconnexion.addActionListener(e -> compteController.deconnexion());

        panel.add(boutonDeconnexion);
    }

    private void styliserBouton(JButton bouton) {
        bouton.setBackground(Color.decode("#4682A9"));
        bouton.setForeground(Color.WHITE);
        bouton.setFocusPainted(false);
        bouton.setFont(new Font("SansSerif", Font.BOLD, 14));
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
}
