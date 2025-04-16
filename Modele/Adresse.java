package Modele;

public class Adresse {
    private int id;
    private int id_client;
    private int num;
    private String rue;
    private String ville;
    private int codePostal;
    private String pays;

    public Adresse(int id, int id_client, int num, String rue, String ville, int codePostal, String pays) {
        this.id = id;
        this.id_client = id_client;
        this.num = num;
        this.rue = rue;
        this.ville = ville;
        this.codePostal = codePostal;
        this.pays = pays;
    }

    public int getId() {return id;}
    public int getId_client() {return id_client;}
    public int getNum() {return num;}
    public String getRue() {return rue;}
    public String getVille() {return ville;}
    public int getCodePostal() {return codePostal;}
    public String getPays() {return pays;}
}
