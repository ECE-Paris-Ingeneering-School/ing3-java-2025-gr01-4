package Controleur;

import DAO.ProduitDAOImpl;
import Vue.ProduitPanel;

import javax.swing.*;

public class RechercheController {
    private JPanel conteneurPrincipal;

    public RechercheController(JPanel conteneurPrincipal) {
        this.conteneurPrincipal = conteneurPrincipal;
    }

    public void afficherProduitsParRecherche(String recherche) {
        ProduitPanel produitPanel = new ProduitPanel(new ProduitDAOImpl().getByRecherche(recherche));

        System.out.println(produitPanel);

        // Nettoyer le contenu existant
        conteneurPrincipal.removeAll();
        conteneurPrincipal.add(new JScrollPane(produitPanel));
        conteneurPrincipal.revalidate();
        conteneurPrincipal.repaint();
    }
}
