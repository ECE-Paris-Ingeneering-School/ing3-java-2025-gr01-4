package Vue;
import java.util.List;
import java.util.Scanner;

public class ProduitView {
    private ProduitController controller;
    private Scanner scanner;

    public ProduitView(ProduitController controller) {
        this.controller = controller;
        this.scanner = new Scanner(System.in);
    }

    public int displayMenu() {
        System.out.println("\n=== Gestion des Produits ===");
        System.out.println("1. Lister tous les produits");
        System.out.println("2. Afficher un produit par ID");
        System.out.println("3. Ajouter un produit");
        System.out.println("4. Modifier un produit");
        System.out.println("5. Supprimer un produit");
        System.out.println("6. Retour");
        System.out.print("Choix : ");

        return scanner.nextInt();
    }

    public void displayProduits(List<Produit> produits) {
        System.out.println("\nListe des produits :");
        for (Produit p : produits) {
            System.out.println(p);
        }
    }

    public void displayProduit(Produit p) {
        if (p != null) {
            System.out.println("\nProduit trouvé :");
            System.out.println(p);
            System.out.println("Description : " + p.getDescription());
        } else {
            System.out.println("Produit non trouvé !");
        }
    }

    public int askForProduitId() {
        System.out.print("ID du produit : ");
        int id = scanner.nextInt();
        scanner.nextLine();
        return id;
    }

    public Produit askForNewProduit() {
        System.out.println("\nAjout d'un nouveau produit :");

        System.out.print("Marque : ");
        String marque = scanner.nextLine();

        System.out.print("Nom : ");
        String nom = scanner.nextLine();

        System.out.print("Prix : ");
        double prix = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Description : ");
        String description = scanner.nextLine();

        System.out.print("Image : ");
        String image = scanner.nextLine();

        return new Produit(0, marque, nom, prix, description, image);
    }

    public Produit askForProduitUpdates(Produit existing) {
        System.out.println("\nModification du produit :");
        System.out.println(existing);

        System.out.print("Nouvelle marque (laisser vide pour ne pas modifier) : ");
        String marque = scanner.nextLine();
        if (!marque.isEmpty()) existing.setMarque(marque);

        System.out.print("Nouveau nom (laisser vide pour ne pas modifier) : ");
        String nom = scanner.nextLine();
        if (!nom.isEmpty()) existing.setNom(nom);

        System.out.print("Nouveau prix (laisser 0 pour ne pas modifier) : ");
        double prix = scanner.nextDouble();
        scanner.nextLine();
        if (prix != 0) existing.setPrix(prix);

        System.out.print("Nouvelle description (laisser vide pour ne pas modifier) : ");
        String description = scanner.nextLine();
        if (!description.isEmpty()) existing.setDescription(description);

        System.out.print("Nouvelle image (laisser vide pour ne pas modifier) : ");
        String image = scanner.nextLine();
        if (!image.isEmpty()) existing.setImage(image);

        return existing;
    }

    public void showOperationResult(boolean success, String operation, int id) {
        if (success) {
            System.out.println("Produit " + operation + " avec succès !" + (id > 0 ? " ID : " + id : ""));
        } else {
            System.out.println("Échec de l'opération !");
        }
    }

    public void showInvalidChoiceMessage() {
        System.out.println("Choix invalide !");
    }

    public void showNotFoundMessage() {
        System.out.println("Produit non trouvé !");
    }
}
