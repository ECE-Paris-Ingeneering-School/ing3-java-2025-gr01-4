package Controleur;

import DAO.UtilisateurDAO;
import DAO.UtilisateurDAOImpl;
import Modele.Utilisateur;
import Vue.InscriptionPanel;

import javax.swing.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        //expression régulière pour valider un email
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        //^ correspond au début de la chaine et le $ correpond à la fin

        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(adresseMail);

        if (!matcher.matches()) {
            JOptionPane.showMessageDialog(null, "L'email saisi est invalide. Veuillez entrer un email valide.", "Erreur", JOptionPane.ERROR_MESSAGE);
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
