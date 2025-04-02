package Modele;

public class Promotion {
    private int id;
    private int idProduit;
    private int quantite;
    private double prix;
    private int idCommande;

    public Promotion() {}

    public Promotion(int id, int idProduit, int quantite, double prix, int idCommande) {
        this.id = id;
        this.idProduit = idProduit;
        this.quantite = quantite;
        this.prix = prix;
        this.idCommande = idCommande;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getIdProduit() { return idProduit; }
    public void setIdProduit(int idProduit) { this.idProduit = idProduit; }
    public int getQuantite() { return quantite; }
    public void setQuantite(int quantite) { this.quantite = quantite; }
    public double getPrix() { return prix; }
    public void setPrix(double prix) { this.prix = prix; }
    public int getIdCommande() { return idCommande; }
    public void setIdCommande(int idCommande) { this.idCommande = idCommande; }

    @Override
    public String toString() {
        return "Promotion [id=" + id + ", idProduit=" + idProduit +
                ", quantite=" + quantite + ", prix=" + prix + ", idCommande=" + idCommande + "]";
    }
}