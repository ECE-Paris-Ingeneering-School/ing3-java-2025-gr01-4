package Vue;

import Modele.Produit;
import DAO.ProduitDAO;
import DAO.ProduitDAOImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class VentePanel extends JPanel {
    private ProduitDAO produitDAO;

    public VentePanel(CardLayout cardLayout, JPanel cardPanel) {
        this.produitDAO = new ProduitDAOImpl();
        initUI(cardLayout, cardPanel);
    }

    private void initUI(CardLayout cardLayout, JPanel cardPanel) {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Bande bleue en haut
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(Color.decode("#4682A9"));
        headerPanel.setPreferredSize(new Dimension(0, 80));
        headerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20));

        JLabel titre = new JLabel("Vendre un article");
        titre.setFont(new Font("SansSerif", Font.BOLD, 20));
        titre.setForeground(Color.WHITE);
        headerPanel.add(titre);

        add(headerPanel, BorderLayout.NORTH);

        // Corps principal
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        JTextField nomProduit = new JTextField("Nom du produit");
        nomProduit.setMaximumSize(new Dimension(300, 30));
        nomProduit.setForeground(Color.GRAY);
        ajouterFocusListener(nomProduit, "Nom du produit");

        mainPanel.add(nomProduit);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JTextField description = new JTextField("Description");
        description.setMaximumSize(new Dimension(300, 30));
        description.setForeground(Color.GRAY);
        ajouterFocusListener(description, "Description");

        mainPanel.add(description);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JTextField prix = new JTextField("Prix");
        prix.setMaximumSize(new Dimension(300, 30));
        prix.setForeground(Color.GRAY);
        ajouterFocusListener(prix, "Prix");

        mainPanel.add(prix);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JTextField quantite = new JTextField("Quantité");
        quantite.setMaximumSize(new Dimension(300, 30));
        quantite.setForeground(Color.GRAY);
        ajouterFocusListener(quantite, "Quantité");

        mainPanel.add(quantite);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        String[] categories = {"Électronique", "Vêtements", "Maison", "Livres", "Autre"};
        JComboBox<String> categorie = new JComboBox<>(categories);
        categorie.setMaximumSize(new Dimension(300, 30));
        mainPanel.add(categorie);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        JButton boutonSoumettre = new JButton("Soumettre");
        styliserBouton(boutonSoumettre);
        boutonSoumettre.setAlignmentX(Component.CENTER_ALIGNMENT);

        boutonSoumettre.addActionListener(e -> {
            try {
                Produit nouveauProduit = new Produit();
                nouveauProduit.setNom(nomProduit.getText());
                nouveauProduit.setDescription(description.getText());
                nouveauProduit.setPrix(Double.parseDouble(prix.getText()));
                nouveauProduit.setQuantite(Integer.parseInt(quantite.getText()));
                nouveauProduit.setMarque(categorie.getSelectedItem().toString());
                nouveauProduit.setImages("default.png");

                produitDAO.ajouter(nouveauProduit);
                JOptionPane.showMessageDialog(this, "Produit ajouté avec succès!");

                // Réinitialisation des champs
                nomProduit.setText("Nom du produit");
                description.setText("Description");
                prix.setText("Prix");
                quantite.setText("Quantité");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Veuillez entrer un prix valide", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });

        mainPanel.add(boutonSoumettre);
        add(mainPanel, BorderLayout.CENTER);
    }

    private void styliserBouton(JButton bouton) {
        bouton.setBackground(Color.decode("#4682A9"));
        bouton.setForeground(Color.WHITE);
        bouton.setFocusPainted(false);
        bouton.setFont(new Font("SansSerif", Font.BOLD, 14));
    }

    private void ajouterFocusListener(JTextField champ, String texteParDefaut) {
        champ.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (champ.getText().equals(texteParDefaut)) {
                    champ.setText("");
                    champ.setForeground(Color.BLACK);
                }
            }

            public void focusLost(FocusEvent e) {
                if (champ.getText().isEmpty()) {
                    champ.setText(texteParDefaut);
                    champ.setForeground(Color.GRAY);
                }
            }
        });
    }
}
