package DAO;
import java.util.List;
import Modele.Adresse;

public interface AdresseDAO {
    Adresse getAdresse(int id);
    List<Adresse> getAdresses();
    boolean insert(Adresse adresse);
    boolean update(Adresse adresse);
    boolean delete(int id);

}
