package Vue;

import javax.swing.*;
import java.awt.*;

public class VentesFlashPanel extends JPanel {
    public VentesFlashPanel() {
        setLayout(new GridLayout(3, 2, 10, 10));
        setBackground(Color.decode("#87bcd6"));

        for (int i = 1; i <= 6; i++) {
            JPanel produit = new JPanel();
            produit.setBackground(Color.WHITE);
            produit.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            produit.add(new JLabel("Produit en promo #" + i));
            add(produit);
        }
    }
}