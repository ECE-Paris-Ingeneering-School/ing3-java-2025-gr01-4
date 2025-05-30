package DAO;

import java.util.List;
import Modele.Utilisateur;

public interface UtilisateurDAO {
    Modele.Utilisateur getById(int id);
    List<Modele.Utilisateur> getAll();
    public void ajouter(Modele.Utilisateur utilisateur);
    public Utilisateur modifier(Utilisateur utilisateur);
    public void supprimer(int id);
    public boolean save(Utilisateur utilisateur);
    boolean utilisateurexistant(String nom, String email); // Méthode à ajouter
}
