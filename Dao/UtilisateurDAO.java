import java.util.List;

public interface UtilisateurDAO {
    Utilisateur getById(int id);
    List<Utilisateur> getAll();
    boolean insert(Utilisateur utilisateur);
    boolean update(Utilisateur utilisateur);
    boolean delete(int id);
}