package Vue;

import Controleur.VentesFlashController;
import Modele.Promotion;
import Modele.Produit;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import javax.swing.ImageIcon;

/**
 * @author Minh-Duc PHAN
  */

/**
 * Classe mettant en avant les produits ayant des promotions
 */
public class VentesFlashPanel extends JPanel {
    private VentesFlashController controller;

    /**
     * Constructeur de la classe
     * @param controller le fichier controleur pour cette classe
     */
    public VentesFlashPanel(VentesFlashController controller) {
        this.controller = controller;
        // Utilisation d'un GridLayout avec 2 colonnes
        setLayout(new GridLayout(0, 2, 10, 10));  // 0 lignes, 2 colonnes, espacement de 10px
        setBackground(Color.decode("#87bcd6"));
        afficherPromotions();
        this.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentShown(java.awt.event.ComponentEvent e) {
                rafraichirPromotion();
            }
        });
    }

    /**
     * Méthode permettant d'afficher les promotions
     */
    private void afficherPromotions() {
        List<Promotion> promotions = controller.getPromotions();

        if (promotions.isEmpty()) {
            add(new JLabel("Il n'y a pas de promotion"));
            return;
        }

        for (Promotion promo : promotions) {
            Produit produit = controller.getProduitById(promo.getId_produit());

            JPanel produitPanel = new JPanel();
            produitPanel.setBackground(Color.WHITE);
            produitPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            produitPanel.setLayout(new BoxLayout(produitPanel, BoxLayout.Y_AXIS));

            if (produit != null) {
                // Afficher l'image du produit
                String cheminImage = produit.getImages(); // Supposons que produit.getImageUrl() retourne l'URL ou le chemin de l'image
                ImageIcon imageIcon = new ImageIcon(cheminImage); // Charger l'image

                // Redimensionner l'image avant de l'afficher
                Image image = imageIcon.getImage(); // Obtenir l'objet Image
                Image resizedImage = image.getScaledInstance(150, 150, Image.SCALE_SMOOTH); // Redimensionner à 150x150
                imageIcon = new ImageIcon(resizedImage); // Mettre à jour l'ImageIcon avec l'image redimensionnée

                JLabel imageLabel = new JLabel();
                imageLabel.setIcon(imageIcon);  // Affichage de l'image redimensionnée
                produitPanel.add(imageLabel);

                // Afficher le nom du produit et autres infos
                produitPanel.add(new JLabel("<html><b>" + produit.getNom() + "</b></html>"));
                produitPanel.add(new JLabel("Description : " + produit.getDescription()));
            } else {
                produitPanel.add(new JLabel("Produit inconnu (ID : " + promo.getId_produit() + ")"));
            }

            produitPanel.add(new JLabel("Quantité pour bénéficier de la promo : " + promo.getQuantite()));
            produitPanel.add(new JLabel("Prix promotionnel : " + promo.getPrix() + "€"));

            add(produitPanel);
        }
    }

    public void rafraichirPromotion() {
        this.removeAll();       // Supprimer tous les composants existants
        afficherPromotions();   // Réafficher les promotions à jour
        this.revalidate();      // Revalider l'affichage
        this.repaint();
    }
}




