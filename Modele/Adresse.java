package Modele;

/**
 * @author William BENOIT
 */

public class Adresse {
    private int id;
    private int id_client;
    private int num;
    private String rue;
    private String ville;
    private int codePostal;
    private String pays;

    /**
     * Constructeur du modèle Adresse
     * @param id, id_client, num, rue, ville, codePostal et pays
     */
    public Adresse(int id, int id_client, int num, String rue, String ville, int codePostal, String pays) {
        this.id = id;
        this.id_client = id_client;
        this.num = num;
        this.rue = rue;
        this.ville = ville;
        this.codePostal = codePostal;
        this.pays = pays;
    }

    /**
     * Permet de retouner l'Id de l'adresse.
     * @return int Id
     */
    public int getId() {return id;}

    /**
     * Permet de retourner l'Id du client.
     * @return int Id_client
     */
    public int getId_client() {return id_client;}

    /**
     * Permet de retourner le numéro de l'adresse.
     * @return int num
     */
    public int getNum() {return num;}

    /**
     * Permet de retourner la rue de l'adresse.
     * @return String rue
     */
    public String getRue() {return rue;}

    /**
     * Permet de retourner la ville de l'adresse.
     * @return String ville
     */
    public String getVille() {return ville;}

    /**
     * Permet de retourner le code postal de l'adresse.
     * @return int codePostal
     */
    public int getCodePostal() {return codePostal;}

    /**
     * Permet de retourner le pays de l'adresse.
     * @return String Pays
     */
    public String getPays() {return pays;}
}
