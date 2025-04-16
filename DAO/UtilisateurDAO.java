package DAO;

import java.util.List;
import Modele.Utilisateur;

public interface UtilisateurDAO {
    Modele.Utilisateur getById(int id);
    List<Modele.Utilisateur> getAll();
    boolean ajouter(Modele.Utilisateur utilisateur);
    boolean modifier(Utilisateur utilisateur);
    boolean supprimer(int id);
}
