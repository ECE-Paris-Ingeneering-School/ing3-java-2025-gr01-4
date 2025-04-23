package DAO;

import Modele.Promotion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PromotionDAOImpl implements PromotionDAO {
    @Override
    public Promotion getById(int id) {
        String sql = "SELECT * FROM promotion WHERE ID = ?";
        try (Connection conn = DAO.DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Promotion(
                        rs.getInt("ID"),
                        rs.getInt("ID_Produit"),
                        rs.getInt("Quantite"),
                        rs.getDouble("Prix")
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

        try (Connection conn = DAO.DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                promotions.add(new Promotion(
                        rs.getInt("ID"),
                        rs.getInt("ID_Produit"),
                        rs.getInt("Quantite"),
                        rs.getDouble("Prix")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return promotions;
    }

    @Override
    /*public void ajouter(Promotion promotion) {
        String sql = "INSERT INTO promotion (ID_Produit, Quantite, Prix, ID_Commande) VALUES (?, ?, ?, ?)";
        try (Connection conn = DAO.DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, promotion.getId_produit());
            stmt.setInt(2, promotion.getQuantite());
            stmt.setDouble(3, promotion.getPrix());

            int affectedRows = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }*/

    public void ajouter(Promotion promotion) {
        String sql = "INSERT INTO promotion (ID_Produit, Quantite, Prix) VALUES (?, ?, ?)";
        try (Connection conn = DAO.DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, promotion.getId_produit());
            stmt.setInt(2, promotion.getQuantite());
            stmt.setDouble(3, promotion.getPrix());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        promotion.setId(generatedKeys.getInt(1)); // Si tu as un ID auto-généré
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Promotion modifier(Promotion promotion) {
        String sql = "UPDATE promotion SET ID_Produit = ?, Quantite = ?, Prix = ?, ID_Commande = ? WHERE ID = ?";
        try (Connection conn = DAO.DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, promotion.getId_produit());
            stmt.setInt(2, promotion.getQuantite());
            stmt.setDouble(3, promotion.getPrix());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                return promotion; // mise à jour réussie
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // mise à jour échouée
    }

    @Override
    public void supprimer(int id) {
        String sql = "DELETE FROM promotion WHERE ID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                System.out.println("Aucune promotion supprimée pour l'ID : " + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
