package Vue;

import Controleur.CategorieControleur;
import Controleur.RechercheController;
import Modele.Produit;
import DAO.ProduitDAOImpl;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
import java.util.List;

public class RecherchePanel extends JPanel{
    private RechercheController controleur;

    public RecherchePanel(JPanel conteneurPrincipal) {
        this.controleur = new RechercheController(conteneurPrincipal);
        setLayout(null);
        setBackground(Color.decode("#87bcd6"));

        JLabel titre = new JLabel("Recherche");
        titre.setBounds(350, 20, 250, 33);
        titre.setFont(new Font("SansSerif", Font.BOLD, 20));
        titre.setForeground(Color.BLACK);
        add(titre, BorderLayout.CENTER);

        JTextField nomProduit = new JTextField("Nom du produit");
        nomProduit.setBounds(320, 100, 160, 25);
        nomProduit.setForeground(Color.GRAY);
        ajouterFocusListener(nomProduit, "Nom du produit");
        add(nomProduit);

        JButton boutonSoumettre = new JButton("Rechercher");
        boutonSoumettre.setBounds(320, 150, 160, 30);
        styliserBouton(boutonSoumettre);
        add(boutonSoumettre);

        boutonSoumettre.addActionListener(e -> {
            try {
                String ProduitRechercher = nomProduit.getText();
                System.out.println(ProduitRechercher);

                controleur.afficherProduitsParRecherche(ProduitRechercher);



            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Veuillez entrer un nom valide", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });

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
