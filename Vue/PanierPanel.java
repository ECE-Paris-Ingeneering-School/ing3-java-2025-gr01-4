package Vue;

import Controleur.CommandeController;
import Modele.Utilisateur;
import Modele.Commande;
import Modele.Produit;
import DAO.CommandeDAO;
import DAO.CommandeDAOImpl;
import DAO.DatabaseConnection;
import DAO.ProduitDAO;
import DAO.ProduitDAOImpl;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jerry CHENG
 * Classe représentant le panier d'achat de l'utilisateur.
 * Affiche les produits ajoutés au panier, permet leur suppression et le calcul du total.
 */
public class PanierPanel extends JPanel {
    private CommandeDAO commandeDAO;
    private ProduitDAO produitDAO;
    private JPanel contentPanel;
    private CommandeController controller;
    private static final String IMAGE_DIR = "images/produits/";

    /**
     * Constructeur du panier. Initialise les composants et affiche le contenu.
     */
    public PanierPanel(CommandeController controller) {
        // Initialisation des DAO
        DatabaseConnection dbConnection = new DatabaseConnection();
        this.controller = controller;
        this.commandeDAO = new CommandeDAOImpl(dbConnection);
        this.produitDAO = new ProduitDAOImpl();

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

        // Titre du panier
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(Color.decode("#4682A9"));
        headerPanel.setPreferredSize(new Dimension(0, 80));
        headerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20));
        headerPanel.setBorder(new EmptyBorder(10, 20, 10, 20));

        JLabel titleLabel = new JLabel("VOTRE PANIER");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel);

        add(headerPanel, BorderLayout.NORTH);

        //afficherContenuPanier();
        this.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentShown(java.awt.event.ComponentEvent e) {
                rafraichirPanier();
            }
        });
    }

    /**
     * Affiche le contenu actuel du panier.
     * Gère les cas où l'utilisateur n'est pas connecté ou le panier est vide.
     */
    private void afficherContenuPanier() {
        contentPanel.removeAll();

        // Vérifier si un utilisateur est connecté
        Utilisateur utilisateur = Utilisateur.getUtilisateurConnecte();
        if (utilisateur == null) {
            JLabel loginMessage = new JLabel("Veuillez vous connecter pour voir votre panier");
            loginMessage.setFont(new Font("SansSerif", Font.PLAIN, 14));
            loginMessage.setAlignmentX(Component.CENTER_ALIGNMENT);
            contentPanel.add(loginMessage);
            contentPanel.revalidate();
            contentPanel.repaint();
            return;
        }

        // Récupérer les commandes de l'utilisateur
        List<Commande> commandes = controller.getCommandesUtilisateur(utilisateur.getId());

        if (commandes.isEmpty()) {
            JLabel emptyMessage = new JLabel("Votre panier est vide");
            emptyMessage.setFont(new Font("SansSerif", Font.PLAIN, 14));
            emptyMessage.setAlignmentX(Component.CENTER_ALIGNMENT);
            contentPanel.add(emptyMessage);
        } else {
            double total = 0;

            for (Commande commande : commandes) {
                Produit produit = produitDAO.getById(commande.getId_produit());
                if (produit != null) {
                    contentPanel.add(creerLigneProduit(produit, commande));
                    contentPanel.add(Box.createRigidArea(new Dimension(0, 15)));
                    total += commande.getPrix();
                }
            }

            // Ajouter le total
            contentPanel.add(Box.createVerticalGlue());

            JPanel totalPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            totalPanel.setBackground(Color.decode("#e0e0e0"));
            totalPanel.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createMatteBorder(1, 0, 0, 0, Color.GRAY),
                    new EmptyBorder(10, 20, 10, 20)
            ));

            JLabel totalLabel = new JLabel(String.format("Total: %.2f€", total));
            totalLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
            totalPanel.add(totalLabel);

            contentPanel.add(totalPanel);

            // Bouton de validation
            JButton validerButton = new JButton("Valider le panier");
            styliserBouton(validerButton, Color.decode("#4CAF50"), 16);
            validerButton.setPreferredSize(new Dimension(200, 40));
            validerButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            validerButton.addActionListener(e -> controller.validerPanier());

            contentPanel.add(Box.createRigidArea(new Dimension(0, 20)));
            contentPanel.add(validerButton);
        }

        contentPanel.revalidate();
        contentPanel.repaint();
    }

    /**
     * Crée une ligne d'affichage pour un produit du panier.
     *
     * @param produit Le produit à afficher
     * @param commande La commande associée au produit
     * @return JPanel représentant la ligne du produit
     */
    private JPanel creerLigneProduit(Produit produit, Commande commande) {
        JPanel ligne = new JPanel(new BorderLayout());
        ligne.setBackground(Color.WHITE);
        ligne.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.decode("#e0e0e0")),
                new EmptyBorder(15, 15, 15, 15)
        ));
        ligne.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));

        // Partie gauche : Image et infos produit
        JPanel leftPanel = new JPanel(new BorderLayout(10, 0));
        leftPanel.setBackground(Color.WHITE);
        leftPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));

        // Image produit (placeholder)
        ImageIcon icon = chargerImage(produit.getImages());
        JLabel imageLabel = new JLabel(new ImageIcon(icon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH)
        ));
        imageLabel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        // Infos produit
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(Color.WHITE);

        JLabel nomLabel = new JLabel(produit.getNom());
        nomLabel.setFont(new Font("SansSerif", Font.BOLD, 14));

        JLabel detailsLabel = new JLabel(String.format(
                "Quantité: %d | Total: %.2f€",
                commande.getQuantite(),
                commande.getPrix()
        ));
        detailsLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        detailsLabel.setForeground(Color.GRAY);

        infoPanel.add(nomLabel);
        infoPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        infoPanel.add(detailsLabel);

        leftPanel.add(imageLabel, BorderLayout.WEST);
        leftPanel.add(infoPanel, BorderLayout.CENTER);

        // Bouton Supprimer
        JButton supprimer = new JButton("Supprimer");
        styliserBouton(supprimer, Color.decode("#f44336"), 12);
        supprimer.setPreferredSize(new Dimension(100, 30));
        supprimer.addActionListener(e -> controller.supprimerCommande(commande, produit, this));


        ligne.add(leftPanel, BorderLayout.CENTER);
        ligne.add(supprimer, BorderLayout.EAST);

        return ligne;
    }





    /**
     * Applique un style commun aux boutons.
     *
     * @param bouton Le bouton à styliser
     * @param couleur La couleur de fond
     * @param taillePolice La taille de la police
     */
    private void styliserBouton(JButton bouton, Color couleur, float taillePolice) {
        bouton.setBackground(couleur);
        bouton.setForeground(Color.WHITE);
        bouton.setFocusPainted(false);
        bouton.setFont(bouton.getFont().deriveFont(Font.BOLD).deriveFont(taillePolice));
        bouton.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
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

    public void rafraichirPanier() {
        afficherContenuPanier();
    }

}