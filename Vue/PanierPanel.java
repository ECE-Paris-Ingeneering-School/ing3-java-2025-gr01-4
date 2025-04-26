package Vue;

import Modele.Utilisateur;
import Modele.Commande;
import Modele.Produit;
import DAO.CommandeDAO;
import DAO.CommandeDAOImpl;
import DAO.DatabaseConnection;
import DAO.ProduitDAO;
import DAO.ProduitDAOImpl;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PanierPanel extends JPanel {
    private CommandeDAO commandeDAO;
    private ProduitDAO produitDAO;

    public PanierPanel() {
        // Initialisation des DAO
        DatabaseConnection dbConnection = new DatabaseConnection();
        this.commandeDAO = new CommandeDAOImpl(dbConnection);
        this.produitDAO = new ProduitDAOImpl();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.decode("#87bcd6"));
        afficherContenuPanier();
    }

    private void afficherContenuPanier() {
        this.removeAll(); // Nettoyer l'affichage existant

        // Vérifier si un utilisateur est connecté
        Utilisateur utilisateur = Utilisateur.getUtilisateurConnecte();
        if (utilisateur == null) {
            add(new JLabel("Veuillez vous connecter pour voir votre panier"));
            return;
        }

        // Récupérer les commandes de l'utilisateur
        List<Commande> commandes = getCommandesUtilisateur(utilisateur.getId());

        if (commandes.isEmpty()) {
            add(new JLabel("Votre panier est vide"));
            return;
        }

        // Afficher chaque produit du panier
        for (Commande commande : commandes) {
            Produit produit = produitDAO.getById(commande.getId_produit());
            if (produit != null) {
                JPanel ligneProduit = creerLigneProduit(produit, commande);
                add(ligneProduit);
                add(Box.createRigidArea(new Dimension(0, 10)));
            }
        }

    }

    private List<Commande> getCommandesUtilisateur(int userId) {
        List<Commande> toutesCommandes = commandeDAO.getAll();
        List<Commande> commandesUtilisateur = new ArrayList<>();

        for (Commande cmd : toutesCommandes) {
            if (cmd.getId_client() == userId) {
                commandesUtilisateur.add(cmd);
            }
        }
        return commandesUtilisateur;
    }

    private JPanel creerLigneProduit(Produit produit, Commande commande) {
        JPanel ligne = new JPanel(new FlowLayout(FlowLayout.LEFT));
        ligne.setBackground(Color.WHITE);
        ligne.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        // Informations du produit
        ligne.add(new JLabel(produit.getNom()));
        ligne.add(new JLabel("Quantité: " + commande.getQuantite()));
        ligne.add(new JLabel(String.format("Prix: %.2f€", commande.getPrix())));

        // Bouton Supprimer
        JButton supprimer = new JButton("Supprimer");
        supprimer.addActionListener(e -> supprimerCommande(commande));
        styliserBouton(supprimer);
        ligne.add(supprimer);

        return ligne;
    }

    private void supprimerCommande(Commande commande) {
        commandeDAO.supprimer(commande);
        JOptionPane.showMessageDialog(this, "Produit supprimé du panier");
        afficherContenuPanier(); // Rafraîchir l'affichage
    }

    private void styliserBouton(JButton bouton) {
        bouton.setBackground(Color.decode("#4682A9"));
        bouton.setForeground(Color.WHITE);
        bouton.setFocusPainted(false);
    }
}