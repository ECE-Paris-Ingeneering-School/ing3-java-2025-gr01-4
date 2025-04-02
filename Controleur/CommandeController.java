package

import java.util.List;

public class CommandeController {
    private CommandeDAO commandeDAO = new CommandeDAOImpl();
    private CommandeView view = new CommandeView(this);

    public void start() {
        boolean back = false;
        while (!back) {
            int choice = view.displayMenu();

            switch (choice) {
                case 1:
                    listAllCommandes();
                    break;
                case 2:
                    getCommandeById();
                    break;
                case 3:
                    addCommande();
                    break;
                case 4:
                    updateCommande();
                    break;
                case 5:
                    deleteCommande();
                    break;
                case 6:
                    back = true;
                    break;
                default:
                    view.showInvalidChoiceMessage();
            }
        }
    }

    private void listAllCommandes() {
        List<Commande> commandes = commandeDAO.getAll();
        view.displayCommandes(commandes);
    }

    private void getCommandeById() {
        int id = view.askForCommandeId();
        Commande c = commandeDAO.getById(id);
        view.displayCommande(c);
    }

    private void addCommande() {
        Commande c = view.askForNewCommande();
        boolean success = commandeDAO.insert(c);
        view.showOperationResult(success, "ajoutée", c.getId());
    }

    private void updateCommande() {
        int id = view.askForCommandeId();
        Commande existing = commandeDAO.getById(id);
        if (existing == null) {
            view.showNotFoundMessage();
            return;
        }

        Commande updates = view.askForCommandeUpdates(existing);
        boolean success = commandeDAO.update(updates);
        view.showOperationResult(success, "modifiée", id);
    }

    private void deleteCommande() {
        int id = view.askForCommandeId();
        boolean success = commandeDAO.delete(id);
        view.showOperationResult(success, "supprimée", id);
    }
}