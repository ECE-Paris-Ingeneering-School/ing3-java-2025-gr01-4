package DAO;

import DAO.DatabaseConnection;
import DAO.ProduitDAO;
import Modele.Produit;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author William BENOIT
 */

public class ProduitDAOImpl implements ProduitDAO {
    /**
     * Permet de rechercher un produit par Id et le retourne.
     * @param id
     * @return null
     */
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
                        rs.getInt("Quantite"),
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

    /**
     * Permet de rechercher une liste de produits par catégorie et retourne une liste de ceux-ci.
     * @param categorie
     * @return une liste de produits
     */
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

    /**
     * Permet de rechercher une liste de produits selon le nom et les retournent sous forme de liste.
     * @param recherche
     * @return une liste de produits
     */
    public List<Produit> getByRecherche(String recherche) {
        List<Produit> produits = new ArrayList<>();
        String sql = "SELECT * FROM produit WHERE NOM LIKE '%"+recherche+"%'";

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

    /**
     * Retourne l'ensemble des produits de la base de donnée.
     * @return
     */
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

    /**
     * Permet d'ajouter un produit à la base de donée
     * @param produit
     */
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

    /**
     * Permet de modifier un produit en prenant en paramètre le produit à modifier.
     * @param produit
     * @return
     */
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

    /**
     * Permet de supprimer un produit de la base de donnée en utilisant sont Id.
     * @param id
     */
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
