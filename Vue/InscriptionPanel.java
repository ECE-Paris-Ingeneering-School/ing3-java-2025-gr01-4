package Vue;

import Controleur.InscriptionController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * @author Pierre-Louis CHARBONNIER
 * Panel d'inscription permettant à un utilisateur de créer un compte.
 * Contient un formulaire avec différents champs et gère l'interaction utilisateur.
 */
public class InscriptionPanel extends JPanel {
    private final InscriptionController controller;

    /**
     * Constructeur du panel d'inscription.
     *
     * @param cardLayout Le gestionnaire de disposition pour la navigation entre panels
     * @param cardPanel Le panel parent contenant les différents panels de l'application
     */
    public InscriptionPanel(CardLayout cardLayout, JPanel cardPanel) {
        setLayout(new BorderLayout()); // BorderLayout pour faire la barre en haut + contenu
        setBackground(Color.decode("#f5f5f5")); // Fond blanc légèrement gris

        // Partie en haut : barre bleue
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(Color.decode("#4682A9"));
        headerPanel.setPreferredSize(new Dimension(0, 80));
        headerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20)); // Centrer verticalement le titre

        JLabel titleLabel = new JLabel("Page d'inscription", SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel, BorderLayout.CENTER);

        add(headerPanel, BorderLayout.NORTH);

        // Partie centrale : formulaire
        JPanel formPanel = new JPanel();
        formPanel.setBackground(Color.WHITE);
        formPanel.setLayout(null); // Pour conserver ton positionnement absolu

        // Ajout du logo
        JLabel logo = chargerLogo();
        formPanel.add(logo);

        JTextField nom = new JTextField("Nom complet");
        nom.setBounds(315, 102, 160, 25);
        nom.setForeground(Color.GRAY);
        ajouterFocusListener(nom, "Nom complet");
        formPanel.add(nom);

        JTextField email = new JTextField("Email");
        email.setBounds(315, 142, 160, 25);
        email.setForeground(Color.GRAY);
        ajouterFocusListener(email, "Email");
        formPanel.add(email);

        String[] sexes = {"Homme", "Femme"};
        JComboBox<String> sexeComboBox = new JComboBox<>(sexes);
        sexeComboBox.setBounds(315, 182, 160, 25);
        formPanel.add(sexeComboBox);

        JPasswordField motDePasse = new JPasswordField("Mot de passe");
        motDePasse.setBounds(315, 222, 160, 25);
        motDePasse.setForeground(Color.GRAY);
        ajouterFocusListener(motDePasse, "Mot de passe");
        motDePasse.setEchoChar((char) 0);
        formPanel.add(motDePasse);

        JButton boutonInscription = new JButton("S'inscrire");
        boutonInscription.setBounds(315, 252, 160, 30);
        styliserBouton(boutonInscription);
        formPanel.add(boutonInscription);

        // Initialiser le contrôleur
        controller = new InscriptionController(this);

        // Clic sur le bouton "S'inscrire"
        boutonInscription.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Récupère les données
                String nomComplet = nom.getText();
                String adresseMail = email.getText();
                String motDePasseTexte = new String(motDePasse.getPassword());
                String sexeSelectionne = (String) sexeComboBox.getSelectedItem();
                boolean sexe = sexeSelectionne.equals("Homme");

                //Appelle le controleur pour vérifier que tout est ok
                controller.inscrireUtilisateur(nomComplet, adresseMail, motDePasseTexte, sexe);
            }
        });

        add(formPanel, BorderLayout.CENTER);
    }

    /**
     * Charge et redimensionne le logo de l'application.
     *
     * @return JLabel contenant le logo redimensionné
     */
    private JLabel chargerLogo() {
        ImageIcon icon = new ImageIcon("Logo Vulpixia.png");
        Image img = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        JLabel label = new JLabel(new ImageIcon(img));
        label.setBounds(20, 20, 100, 100);
        return label;
    }

    /**
     * Applique un style commun aux boutons du formulaire.
     *
     * @param bouton Le bouton à styliser
     */
    private void styliserBouton(JButton bouton) {
        bouton.setBackground(Color.decode("#4682A9"));
        bouton.setForeground(Color.WHITE);
        bouton.setFocusPainted(false);
    }

    /**
     * Ajoute un FocusListener à un champ de texte pour gérer le texte par défaut.
     *
     * @param champ Le champ de texte auquel ajouter le listener
     * @param texteParDefaut Le texte à afficher par défaut
     */
    private void ajouterFocusListener(JTextField champ, String texteParDefaut) {
        champ.addFocusListener(new FocusAdapter() {
            /**
             * Gère l'événement de focus gagné (clic dans le champ).
             */
            public void focusGained(FocusEvent e) {
                if (champ.getText().equals(texteParDefaut)) {
                    champ.setText("");
                    champ.setForeground(Color.BLACK);
                    if (champ instanceof JPasswordField) {
                        ((JPasswordField) champ).setEchoChar('•');
                    }
                }
            }

            /**
             * Gère l'événement de focus perdu (clic hors du champ).
             */
            public void focusLost(FocusEvent e) {
                if (champ.getText().isEmpty()) {
                    champ.setForeground(Color.GRAY);
                    champ.setText(texteParDefaut);
                    if (champ instanceof JPasswordField) {
                        ((JPasswordField) champ).setEchoChar((char) 0);
                    }
                }
            }
        });
    }
}