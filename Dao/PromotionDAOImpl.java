import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PromotionDAOImpl implements PromotionDAO {
    @Override
    public Promotion getById(int id) {
        String sql = "SELECT * FROM promotion WHERE ID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Promotion(
                        rs.getInt("ID"),
                        rs.getInt("ID_Produit"),
                        rs.getInt("Quantite"),
                        rs.getDouble("Prix"),
                        rs.getInt("ID_Commande")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Promotion> getAll() {
        List<Promotion> promotions = new ArrayList<>();
        String sql = "SELECT * FROM promotion";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                promotions.add(new Promotion(
                        rs.getInt("ID"),
                        rs.getInt("ID_Produit"),
                        rs.getInt("Quantite"),
                        rs.getDouble("Prix"),
                        rs.getInt("ID_Commande")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return promotions;
    }

    @Override
    public boolean insert(Promotion promotion) {
        String sql = "INSERT INTO promotion (ID_Produit, Quantite, Prix, ID_Commande) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, promotion.getIdProduit());
            stmt.setInt(2, promotion.getQuantite());
            stmt.setDouble(3, promotion.getPrix());
            stmt.setInt(4, promotion.getIdCommande());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        promotion.setId(generatedKeys.getInt(1));
                    }
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Promotion promotion) {
        String sql = "UPDATE promotion SET ID_Produit = ?, Quantite = ?, Prix = ?, ID_Commande = ? WHERE ID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, promotion.getIdProduit());
            stmt.setInt(2, promotion.getQuantite());
            stmt.setDouble(3, promotion.getPrix());
            stmt.setInt(4, promotion.getIdCommande());
            stmt.setInt(5, promotion.getId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM promotion WHERE ID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}