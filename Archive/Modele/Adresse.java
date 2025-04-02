package Archive.Modele;

public class Adresse {
    private int id;
    private int idUtilisateur;
    private int numero;
    private String rue;
    private int codePostal;
    private String ville;
    private String pays;

    public Adresse() {}

    public Adresse(int id, int idUtilisateur, int numero, String rue,
                   int codePostal, String ville, String pays) {
        this.id = id;
        this.idUtilisateur = idUtilisateur;
        this.numero = numero;
        this.rue = rue;
        this.codePostal = codePostal;
        this.ville = ville;
        this.pays = pays;
    }

    // Getters et Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getIdUtilisateur() { return idUtilisateur; }
    public void setIdUtilisateur(int idUtilisateur) { this.idUtilisateur = idUtilisateur; }
    public int getNumero() { return numero; }
    public void setNumero(Integer numero) { this.numero = numero; }
    public String getRue() { return rue; }
    public void setRue(String rue) { this.rue = rue; }
    public int getCodePostal() { return codePostal; }
    public void setCodePostal(int codePostal) { this.codePostal = codePostal; }
    public String getVille() { return ville; }
    public void setVille(String ville) { this.ville = ville; }
    public String getPays() { return pays; }
    public void setPays(String pays) { this.pays = pays; }

    @Override
    public String toString() {
        return String.format("%s%s, %d %s, %s",
                numero,
                rue != null ? rue : "",
                codePostal,
                ville != null ? ville : "",
                pays != null ? pays : "");
    }
}