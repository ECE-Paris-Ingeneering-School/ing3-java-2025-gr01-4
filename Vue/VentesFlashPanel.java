package Vue;

import Controleur.VentesFlashController;
import Modele.Promotion;
import Modele.Produit;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;
import java.util.List;

/**
 * @author Minh-Duc PHAN
 * Classe mettant en avant les produits ayant des promotions
 */
public class VentesFlashPanel extends JPanel {
    private static final String IMAGE_DIR = "images/produits/";
    private VentesFlashController controller;
    private JPanel contentPanel;

    public VentesFlashPanel(VentesFlashController controller) {
        this.controller = controller;
        setLayout(new BorderLayout());
        setBackground(Color.decode("#f5f5f5"));

        // Panel de contenu avec scrolling
        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(Color.decode("#f5f5f5"));
        contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        add(scrollPane, BorderLayout.CENTER);

        // Titre
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(Color.decode("#FF5252")); // Rouge pour les promos
        headerPanel.setPreferredSize(new Dimension(0, 80));
        headerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20));
        headerPanel.setBorder(new EmptyBorder(10, 20, 10, 20));

        JLabel titleLabel = new JLabel("VENTES FLASH");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel);

        add(headerPanel, BorderLayout.NORTH);

        afficherPromotions();
    }


    private void afficherPromotions() {
        contentPanel.removeAll();
        List<Promotion> promotions = controller.getPromotions();

        if (promotions.isEmpty()) {
            JLabel emptyLabel = new JLabel("Aucune promotion disponible");
            emptyLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
            emptyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            contentPanel.add(emptyLabel);
            return;
        }

        for (Promotion promo : promotions) {
            Produit produit = controller.getProduitById(promo.getId_produit());
            if (produit != null) {
                contentPanel.add(creerCartePromo(produit, promo));
                contentPanel.add(Box.createRigidArea(new Dimension(0, 15)));
            }
        }

        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private JPanel creerCartePromo(Produit produit, Promotion promo) {
        JPanel carte = new JPanel(new BorderLayout());
        carte.setBackground(Color.WHITE);
        carte.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.decode("#e0e0e0")),
                new EmptyBorder(15, 15, 15, 15)
        ));
        carte.setMaximumSize(new Dimension(Integer.MAX_VALUE, 150));

        // Partie gauche : Image et infos produit
        JPanel leftPanel = new JPanel(new BorderLayout(10, 0));
        leftPanel.setBackground(Color.WHITE);
        leftPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));

        // Image produit
        ImageIcon icon = chargerImage(produit.getImages());
        JLabel imageLabel = new JLabel(new ImageIcon(icon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH)));
        imageLabel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        // Infos produit
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(Color.WHITE);

        JLabel nomLabel = new JLabel(produit.getNom());
        nomLabel.setFont(new Font("SansSerif", Font.BOLD, 14));

        JLabel descLabel = new JLabel(produit.getDescription());
        descLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        descLabel.setForeground(Color.GRAY);

        // Prix
        JPanel prixPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        prixPanel.setBackground(Color.WHITE);

        JLabel prixOrigine = new JLabel(String.format("%.2f€", produit.getPrix()* promo.getQuantite()));
        prixOrigine.setFont(new Font("SansSerif", Font.PLAIN, 12));
        prixOrigine.setForeground(Color.GRAY);

        JLabel fleche = new JLabel("→");

        JLabel prixPromo = new JLabel(String.format("%.2f€", promo.getPrix()));
        prixPromo.setFont(new Font("SansSerif", Font.BOLD, 14));
        prixPromo.setForeground(Color.decode("#FF5252"));

        prixPanel.add(prixOrigine);
        prixPanel.add(fleche);
        prixPanel.add(prixPromo);

        // Détails promo
        JLabel promoLabel = new JLabel("Promo: " + promo.getQuantite() + " unités");
        promoLabel.setFont(new Font("SansSerif", Font.ITALIC, 12));

        infoPanel.add(nomLabel);
        infoPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        infoPanel.add(descLabel);
        infoPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        infoPanel.add(prixPanel);
        infoPanel.add(promoLabel);

        leftPanel.add(imageLabel, BorderLayout.WEST);
        leftPanel.add(infoPanel, BorderLayout.CENTER);

        // Bouton Ajouter
        JButton ajouterButton = new JButton("Ajouter");
        styliserBouton(ajouterButton, Color.decode("#4CAF50"), 12);
        ajouterButton.setPreferredSize(new Dimension(100, 30));
        ajouterButton.addActionListener(e -> {
            // Appel au contrôleur pour ajouter le produit en promo
            controller.ajouterProduitEnPromo(produit, promo.getQuantite());
            JOptionPane.showMessageDialog(this,
                    promo.getQuantite() + " x " + produit.getNom() + " ajouté(s) au panier !",
                    "Succès",
                    JOptionPane.INFORMATION_MESSAGE);
        });

        carte.add(leftPanel, BorderLayout.CENTER);
        carte.add(ajouterButton, BorderLayout.EAST);

        return carte;
    }

    private void styliserBouton(JButton bouton, Color couleur, float taillePolice) {
        bouton.setBackground(couleur);
        bouton.setForeground(Color.WHITE);
        bouton.setFocusPainted(false);
        bouton.setFont(bouton.getFont().deriveFont(Font.BOLD).deriveFont(taillePolice));
        bouton.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
    }

    private ImageIcon chargerImage(String imagePath) {
        try {
            String fullPath = IMAGE_DIR + imagePath;
            if (new File(fullPath).exists()) {
                ImageIcon originalIcon = new ImageIcon(fullPath);
                Image scaledImage = originalIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
                return new ImageIcon(scaledImage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ImageIcon(IMAGE_DIR + "default.png");
    }

    public void rafraichirPromotion() {
        afficherPromotions();
    }
}