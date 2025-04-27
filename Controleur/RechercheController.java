package Controleur;

import DAO.ProduitDAOImpl;
import Vue.ProduitPanel;

import javax.swing.*;
import java.awt.*;

public class RechercheController {
    private JPanel conteneurPrincipal;

    public RechercheController(JPanel conteneurPrincipal) {
        this.conteneurPrincipal = conteneurPrincipal;
    }

    public void afficherProduitsParRecherche(String recherche) {
        /*
        ProduitPanel produitPanel = new ProduitPanel(new ProduitDAOImpl().getByRecherche(recherche));

        System.out.println(produitPanel);

        // Nettoyer le contenu existant
        conteneurPrincipal.removeAll();
        conteneurPrincipal.add(new JScrollPane(produitPanel));
        conteneurPrincipal.revalidate();
        conteneurPrincipal.repaint();

         */
        ProduitPanel produitPanel = new ProduitPanel(new ProduitDAOImpl().getByRecherche(recherche));

        Component composant = conteneurPrincipal.getComponent(0);
        if (composant instanceof JPanel) {
            JPanel panel = (JPanel) composant;
            panel.removeAll();
            // Ajouter une nouvelle carte temporaire pour la catégorie sélectionnée
            conteneurPrincipal.add(new JScrollPane(produitPanel), "CategorieSelection");

            CardLayout cl = (CardLayout) conteneurPrincipal.getLayout();
            cl.show(conteneurPrincipal, "CategorieSelection");
            panel.revalidate();
            panel.repaint();
        }
    }
}
