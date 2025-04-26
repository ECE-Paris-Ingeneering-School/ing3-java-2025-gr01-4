package DAO;

// import des packages
import DAO.CommandeDAO;
import DAO.DatabaseConnection;
import Modele.Commande;
import java.sql.*;
import java.util.ArrayList;

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
                int Id = resultats.getInt(1);
                int Id_client = resultats.getInt(2);
                int Id_produit = resultats.getInt(3);
                int quantite  = resultats.getInt(4);
                double prix = resultats.getDouble(5);
                String date = resultats.getString(6);

                // instancier un objet de Produit
                Commande achat = new Commande(Id, Id_client, Id_produit, quantite, prix, date);

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

    public ArrayList<Commande> getByClientId(int clientId) {
        ArrayList<Commande> commandes = new ArrayList<>();
        try {
            Connection connexion = daoFactory.getConnection();
            PreparedStatement ps = connexion.prepareStatement(
                    "SELECT * FROM commande WHERE ID_client = ?"
            );
            ps.setInt(1, clientId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Commande cmd = new Commande(
                        rs.getInt("ID"),
                        rs.getInt("ID_client"),
                        rs.getInt("ID_produit"),
                        rs.getInt("Quantite"),
                        rs.getDouble("Prix"),
                        rs.getString("Date")
                );
                commandes.add(cmd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return commandes;
    }

    @Override
    /**
     Ajouter une nouvelle commande d'un produit par un client en paramètre dans la base de données
     @params : achat = objet de la commande en paramètre à insérer dans la base de données
     */
    public void ajouter(Commande achat) {
        /*
            A MODIDIER CAR PAS PRISE EN CHARGE DE L'A_I
         */
        try{
            //SELECT `ID` FROM `commande` ORDER BY `ID` DESC LIMIT 1
            Connection connexion = daoFactory.getConnection();
            PreparedStatement preparedStatement = connexion.prepareStatement("INSERT INTO commande (ID_client, ID_produit, Quantite, Prix, Date) VALUES (?,?,?,?,?)");

            preparedStatement.setInt(1, achat.getId_client());
            preparedStatement.setInt(2, achat.getId_produit());
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
                    "SELECT * FROM commande WHERE clientID = ? AND produitID = ?"
            );
            preparedStatement.setInt(1, clientID);
            preparedStatement.setInt(2, produitID);
            ResultSet resultats = preparedStatement.executeQuery();
            if (resultats.next()) {
                int Id = resultats.getInt("ID");
                int quantite = resultats.getInt("Quantite");
                double prix = resultats.getDouble("Prix");
                String date = resultats.getString("Date");
                achat = new Commande(Id, clientID, produitID, quantite, prix, date);
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
        /*
            A COMPLETER
         */
        try {
            Connection connexion = daoFactory.getConnection();
            PreparedStatement preparedStatement = connexion.prepareStatement(
                    "UPDATE commande SET Quantite = ? AND Prix = ? WHERE clientID = ? AND produitID = ?"
            );
            preparedStatement.setInt(1, achat.getQuantite());
            preparedStatement.setDouble(2, achat.getPrix());
            preparedStatement.setInt(3, achat.getId_client());
            preparedStatement.setInt(4, achat.getId_produit());
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
    public void supprimer (Commande achat){
        /*
            A COMPLETER
         */
        try {
            Connection connexion = daoFactory.getConnection();

            // Suppression des commandes liées au client (contrainte d'intégrité référentielle)
            String requeteCommandes = "DELETE FROM commande WHERE clientID = ? AND produitID = ?";
            PreparedStatement psCommandes = connexion.prepareStatement(requeteCommandes);
            psCommandes.setInt(1, achat.getId_client());
            psCommandes.setInt(2, achat.getId_produit());
            psCommandes.executeUpdate();
            System.out.println("Commande supprimée avec succès");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Suppression du client impossible");
        }

    }

}
