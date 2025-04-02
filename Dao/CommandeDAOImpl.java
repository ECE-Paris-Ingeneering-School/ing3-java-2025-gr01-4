package Dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommandeDAOImpl implements CommandeDAO {
    @Override
    public Commande getById(int id) {
        String sql = "SELECT * FROM commande WHERE ID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Commande(
                    rs.getInt("ID"),
                    rs.getInt("ID_Client"),
                    rs.getInt("ID_Produit"),
                    rs.getInt("Quantite"),
                    rs.getDouble("Prix"),
                    rs.getDate("Date")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Commande> getAll() {
        List<Commande> commandes = new ArrayList<>();
        String sql = "SELECT * FROM commande";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                commandes.add(new Commande(
                    rs.getInt("ID"),
                    rs.getInt("ID_Client"),
                    rs.getInt("ID_Produit"),
                    rs.getInt("Quantite"),
                    rs.getDouble("Prix"),
                    rs.getDate("Date")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return commandes;
    }

    @Override
    public boolean insert(Commande commande) {
        String sql = "INSERT INTO commande (ID_Client, ID_Produit, Quantite, Prix, Date) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, commande.getIdClient());
            stmt.setInt(2, commande.getIdProduit());
            stmt.setInt(3, commande.getQuantite());
            stmt.setDouble(4, commande.getPrix());
            stmt.setDate(5, new java.sql.Date(commande.getDate().getTime()));

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        commande.setId(generatedKeys.getInt(1));
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
    public boolean update(Commande commande) {
        String sql = "UPDATE commande SET ID_Client = ?, ID_Produit = ?, Quantite = ?, Prix = ?, Date = ? WHERE ID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, commande.getIdClient());
            stmt.setInt(2, commande.getIdProduit());
            stmt.setInt(3, commande.getQuantite());
            stmt.setDouble(4, commande.getPrix());
            stmt.setDate(5, new java.sql.Date(commande.getDate().getTime()));
            stmt.setInt(6, commande.getId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM commande WHERE ID = ?";
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