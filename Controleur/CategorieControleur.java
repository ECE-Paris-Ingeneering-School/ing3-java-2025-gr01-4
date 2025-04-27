package Controleur;

import DAO.CommandeDAOImpl;
import DAO.DatabaseConnection;
import DAO.ProduitDAOImpl;
import Vue.ProduitPanel;
import javax.swing.*;
import java.awt.*;

/**
 * @author William BENOIT
 */

public class CategorieControleur {
    private JPanel conteneurPrincipal;
    private DatabaseConnection dbConnection;

    /**
     * Permet de sauvegarder en attribu le JPanel et la connection à la data base pour commander les produits.
     * @param conteneurPrincipal
     * @param dbConnection
     */
    public CategorieControleur(JPanel conteneurPrincipal, DatabaseConnection dbConnection) {
        this.conteneurPrincipal = conteneurPrincipal;
        this.dbConnection = dbConnection;
    }

    /**
     * Permet d'appeler le DAO pour faire une requête SQL pour rechercher les produits correspondants à la
     * catégorie entrée en paramètre pour appeler ProduitPanel pour les afficher.
     * @param categorie
     */
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

        ProduitDAOImpl produitDAO = new ProduitDAOImpl();
        CommandeDAOImpl commandeDAO = new CommandeDAOImpl(dbConnection);

        ProduitPanel produitPanel = new ProduitPanel(
                produitDAO.getCategorie(categorie),
                produitDAO,
                commandeDAO
        );

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