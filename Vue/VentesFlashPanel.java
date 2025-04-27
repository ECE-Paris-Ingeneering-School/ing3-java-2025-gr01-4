/*package Vue;

import javax.swing.*;
import java.awt.*;

public class VentesFlashPanel extends JPanel {
    public VentesFlashPanel() {
        setLayout(new GridLayout(3, 2, 10, 10));
        setBackground(Color.decode("#87bcd6"));

        for (int i = 1; i <= 6; i++) {
            JPanel produit = new JPanel();
            produit.setBackground(Color.WHITE);
            produit.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            produit.add(new JLabel("Produit en promo #" + i));
            add(produit);
        }
    }
}*/
package Vue;

import Controleur.VentesFlashController;
import Modele.Promotion;
import Modele.Produit;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import javax.swing.ImageIcon;

public class VentesFlashPanel extends JPanel {
    private VentesFlashController controller;

    public VentesFlashPanel(VentesFlashController controller) {
        this.controller = controller;
        // Utilisation d'un GridLayout avec 2 colonnes
        setLayout(new GridLayout(0, 2, 10, 10));  // 0 lignes, 2 colonnes, espacement de 10px
        setBackground(Color.decode("#87bcd6"));
        afficherPromotions();
    }

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
}




