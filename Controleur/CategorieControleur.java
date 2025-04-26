package Controleur;

import DAO.ProduitDAOImpl;
import Vue.ProduitPanel;
import javax.swing.*;

public class CategorieControleur {
    private JPanel conteneurPrincipal;

    public CategorieControleur(JPanel conteneurPrincipal) {
        this.conteneurPrincipal = conteneurPrincipal;
    }

    public void afficherProduitsParCategorie(String categorie) {
        ProduitPanel produitPanel = new ProduitPanel(new ProduitDAOImpl().getCategorie(categorie));

        // Nettoyer le contenu existant
        conteneurPrincipal.removeAll();
        conteneurPrincipal.add(new JScrollPane(produitPanel));
        conteneurPrincipal.revalidate();
        conteneurPrincipal.repaint();
    }
}