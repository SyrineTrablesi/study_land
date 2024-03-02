//package controllers;
//
//import entities.Formation;
//import entities.Panier;
//import javafx.fxml.FXML;
//import javafx.scene.control.TreeItem;
//import javafx.scene.control.TreeTableColumn;
//import javafx.scene.control.TreeTableView;
//import javafx.scene.control.cell.TreeItemPropertyValueFactory;
//import services.ServicePanier;
//
//import java.sql.SQLException;
//import java.util.List;
//
//public class AffPanier {
//
//    @FXML
//    private TreeTableColumn<Panier, Integer> id;
//
//    @FXML
//    private TreeTableColumn<Panier, Integer> id_formation;
//
//    @FXML
//    private TreeTableView<Panier> tab;
//
//    @FXML
//    private TreeTableColumn<Panier, String> titre;
//     ServicePanier servicePanier =new ServicePanier(); // Créez une instance de ServicePanier
//
//    @FXML
//    public void initialize() {
//        servicePanier = new ServicePanier(); // Initialisez l'instance de ServicePanier
//
//        try {
//            // Obtenez les données à partir de la méthode afficherPanierAdmin
//            List<Panier> paniers = servicePanier.afficherPanierAdmin();
//
//            // Créez un TreeItem racine
//            TreeItem<Panier> root = new TreeItem<>();
//
//            // Pour chaque Panier dans la liste, créez un TreeItem et ajoutez-le à la racine
//            for (Panier panier : paniers) {
//                TreeItem<Panier> item = new TreeItem<>(panier);
//                root.getChildren().add(item);
//            }
//
//            // Définissez la racine du TreeTableView sur le TreeItem racine
//            tab.setRoot(root);
//
//            // Définissez comment chaque colonne doit récupérer ses données
//            id.setCellValueFactory(new TreeItemPropertyValueFactory<>("id_user"));
//            id_formation.setCellValueFactory(new TreeItemPropertyValueFactory<>("id_formation"));
//            titre.setCellValueFactory(new TreeItemPropertyValueFactory<>("titre"));
//
//        } catch (SQLException e) {
//            e.printStackTrace(); // Gérez l'exception selon votre logique d'application
//        }
//    }
//}
