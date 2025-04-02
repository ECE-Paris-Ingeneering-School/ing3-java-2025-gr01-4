package Archive.Modele;

public class Promotion {
    private int idProduit;
    private int quantite;
    private double prix;
    private int idCommande;

    public Promotion() {}

    public Promotion(int idProduit, int quantite, double prix, int idCommande) {
        this.idProduit = idProduit;
        this.quantite = quantite;
        this.prix = prix;
        this.idCommande = idCommande;
    }

    // Getters and Setters
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
        return "Promotion [idProduit=" + idProduit +
                ", quantite=" + quantite + ", prix=" + prix + ", idCommande=" + idCommande + "]";
    }
}