package DAO;

import java.util.List;
import Modele.Produit;

public interface ProduitDAO {
    Produit getById(int id);
    List<Produit> getAll();
    public void  ajouter(Produit produit);
    public Produit modifier(Produit produit);
    public void supprimer(int id);
}