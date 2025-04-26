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
                        rs.getInt("Nom"),
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

    public List<Produit> getCategorie(String categorie) {
        List<Produit> produits = new ArrayList<>();
        String sql = "SELECT * FROM produit WHERE MARQUE = '"+categorie+"'";

        try (Connection conn = DAO.DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                produits.add(new Produit(
                        rs.getInt("ID"),
                        rs.getString("Marque"),
                        rs.getDouble("Prix"),
                        rs.getInt("Quantite"),
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
                        rs.getInt("Quantite"),
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
    public void ajouter(Produit produit) {
        String sql = "INSERT INTO produit (Marque, Nom, Prix, Quantite, Descritpion, Image) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DAO.DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, produit.getMarque());
            stmt.setString(2, produit.getNom());
            stmt.setDouble(3, produit.getPrix());
            stmt.setInt(4, produit.getQuantite());
            stmt.setString(5, produit.getDescription());
            stmt.setString(6, produit.getImages());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        produit.setId(generatedKeys.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Produit modifier(Produit produit) {
        String sql = "UPDATE produit SET Marque = ?, Nom = ?, Prix = ?, Quantite = ?, Descritpion = ?, Image = ? WHERE ID = ?";
        try (Connection conn = DAO.DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, produit.getMarque());
            stmt.setString(2, produit.getNom());
            stmt.setDouble(3, produit.getPrix());
            stmt.setInt(4, produit.getQuantite());
            stmt.setString(5, produit.getDescription());
            stmt.setString(6, produit.getImages());
            stmt.setInt(7, produit.getId());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                return produit; // modif réussie
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // modif échouée
    }

    @Override
    public void supprimer(int id) {
        String sql = "DELETE FROM produit WHERE ID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                System.out.println("Aucune ligne supprimée pour l'ID : " + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
