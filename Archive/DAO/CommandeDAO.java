package Archive.DAO;
import java.util.List;
import Archive.Modele.Commande;

public interface CommandeDAO {
    Commande getById(int id);
    List<Commande> getAll();
    boolean insert(Commande commande);
    boolean update(Commande commande);
    boolean delete(int id);
}