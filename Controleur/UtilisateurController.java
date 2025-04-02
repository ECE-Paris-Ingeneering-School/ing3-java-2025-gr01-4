import java.util.List;

public class UtilisateurController {
    private UtilisateurDAO utilisateurDAO = new UtilisateurDAOImpl();
    private UtilisateurView view = new UtilisateurView(this);

    public void start() {
        boolean back = false;
        while (!back) {
            int choice = view.displayMenu();

            switch (choice) {
                case 1:
                    listAllUtilisateurs();
                    break;
                case 2:
                    getUtilisateurById();
                    break;
                case 3:
                    addUtilisateur();
                    break;
                case 4:
                    updateUtilisateur();
                    break;
                case 5:
                    deleteUtilisateur();
                    break;
                case 6:
                    back = true;
                    break;
                default:
                    view.showInvalidChoiceMessage();
            }
        }
    }

    private void listAllUtilisateurs() {
        List<Utilisateur> utilisateurs = utilisateurDAO.getAll();
        view.displayUtilisateurs(utilisateurs);
    }

    private void getUtilisateurById() {
        int id = view.askForUtilisateurId();
        Utilisateur u = utilisateurDAO.getById(id);
        view.displayUtilisateur(u);
    }

    private void addUtilisateur() {
        Utilisateur u = view.askForNewUtilisateur();
        boolean success = utilisateurDAO.insert(u);
        view.showOperationResult(success, "ajouté", u.getId());
    }

    private void updateUtilisateur() {
        int id = view.askForUtilisateurId();
        Utilisateur existing = utilisateurDAO.getById(id);
        if (existing == null) {
            view.showNotFoundMessage();
            return;
        }

        Utilisateur updates = view.askForUtilisateurUpdates(existing);
        boolean success = utilisateurDAO.update(updates);
        view.showOperationResult(success, "modifié", id);
    }

    private void deleteUtilisateur() {
        int id = view.askForUtilisateurId();
        boolean success = utilisateurDAO.delete(id);
        view.showOperationResult(success, "supprimé", id);
    }
}