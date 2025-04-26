package DAO;
import java.util.List;
import Modele.Commande;

public interface CommandeDAO {
    Commande getById(int id);
    List<Commande> getAll();
    List<Commande> getByClientId(int clientId);
    void ajouter(Commande commande);
    Commande modifier(Commande commande);
    void supprimer(Commande achat);
    public Commande chercher(int clientID, int produitID);
}