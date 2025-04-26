package Vue;

import javax.swing.*;
import java.awt.*;
import Modele.Utilisateur;
import DAO.UtilisateurDAO;
import DAO.UtilisateurDAOImpl;

public class ComptePanel extends JPanel {
    public ComptePanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.decode("#87bcd6"));

        //Récupère les informations de l'utilisateur connecté
        Utilisateur utilisateur = Utilisateur.getUtilisateurConnecte(); //Utilisation des infos du compte connecté
        if (utilisateur != null) {
            String[] infos = {
                    "Nom: " + utilisateur.getNom(),
                    "Email: " + utilisateur.getMail(),
                    "Mot de passe: " + utilisateur.getMot_de_passe(),
                    "Sexe: " + (utilisateur.getSexe() ? "Homme" : "Femme"),
                    "Admin: " + (utilisateur.isAdmin() ? "Oui" : "Non")
            };
            for (String info : infos) {
                JLabel label = new JLabel(info);
                label.setAlignmentX(Component.CENTER_ALIGNMENT);
                label.setFont(new Font("SansSerif", Font.PLAIN, 16));
                add(Box.createRigidArea(new Dimension(0, 10)));
                add(label);
            }
        }else{ //S'il n'est pas connecté on affiche cette page plutôt
            String[] infos = {
                    "Vous n'êtes pas connecté"
            };
            for (String info : infos) {
                JLabel label = new JLabel(info);
                label.setAlignmentX(Component.CENTER_ALIGNMENT);
                label.setFont(new Font("SansSerif", Font.PLAIN, 16));
                add(Box.createRigidArea(new Dimension(0, 10)));
                add(label);
            }
        }
    }
}