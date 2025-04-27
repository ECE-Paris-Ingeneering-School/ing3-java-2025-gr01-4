package Controleur;

import DAO.CommandeDAOImpl;
import Modele.Produit;
import DAO.ProduitDAOImpl;
import Vue.ProduitPanel;
import javax.swing.*;
import java.awt.*;

/**
 * @author William BENOIT
 */

public class RechercheController {
    private JPanel conteneurPrincipal;
    private ProduitDAOImpl produitDAO;
    private CommandeDAOImpl commandeDAO;

    /**
     * Permet de sauvegarder en attribu le JPanel.
     * @param conteneurPrincipal
     */
    public RechercheController(JPanel conteneurPrincipal) {
        this.conteneurPrincipal = conteneurPrincipal;
    }

    /**
     * Permet d'appeler le DAO pour faire une requête SQL pour rechercher les produits correspondants au nom
     * entrée en paramètre pour appeler ProduitPanel pour les afficher et affiche un message si aucun produit ne
     * correspond.
     * @param recherche
     */
    public void afficherProduitsParRecherche(String recherche) {
        java.util.List<Produit> produit = new ProduitDAOImpl().getByRecherche(recherche);
        Component composant = conteneurPrincipal.getComponent(0);
        if (produit.isEmpty()) {
            //renvoie un message d'erreur
            JOptionPane.showMessageDialog(conteneurPrincipal, "Aucun produit trouvé pour votre recherche.", "Erreur", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        //Si on trouve un produit, on renvoie la page du produit
        ProduitPanel produitPanel = new ProduitPanel(produit, produitDAO, commandeDAO);

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
