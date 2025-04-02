import java.util.List;

public class ProduitController {
    private ProduitDAO produitDAO = new ProduitDAOImpl();
    private ProduitView view = new ProduitView(this);

    public void start() {
        boolean back = false;
        while (!back) {
            int choice = view.displayMenu();

            switch (choice) {
                case 1:
                    listAllProduits();
                    break;
                case 2:
                    getProduitById();
                    break;
                case 3:
                    addProduit();
                    break;
                case 4:
                    updateProduit();
                    break;
                case 5:
                    deleteProduit();
                    break;
                case 6:
                    back = true;
                    break;
                default:
                    view.showInvalidChoiceMessage();
            }
        }
    }

    private void listAllProduits() {
        List<Produit> produits = produitDAO.getAll();
        view.displayProduits(produits);
    }

    private void getProduitById() {
        int id = view.askForProduitId();
        Produit p = produitDAO.getById(id);
        view.displayProduit(p);
    }

    private void addProduit() {
        Produit p = view.askForNewProduit();
        boolean success = produitDAO.insert(p);
        view.showOperationResult(success, "ajouté", p.getId());
    }

    private void updateProduit() {
        int id = view.askForProduitId();
        Produit existing = produitDAO.getById(id);
        if (existing == null) {
            view.showNotFoundMessage();
            return;
        }

        Produit updates = view.askForProduitUpdates(existing);
        boolean success = produitDAO.update(updates);
        view.showOperationResult(success, "modifié", id);
    }

    private void deleteProduit() {
        int id = view.askForProduitId();
        boolean success = produitDAO.delete(id);
        view.showOperationResult(success, "supprimé", id);
    }
}