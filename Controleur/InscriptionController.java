package Controleur;

import DAO.UtilisateurDAO;
import DAO.UtilisateurDAOImpl;
import Modele.Utilisateur;
import Vue.InscriptionPanel;

import javax.swing.*;

public class InscriptionController {
    private final InscriptionPanel inscriptionPanel;

    public InscriptionController(InscriptionPanel inscriptionPanel) {
        this.inscriptionPanel = inscriptionPanel;
    }

    public void inscrireUtilisateur(String nomComplet, String adresseMail, String motDePasseTexte, boolean sexe) {
        // Vérification si l'utilisateur existe déjà
        UtilisateurDAO verifieutilisateur = new UtilisateurDAOImpl();
        if (verifieutilisateur.utilisateurexistant(nomComplet, adresseMail)) {
            JOptionPane.showMessageDialog(null, "Un utilisateur avec cet email existe déjà.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Vérification des champs vides
        if (nomComplet.isEmpty() || adresseMail.isEmpty() || motDePasseTexte.isEmpty() || nomComplet.equals("Nom complet")
                || adresseMail.equals("Email")
                || motDePasseTexte.equals("Mot de passe")) {
            JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Créer un nouvel utilisateur
        Utilisateur nouvelUtilisateur = new Utilisateur(
                0,
                nomComplet,
                motDePasseTexte,
                adresseMail,
                sexe,
                false
        );

        // Sauvegarder l'utilisateur
        UtilisateurDAO utilisateurDAO = new UtilisateurDAOImpl();
        boolean success = utilisateurDAO.save(nouvelUtilisateur);

        if (success) {
            JOptionPane.showMessageDialog(null, "Inscription réussie !");
        } else {
            JOptionPane.showMessageDialog(null, "Erreur lors de l'inscription.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
}
