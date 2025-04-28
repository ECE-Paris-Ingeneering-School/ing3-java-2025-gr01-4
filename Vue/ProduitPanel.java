package Vue;

import DAO.CommandeDAOImpl;
import DAO.ProduitDAOImpl;
import Modele.Commande;
import Modele.Produit;
import Modele.Utilisateur;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.io.File;

/**
 * @author William BENOIT
 */
public class ProduitPanel extends JPanel {

    private static final String IMAGE_DIR = "images/produits/";
    private ProduitDAOImpl produitDAO;
    private CommandeDAOImpl commandeDAO;

    /**
     * Permet d'afficher la liste de produit entrée en paramètre.
     * @param produits, produitDAO et commandeDAO
     */
    public ProduitPanel(List<Produit> produits, ProduitDAOImpl produitDAO,
                        CommandeDAOImpl commandeDAO) {
        this.produitDAO = produitDAO;
        this.commandeDAO = commandeDAO;

        setLayout(new GridLayout(0, 2, 10, 10));
        setBackground(Color.decode("#87bcd6"));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        produits.forEach(produit -> add(creerCarteProduit(produit)));
    }

    /**
     * S'occupe de la mise en page de la présentation des produits sous forme de carte.
     * @param produit
     * @return JPanel carte
     */
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

        // Bouton Ajouter au panier
        JButton ajouterButton = new JButton("Ajouter au panier");
        ajouterButton.setBackground(Color.decode("#4CAF50"));
        ajouterButton.setForeground(Color.WHITE);
        ajouterButton.addActionListener(e -> ajouterCommande(produit));
        infoPanel.add(ajouterButton);

        carte.add(infoPanel, BorderLayout.SOUTH);

        return carte;
    }

    /**
     * Permet d'ajouter un produit à la commande.
     * @param produit
     */
    private void ajouterCommande(Produit produit) {
        try {
            // Vérifier si un utilisateur est connecté
            Utilisateur utilisateur = Utilisateur.getUtilisateurConnecte();
            if (utilisateur == null) {
                JOptionPane.showMessageDialog(this, "Veuillez vous connecter pour ajouter des articles", "Erreur", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Vérifier le stock
            if (produit.getQuantite() <= 0) {
                JOptionPane.showMessageDialog(this, "Stock épuisé", "Erreur", JOptionPane.WARNING_MESSAGE);
                return;
            }

            //  Préparer la commande
            Commande existante = commandeDAO.chercher(utilisateur.getId(), produit.getId());

            if (existante != null) {
                // Mise à jour de la quantité existante
                commandeDAO.ajouter(existante);

            } else {
                // Nouvelle commande
                Commande nouvelleCommande = new Commande(
                        0,
                        utilisateur.getId(),
                        produit.getId(),
                        1,
                        produit.getPrix(),
                        LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                );
                commandeDAO.ajouter(nouvelleCommande);
            }

            // Mise à jour du stock
            produit.setQuantite(produit.getQuantite() - 1);
            produitDAO.modifier(produit);

            JOptionPane.showMessageDialog(this,
                    produit.getNom() + " ajouté à vos commandes!",
                    "Succès",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Erreur lors de l'ajout: " + ex.getMessage(),
                    "Erreur",
                    JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    /**
     * Permet de charger l'image du produit.
     * Si le produit n'a pas d'image, retourne une image par défault.
     * @param imagePath
     * @return image
     */
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