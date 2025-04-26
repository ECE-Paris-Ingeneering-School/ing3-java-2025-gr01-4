package Vue;

import Modele.Utilisateur;
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

        // Ajout des différents panels
        contenuCentral.add(new ConnexionPanel(cardLayout, contenuCentral, this), "Connexion");
        contenuCentral.add(new InscriptionPanel(cardLayout, contenuCentral), "Inscription");
        contenuCentral.add(new VentePanel(cardLayout, contenuCentral), "Vente");
        contenuCentral.add(new ComptePanel(cardLayout, contenuCentral, this), "Compte");
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
        /*Utilisateur utilisateur = Utilisateur.getUtilisateurConnecte();
// 🔥 Ajouter le bouton "Vendre un article" uniquement si admin
        if (utilisateur != null && utilisateur.isAdmin()) {
            JMenuItem itemVente = new JMenuItem("Vendre un article");
            itemVente.setActionCommand("Vente");
            itemVente.addActionListener(afficherMenuListener);
            this.add(itemVente);
        }*/



        // Afficher la page de connexion par défaut
        cardLayout.show(contenuCentral, "Connexion");
    }


    public void mettreAJourMenu() {
        this.removeAll(); // enlève tous les anciens boutons
        construireMenu(); // 🔥 recrée tout
        this.revalidate();
        this.repaint();
    }

    //Nouveau menu en tant qu'admin
    private void construireMenu() {
        ActionListener afficherMenuListener = e -> {
            String action = e.getActionCommand();
            cardLayout.show(contenuCentral, action);
        };

        String[] items = {"Informations du compte", "Catégories", "Ventes flash", "Panier"};
        String[] commands = {"Compte", "Categories", "VentesFlash", "Panier"};

        for (int i = 0; i < items.length; i++) {
            JMenuItem item = new JMenuItem(items[i]);
            item.setActionCommand(commands[i]);
            item.addActionListener(afficherMenuListener);
            this.add(item);
        }
        Utilisateur utilisateur = Utilisateur.getUtilisateurConnecte();
        if (utilisateur != null && utilisateur.isAdmin()) {
            JMenuItem itemVente = new JMenuItem("Vendre un article");
            itemVente.setActionCommand("Vente");
            itemVente.addActionListener(afficherMenuListener);
            this.add(itemVente);
        }
    }

    //Nouveau menu si l'utilisateur se déconnecte
    public void mettreAJourMenuDeconnexion() {
        this.removeAll(); // enlève tous les anciens boutons
        construireMenuDeconnexion(); // 🔥 recrée tout
        this.revalidate();
        this.repaint();
    }

    private void construireMenuDeconnexion() {
        ActionListener afficherMenuListener = e -> {
            String action = e.getActionCommand();
            cardLayout.show(contenuCentral, action);
        };

        String[] items = {"Connexion", "Inscription","Informations du compte", "Catégories", "Ventes flash", "Panier"};
        String[] commands = {"Connexion", "Inscription","Compte", "Categories", "VentesFlash", "Panier"};

        for (int i = 0; i < items.length; i++) {
            JMenuItem item = new JMenuItem(items[i]);
            item.setActionCommand(commands[i]);
            item.addActionListener(afficherMenuListener);
            this.add(item);
        }
    }

}