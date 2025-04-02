package Modele;

public class Utilisateur {
    private int id;
    private String mail;
    private String motDePasse;
    private String nom;
    private boolean sexe;
    private boolean admin;

    public Utilisateur() {}

    public Utilisateur(int id, String mail, String motDePasse, String nom, boolean sexe, boolean admin) {
        this.id = id;
        this.mail = mail;
        this.motDePasse = motDePasse;
        this.nom = nom;
        this.sexe = sexe;
        this.admin = admin;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getMail() { return mail; }
    public void setMail(String mail) { this.mail = mail; }
    public String getMotDePasse() { return motDePasse; }
    public void setMotDePasse(String motDePasse) { this.motDePasse = motDePasse; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public boolean getSexe() { return sexe; }
    public void setSexe(boolean sexe) { this.sexe = sexe; }
    public boolean isAdmin() { return admin; }
    public void setAdmin(boolean admin) { this.admin = admin; }

    @Override
    public String toString() {
        return "Utilisateur [id=" + id + ", mail=" + mail + ", admin=" + admin + "]";
    }
}