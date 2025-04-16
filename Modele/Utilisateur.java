package Modele;

public class Utilisateur {
    private int id;
    private String nom;
    private String mot_de_passe;
    private String mail;
    private boolean admin;

    /**Constructeur de la classe utilisateur*/
    public Utilisateur(int id, String nom, String mot_de_passe, String mail, boolean admin) {
        this.id = id;
        this.nom = nom;
        this.mot_de_passe = mot_de_passe;
        this.mail = mail;
        this.admin = admin;
    }

    public int getId() {return id;}
    public String getNom() {return nom;}
    public String getMot_de_passe() {return mot_de_passe;}
    public String getMail() {return mail;}
    public boolean isAdmin() {return admin;}
}