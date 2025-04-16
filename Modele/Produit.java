package Modele;

public class Produit {
    private int id;
    private String nom;
    private float prix;
    private String marque;
    private String description;
    private String images;

    public Produit(int id, String nom, float prix, String marque, String description, String images) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
        this.marque = marque;
        this.description = description;
        this.images = images;
    }

    public int getId() {return this.id;}
    public String getNom() {return this.nom;}
    public float getPrix() {return this.prix;}
    public String getMarque() {return this.marque;}
    public String getDescription() {return this.description;}
    public String getImages() {return this.images;}

}
