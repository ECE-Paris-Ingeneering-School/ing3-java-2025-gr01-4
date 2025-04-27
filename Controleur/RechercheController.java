package Controleur;

import DAO.ProduitDAOImpl;
import Vue.ProduitPanel;
import javax.swing.*;
import java.awt.*;

public class RechercheController {
    private JPanel conteneurPrincipal;

    public RechercheController(JPanel conteneurPrincipal) {
        this.conteneurPrincipal = conteneurPrincipal;
    }

    public void afficherProduitsParRecherche(String recherche) {
        ProduitPanel produitPanel = new ProduitPanel(new ProduitDAOImpl().getByRecherche(recherche));
        JScrollPane scrollPane = new JScrollPane(produitPanel);

        // 🔥 Ajouter un nouvel écran sans tout enlever
        conteneurPrincipal.add(scrollPane, "ResultatRecherche");

        CardLayout cl = (CardLayout) (conteneurPrincipal.getLayout());
        cl.show(conteneurPrincipal, "ResultatRecherche");
    }
}
