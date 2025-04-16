package DAO;

import DAO.DatabaseConnection;
import DAO.ProduitDAO;
import Modele.Produit;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProduitDAOImpl implements ProduitDAO {
    @Override
    public Produit getById(int id) {
        String sql = "SELECT * FROM produit WHERE ID = ?";
        try (Connection conn = DAO.DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Produit(
                        rs.getInt("ID"),
                        rs.getString("Marque"),
                        rs.getDouble("Prix"),
                        rs.getString("Nom"),
                        rs.getString("Descritpion"),
                        rs.getString("Image")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Produit> getAll() {
        List<Produit> produits = new ArrayList<>();
        String sql = "SELECT * FROM produit";

        try (Connection conn = DAO.DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                produits.add(new Produit(
                        rs.getInt("ID"),
                        rs.getString("Marque"),
                        rs.getDouble("Prix"),
                        rs.getString("Nom"),
                        rs.getString("Descritpion"),
                        rs.getString("Image")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produits;
    }

    @Override
    public boolean insert(Produit produit) {
        String sql = "INSERT INTO produit (Marque, Nom, Prix, Descritpion, Image) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DAO.DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, produit.getMarque());
            stmt.setString(2, produit.getNom());
            stmt.setDouble(3, produit.getPrix());
            stmt.setString(4, produit.getDescription());
            stmt.setString(5, produit.getImage());

            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Produit produit) {
        String sql = "UPDATE produit SET Marque = ?, Nom = ?, Prix = ?, Descritpion = ?, Image = ? WHERE ID = ?";
        try (Connection conn = DAO.DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, produit.getMarque());
            stmt.setString(2, produit.getNom());
            stmt.setDouble(3, produit.getPrix());
            stmt.setString(4, produit.getDescription());
            stmt.setString(5, produit.getImage());
            stmt.setInt(6, produit.getId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM produit WHERE ID = ?";
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
