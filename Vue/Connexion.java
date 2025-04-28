package Vue;

import Controleur.CommandeController;
import DAO.*;
import Modele.Utilisateur;
import Controleur.VentesFlashController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * @author Pierre-Louis CHARBONNIER
 * Barre de menu principale de l'application gérant la navigation entre les différents panels.
 * Gère également la connexion/déconnexion des utilisateurs et l'affichage conditionnel des menus.
 */
public class Connexion extends JMenuBar {
    private JPanel contenuCentral;
    private CardLayout cardLayout;
    private JFrame frame;
    private DatabaseConnection dbConnection;

    /**
     * Constructeur de la barre de menu.
     *
     * @param frame La fenêtre principale de l'application à laquelle ce menu est attaché
     */
    public Connexion(JFrame frame) {
        this.frame = frame;
        contenuCentral = new JPanel();
        cardLayout = new CardLayout();
        contenuCentral.setLayout(cardLayout);

        // Initialisation des DAO pour les ventes flash
        PromotionDAO promotionDAO = new PromotionDAOImpl();
        ProduitDAO produitDAO = new ProduitDAOImpl();
        CommandeDAO commandeDAO = new CommandeDAOImpl(dbConnection);
        VentesFlashController controller = new VentesFlashController(promotionDAO, produitDAO, commandeDAO);
        CommandeController commandeController = new CommandeController(null, commandeDAO, produitDAO);

        // Ajout des différents panels
        contenuCentral.add(new ConnexionPanel(cardLayout, contenuCentral, this), "Connexion");
        contenuCentral.add(new InscriptionPanel(cardLayout, contenuCentral), "Inscription");
        contenuCentral.add(new VentePanel(cardLayout, contenuCentral), "Vente");
        contenuCentral.add(new ComptePanel(cardLayout, contenuCentral, this), "Compte");
        contenuCentral.add(new CategoriePanel(contenuCentral, dbConnection), "Categories");
        contenuCentral.add(new VentesFlashPanel(controller), "VentesFlash");
        contenuCentral.add(new PanierPanel(commandeController), "Panier");
        contenuCentral.add(new RecherchePanel(contenuCentral), "Recherche");
        contenuCentral.add(new PromotionPanel(cardLayout, contenuCentral), "Promotion");

        frame.getContentPane().add(contenuCentral, BorderLayout.CENTER);

        ActionListener afficherMenuListener = e -> {
            String action = e.getActionCommand();
            cardLayout.show(contenuCentral, action);
        };

        // Menu initial (non connecté)
        String[] items = {"Connexion", "Inscription"};
        String[] commands = {"Connexion", "Inscription"};

        for (int i = 0; i < items.length; i++) {
            JMenuItem item = new JMenuItem(items[i]);
            item.setActionCommand(commands[i]);
            item.addActionListener(afficherMenuListener);
            this.add(item);
        }

        // Afficher la page de connexion par défaut
        cardLayout.show(contenuCentral, "Connexion");
    }

    /**
     * Met à jour le menu après une connexion réussie.
     * Affiche les options disponibles pour l'utilisateur connecté.
     */
    public void mettreAJourMenu() {
        this.removeAll(); // enlève tous les anciens boutons
        construireMenu();
        this.revalidate();
        this.repaint();
    }

    /**
     * Construit le menu pour un utilisateur connecté.
     * Inclut des options supplémentaires pour les administrateurs.
     */
    private void construireMenu() {
        ActionListener afficherMenuListener = e -> {
            String action = e.getActionCommand();
            cardLayout.show(contenuCentral, action);
        };

        String[] items = {"Informations du compte", "Catégories", "Ventes flash", "Panier", "Recherche"};
        String[] commands = {"Compte", "Categories", "VentesFlash", "Panier", "Recherche"};

        for (int i = 0; i < items.length; i++) {
            JMenuItem item = new JMenuItem(items[i]);
            item.setActionCommand(commands[i]);
            item.addActionListener(afficherMenuListener);
            this.add(item);
        }

        // Ajout option "Vendre un article" pour les admins
        Utilisateur utilisateur = Utilisateur.getUtilisateurConnecte();
        if (utilisateur != null && utilisateur.isAdmin()) {
            JMenuItem itemVente = new JMenuItem("Vendre un article");
            itemVente.setActionCommand("Vente");
            itemVente.addActionListener(afficherMenuListener);
            this.add(itemVente);

            JMenuItem itemPromo = new JMenuItem("Ajouter une promotion");
            itemPromo.setActionCommand("Promotion");
            itemPromo.addActionListener(afficherMenuListener);
            this.add(itemPromo);
        }
    }

    /**
     * Met à jour le menu après une déconnexion.
     * Retourne au menu de base avec seulement Connexion/Inscription.
     */
    public void mettreAJourMenuDeconnexion() {
        this.removeAll(); // enlève tous les anciens boutons
        construireMenuDeconnexion();
        this.revalidate();
        this.repaint();
    }

    /**
     * Construit le menu de base pour les utilisateurs non connectés.
     */
    private void construireMenuDeconnexion() {
        ActionListener afficherMenuListener = e -> {
            String action = e.getActionCommand();
            cardLayout.show(contenuCentral, action);
        };

        String[] items = {"Connexion", "Inscription"};
        String[] commands = {"Connexion", "Inscription"};

        for (int i = 0; i < items.length; i++) {
            JMenuItem item = new JMenuItem(items[i]);
            item.setActionCommand(commands[i]);
            item.addActionListener(afficherMenuListener);
            this.add(item);
        }
    }
}