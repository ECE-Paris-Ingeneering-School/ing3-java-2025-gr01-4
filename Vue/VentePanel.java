package Vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class VentePanel extends JPanel {
    public VentePanel() {
        setLayout(null);
        setBackground(Color.decode("#87bcd6"));

        JLabel logo = chargerLogo();
        add(logo);

        JLabel titre = new JLabel("Vendre un article");
        titre.setBounds(350, 20, 250, 33);
        titre.setFont(new Font("SansSerif", Font.BOLD, 20));
        titre.setForeground(Color.BLACK);
        add(titre);

        String[] placeholders = {"Nom du produit", "Description", "Prix", "Unité"};
        for (int i = 0; i < placeholders.length; i++) {
            JTextField champ = new JTextField(placeholders[i]);
            champ.setBounds(392, 102 + i * 40, 160, 25);
            champ.setForeground(Color.GRAY);
            ajouterFocusListener(champ, placeholders[i]);
            add(champ);
        }

        String[] categories = {"Électronique", "Vêtements", "Maison", "Livres", "Autre"};
        JComboBox<String> categorie = new JComboBox<>(categories);
        categorie.setBounds(392, 262, 160, 25);
        add(categorie);

        JButton boutonSoumettre = new JButton("Soumettre");
        boutonSoumettre.setBounds(385, 310, 160, 30);
        styliserBouton(boutonSoumettre);
        add(boutonSoumettre);
    }

    // Méthodes utilitaires (identique aux autres panels)
    private JLabel chargerLogo() {
        ImageIcon icon = new ImageIcon("src/Logo Vulpixia.png");
        Image img = icon.getImage().getScaledInstance(100, 60, Image.SCALE_SMOOTH);
        JLabel label = new JLabel(new ImageIcon(img));
        label.setBounds(20, 20, 100, 60);
        return label;
    }

    private void styliserBouton(JButton bouton) {
        bouton.setBackground(Color.decode("#4682A9"));
        bouton.setForeground(Color.WHITE);
        bouton.setFocusPainted(false);
    }

    private void ajouterFocusListener(JTextField champ, String texteParDefaut) {
        champ.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (champ.getText().equals(texteParDefaut)) {
                    champ.setText("");
                    champ.setForeground(Color.BLACK);
                }
            }

            public void focusLost(FocusEvent e) {
                if (champ.getText().isEmpty()) {
                    champ.setText(texteParDefaut);
                    champ.setForeground(Color.GRAY);
                }
            }
        });
    }
}