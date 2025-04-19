package DAO;
import java.util.List;
import Modele.Adresse;

public interface AdresseDAO {
    Adresse getAdresse(int id);
    List<Adresse> getAdresses();
    public void ajouter(Adresse adresse);
    public Adresse modifier(Adresse adresse);
    public void supprimer(int id);

}
