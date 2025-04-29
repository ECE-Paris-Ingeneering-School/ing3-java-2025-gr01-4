package Controleur;

import DAO.*;
import Modele.Commande;
import Modele.Produit;
import Modele.Promotion;
import Modele.Utilisateur;
import Vue.PanierPanel;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
/**
 * @author Minh-Duc PHAN
 */

/**
 * Classe controleur pour les commandes dans le panier de l'utilisateur
 */
public class CommandeController {
    private PanierPanel view;
    private CommandeDAO commandeDAO;
    private ProduitDAO produitDAO;

    /**
     * Constructeur de la classe
     * @param view vue associé au panier
     * @param commandeDAO pemret d'accéder aux commandes
     * @param produitDAO permet d'accéder aux produits
     */
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
     * @param produit Le produit a ajouté en stock
     * @param view La panier à rafraîchir
     */
    public void supprimerCommande(Commande commande, Produit produit, PanierPanel view) {
        int confirm = JOptionPane.showConfirmDialog(
                view,
                "Êtes-vous sûr de vouloir supprimer ce produit de votre panier ?",
                "Confirmation de suppression",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            commandeDAO.supprimer(commande);

            // Mise à jour du stock
            produit.setQuantite(produit.getQuantite() + commande.getQuantite());
            produitDAO.modifier(produit);

            view.rafraichirPanier();
        }
    }

    /**
     * Valide le panier
     */
    public void validerPanier() {
        // Implémentation de la logique de validation
        Utilisateur user = Utilisateur.getUtilisateurConnecte();
        if (user != null) {
            // Récupérer toutes les commandes de l'utilisateur
            List<Commande> commandes = getCommandesUtilisateur(user.getId());

            if (commandes.isEmpty()) {
                JOptionPane.showMessageDialog(view,
                        "Votre panier est vide !",
                        "Panier vide",
                        JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            // Confirmation de la validation
            int confirm = JOptionPane.showConfirmDialog(
                    view,
                    "Êtes-vous sûr de vouloir valider votre commande ?",
                    "Confirmation de commande",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {
                // Supprimer toutes les commandes de l'utilisateur
                for (Commande commande : commandes) {
                    commandeDAO.supprimer(commande);
                }

                // Message de succès
                JOptionPane.showMessageDialog(view,
                        "Votre commande sera livrée !",
                        "Commande validée",
                        JOptionPane.INFORMATION_MESSAGE);

                // Rafraîchir l'affichage du panier
                view.rafraichirPanier();
            }
        } else {
            JOptionPane.showMessageDialog(view,
                    "Vous devez être connecté pour valider votre panier",
                    "Non connecté",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    public double calculerPrixAvecPromotion(Produit produit, int quantite) {
        // Récupérer toutes les promotions pour ce produit
        List<Promotion> promotions = getPromotionsPourProduit(produit.getId());

        if (promotions.isEmpty()) {
            return produit.getPrix() * quantite;
        }

        // Trouver la meilleure promotion applicable (ici on prend la première pour l'exemple)
        Promotion meilleurePromotion = promotions.get(0);
        int quantitePromo = meilleurePromotion.getQuantite();
        double prixPromo = meilleurePromotion.getPrix();

        // Calculer combien de fois la promotion s'applique
        int nbPromos = quantite / quantitePromo;
        int reste = quantite % quantitePromo;

        return (nbPromos * prixPromo) + (reste * produit.getPrix());
    }

    // Méthode pour récupérer les promotions d'un produit
    public List<Promotion> getPromotionsPourProduit(int produitId) {
        PromotionDAO promotionDAO = new PromotionDAOImpl();
        List<Promotion> toutesPromotions = promotionDAO.getAll();
        List<Promotion> promotionsProduit = new ArrayList<>();

        for (Promotion promo : toutesPromotions) {
            if (promo.getId_produit() == produitId) {
                promotionsProduit.add(promo);
            }
        }

        return promotionsProduit;
    }


}