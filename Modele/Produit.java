package Modele;

/**
 * @author William BENOIT & Jerry CHENG
 */

public class Produit {
    private int id;
    private String nom;
    private double prix;
    private int quantite;
    private String marque;
    private String description;
    private String images;

    // Constructeurs
    public Produit() {}

    /**
     * Constructeurs permettant d'initialiser tous les attributs.
     * @param id, marque, prix, quantite, nom, description et images
     */
    public Produit(int id, String marque, double prix, int quantite, String nom, String description, String images) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
        this.quantite = quantite;
        this.marque = marque;
        this.description = description;
        this.images = images;
    }

    // Getters et Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public double getPrix() { return prix; }
    public void setPrix(double prix) { this.prix = prix; }

    public int getQuantite() { return quantite; }
    public void setQuantite(int quantite) { this.quantite = quantite; }

    public String getMarque() { return marque; }
    public void setMarque(String marque) { this.marque = marque; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getImages() { return images; }
    public void setImages(String images) { this.images = images; }

    /**
     * Permet de retourner tous les attributs du produit sous le format String
     * @return le produit en String
     */
    @Override
    public String toString() {
        return "Produit{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prix=" + prix +
                ", quantite=" + quantite +
                ", marque='" + marque + '\'' +
                ", description='" + description + '\'' +
                ", images='" + images + '\'' +
                '}';
    }
}