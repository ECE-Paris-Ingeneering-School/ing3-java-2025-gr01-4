package DAO;

import java.util.List;
import Modele.Promotion;

public interface PromotionDAO {
    Promotion getById(int id);
    List<Promotion> getAll();
    boolean insert(Promotion promotion);
    boolean update(Promotion promotion);
    boolean delete(int id);
}
