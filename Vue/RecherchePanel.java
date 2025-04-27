package Vue;

import Controleur.RechercheController;
import Modele.Produit;
import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class RecherchePanel extends JPanel {
    private RechercheController controleur;

    public RecherchePanel(JPanel conteneurPrincipal) {
        this.controleur = new RechercheController(conteneurPrincipal);
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Bande bleue en haut
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(Color.decode("#4682A9"));
        headerPanel.setPreferredSize(new Dimension(0, 80));
        headerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20));

        JLabel titre = new JLabel("Recherche");
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
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        JButton boutonSoumettre = new JButton("Rechercher");
        styliserBouton(boutonSoumettre);
        boutonSoumettre.setAlignmentX(Component.CENTER_ALIGNMENT);

        boutonSoumettre.addActionListener(e -> {
                String ProduitRechercher = nomProduit.getText();

                //vérifie que le texte n'est pas vide
                if (ProduitRechercher.isEmpty() || ProduitRechercher.equals("Nom du produit")) {
                    JOptionPane.showMessageDialog(this, "Veuillez entrer un nom de produit valide.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                System.out.println(ProduitRechercher);

                controleur.afficherProduitsParRecherche(ProduitRechercher);
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
