package Vue;

import Modele.Produit;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.io.File;

public class ProduitPanel extends JPanel {

    private static final String IMAGE_DIR = "images/produits/";

    public ProduitPanel(List<Produit> produits) {
        setLayout(new GridLayout(0, 2, 10, 10)); // 0 pour un nombre dynamique de lignes
        setBackground(Color.decode("#87bcd6"));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        for (Produit produit : produits) {
            JPanel produitCard = creerCarteProduit(produit);
            add(produitCard);
        }
    }

    private JPanel creerCarteProduit(Produit produit) {
        // Panel principal de la carte
        JPanel carte = new JPanel(new BorderLayout(10, 10));
        carte.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        carte.setBackground(Color.WHITE);

        // Chargement de l'image
        ImageIcon icon = chargerImage(produit.getImages());
        JLabel imageLabel = new JLabel(icon);
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        carte.add(imageLabel, BorderLayout.CENTER);

        // Panel pour les infos texte
        JPanel infoPanel = new JPanel(new GridLayout(3, 1, 5, 5));
        infoPanel.setBackground(Color.WHITE);

        // Nom du produit
        JLabel nomLabel = new JLabel(produit.getNom(), SwingConstants.CENTER);
        nomLabel.setFont(new Font("Arial", Font.BOLD, 14));
        infoPanel.add(nomLabel);

        // Prix
        JLabel prixLabel = new JLabel(String.format("%.2f €", produit.getPrix()), SwingConstants.CENTER);
        prixLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        infoPanel.add(prixLabel);

        // Description (avec tooltip si trop long)
        JTextArea descArea = new JTextArea(produit.getDescription());
        descArea.setEditable(false);
        descArea.setLineWrap(true);
        descArea.setWrapStyleWord(true);
        descArea.setBackground(Color.WHITE);
        if (produit.getDescription().length() > 50) {
            descArea.setToolTipText(produit.getDescription());
        }
        infoPanel.add(new JScrollPane(descArea));

        carte.add(infoPanel, BorderLayout.SOUTH);

        return carte;
    }

    private ImageIcon chargerImage(String imagePath) {
        try {
            String fullPath = IMAGE_DIR + imagePath;
            if (new File(fullPath).exists()) {
                ImageIcon originalIcon = new ImageIcon(fullPath);
                // Redimensionner l'image pour uniformité
                Image scaledImage = originalIcon.getImage().getScaledInstance(
                        200, 200, Image.SCALE_SMOOTH);
                return new ImageIcon(scaledImage);
            } else {
                // Image par défaut si non trouvée
                return new ImageIcon(IMAGE_DIR + "default.png");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ImageIcon(IMAGE_DIR + "default.png");
        }
    }
}