package Archive.Modele;

public class Produit {
    private int id;
    private String marque;
    private String nom;
    private double prix;
    private String description;
    private String image;

    public Produit() {}

    public Produit(int id, String marque, String nom, double prix, String description, String image) {
        this.id = id;
        this.marque = marque;
        this.nom = nom;
        this.prix = prix;
        this.description = description;
        this.image = image;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getMarque() { return marque; }
    public void setMarque(String marque) { this.marque = marque; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public double getPrix() { return prix; }
    public void setPrix(double prix) { this.prix = prix; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }

    @Override
    public String toString() {
        return "Produit [id=" + id + ", marque=" + marque + ", nom=" + nom +
                ", prix=" + prix + ", image=" + image + "]";
    }
}