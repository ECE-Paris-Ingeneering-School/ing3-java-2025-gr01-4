package Controleur;

import DAO.PromotionDAO;
import DAO.ProduitDAO;
import Modele.Promotion;
import Modele.Produit;

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

    /**
     * Constructeur de la classe
     * @param promotionDAO instance la promotion DAO pour accéder aux promotions
     * @param produitDAO instance du produit DAO pour accéder aux produits
     */
    public VentesFlashController(PromotionDAO promotionDAO, ProduitDAO produitDAO) {
        this.promotionDAO = promotionDAO;
        this.produitDAO = produitDAO;
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
}

