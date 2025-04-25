package Vue;

import javax.swing.*;
import java.awt.*;

public class PanierPanel extends JPanel {
    public PanierPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.decode("#87bcd6"));

        for (int i = 1; i <= 5; i++) {
            JPanel ligne = new JPanel(new FlowLayout(FlowLayout.LEFT));
            ligne.setBackground(Color.WHITE);
            ligne.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
            ligne.add(new JLabel("Article " + i));

            JButton supprimer = new JButton("Supprimer");
            styliserBouton(supprimer);
            ligne.add(supprimer);

            add(Box.createRigidArea(new Dimension(0, 5)));
            add(ligne);
        }
    }

    private void styliserBouton(JButton bouton) {
        bouton.setBackground(Color.decode("#4682A9"));
        bouton.setForeground(Color.WHITE);
        bouton.setFocusPainted(false);
    }
}