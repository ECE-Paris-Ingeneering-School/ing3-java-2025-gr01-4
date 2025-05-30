package Vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

import Controleur.CommandeController;
import DAO.*;
import Modele.Utilisateur;

/**@author Minh-Duc PHAN

/**
 * Cette classe permet d'afficher la page de connexion du site
 * Elle demande l'identifiant ainsi que le mot de passe pour se connecter
 */
public class ConnexionPanel extends JPanel {
    private CardLayout cardLayout;
    private JPanel contenuCentral;
    private Connexion fenetreConnexion;

    /**
     * Constructeur de la classe
     * @param cardLayout c'est le menu qui permet de naviguer entre les panels
     * @param contenuCentral c'est le panel principal pour les différentes pages
     * @param fenetreConnexion c'est la fenêtre avec la page de connexion
     */
    public ConnexionPanel(CardLayout cardLayout, JPanel contenuCentral, Connexion fenetreConnexion) {
        this.cardLayout = cardLayout;
        this.contenuCentral = contenuCentral;
        this.fenetreConnexion = fenetreConnexion;

        setLayout(new BorderLayout());

        // Bande bleue en haut
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(Color.decode("#4682A9"));
        headerPanel.setPreferredSize(new Dimension(0, 80));
        headerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20)); // Centrer verticalement le titre

        JLabel titre = new JLabel("Page de connexion");
        titre.setFont(new Font("SansSerif", Font.BOLD, 20));
        titre.setForeground(Color.WHITE);
        headerPanel.add(titre);

        add(headerPanel, BorderLayout.NORTH);

        // Panel principal en blanc
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setLayout(null);

        // Logo
        JLabel logo = chargerLogo();
        logo.setBounds(20, 20, 100, 100);
        mainPanel.add(logo);

        // Champ email
        JTextField email = new JTextField("Identifiant");
        email.setBounds(315, 102, 160, 25);
        email.setForeground(Color.GRAY);
        ajouterFocusListener(email, "Identifiant");
        mainPanel.add(email);

        // Champ mot de passe
        JPasswordField motDePasse = new JPasswordField("Mot de passe");
        motDePasse.setBounds(315, 152, 160, 25);
        motDePasse.setEchoChar((char) 0);
        motDePasse.setForeground(Color.GRAY);
        ajouterFocusListener(motDePasse, "Mot de passe");
        mainPanel.add(motDePasse);

        // Bouton connexion
        JButton boutonConnexion = new JButton("Connexion");
        boutonConnexion.setBounds(315, 200, 160, 30);
        styliserBouton(boutonConnexion);
        mainPanel.add(boutonConnexion);

        add(mainPanel, BorderLayout.CENTER);

        // Action du bouton
        boutonConnexion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String identifiant = email.getText();
                String mdp = new String(motDePasse.getPassword());

                UtilisateurDAO utilisateurDAO = new UtilisateurDAOImpl();
                List<Utilisateur> utilisateurs = utilisateurDAO.getAll();

                for (Utilisateur u : utilisateurs) {
                    if (u.getMail().equals(identifiant) && u.getMot_de_passe().equals(mdp)) {
                        JOptionPane.showMessageDialog(null, "Connexion réussie !");
                        Utilisateur.setUtilisateurConnecte(u);

                        ComptePanel comptePanel = new ComptePanel(cardLayout, contenuCentral, fenetreConnexion);

                        DatabaseConnection dbConnection = new DatabaseConnection();
                        CommandeDAO commandeDAO = new CommandeDAOImpl(dbConnection);
                        ProduitDAO produitDAO = new ProduitDAOImpl();
                        PromotionDAO promotionDAO = new PromotionDAOImpl();
                        CommandeController commandeController = new CommandeController(null, commandeDAO, produitDAO);
                        PanierPanel panierPanel = new PanierPanel(commandeDAO, produitDAO, promotionDAO);

                        contenuCentral.add(comptePanel, "Compte");
                        contenuCentral.add(panierPanel, "Panier");

                        fenetreConnexion.mettreAJourMenu();

                        cardLayout.show(contenuCentral, "VentesFlash");
                        return;
                    }
                }

                JOptionPane.showMessageDialog(null, "Identifiant ou mot de passe incorrect.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    /**
     * Cette méthode permet de charger le logo du site et de l'afficher en haut à gauche
     * @return retourne l'image
     */
    private JLabel chargerLogo() {
        ImageIcon icon = new ImageIcon("Logo Vulpixia.png");
        Image img = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        return new JLabel(new ImageIcon(img));
    }

    /**
     * Permet de donner un visuel au bouton de connexion
     * @param bouton c'est le bouton à styliser
     */
    private void styliserBouton(JButton bouton) {
        bouton.setBackground(Color.decode("#4682A9"));
        bouton.setForeground(Color.WHITE);
        bouton.setFocusPainted(false);
        bouton.setFont(bouton.getFont().deriveFont(Font.BOLD, 14f));
    }

    /**
     * Cette méthode permet de modifier le champ de texte du mot de passe pour qu'il soit cacher et qu'il y ai
     * un texte d'exemple
     * @param champ le texte à remplir
     * @param texteParDefaut le texte afficher sur le champ lorsqu'il n'est pas rempli
     */
    private void ajouterFocusListener(JTextField champ, String texteParDefaut) {
        champ.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (champ.getText().equals(texteParDefaut)) {
                    champ.setText("");
                    champ.setForeground(Color.BLACK);
                    if (champ instanceof JPasswordField) {
                        ((JPasswordField) champ).setEchoChar('•');
                    }
                }
            }

            public void focusLost(FocusEvent e) {
                if (champ.getText().isEmpty()) {
                    champ.setText(texteParDefaut);
                    champ.setForeground(Color.GRAY);
                    if (champ instanceof JPasswordField) {
                        ((JPasswordField) champ).setEchoChar((char) 0);
                    }
                }
            }
        });
    }
}
