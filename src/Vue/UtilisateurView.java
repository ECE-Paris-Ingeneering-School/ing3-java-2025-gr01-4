package Vue;

import java.util.List;
import java.util.Scanner;
import Controleur.UtilisateurController;
import Modele.Utilisateur;

public class UtilisateurView {
    private UtilisateurController controller;
    private Scanner scanner;

    public UtilisateurView(UtilisateurController controller) {
        this.controller = controller;
        this.scanner = new Scanner(System.in);
    }

    public int displayMenu() {
        System.out.println("\n=== Gestion des Utilisateurs ===");
        System.out.println("1. Lister tous les utilisateurs");
        System.out.println("2. Afficher un utilisateur par ID");
        System.out.println("3. Ajouter un utilisateur");
        System.out.println("4. Modifier un utilisateur");
        System.out.println("5. Supprimer un utilisateur");
        System.out.println("6. Retour");
        System.out.print("Choix : ");

        return scanner.nextInt();
    }

    public void displayUtilisateurs(List<Utilisateur> utilisateurs) {
        System.out.println("\nListe des utilisateurs :");
        for (Utilisateur u : utilisateurs) {
            System.out.println(u);
        }
    }

    public void displayUtilisateur(Utilisateur u) {
        if (u != null) {
            System.out.println("\nUtilisateur trouvé :");
            System.out.println(u);
        } else {
            System.out.println("Utilisateur non trouvé !");
        }
    }

    public int askForUtilisateurId() {
        System.out.print("ID de l'utilisateur : ");
        int id = scanner.nextInt();
        scanner.nextLine();
        return id;
    }

    public Utilisateur askForNewUtilisateur() {
        System.out.println("\nAjout d'un nouvel utilisateur :");

        System.out.print("Nom : ");
        String nom = scanner.nextLine();

        System.out.print("Email : ");
        String mail = scanner.nextLine();

        System.out.print("Mot de passe : ");
        String mdp = scanner.nextLine();

        System.out.print("Sexe : ");
        boolean sexe = scanner.nextBoolean();

        System.out.print("Admin (true/false) : ");
        boolean admin = scanner.nextBoolean();
        scanner.nextLine();

        return new Utilisateur(0, nom, mail, mdp, sexe, admin);
    }

    public Utilisateur askForUtilisateurUpdates(Utilisateur existing) {
        System.out.println("\nModification de l'utilisateur :");
        System.out.println(existing);

        System.out.print("Nouvel email (laisser vide pour ne pas modifier) : ");
        String mail = scanner.nextLine();
        if (!mail.isEmpty()) existing.setMail(mail);

        System.out.print("Nouveau mot de passe (laisser vide pour ne pas modifier) : ");
        String mdp = scanner.nextLine();
        if (!mdp.isEmpty()) existing.setMotDePasse(mdp);

        System.out.print("Nouveau statut admin (true/false, laisser vide pour ne pas modifier) : ");
        String adminStr = scanner.nextLine();
        if (!adminStr.isEmpty()) existing.setAdmin(Boolean.parseBoolean(adminStr));

        return existing;
    }

    public void showOperationResult(boolean success, String operation, int id) {
        if (success) {
            System.out.println("Utilisateur " + operation + " avec succès !" + (id > 0 ? " ID : " + id : ""));
        } else {
            System.out.println("Échec de l'opération !");
        }
    }

    public void showInvalidChoiceMessage() {
        System.out.println("Choix invalide !");
    }

    public void showNotFoundMessage() {
        System.out.println("Utilisateur non trouvé !");
    }
}