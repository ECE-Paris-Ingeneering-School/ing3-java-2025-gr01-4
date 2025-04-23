package Modele;

public class Produit {
    private int id;
    private String nom;
    private double prix;
    private String marque;
    private String description;
    private String images;

    public Produit(int id, String nom, double prix, String marque, String description, String images) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
        this.marque = marque;
        this.description = description;
        this.images = images;
    }

    public int getId() {return id;}
    public String getNom() {return nom;}
    public double getPrix() {return prix;}
    public String getMarque() {return marque;}
    public String getDescription() {return description;}
    public String getImage() {return images;}

    public void setId(int id) {this.id = id;}



}
