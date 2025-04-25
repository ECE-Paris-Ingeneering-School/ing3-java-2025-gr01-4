package Modele;

import java.sql.*;

public class UserModel {
    private Connection connection;

    public UserModel() {
        try {
            // Adapte cette ligne selon ton driver et ta BDD
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/ma_base", "utilisateur", "motdepasse"
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean validateUser(String username, String password) {
        try {
            String query = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            return rs.next(); // S'il trouve un résultat, c'est valide
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
