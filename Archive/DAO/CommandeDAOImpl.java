package Archive.DAO;

// import des packages
import java.sql.*;
import java.util.ArrayList;

import Archive.Modele.Commande;

/**
 * implémentation MySQL du stockage dans la base de données des méthodes définies dans l'interface
 * CommanderDao.
 */
public class CommandeDAOImpl implements CommandeDAO {
    // attribut privé pour l'objet du DaoFactoru
    private DatabaseConnection daoFactory;

    // constructeur dépendant de la classe DaoFactory
    public CommandeDAOImpl(DatabaseConnection daoFactory) {
        this.daoFactory = daoFactory;
    }
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
                        rs.getString("Date")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    /**
     * Récupérer de la base de données tous les objets des commandes des produits par les clients dans une liste
     * @return : liste retournée des objets des produits récupérés
     */
    public ArrayList<Commande> getAll() {
        ArrayList<Commande> listeCommandes = new  ArrayList<Commande>();

        /*
            Récupérer la liste des clients de la base de données dans listeProduits
        */
        try {
            // connexion
            Connection connexion = daoFactory.getConnection();;
            Statement statement = connexion.createStatement();

            // récupération de l'ordre de la requete
            ResultSet resultats = statement.executeQuery("select * from commande");

            // récupération du résultat de l'ordre
            ResultSetMetaData rsmd = resultats.getMetaData();

            // 	Se déplacer sur le prochain enregistrement : retourne false si la fin est atteinte
            while (resultats.next()) {
                // récupérer les 3 champs de la table commande
                int IdCommande = resultats.getInt(1);
                int clientId = resultats.getInt(2);
                int produitId = resultats.getInt(3);
                int quantite  = resultats.getInt(4);
                double prix  = resultats.getDouble(5);
                String date  = resultats.getString(6);

                // instancier un objet de Produit
                Commande achat = new Commande(IdCommande, clientId, produitId,quantite, prix, date);

                // ajouter ce produit à listeProduits
                listeCommandes.add(achat);
            }
        }
        catch (SQLException e) {
            //traitement de l'exception
            e.printStackTrace();
            System.out.println("Création de la liste des commandes impossible");
        }

        return listeCommandes;
    }

    @Override
    /**
     Ajouter une nouvelle commande d'un produit par un client en paramètre dans la base de données
     @params : achat = objet de la commande en paramètre à insérer dans la base de données
     */
    public void ajouter(Commande achat) {
        /*
            A COMPLETER
         */
        try{
            Connection connexion = daoFactory.getConnection();
            PreparedStatement preparedStatement = connexion.prepareStatement("INSERT INTO commande (ID_Client, ID_Produit, Quantite,Prix, Date) VALUES (?,?,?,?,?)");

            preparedStatement.setInt(1, achat.getIdClient());
            preparedStatement.setInt(2, achat.getIdProduit());
            preparedStatement.setInt(3, achat.getQuantite());
            preparedStatement.setDouble(4, achat.getPrix());
            preparedStatement.setString(5, achat.getDate());
            preparedStatement.executeUpdate();


        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Ajout de la commande impossible");
        }

    }

    @Override
    /**
     * Permet de chercher et récupérer un objet de Commander dans la base de données via ses clientID et produitID
     * en paramètres
     * @param : clientID et produitID
     * @return : objet de commande cherché et retourné
     */
    public Commande chercher(int clientID, int produitID){
        Commande achat = null;
        try {
            Connection connexion = daoFactory.getConnection();
            PreparedStatement preparedStatement = connexion.prepareStatement(
                    "SELECT * FROM commande WHERE ID_CLient = ? AND ID_Produit = ?"
            );
            preparedStatement.setInt(1, clientID);
            preparedStatement.setInt(2, produitID);
            ResultSet resultats = preparedStatement.executeQuery();
            if (resultats.next()) {
                int quantite = resultats.getInt("quantité");
                achat = new Commande(clientID, produitID, quantite);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur lors de la recherche de la commande");
        }
        return achat;
    }

    @Override
    /**
     * Permet de modifier les données du nom de l'objet de la classe Commander en paramètre
     * dans la base de données à partir de clientID et produitID de cet objet en paramètre
     * @param : achat = objet en paramètre de la classe Commander à mettre à jour
     * @return : objet achat en paramètre mis à jour  dans la base de données à retourner
     */
    public Commande modifier(Commande achat) {
        try {
            Connection connexion = daoFactory.getConnection();
            PreparedStatement preparedStatement = connexion.prepareStatement(
                    "UPDATE commande SET Quantite = ? AND Prix = ? WHERE ID_Client = ? AND ID_Produit = ?"
            );
            preparedStatement.setInt(1, achat.getQuantite());
            preparedStatement.setDouble(2, achat.getPrix());
            preparedStatement.setInt(3, achat.getIdClient());
            preparedStatement.setInt(4, achat.getIdProduit());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Modification de la commande impossible");
        }

        return achat;
    }

    @Override
    /**
     Supprimer un objet de la classe Commander en paramètre dans la base de données
     @params : product = objet de Produit en paramètre à supprimer de la base de données
     */
    public boolean delete (Commande achat){
        try {
            Connection connexion = daoFactory.getConnection();

            // Suppression des commandes liées au client (contrainte d'intégrité référentielle)
            String requeteCommandes = "DELETE FROM commande WHERE ID_Client = ? AND ID_Produit = ?";
            PreparedStatement psCommandes = connexion.prepareStatement(requeteCommandes);
            psCommandes.setInt(1, achat.getIdClient());
            psCommandes.setInt(2, achat.getIdProduit());
            psCommandes.executeUpdate();
            System.out.println("Commande supprimée avec succès");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Suppression du client impossible");
        }

    }

}
