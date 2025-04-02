package Archive.Modele;

public class Commande {
    private int id;
    private int idClient;
    private int idProduit;
    private int quantite;
    private double prix;
    private String date;

    public Commande() {}

    public Commande(int idClient, int idProduit, int quantite) {
        this.idClient = idClient;
        this.idProduit = idProduit;
        this.quantite = quantite;
    }

    public Commande(int id, int idClient, int idProduit, int quantite, double prix, String date) {
        this.idClient = idClient;
        this.idProduit = idProduit;
        this.quantite = quantite;
        this.date = date;
        this.id = id;
        this.prix = prix;
    }

    // Getters and Setters
    public int getId() {return id;}
    public int getIdClient() { return idClient; }
    public void setIdClient(int idClient) { this.idClient = idClient; }
    public int getIdProduit() { return idProduit; }
    public void setIdProduit(int idProduit) { this.idProduit = idProduit; }
    public int getQuantite() { return quantite; }
    public void setQuantite(int quantite) { this.quantite = quantite; }
    public double getPrix() { return prix; }
    public void setPrix(double prix) { this.prix = prix; }
    public String getDate() { return date; }


    @Override
    public String toString() {
        return "Commande [id=" + id + ", idClient=" + idClient + ", idProduit=" + idProduit +
                ", quantite=" + quantite + ", prix=" + prix + ", date=" + date + "]";
    }
}