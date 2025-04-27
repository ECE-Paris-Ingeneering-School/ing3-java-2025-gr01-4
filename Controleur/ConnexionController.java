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
}