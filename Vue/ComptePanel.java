package Vue;

import javax.swing.*;
import java.awt.*;

public class ComptePanel extends JPanel {
    public ComptePanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.decode("#87bcd6"));

        String[] infos = {
                "Nom: Dupont Jean",
                "Email: jean.dupont@mail.com",
                "Adresse: 123 Rue Exemple, Paris",
                "Téléphone: 06 12 34 56 78",
                "Statut: Client régulier"
        };
        for (String info : infos) {
            JLabel label = new JLabel(info);
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            label.setFont(new Font("SansSerif", Font.PLAIN, 16));
            add(Box.createRigidArea(new Dimension(0, 10)));
            add(label);
        }
    }
}