package DAO;

import java.util.List;
import Modele.Promotion;

public interface PromotionDAO {
    Promotion getById(int id);
    List<Promotion> getAll();
    public void ajouter(Promotion promotion);
    public Promotion modifier(Promotion promotion);
    public void supprimer(int id);
}
