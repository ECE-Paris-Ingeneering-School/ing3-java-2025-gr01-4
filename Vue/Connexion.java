
import javax.swing.*;
import java.awt.*;
import helper_classes.*;

public class WindowBuilder {
    public static void main(String[] args) {

        JFrame frame = new JFrame("My Awesome Window");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(938, 507);
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.decode("#1e1e1e"));

        JLabel element1 = new JLabel("Page de connexion");
        element1.setBounds(350, 20, 210, 33);
        element1.setFont(new Font("Arial", Font.BOLD,30));
        element1.setForeground(Color.decode("#D9D9D9"));
        panel.add(element1);

        JTextField element2 = new JTextField("");
        element2.setBounds(392, 102, 106, 21);
        element2.setFont(new Font("Arial", Font.BOLD,30));
        element2.setBackground(Color.decode("#B2B2B2"));
        element2.setForeground(Color.decode("#656565"));
        element2.setBorder(new RoundedBorder(2, Color.decode("#979797"), 0));
        OnFocusEventHelper.setOnFocusText(element2, "Identifiant", Color.decode("#353535"),   Color.decode("#656565"));
        panel.add(element2);

        JPasswordField element3 = new JPasswordField("");
        element3.setBounds(390, 182, 106, 21);
        element3.setFont(new Font("Arial", Font.BOLD,30));
        element3.setBackground(Color.decode("#B2B2B2"));
        element3.setForeground(Color.decode("#656565"));
        element3.setBorder(new RoundedBorder(2, Color.decode("#979797"), 0));
        OnFocusEventHelper.setOnFocusText(element3, "Mot de passe", Color.decode("#353535"),   Color.decode("#656565"));
        panel.add(element3);

        JButton element4 = new JButton("Connexion");
        element4.setBounds(385, 337, 106, 28);
        element4.setBackground(Color.decode("#2e2e2e"));
        element4.setForeground(Color.decode("#D9D9D9"));
        element4.setFont(new Font("Arial", Font.BOLD,30));
        element4.setBorder(new RoundedBorder(4, Color.decode("#979797"), 1));
        element4.setFocusPainted(false);
        OnClickEventHelper.setOnClickColor(element4, Color.decode("#232323"), Color.decode("#2e2e2e"));
        panel.add(element4);

        JLabel element5 = new JLabel("Bon retour parmis nous, nous espérerons que vous trouverez tout ce que vous voudrez");
        element5.setBounds(361, 252, 232, 54);
        element5.setFont(new Font("Arial", Font.BOLD,30));
        element5.setForeground(Color.decode("#D9D9D9"));
        panel.add(element5);

        JButton element6 = new JButton("Inscription");
        element6.setBounds(555, 390, 106, 28);
        element6.setBackground(Color.decode("#2e2e2e"));
        element6.setForeground(Color.decode("#D9D9D9"));
        element6.setFont(new Font("Arial", Font.BOLD,30));
        element6.setBorder(new RoundedBorder(4, Color.decode("#979797"), 1));
        element6.setFocusPainted(false);
        OnClickEventHelper.setOnClickColor(element6, Color.decode("#232323"), Color.decode("#2e2e2e"));
        panel.add(element6);

        JLabel element7 = new JLabel("VULPIXIA");
        element7.setBounds(24, 13, 184, 55);
        element7.setFont(new Font("Arial", Font.BOLD,30));
        element7.setForeground(Color.decode("#D9D9D9"));
        panel.add(element7);

        frame.add(panel);
        frame.setVisible(true);

    }
}