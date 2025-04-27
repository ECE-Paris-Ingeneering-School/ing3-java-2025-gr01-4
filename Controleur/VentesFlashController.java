package Controleur;

import DAO.PromotionDAO;
import DAO.ProduitDAO;
import Modele.Promotion;
import Modele.Produit;

import java.util.List;

public class VentesFlashController {
    private PromotionDAO promotionDAO;
    private ProduitDAO produitDAO;

    public VentesFlashController(PromotionDAO promotionDAO, ProduitDAO produitDAO) {
        this.promotionDAO = promotionDAO;
        this.produitDAO = produitDAO;
    }

    public List<Promotion> getPromotions() {
        return promotionDAO.getAll();
    }

    public Produit getProduitById(int idProduit) {
        return produitDAO.getById(idProduit);
    }
}

