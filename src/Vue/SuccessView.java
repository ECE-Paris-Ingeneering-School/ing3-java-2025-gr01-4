package Vue;

import javax.swing.*;
import java.awt.*;

public class SuccessView extends JFrame {
    public SuccessView() {
        setTitle("Succès");
        setSize(250, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel label = new JLabel("✅ Bien connecté !", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        add(label);
    }
}
