package Modele;

/**
 * @author Pierre-Louis CHARBONNIER
 * Classe représentant un utilisateur de l'application.
 * Gère les informations personnelles et le statut de connexion des utilisateurs.
 */
public class Utilisateur {
    private int id;
    private String nom;
    private String mot_de_passe;
    private String mail;
    private boolean sexe;
    private boolean admin;

    private static Utilisateur utilisateurConnecte; // Permet de garder en mémoire l'utilisateur connecté

    /**
     * Constructeur complet d'un utilisateur.
     *
     * @param id Identifiant unique de l'utilisateur
     * @param nom Nom complet de l'utilisateur
     * @param mot_de_passe Mot de passe chiffré de l'utilisateur
     * @param mail Adresse email de l'utilisateur
     * @param sexe Genre de l'utilisateur (true pour homme, false pour femme)
     * @param admin Statut administrateur de l'utilisateur
     */
    public Utilisateur(int id, String nom, String mot_de_passe, String mail, boolean sexe, boolean admin) {
        this.id = id;
        this.nom = nom;
        this.mot_de_passe = mot_de_passe;
        this.mail = mail;
        this.sexe = sexe;
        this.admin = admin;
    }

    /**
     * @return L'identifiant de l'utilisateur
     */
    public int getId() { return id; }

    /**
     * @return Le nom complet de l'utilisateur
     */
    public String getNom() { return nom; }

    /**
     * @return Le mot de passe de l'utilisateur (non chiffré)
     */
    public String getMot_de_passe() { return mot_de_passe; }

    /**
     * @return L'adresse email de l'utilisateur
     */
    public String getMail() { return mail; }

    /**
     * @return Le genre de l'utilisateur (true pour homme, false pour femme)
     */
    public boolean getSexe() { return sexe; }

    /**
     * Vérifie si l'utilisateur a les droits administrateur.
     * @return true si l'utilisateur est administrateur, false sinon
     */
    public boolean isAdmin() { return admin; }

    /**
     * Modifie l'identifiant de l'utilisateur.
     * @param id Nouvel identifiant
     */
    public void setId(int id) { this.id = id; }

    /**
     * Définit l'utilisateur actuellement connecté à l'application.
     * @param u Utilisateur à définir comme connecté
     */
    public static void setUtilisateurConnecte(Utilisateur u) {
        utilisateurConnecte = u;
    }

    /**
     * Récupère l'utilisateur actuellement connecté.
     * @return L'utilisateur connecté ou null si aucun utilisateur n'est connecté
     */
    public static Utilisateur getUtilisateurConnecte() {
        return utilisateurConnecte;
    }
}