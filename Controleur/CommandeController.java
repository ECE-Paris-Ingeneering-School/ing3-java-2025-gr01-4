package Controleur;

import DAO.CommandeDAO;
import DAO.ProduitDAO;
import Modele.Commande;
import Modele.Produit;
import Modele.Utilisateur;
import Vue.PanierPanel;

import javax.swing.*;
import java.util.List;

public class CommandeController {
    private PanierPanel view;
    private CommandeDAO commandeDAO;
    private ProduitDAO produitDAO;

    public CommandeController(PanierPanel view, CommandeDAO commandeDAO, ProduitDAO produitDAO) {
        this.view = view;
        this.commandeDAO = commandeDAO;
        this.produitDAO = produitDAO;
    }

    /**
     * Récupère les commandes d'un utilisateur
     * @param userId ID de l'utilisateur
     * @return Liste des commandes de l'utilisateur
     */
    public List<Commande> getCommandesUtilisateur(int userId) {
        return commandeDAO.getByClientId(userId); // À implémenter dans CommandeDAO
    }

    /**
     * Supprime une commande du panier
     * @param commande La commande à supprimer
     */
    public void supprimerCommande(Commande commande) {
        int confirm = JOptionPane.showConfirmDialog(
                view,
                "Êtes-vous sûr de vouloir supprimer ce produit de votre panier ?",
                "Confirmation de suppression",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            commandeDAO.supprimer(commande);
            view.rafraichirPanier();
        }
    }

    /**
     * Valide le panier (à implémenter)
     */
    public void validerPanier() {
        // Implémentation de la logique de validation
        Utilisateur user = Utilisateur.getUtilisateurConnecte();
        if (user != null) {
            List<Commande> commandes = getCommandesUtilisateur(user.getId());
            // Logique de validation...
        }
    }
}