package Controleur;

import DAO.PromotionDAO;
import DAO.ProduitDAO;
import DAO.CommandeDAO;
import Modele.Promotion;
import Modele.Produit;
import Modele.Utilisateur;
import Modele.Commande;

import javax.swing.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
/**
 * @author Minh-Duc PHAN
 */

/**
 * Controleur pour la vente flash
 */
public class VentesFlashController {
    private PromotionDAO promotionDAO;
    private ProduitDAO produitDAO;
    private CommandeDAO commandeDAO;

    /**
     * Constructeur de la classe
     * @param promotionDAO instance la promotion DAO pour accéder aux promotions
     * @param produitDAO instance du produit DAO pour accéder aux produits
     */
    public VentesFlashController(PromotionDAO promotionDAO, ProduitDAO produitDAO, CommandeDAO commandeDAO) {
        this.promotionDAO = promotionDAO;
        this.produitDAO = produitDAO;
        this.commandeDAO = commandeDAO;
    }

    /**
     * Récupère toutes les promotions
     * @return retourne une liste de promotions
     */
    public List<Promotion> getPromotions() {
        return promotionDAO.getAll();
    }

    /**
     * Récupère un produit à parti de son identifiant
     * @param idProduit identifiant du produit
     * @return retourne le produit, sinon null
     */
    public Produit getProduitById(int idProduit) {
        return produitDAO.getById(idProduit);
    }


    public void ajouterProduitEnPromo(Produit produit, int quantitePromo) {
        try {
            Utilisateur user = Utilisateur.getUtilisateurConnecte();
            if (user == null) {
                throw new Exception("Veuillez vous connecter");
            }

            // Vérifier le stock
            if (produit.getQuantite() < quantitePromo) {
                throw new Exception("Stock insuffisant");
            }

            // Créer/modifier la commande
            Commande existante = commandeDAO.chercher(user.getId(), produit.getId());
            if (existante != null) {
                existante.setQuantite(existante.getQuantite() + quantitePromo);
                commandeDAO.modifier(existante);
            } else {
                Commande nouvelle = new Commande(
                        0,
                        user.getId(),
                        produit.getId(),
                        quantitePromo,
                        produit.getPrix(),
                        LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                );
                commandeDAO.ajouter(nouvelle);
            }

            // Mettre à jour le stock
            produit.setQuantite(produit.getQuantite() - quantitePromo);
            produitDAO.modifier(produit);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Erreur: " + e.getMessage(),
                    "Erreur",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}

