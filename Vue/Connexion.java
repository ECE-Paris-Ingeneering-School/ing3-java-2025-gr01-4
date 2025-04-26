package Vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Connexion extends JMenuBar {
    private JPanel contenuCentral;
    private CardLayout cardLayout;
    private JFrame frame;

    public Connexion(JFrame frame) {
        this.frame = frame;
        contenuCentral = new JPanel();
        cardLayout = new CardLayout();
        contenuCentral.setLayout(cardLayout);

        // Ajout des différents panels
        contenuCentral.add(new ConnexionPanel(cardLayout, contenuCentral), "Connexion");
        contenuCentral.add(new InscriptionPanel(cardLayout, contenuCentral), "Inscription");
        contenuCentral.add(new VentePanel(cardLayout, contenuCentral), "Vente");
        contenuCentral.add(new ComptePanel(), "Compte");
        contenuCentral.add(new CategoriePanel(), "Categories");
        contenuCentral.add(new VentesFlashPanel(), "VentesFlash");
        contenuCentral.add(new PanierPanel(), "Panier");

        frame.getContentPane().add(contenuCentral, BorderLayout.CENTER);

        ActionListener afficherMenuListener = e -> {
            String action = e.getActionCommand();
            cardLayout.show(contenuCentral, action);
        };

        String[] items = {"Connexion", "Inscription", "Vendre un article", "Informations du compte", "Catégories", "Ventes flash", "Panier"};
        String[] commands = {"Connexion", "Inscription", "Vente", "Compte", "Categories", "VentesFlash", "Panier"};

        for (int i = 0; i < items.length; i++) {
            JMenuItem item = new JMenuItem(items[i]);
            item.setActionCommand(commands[i]);
            item.addActionListener(afficherMenuListener);
            this.add(item);
        }

        // Afficher la page de connexion par défaut
        cardLayout.show(contenuCentral, "Connexion");
    }
}