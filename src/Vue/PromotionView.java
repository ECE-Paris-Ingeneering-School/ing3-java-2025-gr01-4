package Vue;

import java.util.List;
import java.util.Scanner;
import Controleur.PromotionController;
import Modele.Promotion;
public class PromotionView {
    private PromotionController controller;
    private Scanner scanner;

    public PromotionView(PromotionController controller) {
        this.controller = controller;
        this.scanner = new Scanner(System.in);
    }

    public int displayMenu() {
        System.out.println("\n=== Gestion des Promotions ===");
        System.out.println("1. Lister toutes les promotions");
        System.out.println("2. Afficher une promotion par ID");
        System.out.println("3. Ajouter une promotion");
        System.out.println("4. Modifier une promotion");
        System.out.println("5. Supprimer une promotion");
        System.out.println("6. Retour");
        System.out.print("Choix : ");

        return scanner.nextInt();
    }

    public void displayPromotions(List<Promotion> promotions) {
        System.out.println("\nListe des promotions :");
        for (Promotion p : promotions) {
            System.out.println(p);
        }
    }

    public void displayPromotion(Promotion p) {
        if (p != null) {
            System.out.println("\nPromotion trouvée :");
            System.out.println(p);
        } else {
            System.out.println("Promotion non trouvée !");
        }
    }

    public int askForPromotionId() {
        System.out.print("ID de la promotion : ");
        int id = scanner.nextInt();
        scanner.nextLine();
        return id;
    }

    public Promotion askForNewPromotion() {
        System.out.println("\nAjout d'une nouvelle promotion :");

        System.out.print("ID Produit : ");
        int idProduit = scanner.nextInt();

        System.out.print("Quantité : ");
        int quantite = scanner.nextInt();

        System.out.print("Prix : ");
        double prix = scanner.nextDouble();

        System.out.print("ID Commande : ");
        int idCommande = scanner.nextInt();
        scanner.nextLine();

        return new Promotion(0, idProduit, quantite, prix);
    }

    public Promotion askForPromotionUpdates(Promotion existing) {
        System.out.println("\nModification de la promotion :");
        System.out.println(existing);

        System.out.print("Nouvel ID Produit (laisser 0 pour ne pas modifier) : ");
        int idProduit = scanner.nextInt();
        if (idProduit != 0) existing.setIdProduit(idProduit);

        System.out.print("Nouvelle Quantité (laisser 0 pour ne pas modifier) : ");
        int quantite = scanner.nextInt();
        if (quantite != 0) existing.setQuantite(quantite);

        System.out.print("Nouveau Prix (laisser 0 pour ne pas modifier) : ");
        double prix = scanner.nextDouble();
        if (prix != 0) existing.setPrix(prix);

        return existing;
    }

    public void showOperationResult(boolean success, String operation, int id) {
        if (success) {
            System.out.println("Promotion " + operation + " avec succès !" + (id > 0 ? " ID : " + id : ""));
        } else {
            System.out.println("Échec de l'opération !");
        }
    }

    public void showInvalidChoiceMessage() {
        System.out.println("Choix invalide !");
    }

    public void showNotFoundMessage() {
        System.out.println("Promotion non trouvée !");
    }
}