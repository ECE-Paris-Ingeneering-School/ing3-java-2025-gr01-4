package Modele;

public class Commande {
    private int id;
    private int id_client;
    private int id_produit;
    private int quantite;
    private double prix;
    private String date;

    public Commande (int id, int id_client, int id_produit, int quantite, double prix, String date) {
        this.id = id;
        this.id_client = id_client;
        this.id_produit = id_produit;
        this.quantite = quantite;
        this.prix = prix;
        this.date = date;
    }

    public int getId(){ return this.id;}
    public int getId_client(){ return this.id_client;}
    public int getId_produit(){ return this.id_produit;}
    public int getQuantite(){ return this.quantite;}
    public double getPrix(){ return this.prix;}
    public String getDate(){ return this.date;}
}


