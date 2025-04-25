package Vue;

import javax.swing.*;
import java.awt.*;

public class CategoriePanel extends JPanel {
    public CategoriePanel() {
        setLayout(new GridLayout(2, 3, 10, 10));
        setBackground(Color.decode("#87bcd6"));

        String[] categories = {"Électronique", "Vêtements", "Maison", "Livres", "Sport", "Autre"};
        for (String categorie : categories) {
            JButton bouton = new JButton(categorie);
            styliserBouton(bouton);
            add(bouton);
        }
    }

    private void styliserBouton(JButton bouton) {
        bouton.setBackground(Color.decode("#4682A9"));
        bouton.setForeground(Color.WHITE);
        bouton.setFocusPainted(false);
    }
}