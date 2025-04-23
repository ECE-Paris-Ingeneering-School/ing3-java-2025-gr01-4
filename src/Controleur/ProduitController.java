package Controleur;

import java.util.ArrayList;
import java.util.List;
import Modele.Produit;

public class ProduitController {
    private List<Produit> produits;
    private int nextId = 1;

    public ProduitController() {
        this.produits = new ArrayList<>();
    }

    public List<Produit> getAllProduits() {
        return produits;
    }

    public Produit getProduitById(int id) {
        return produits.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    public boolean addProduit(Produit produit) {
        produit.setId(nextId++);
        return produits.add(produit);
    }

    public boolean updateProduit(Produit updatedProduit) {
        int index = -1;
        for (int i = 0; i < produits.size(); i++) {
            if (produits.get(i).getId() == updatedProduit.getId()) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            produits.set(index, updatedProduit);
            return true;
        }
        return false;
    }

    public boolean deleteProduit(int id) {
        return produits.removeIf(p -> p.getId() == id);
    }
}