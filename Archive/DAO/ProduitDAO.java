package Archive.DAO;
import java.util.List;
import Archive.Modele.Produit;

public interface ProduitDAO {
    Produit getById(int id);
    List<Produit> getAll();
    boolean insert(Produit produit);
    boolean update(Produit produit);
    boolean delete(int id);
}