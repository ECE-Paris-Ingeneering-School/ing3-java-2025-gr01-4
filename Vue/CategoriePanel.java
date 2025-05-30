package Vue;

import Controleur.CategorieControleur;
import DAO.DatabaseConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author William BENOIT
 */

public class CategoriePanel extends JPanel {
    private CategorieControleur controleur;
    private CategorieSelectionListener listener;

    public interface CategorieSelectionListener {
        void CategorieSelectionne(String categorie);
    }

    public static final String ACTION_CATEGORIE = "CATEGORIE_SELECTED";

    /**
     * Permet d'afficher les boutons avec le nom des différentes catégories de produits.
     * @param conteneurPrincipal, dbConnection
     */
    public CategoriePanel(JPanel conteneurPrincipal, DatabaseConnection dbConnection) {
        this.controleur = new CategorieControleur(conteneurPrincipal, dbConnection);
        setLayout(new GridLayout(2, 3, 10, 10));
        setBackground(Color.decode("#87bcd6"));

        String[] categories = {"Électronique", "Vêtements", "Maison", "Livres", "Sport", "Autre"};
        for (String categorie : categories) {
            JButton bouton = new JButton(categorie);
            styliserBouton(bouton);
            bouton.setActionCommand(categorie);
            bouton.addActionListener(this::fireCategorieEvent);
            add(bouton);
        }
    }

    /**
     * Listener permettant d'appeler CategorieControleur avec la catégorie sélectionnée lorsque l'utilisateur a cliqué.
     * @param e
     */
    private void fireCategorieEvent(ActionEvent e) {
        String categorie = e.getActionCommand();
        firePropertyChange(ACTION_CATEGORIE, null, categorie);
        System.out.println("CATEGORIE SELECTION: " + categorie);
        controleur.afficherProduitsParCategorie(categorie);
    }

    /**
     * Permet de changer la couleur d'un bouton lorsque la souris le survole.
     * @param bouton
     */
    private void styliserBouton(JButton bouton) {
        bouton.setBackground(Color.decode("#4682A9"));
        bouton.setForeground(Color.WHITE);
        bouton.setFocusPainted(false);
    }

}