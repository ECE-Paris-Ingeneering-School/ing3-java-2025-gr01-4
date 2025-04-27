package DAO;
import DAO.DatabaseConnection;
import Modele.Utilisateur;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UtilisateurDAOImpl implements UtilisateurDAO {
    @Override
    public Modele.Utilisateur getById(int id) {
        String sql = "SELECT * FROM utilisateur WHERE ID = ?";
        try (Connection conn = DAO.DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Modele.Utilisateur(
                        rs.getInt("ID"),
                        rs.getString("Nom"),
                        rs.getString("Mot_De_Passe"),
                        rs.getString("Mail"),
                        rs.getBoolean("Sexe"),
                        rs.getBoolean("Admin")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Modele.Utilisateur> getAll() {
        List<Modele.Utilisateur> utilisateurs = new ArrayList<>();
        String sql = "SELECT * FROM utilisateur";

        try (Connection conn = DAO.DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                utilisateurs.add(new Modele.Utilisateur(
                        rs.getInt("ID"),
                        rs.getString("Nom"),
                        rs.getString("Mot_De_Passe"),
                        rs.getString("Mail"),
                        rs.getBoolean("Sexe"),
                        rs.getBoolean("Admin")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return utilisateurs;
    }

    @Override
    public void ajouter(Modele.Utilisateur utilisateur) {
        String sql = "INSERT INTO utilisateur (Mail, Mot_De_Passe, Nom, Sexe, Admin) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DAO.DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, utilisateur.getMail());
            stmt.setString(2, utilisateur.getMot_de_passe());
            stmt.setString(3, utilisateur.getNom());
            stmt.setBoolean(4, utilisateur.getSexe());
            stmt.setBoolean(5, utilisateur.isAdmin());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        utilisateur.setId(generatedKeys.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Utilisateur modifier(Utilisateur utilisateur) {
        String sql = "UPDATE utilisateur SET Mail = ?,Nom = ?, Mot_De_Passe = ?, Admin = ? WHERE ID = ?";
        try (Connection conn = DAO.DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, utilisateur.getMail());
            stmt.setString(2, utilisateur.getNom());
            stmt.setString(3, utilisateur.getMot_de_passe());
            stmt.setBoolean(4, utilisateur.isAdmin());
            stmt.setInt(5, utilisateur.getId());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                return utilisateur; // modif réussie
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // modif échouée
    }

    @Override
    public void supprimer(int id) {
        String sql = "DELETE FROM utilisateur WHERE ID = ?";
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

    @Override
    public boolean save(Utilisateur utilisateur) {
        try {
            ajouter(utilisateur); // Utilise ta méthode ajouter déjà prête
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public boolean utilisateurexistant(String nom, String email) {
        String query = "SELECT COUNT(*) FROM utilisateur WHERE Mail = ?";
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // Si le compte existe déjà, on retourne true
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Si pas de correspondance, retourne false
    }



}
