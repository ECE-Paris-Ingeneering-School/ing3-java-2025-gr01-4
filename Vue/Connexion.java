import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Connexion extends JMenuBar {
    private JPanel contenuCentral;
    private CardLayout cardLayout;

    public Main(JFrame frame) {
        contenuCentral = new JPanel();
        cardLayout = new CardLayout();
        contenuCentral.setLayout(cardLayout);

        contenuCentral.add(creerConnexionPanel(), "Connexion");
        contenuCentral.add(creerInscriptionPanel(), "Inscription");
        contenuCentral.add(creerVentePanel(), "Vente");

        frame.getContentPane().add(contenuCentral, BorderLayout.CENTER);

        // Menus agissant comme boutons
        JMenu menuConnexion = new JMenu("Connexion");
        menuConnexion.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                cardLayout.show(contenuCentral, "Connexion");
            }
        });

        JMenu menuInscription = new JMenu("Inscription");
        menuInscription.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                cardLayout.show(contenuCentral, "Inscription");
            }
        });

        JMenu menuVente = new JMenu("Vendre un article");
        menuVente.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                cardLayout.show(contenuCentral, "Vente");
            }
        });

        add(menuConnexion);
        add(menuInscription);
        add(menuVente);
    }

    private JPanel creerConnexionPanel() {
        JPanel panel = new JPanel(null);
        panel.setBackground(Color.decode("#1e1e1e"));

        JLabel titre = new JLabel("Page de connexion");
        titre.setBounds(350, 20, 250, 30);
        titre.setFont(new Font("SansSerif", Font.BOLD, 20));
        titre.setForeground(Color.decode("#D9D9D9"));
        panel.add(titre);

        JTextField emailField = new JTextField();
        emailField.setBounds(392, 102, 150, 25);
        emailField.setBackground(new Color(178, 178, 178));
        ajouterPlaceholder(emailField, "Identifiant");
        panel.add(emailField);

        JPasswordField mdpField = new JPasswordField();
        mdpField.setBounds(392, 182, 150, 25);
        mdpField.setBackground(new Color(178, 178, 178));
        ajouterPlaceholder(mdpField, "Mot de passe");
        panel.add(mdpField);

        JButton seConnecter = new JButton("Connexion");
        seConnecter.setBounds(385, 337, 106, 28);
        seConnecter.setBackground(Color.decode("#2e2e2e"));
        seConnecter.setForeground(Color.decode("#D9D9D9"));
        panel.add(seConnecter);

        JLabel message = new JLabel("<html><div style='text-align: center;'>Bon retour parmi nous,<br>nous espérons que vous trouverez<br>tout ce que vous voudrez</div></html>");
        message.setBounds(361, 252, 250, 54);
        message.setForeground(Color.decode("#D9D9D9"));
        panel.add(message);

        return panel;
    }

    private JPanel creerInscriptionPanel() {
        JPanel panel = new JPanel(null);
        panel.setBackground(Color.decode("#1e1e1e"));

        JLabel titre = new JLabel("Page d'inscription");
        titre.setBounds(340, 20, 250, 30);
        titre.setFont(new Font("SansSerif", Font.BOLD, 20));
        titre.setForeground(Color.decode("#D9D9D9"));
        panel.add(titre);

        JTextField nomField = new JTextField();
        nomField.setBounds(392, 80, 150, 25);
        nomField.setBackground(new Color(178, 178, 178));
        ajouterPlaceholder(nomField, "Nom");
        panel.add(nomField);

        JTextField emailField = new JTextField();
        emailField.setBounds(392, 130, 150, 25);
        emailField.setBackground(new Color(178, 178, 178));
        ajouterPlaceholder(emailField, "Email");
        panel.add(emailField);

        JPasswordField mdpField = new JPasswordField();
        mdpField.setBounds(392, 180, 150, 25);
        mdpField.setBackground(new Color(178, 178, 178));
        ajouterPlaceholder(mdpField, "Mot de passe");
        panel.add(mdpField);

        JButton inscrire = new JButton("S'inscrire");
        inscrire.setBounds(385, 337, 106, 28);
        inscrire.setBackground(Color.decode("#2e2e2e"));
        inscrire.setForeground(Color.decode("#D9D9D9"));
        panel.add(inscrire);

        return panel;
    }

    private JPanel creerVentePanel() {
        JPanel panel = new JPanel(null);
        panel.setBackground(Color.decode("#1e1e1e"));

        JLabel titre = new JLabel("Vendre un article");
        titre.setBounds(350, 20, 250, 30);
        titre.setFont(new Font("SansSerif", Font.BOLD, 20));
        titre.setForeground(Color.decode("#D9D9D9"));
        panel.add(titre);

        JTextField nomProduit = new JTextField();
        nomProduit.setBounds(392, 80, 200, 25);
        nomProduit.setBackground(new Color(178, 178, 178));
        ajouterPlaceholder(nomProduit, "Nom du produit");
        panel.add(nomProduit);

        JTextArea description = new JTextArea();
        description.setBounds(392, 120, 200, 60);
        description.setLineWrap(true);
        description.setWrapStyleWord(true);
        description.setBackground(new Color(178, 178, 178));
        description.setForeground(Color.GRAY);
        description.setText("Description");
        ajouterPlaceholder(description, "Description");
        panel.add(description);

        JTextField prix = new JTextField();
        prix.setBounds(392, 200, 90, 25);
        prix.setBackground(new Color(178, 178, 178));
        ajouterPlaceholder(prix, "Prix");
        panel.add(prix);

        JTextField unite = new JTextField();
        unite.setBounds(502, 200, 90, 25);
        unite.setBackground(new Color(178, 178, 178));
        ajouterPlaceholder(unite, "Unité");
        panel.add(unite);

        String[] categories = {"Électronique", "Maison", "Vêtements", "Sport", "Autre"};
        JComboBox<String> categorieBox = new JComboBox<>(categories);
        categorieBox.setBounds(392, 240, 200, 25);
        panel.add(categorieBox);

        JButton valider = new JButton("Submit");
        valider.setBounds(392, 290, 100, 28);
        valider.setBackground(Color.decode("#2e2e2e"));
        valider.setForeground(Color.decode("#D9D9D9"));
        panel.add(valider);

        return panel;
    }

    private void ajouterPlaceholder(JTextField field, String placeholderText) {
        field.setForeground(Color.GRAY);
        field.setText(placeholderText);

        field.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (field.getText().equals(placeholderText)) {
                    field.setText("");
                    field.setForeground(Color.BLACK);
                }
            }

            public void focusLost(FocusEvent e) {
                if (field.getText().isEmpty()) {
                    field.setForeground(Color.GRAY);
                    field.setText(placeholderText);
                }
            }
        });
    }

    private void ajouterPlaceholder(JPasswordField field, String placeholderText) {
        field.setForeground(Color.GRAY);
        field.setEchoChar((char) 0);
        field.setText(placeholderText);

        field.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (new String(field.getPassword()).equals(placeholderText)) {
                    field.setText("");
                    field.setEchoChar('•');
                    field.setForeground(Color.BLACK);
                }
            }

            public void focusLost(FocusEvent e) {
                if (new String(field.getPassword()).isEmpty()) {
                    field.setEchoChar((char) 0);
                    field.setText(placeholderText);
                    field.setForeground(Color.GRAY);
                }
            }
        });
    }

    private void ajouterPlaceholder(JTextArea area, String placeholderText) {
        area.setText(placeholderText);
        area.setForeground(Color.GRAY);

        area.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (area.getText().equals(placeholderText)) {
                    area.setText("");
                    area.setForeground(Color.BLACK);
                }
            }

            public void focusLost(FocusEvent e) {
                if (area.getText().isEmpty()) {
                    area.setText(placeholderText);
                    area.setForeground(Color.GRAY);
                }
            }
        });
    }

    /// A mettre dans le main je dois régler qlq problèmes sur git
    /*
    public static void main(String[] args) {
        JFrame frame = new JFrame("VULPIXIA - Interface");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(938, 507));
        frame.setLayout(new BorderLayout());

        Main menuBar = new Main(frame);
        frame.setJMenuBar(menuBar);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }*/
}
