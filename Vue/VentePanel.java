package Vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import Modele.Produit;
import DAO.ProduitDAO;
import DAO.ProduitDAOImpl;

public class VentePanel extends JPanel {
    private ProduitDAO produitDAO;

    public VentePanel(CardLayout cardLayout, JPanel cardPanel) {
        this.produitDAO = new ProduitDAOImpl();
        initUI(cardLayout, cardPanel);
    }

    private void initUI(CardLayout cardLayout, JPanel cardPanel) {
        setLayout(null);
        setBackground(Color.decode("#87bcd6"));
        
        JLabel logo = chargerLogo();
        add(logo);

        JLabel titre = new JLabel("Vendre un article");
        titre.setBounds(350, 20, 250, 33);
        titre.setFont(new Font("SansSerif", Font.BOLD, 20));
        titre.setForeground(Color.BLACK);
        add(titre);

        JTextField nomProduit = new JTextField("Nom du produit");
        nomProduit.setBounds(392, 102, 160, 25);
        nomProduit.setForeground(Color.GRAY);
        ajouterFocusListener(nomProduit, "Nom du produit");
        add(nomProduit);

        JTextField description = new JTextField("Description");
        description.setBounds(392, 142, 160, 25);
        description.setForeground(Color.GRAY);
        ajouterFocusListener(description, "Description");
        add(description);

        JTextField prix = new JTextField("Prix");
        prix.setBounds(392, 182, 160, 25);
        prix.setForeground(Color.GRAY);
        ajouterFocusListener(prix, "Prix");
        add(prix);

        JTextField quantite = new JTextField("Quantité");
        quantite.setBounds(392, 222, 160, 25);
        quantite.setForeground(Color.GRAY);
        ajouterFocusListener(quantite, "Quantité");
        add(quantite);

        String[] categories = {"Électronique", "Vêtements", "Maison", "Livres", "Autre"};
        JComboBox<String> categorie = new JComboBox<>(categories);
        categorie.setBounds(392, 262, 160, 25);
        add(categorie);

        JButton boutonSoumettre = new JButton("Soumettre");
        boutonSoumettre.setBounds(385, 310, 160, 30);
        styliserBouton(boutonSoumettre);
        add(boutonSoumettre);

        // Gestion de l'événement
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
                quantite.setText("Quantite");
                

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Veuillez entrer un prix valide", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    // Méthodes utilitaires (chargerLogo, styliserBouton, ajouterFocusListener)...
    private JLabel chargerLogo() {
        ImageIcon icon = new ImageIcon("src/Logo Vulpixia.png");
        Image img = icon.getImage().getScaledInstance(100, 60, Image.SCALE_SMOOTH);
        JLabel label = new JLabel(new ImageIcon(img));
        label.setBounds(20, 20, 100, 60);
        return label;
    }

    private void styliserBouton(JButton bouton) {
        bouton.setBackground(Color.decode("#4682A9"));
        bouton.setForeground(Color.WHITE);
        bouton.setFocusPainted(false);
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