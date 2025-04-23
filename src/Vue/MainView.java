package Vue;

import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


public class MainView {
    private "MainController" controller;
    private Scanner scanner;

    public MainView(MainController controller) {
        this.controller = controller;
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        System.out.println("\n=== Menu Principal ===");
        System.out.println("1. Gestion des Utilisateurs");
        System.out.println("2. Gestion des Produits");
        System.out.println("3. Gestion des Commandes");
        System.out.println("4. Gestion des Promotions");
        System.out.println("5. Quitter");
        System.out.print("Choix : ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        controller.handleUserChoice(choice);
    }

    public void showInvalidChoiceMessage() {
        System.out.println("Choix invalide !");
    }

    public void showExitMessage() {
        System.out.println("Au revoir !");
    }
}