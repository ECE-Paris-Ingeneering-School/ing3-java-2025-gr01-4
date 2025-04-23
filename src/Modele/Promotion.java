package Modele;

public class Promotion {
    private int id;
    private int id_produit;
    private int quantite;
    private double prix;

    public Promotion(int id, int id_produit, int quantite, double prix) {
        this.id = id;
        this.id_produit = id_produit;
        this.quantite = quantite;
        this.prix = prix;
    }

    public int getId() {return id;}
    public int getId_produit() {return id_produit;}
    public int getQuantite() {return quantite;}
    public double getPrix() {return prix;}

    public void setId(int id) {this.id = id;}
    public void setIdProduit(int id_produit) {this.id_produit = id_produit;}
    public void setQuantite(int quantite) {this.quantite = quantite;}
    public void setPrix(double prix) {this.prix = prix;}
}
