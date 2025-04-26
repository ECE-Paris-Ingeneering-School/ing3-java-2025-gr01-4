package Vue;

import Modele.Utilisateur;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Connexion extends JMenuBar {
    private JPanel contenuCentral;
    private CardLayout cardLayout;
    private JFrame frame;
    private Utilisateur utilisateurConnecte; // 🔥 stocker l'utilisateur connecté


    public Connexion(JFrame frame) {
        this.frame = frame;
        contenuCentral = new JPanel();
        cardLayout = new CardLayout();
        contenuCentral.setLayout(cardLayout);

        // Ajout des différents panels
        contenuCentral.add(new ConnexionPanel(cardLayout, contenuCentral, this), "Connexion");
        contenuCentral.add(new InscriptionPanel(cardLayout, contenuCentral), "Inscription");
        contenuCentral.add(new VentePanel(cardLayout, contenuCentral), "Vente");
        contenuCentral.add(new ComptePanel(), "Compte");
        contenuCentral.add(new CategoriePanel(), "Categories");
        contenuCentral.add(new VentesFlashPanel(), "VentesFlash");
        contenuCentral.add(new PanierPanel(), "Panier");

        frame.getContentPane().add(contenuCentral, BorderLayout.CENTER);

        ActionListener afficherMenuListener = e -> {
            String action = e.getActionCommand();
            cardLayout.show(contenuCentral, action);
        };

        String[] items = {"Connexion", "Inscription", "Informations du compte", "Catégories", "Ventes flash", "Panier"};
        String[] commands = {"Connexion", "Inscription", "Compte", "Categories", "VentesFlash", "Panier"};

        for (int i = 0; i < items.length; i++) {
            JMenuItem item = new JMenuItem(items[i]);
            item.setActionCommand(commands[i]);
            item.addActionListener(afficherMenuListener);
            this.add(item);
        }

// 🔥 Ajouter le bouton "Vendre un article" uniquement si admin
        if (utilisateurConnecte != null && utilisateurConnecte.isAdmin()) {
            JMenuItem itemVente = new JMenuItem("Vendre un article");
            itemVente.setActionCommand("Vente");
            itemVente.addActionListener(afficherMenuListener);
            this.add(itemVente);
        }



        // Afficher la page de connexion par défaut
        cardLayout.show(contenuCentral, "Connexion");
    }

    public void setUtilisateurConnecte(Utilisateur utilisateur) {
        this.utilisateurConnecte = utilisateur;
    }

    public void mettreAJourMenu() {
        this.removeAll(); // enlève tous les anciens boutons
        construireMenu(); // 🔥 recrée tout
        this.revalidate();
        this.repaint();
    }

    // 🔥 Code déplacé dans construireMenu
    private void construireMenu() {
        ActionListener afficherMenuListener = e -> {
            String action = e.getActionCommand();
            cardLayout.show(contenuCentral, action);
        };

        String[] items = {"Connexion", "Inscription", "Informations du compte", "Catégories", "Ventes flash", "Panier"};
        String[] commands = {"Connexion", "Inscription", "Compte", "Categories", "VentesFlash", "Panier"};

        for (int i = 0; i < items.length; i++) {
            JMenuItem item = new JMenuItem(items[i]);
            item.setActionCommand(commands[i]);
            item.addActionListener(afficherMenuListener);
            this.add(item);
        }

        if (utilisateurConnecte != null && utilisateurConnecte.isAdmin()) {
            JMenuItem itemVente = new JMenuItem("Vendre un article");
            itemVente.setActionCommand("Vente");
            itemVente.addActionListener(afficherMenuListener);
            this.add(itemVente);
        }
    }

}