package Vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Connexion extends JMenuBar {
    private JPanel contenuCentral;
    private CardLayout cardLayout;
    private JFrame frame;

    public Connexion(JFrame frame) {
        this.frame = frame;
        contenuCentral = new JPanel();
        cardLayout = new CardLayout();
        contenuCentral.setLayout(cardLayout);

        contenuCentral.add(creerConnexionPanel(), "Vue.Connexion");
        contenuCentral.add(creerInscriptionPanel(), "Inscription");
        contenuCentral.add(creerVentePanel(), "Vente");
        contenuCentral.add(creerComptePanel(), "Compte");
        contenuCentral.add(creerCategoriePanel(), "Categories");
        contenuCentral.add(creerVentesFlashPanel(), "VentesFlash");
        contenuCentral.add(creerPanierPanel(), "Panier");

        frame.getContentPane().add(contenuCentral, BorderLayout.CENTER);

        ActionListener afficherMenuListener = e -> {
            String action = e.getActionCommand();
            cardLayout.show(contenuCentral, action);
        };

        String[] items = {"Vue.Connexion", "Inscription", "Vendre un article", "Informations du compte", "Catégories", "Ventes flash", "Panier"};
        String[] commands = {"Vue.Connexion", "Inscription", "Vente", "Compte", "Categories", "VentesFlash", "Panier"};

        for (int i = 0; i < items.length; i++) {
            JMenuItem item = new JMenuItem(items[i]);
            item.setActionCommand(commands[i]);
            item.addActionListener(afficherMenuListener);
            this.add(item);
        }
    }

    private JPanel creerConnexionPanel() {
        JPanel panel = new JPanel(null);
        panel.setBackground(Color.decode("#87bcd6"));

        JLabel logo = chargerLogo();
        panel.add(logo);

        JLabel titre = new JLabel("Page de connexion");
        titre.setBounds(350, 20, 210, 33);
        titre.setFont(new Font("SansSerif", Font.BOLD, 20));
        titre.setForeground(Color.BLACK);
        panel.add(titre);

        JTextField email = new JTextField("Identifiant");
        email.setBounds(392, 102, 160, 25);
        email.setForeground(Color.GRAY);
        ajouterFocusListener(email, "Identifiant");
        panel.add(email);

        JPasswordField motDePasse = new JPasswordField("Mot de passe");
        motDePasse.setBounds(390, 152, 160, 25);
        motDePasse.setEchoChar((char) 0);
        motDePasse.setForeground(Color.GRAY);
        ajouterFocusListener(motDePasse, "Mot de passe");
        panel.add(motDePasse);

        JButton boutonConnexion = new JButton("Vue.Connexion");
        boutonConnexion.setBounds(385, 200, 160, 30);
        styliserBouton(boutonConnexion);
        panel.add(boutonConnexion);

        return panel;
    }

    private JPanel creerInscriptionPanel() {
        JPanel panel = new JPanel(null);
        panel.setBackground(Color.decode("#87bcd6"));

        JLabel logo = chargerLogo();
        panel.add(logo);

        JLabel titre = new JLabel("Page d'inscription");
        titre.setBounds(350, 20, 250, 33);
        titre.setFont(new Font("SansSerif", Font.BOLD, 20));
        titre.setForeground(Color.BLACK);
        panel.add(titre);

        JTextField nom = new JTextField("Nom complet");
        nom.setBounds(392, 102, 160, 25);
        nom.setForeground(Color.GRAY);
        ajouterFocusListener(nom, "Nom complet");
        panel.add(nom);

        JTextField email = new JTextField("Email");
        email.setBounds(392, 142, 160, 25);
        email.setForeground(Color.GRAY);
        ajouterFocusListener(email, "Email");
        panel.add(email);

        JPasswordField motDePasse = new JPasswordField("Mot de passe");
        motDePasse.setBounds(392, 182, 160, 25);
        motDePasse.setForeground(Color.GRAY);
        motDePasse.setEchoChar((char) 0);
        ajouterFocusListener(motDePasse, "Mot de passe");
        panel.add(motDePasse);

        JButton boutonInscription = new JButton("S'inscrire");
        boutonInscription.setBounds(385, 230, 160, 30);
        styliserBouton(boutonInscription);
        panel.add(boutonInscription);

        return panel;
    }

    private JPanel creerVentePanel() {
        JPanel panel = new JPanel(null);
        panel.setBackground(Color.decode("#87bcd6"));

        JLabel logo = chargerLogo();
        panel.add(logo);

        JLabel titre = new JLabel("Vendre un article");
        titre.setBounds(350, 20, 250, 33);
        titre.setFont(new Font("SansSerif", Font.BOLD, 20));
        titre.setForeground(Color.BLACK);
        panel.add(titre);

        String[] placeholders = {"Nom du produit", "Description", "Prix", "Unité"};
        for (int i = 0; i < placeholders.length; i++) {
            JTextField champ = new JTextField(placeholders[i]);
            champ.setBounds(392, 102 + i * 40, 160, 25);
            champ.setForeground(Color.GRAY);
            ajouterFocusListener(champ, placeholders[i]);
            panel.add(champ);
        }

        String[] categories = {"Électronique", "Vêtements", "Maison", "Livres", "Autre"};
        JComboBox<String> categorie = new JComboBox<>(categories);
        categorie.setBounds(392, 262, 160, 25);
        panel.add(categorie);

        JButton boutonSoumettre = new JButton("Soumettre");
        boutonSoumettre.setBounds(385, 310, 160, 30);
        styliserBouton(boutonSoumettre);
        panel.add(boutonSoumettre);

        return panel;
    }

    private JPanel creerComptePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.decode("#87bcd6"));

        String[] infos = {
                "Nom: Dupont Jean",
                "Email: jean.dupont@mail.com",
                "Adresse: 123 Rue Exemple, Paris",
                "Téléphone: 06 12 34 56 78",
                "Statut: Client régulier"
        };
        for (String info : infos) {
            JLabel label = new JLabel(info);
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            label.setFont(new Font("SansSerif", Font.PLAIN, 16));
            panel.add(Box.createRigidArea(new Dimension(0, 10)));
            panel.add(label);
        }
        return panel;
    }

    private JPanel creerCategoriePanel() {
        JPanel panel = new JPanel(new GridLayout(2, 3, 10, 10));
        panel.setBackground(Color.decode("#87bcd6"));

        String[] categories = {"Électronique", "Vêtements", "Maison", "Livres", "Sport", "Autre"};
        for (String categorie : categories) {
            JButton bouton = new JButton(categorie);
            styliserBouton(bouton);
            panel.add(bouton);
        }
        return panel;
    }

    private JPanel creerVentesFlashPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBackground(Color.decode("#87bcd6"));

        for (int i = 1; i <= 6; i++) {
            JPanel produit = new JPanel();
            produit.setBackground(Color.WHITE);
            produit.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            produit.add(new JLabel("Produit en promo #" + i));
            panel.add(produit);
        }
        return panel;
    }

    private JPanel creerPanierPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.decode("#87bcd6"));

        for (int i = 1; i <= 5; i++) {
            JPanel ligne = new JPanel(new FlowLayout(FlowLayout.LEFT));
            ligne.setBackground(Color.WHITE);
            ligne.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
            ligne.add(new JLabel("Article " + i));

            JButton supprimer = new JButton("Supprimer");
            styliserBouton(supprimer);
            ligne.add(supprimer);

            panel.add(Box.createRigidArea(new Dimension(0, 5)));
            panel.add(ligne);
        }
        return panel;
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

    private JLabel chargerLogo() {
        ImageIcon icon = new ImageIcon("src/Logo Vulpixia.png");
        Image img = icon.getImage().getScaledInstance(100, 60, Image.SCALE_SMOOTH);
        JLabel label = new JLabel(new ImageIcon(img));
        label.setBounds(20, 20, 100, 60);
        return label;
    }
}
