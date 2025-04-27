package Controleur;

import Vue.ComptePanel;
import Modele.Utilisateur;
import DAO.UtilisateurDAO;
import DAO.UtilisateurDAOImpl;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CompteController {

    private ComptePanel comptePanel;

    public CompteController(ComptePanel comptePanel) {
        this.comptePanel = comptePanel;
    }

    // Méthode pour gérer la déconnexion
    public void deconnexion() {
        Utilisateur.setUtilisateurConnecte(null);
        JOptionPane.showMessageDialog(comptePanel, "Vous êtes déconnecté !");
        comptePanel.getFenetreConnexion().mettreAJourMenuDeconnexion();
        comptePanel.getCardLayout().show(comptePanel.getContenuCentral(), "Connexion");
    }

    // Méthode pour gérer la modification des informations
    public void modifierInformations(String nouveauNom, String nouvelEmail, String nouveauMotDePasse) {
        if (nouveauNom.isEmpty() || nouvelEmail.isEmpty() || nouveauMotDePasse.isEmpty()) {
            JOptionPane.showMessageDialog(comptePanel, "Tous les champs doivent être remplis.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Utilisateur utilisateur = Utilisateur.getUtilisateurConnecte();
        utilisateur.setNom(nouveauNom);
        utilisateur.setMail(nouvelEmail);
        utilisateur.setMot_De_Passe(nouveauMotDePasse);

        UtilisateurDAO utilisateurDAO = new UtilisateurDAOImpl();
        utilisateurDAO.modifier(utilisateur);

        JOptionPane.showMessageDialog(comptePanel, "Informations mises à jour avec succès !");
    }
}
