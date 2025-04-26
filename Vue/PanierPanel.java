package Vue;

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
import java.util.ArrayList;
import java.util.List;

public class PanierPanel extends JPanel {
    private CommandeDAO commandeDAO;
    private ProduitDAO produitDAO;
    private JPanel contentPanel;

    public PanierPanel() {
        // Initialisation des DAO
        DatabaseConnection dbConnection = new DatabaseConnection();
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
        headerPanel.setBorder(new EmptyBorder(10, 20, 10, 20));

        JLabel titleLabel = new JLabel("VOTRE PANIER");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel);

        add(headerPanel, BorderLayout.NORTH);

        afficherContenuPanier();
    }

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
        List<Commande> commandes = getCommandesUtilisateur(utilisateur.getId());

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
            validerButton.addActionListener(e -> validerPanier());

            contentPanel.add(Box.createRigidArea(new Dimension(0, 20)));
            contentPanel.add(validerButton);
        }

        contentPanel.revalidate();
        contentPanel.repaint();
    }

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
        JLabel imageLabel = new JLabel(new ImageIcon(
                new ImageIcon("chemin/vers/image.png").getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH)
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
        supprimer.addActionListener(e -> supprimerCommande(commande));

        ligne.add(leftPanel, BorderLayout.CENTER);
        ligne.add(supprimer, BorderLayout.EAST);

        return ligne;
    }

    private void supprimerCommande(Commande commande) {
        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Êtes-vous sûr de vouloir supprimer ce produit de votre panier ?",
                "Confirmation de suppression",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            commandeDAO.supprimer(commande);
            JOptionPane.showMessageDialog(this, "Produit supprimé du panier");
            afficherContenuPanier();
        }
    }

    private void validerPanier() {
        // Implémentez la logique de validation du panier ici
        JOptionPane.showMessageDialog(this, "Fonctionnalité de validation à implémenter");
    }

    private void styliserBouton(JButton bouton, Color couleur, float taillePolice) {
        bouton.setBackground(couleur);
        bouton.setForeground(Color.WHITE);
        bouton.setFocusPainted(false);
        bouton.setFont(bouton.getFont().deriveFont(Font.BOLD).deriveFont(taillePolice));
        bouton.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
    }

    private List<Commande> getCommandesUtilisateur(int userId) {
        List<Commande> toutesCommandes = commandeDAO.getAll();
        List<Commande> commandesUtilisateur = new ArrayList<>();

        for (Commande cmd : toutesCommandes) {
            if (cmd.getId_client() == userId) {
                commandesUtilisateur.add(cmd);
            }
        }
        return commandesUtilisateur;
    }
}