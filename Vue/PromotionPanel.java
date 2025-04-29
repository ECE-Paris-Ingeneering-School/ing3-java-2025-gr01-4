package Vue;

import Modele.Promotion;
import DAO.PromotionDAO;
import DAO.PromotionDAOImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
/**
 * @author Minh-Duc PHAN
 */

/**
 * Classe pour l'ajout d'un nouveau produit
 */
public class PromotionPanel extends JPanel {
    private PromotionDAO promotionDAO;

    /**
     * COnstructeur de la classe
     * Initialise le panel
     * @param cardLayout gestionnaire des cartes
     * @param cardPanel panneau contenant les cartes
     */

    public PromotionPanel(CardLayout cardLayout, JPanel cardPanel) {
        this.promotionDAO = new PromotionDAOImpl();
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Bande bleue en haut
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(Color.decode("#4682A9"));
        headerPanel.setPreferredSize(new Dimension(0, 80));
        headerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20));

        JLabel titre = new JLabel("Promotion d'un article");
        titre.setFont(new Font("SansSerif", Font.BOLD, 20));
        titre.setForeground(Color.WHITE);
        headerPanel.add(titre);

        add(headerPanel, BorderLayout.NORTH);

        // Corps principal
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        JTextField IDProduit = new JTextField("ID du produit");
        IDProduit.setMaximumSize(new Dimension(300, 30));
        IDProduit.setForeground(Color.GRAY);
        ajouterFocusListener(IDProduit, "ID du produit");

        mainPanel.add(IDProduit);
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

        JButton boutonSoumettre = new JButton("Soumettre");
        styliserBouton(boutonSoumettre);
        boutonSoumettre.setAlignmentX(Component.CENTER_ALIGNMENT);

        boutonSoumettre.addActionListener(e -> {
            try {
                Promotion nouveaupromotion = new Promotion();
                nouveaupromotion.setId_produit(Integer.parseInt(IDProduit.getText()));
                nouveaupromotion.setPrix(Double.parseDouble(prix.getText()));
                nouveaupromotion.setQuantite(Integer.parseInt(quantite.getText()));

                promotionDAO.ajouter(nouveaupromotion);
                JOptionPane.showMessageDialog(this, "Produit ajouté avec succès!");

                // Réinitialisation des champs
                IDProduit.setText("ID du produit");
                prix.setText("Prix");
                quantite.setText("Quantité");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Veuillez entrer un prix valide", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });

        mainPanel.add(boutonSoumettre);
        add(mainPanel, BorderLayout.CENTER);
    }

    /**
     * Permet de donner un visuel aux boutons
     * @param bouton bouton qui sera styliser
     */
    private void styliserBouton(JButton bouton) {
        bouton.setBackground(Color.decode("#4682A9"));
        bouton.setForeground(Color.WHITE);
        bouton.setFocusPainted(false);
        bouton.setFont(new Font("SansSerif", Font.BOLD, 14));
    }

    /**
     * Cette méthode permet de modifier le champ de texte du mot de passe pour qu'il soit cacher et qu'il y ai
     * un texte d'exemple
     * @param champ le texte à remplir
     * @param texteParDefaut le texte afficher sur le champ lorsqu'il n'est pas rempli
     */
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
