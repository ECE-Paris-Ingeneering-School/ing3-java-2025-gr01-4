public class MainController {
    private UtilisateurController utilisateurController;
    private ProduitController produitController;
    private CommandeController commandeController;
    private PromotionController promotionController;
    private MainView mainView;

    public MainController() {
        this.utilisateurController = new UtilisateurController();
        this.produitController = new ProduitController();
        this.commandeController = new CommandeController();
        this.promotionController = new PromotionController();
        this.mainView = new MainView(this);
    }

    public void start() {
        mainView.displayMenu();
    }

    public void handleUserChoice(int choice) {
        switch (choice) {
            case 1:
                utilisateurController.start();
                break;
            case 2:
                produitController.start();
                break;
            case 3:
                commandeController.start();
                break;
            case 4:
                promotionController.start();
                break;
            case 5:
                mainView.showExitMessage();
                System.exit(0);
                break;
            default:
                mainView.showInvalidChoiceMessage();
        }
    }
}