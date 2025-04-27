package Controleur;

import DAO.UtilisateurDAO;
import DAO.UtilisateurDAOImpl;
import Modele.Utilisateur;
import Vue.ComptePanel;
import Vue.Connexion;
import Vue.ConnexionPanel;
import Vue.PanierPanel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * @author Pierre-Louis CHARBONNIER
 * Contrôleur gérant la logique de connexion des utilisateurs.
 * Fait le lien entre la vue (ConnexionPanel) et le modèle (Utilisateur).
 */
public class ConnexionController {
    private ConnexionPanel view;
    private CardLayout cardLayout;
    private JPanel contenuCentral;
    private Connexion fenetreConnexion;

    /**
     * Constructeur du contrôleur de connexion.
     *
     * @param view La vue ConnexionPanel à contrôler
     * @param cardLayout Le gestionnaire de disposition pour la navigation
     * @param contenuCentral Le panel principal contenant les différentes vues
     * @param fenetreConnexion La barre de menu principale de l'application
     */
    public ConnexionController(ConnexionPanel view, CardLayout cardLayout,
                               JPanel contenuCentral, Connexion fenetreConnexion) {
        this.view = view;
        this.cardLayout = cardLayout;
        this.contenuCentral = contenuCentral;
        this.fenetreConnexion = fenetreConnexion;
    }

    /*public void attemptLogin() {
        String identifiant = view.getEmail();
        String mdp = view.getPassword();

        UtilisateurDAO utilisateurDAO = new UtilisateurDAOImpl();
        List<Utilisateur> utilisateurs = utilisateurDAO.getAll();

        for (Utilisateur u : utilisateurs) {
            if (u.getMail().equals(identifiant) && u.getMot_de_passe().equals(mdp)) {
                JOptionPane.showMessageDialog(null, "Connexion réussie !");
                Utilisateur.setUtilisateurConnecte(u);

                ComptePanel comptePanel = new ComptePanel(cardLayout, contenuCentral, fenetreConnexion);
                PanierPanel panierPanel = new PanierPanel();
                contenuCentral.add(comptePanel, "Compte");
                contenuCentral.add(panierPanel, "Panier");

                fenetreConnexion.mettreAJourMenu();

                cardLayout.show(contenuCentral, "VentesFlash");
                return;
            }
        }

        JOptionPane.showMessageDialog(null, "Identifiant ou mot de passe incorrect.", "Erreur", JOptionPane.ERROR_MESSAGE);
    }*/
}