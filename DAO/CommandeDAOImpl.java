package DAO;

// import des packages
import DAO.CommandeDAO;
import DAO.DatabaseConnection;
import Modele.Commande;
import java.sql.*;
import java.util.ArrayList;

/**
 * @author William BENOIT
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

    /**
     * Permet de récupérer l'ensemble des commandes de la base de donnée.
     * @return liste de commandes
     */
    @Override
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

    /**
     * Permet de rechercher l'ensemble des commandes d'un client en utilisant son Id.
     * @param clientId
     * @return une liste de commandes
     */
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

    /**
     Ajouter une nouvelle commande d'un produit par un client en paramètre dans la base de données
     @params commande
     */
    @Override
    public void ajouter(Commande achat) {
        /*
            A MODIDIER CAR PAS PRISE EN CHARGE DE L'A_I
         */
        try{
            // Vérifier si une commande existe déjà pour ce client et produit
            Commande existante = chercher(achat.getId_client(), achat.getId_produit());
            System.out.println(existante);

            if (existante != null) {
                // Mise à jour de la quantité si la commande existe déjà
                System.out.println(existante.getQuantite());
                System.out.println(achat.getQuantite());
                existante.setQuantite(existante.getQuantite() + achat.getQuantite());
                modifier(existante);
                System.out.println("Modification sur commande existante");
            } else {
                // Nouvelle commande
                Connection connexion = daoFactory.getConnection();
                PreparedStatement preparedStatement = connexion.prepareStatement(
                        "INSERT INTO commande (ID_client, ID_produit, Quantite, Prix, Date) VALUES (?,?,?,?,?)"
                );
                preparedStatement.setInt(1, achat.getId_client());
                preparedStatement.setInt(2, achat.getId_produit());
                preparedStatement.setInt(3, achat.getQuantite());
                preparedStatement.setDouble(4, achat.getPrix());
                preparedStatement.setString(5, achat.getDate());
                preparedStatement.executeUpdate();
            }


        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Ajout de la commande impossible");
        }

    }

    /**
     * Permet de chercher et récupérer un objet de Commander dans la base de données via ses clientID et produitID
     * en paramètres
     * @param clientID et produitID
     * @return objet de commande cherché et retourné
     */
    @Override
    public Commande chercher(int clientID, int produitID){
        Commande achat = null;
        try {
            Connection connexion = daoFactory.getConnection();
            PreparedStatement preparedStatement = connexion.prepareStatement(
                    "SELECT * FROM commande WHERE ID_Client = ? AND ID_Produit = ?"
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

    /**
     * Permet de modifier les données du nom de l'objet de la classe Commander en paramètre
     * dans la base de données à partir de clientID et produitID de cet objet en paramètre
     * @param achat
     * @return une commande
     */
    @Override
    public Commande modifier(Commande achat) {
        /*
            A COMPLETER
         */
        try {
            Connection connexion = daoFactory.getConnection();
            PreparedStatement preparedStatement = connexion.prepareStatement(
                    "UPDATE commande SET Quantite = ?, Prix = ? WHERE ID_Client= ? AND ID_Produit = ?"
            );
            System.out.println("Quantité modifiée : " + achat.getQuantite());
            System.out.println("ID du client : " + achat.getId_client());
            System.out.println("ID du produit : " + achat.getId_produit());

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

    /**
     * @author Jerry CHENG
     */
    @Override
    public void supprimer(Commande achat) {
        String sql = "DELETE FROM commande WHERE ID = ?";

        try (Connection connexion = daoFactory.getConnection();
             PreparedStatement stmt = connexion.prepareStatement(sql)) {

            stmt.setInt(1, achat.getId());
            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                System.out.println("Aucune commande trouvée avec l'ID: " + achat.getId());
            } else {
                System.out.println("Commande supprimée avec succès");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Échec de la suppression", e);
        }
    }

}
