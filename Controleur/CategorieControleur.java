package Controleur;

import DAO.ProduitDAOImpl;
import Vue.ProduitPanel;
import javax.swing.*;
import java.awt.*;

public class CategorieControleur {
    private JPanel conteneurPrincipal;

    public CategorieControleur(JPanel conteneurPrincipal) {
        this.conteneurPrincipal = conteneurPrincipal;
    }

    public void afficherProduitsParCategorie(String categorie) {
        /*
        ProduitPanel produitPanel = new ProduitPanel(new ProduitDAOImpl().getCategorie(categorie));

        // Nettoyer le contenu existant
        conteneurPrincipal.removeAll();
        conteneurPrincipal.setLayout(new BorderLayout()); // BorderLayout pour faire la barre en haut + contenu
        conteneurPrincipal.setBackground(Color.decode("#f5f5f5"));
        conteneurPrincipal.add(new JScrollPane(produitPanel));
        conteneurPrincipal.revalidate();
        conteneurPrincipal.repaint();
         */

        ProduitPanel produitPanel = new ProduitPanel(new ProduitDAOImpl().getCategorie(categorie));

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