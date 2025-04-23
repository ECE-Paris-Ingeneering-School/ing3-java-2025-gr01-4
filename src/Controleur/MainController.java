package Controleur;

import Vue.MainView;

public class MainController {
    public void handleUserChoice(int choice) {
        switch (choice) {
            case 1:
                System.out.println("-> Gestion des Utilisateurs");
                break;
            case 2:
                System.out.println("-> Gestion des Produits");
                break;
            case 3:
                System.out.println("-> Gestion des Commandes");
                break;
            case 4:
                System.out.println("-> Gestion des Promotions");
                break;
            case 5:
                System.out.println("-> Fermeture de l'application");
                break;
            default:
                System.out.println("Choix invalide !");
        }
    }

    public static void main(String[] args) {
        new MainView(new MainController());
    }
}
