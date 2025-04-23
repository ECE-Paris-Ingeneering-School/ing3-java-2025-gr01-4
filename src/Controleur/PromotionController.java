package Controleur;

import java.util.ArrayList;
import java.util.List;
import Modele.Promotion;

public class PromotionController {
    private List<Promotion> promotions;
    private int nextId = 1;

    public PromotionController() {
        this.promotions = new ArrayList<>();
    }

    public List<Promotion> getAllPromotions() {
        return promotions;
    }

    public Promotion getPromotionById(int id) {
        return promotions.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    public boolean addPromotion(Promotion promotion) {
        promotion.setId(nextId++);
        return promotions.add(promotion);
    }

    public boolean updatePromotion(Promotion updatedPromotion) {
        for (int i = 0; i < promotions.size(); i++) {
            if (promotions.get(i).getId() == updatedPromotion.getId()) {
                promotions.set(i, updatedPromotion);
                return true;
            }
        }
        return false;
    }

    public boolean deletePromotion(int id) {
        return promotions.removeIf(p -> p.getId() == id);
    }
}
