import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Connexion extends JMenuBar {
    private JPanel contenuCentral;
    private CardLayout cardLayout;
    private JFrame frame;

    public Main(JFrame frame) {
        this.frame = frame;
        contenuCentral = new JPanel();
        cardLayout = new CardLayout();
        contenuCentral.setLayout(cardLayout);

        contenuCentral.add(creerConnexionPanel(), "Connexion");
        contenuCentral.add(creerInscriptionPanel(), "Inscription");
        contenuCentral.add(creerVentePanel(), "Vente");

        frame.getContentPane().add(contenuCentral, BorderLayout.CENTER);

        ActionListener afficherMenuListener = e -> {
            String action = e.getActionCommand();
            cardLayout.show(contenuCentral, action);
        };

        JMenuBar menuBar = this;

        JMenuItem connexion = new JMenuItem("Connexion");
        connexion.setActionCommand("Connexion");
        connexion.addActionListener(afficherMenuListener);
        menuBar.add(connexion);

        JMenuItem inscription = new JMenuItem("Inscription");
        inscription.setActionCommand("Inscription");
        inscription.addActionListener(afficherMenuListener);
        menuBar.add(inscription);

        JMenuItem vente = new JMenuItem("Vendre un article");
        vente.setActionCommand("Vente");
        vente.addActionListener(afficherMenuListener);
        menuBar.add(vente);
    }

    private JPanel creerConnexionPanel() {
        JPanel panel = new JPanel(null);
        panel.setBackground(Color.decode("#87bcd6"));

        JLabel logo = chargerLogo();
        panel.add(logo);

        JLabel titre = new JLabel("Page de connexion");
        titre.setBounds(350, 20, 210, 33);
        titre.setFont(new Font("SansSerif", Font.BOLD, 20));
        titre.setForeground(Color.BLACK);
        panel.add(titre);

        JTextField email = new JTextField();
        email.setBounds(392, 102, 160, 25);
        email.setText("Identifiant");
        email.setForeground(Color.GRAY);
        ajouterFocusListener(email, "Identifiant");
        panel.add(email);

        JPasswordField motDePasse = new JPasswordField();
        motDePasse.setBounds(390, 152, 160, 25);
        motDePasse.setEchoChar((char) 0);
        motDePasse.setText("Mot de passe");
        motDePasse.setForeground(Color.GRAY);
        ajouterFocusListener(motDePasse, "Mot de passe");
        panel.add(motDePasse);

        JButton boutonConnexion = new JButton("Connexion");
        boutonConnexion.setBounds(385, 200, 160, 30);
        styliserBouton(boutonConnexion);
        panel.add(boutonConnexion);

        return panel;
    }

    private JPanel creerInscriptionPanel() {
        JPanel panel = new JPanel(null);
        panel.setBackground(Color.decode("#87bcd6"));

        JLabel logo = chargerLogo();
        panel.add(logo);

        JLabel titre = new JLabel("Page d'inscription");
        titre.setBounds(350, 20, 250, 33);
        titre.setFont(new Font("SansSerif", Font.BOLD, 20));
        titre.setForeground(Color.BLACK);
        panel.add(titre);

        JTextField nom = new JTextField();
        nom.setBounds(392, 102, 160, 25);
        nom.setText("Nom complet");
        nom.setForeground(Color.GRAY);
        ajouterFocusListener(nom, "Nom complet");
        panel.add(nom);

        JTextField email = new JTextField();
        email.setBounds(392, 142, 160, 25);
        email.setText("Email");
        email.setForeground(Color.GRAY);
        ajouterFocusListener(email, "Email");
        panel.add(email);

        JPasswordField motDePasse = new JPasswordField();
        motDePasse.setBounds(392, 182, 160, 25);
        motDePasse.setText("Mot de passe");
        motDePasse.setForeground(Color.GRAY);
        motDePasse.setEchoChar((char) 0);
        ajouterFocusListener(motDePasse, "Mot de passe");
        panel.add(motDePasse);

        JButton boutonInscription = new JButton("S'inscrire");
        boutonInscription.setBounds(385, 230, 160, 30);
        styliserBouton(boutonInscription);
        panel.add(boutonInscription);

        return panel;
    }

    private JPanel creerVentePanel() {
        JPanel panel = new JPanel(null);
        panel.setBackground(Color.decode("#87bcd6"));

        JLabel logo = chargerLogo();
        panel.add(logo);

        JLabel titre = new JLabel("Vendre un article");
        titre.setBounds(350, 20, 250, 33);
        titre.setFont(new Font("SansSerif", Font.BOLD, 20));
        titre.setForeground(Color.BLACK);
        panel.add(titre);

        JTextField nomProduit = new JTextField("Nom du produit");
        nomProduit.setBounds(392, 102, 160, 25);
        nomProduit.setForeground(Color.GRAY);
        ajouterFocusListener(nomProduit, "Nom du produit");
        panel.add(nomProduit);

        JTextField description = new JTextField("Description");
        description.setBounds(392, 142, 160, 25);
        description.setForeground(Color.GRAY);
        ajouterFocusListener(description, "Description");
        panel.add(description);

        JTextField prix = new JTextField("Prix");
        prix.setBounds(392, 182, 160, 25);
        prix.setForeground(Color.GRAY);
        ajouterFocusListener(prix, "Prix");
        panel.add(prix);

        JTextField unite = new JTextField("Unité");
        unite.setBounds(392, 222, 160, 25);
        unite.setForeground(Color.GRAY);
        ajouterFocusListener(unite, "Unité");
        panel.add(unite);

        String[] categories = {"Électronique", "Vêtements", "Maison", "Livres", "Autre"};
        JComboBox<String> categorie = new JComboBox<>(categories);
        categorie.setBounds(392, 262, 160, 25);
        panel.add(categorie);

        JButton boutonSoumettre = new JButton("Soumettre");
        boutonSoumettre.setBounds(385, 310, 160, 30);
        styliserBouton(boutonSoumettre);
        panel.add(boutonSoumettre);

        return panel;
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

    private JLabel chargerLogo() {
        ImageIcon icon = new ImageIcon("src/Vulpixia.jpg");
        Image img = icon.getImage().getScaledInstance(100, 60, Image.SCALE_SMOOTH);
        JLabel label = new JLabel(new ImageIcon(img));
        label.setBounds(20, 20, 100, 60);
        return label;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Interface Shopping");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(800, 600));
        frame.setLayout(new BorderLayout());

        Main menuBar = new Main(frame);
        frame.setJMenuBar(menuBar);

        frame.pack();
        frame.setVisible(true);
    }
}
