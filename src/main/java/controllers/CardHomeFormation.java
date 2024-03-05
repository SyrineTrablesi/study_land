package controllers;

        import entities.Favoris;
        import entities.Formation;
        import entities.Inscrit;
        import entities.User;
        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.scene.control.Button;
        import javafx.scene.control.Label;
        import javafx.scene.layout.AnchorPane;
        import javafx.scene.layout.FlowPane;
        import javafx.scene.text.Text;
        import services.ServiceFavoris;
        import services.ServiceFormation;
        import services.ServiceInscrit;

        import java.sql.Date;
        import java.sql.SQLException;
        import java.text.Format;

public class CardHomeFormation {
    @FXML
    private AnchorPane cardsContainer;

    @FXML
    private FlowPane cardsFlowPane;

    @FXML
    private FlowPane cardsFlowPane2;

    @FXML
    private AnchorPane idListFavoris;

    @FXML
    private AnchorPane idListInscite;

    @FXML
    private Button btn_AjoutFavoris;

    @FXML
    private Button btn_AjoutInscrit;

    @FXML
    private Label categorieLabel;

    @FXML
    private Label dureeLabel;

    @FXML
    private Text idtext;

    @FXML
    private Label idtitle;

    @FXML
    private Label niveauLabel;

    @FXML
    private Label prixLabel;
    public void setData(String title, String description, String niveau, int duree, float prix, String categorie) {
        idtitle.setText(title);
        idtext.setText(description);
        niveauLabel.setText(niveau);
        dureeLabel.setText(Integer.toString(duree));
        prixLabel.setText(Float.toString(prix));
        categorieLabel.setText(categorie);
    }
    @FXML
    void btn_AjouterFavoris(ActionEvent event) {
        User user =new User();
         user.setId(2);
         ServiceFormation serviceFormation=new ServiceFormation();
         try {
             Formation formation= serviceFormation.rechercherParTitre(idtitle.getText());
             //instance de formation type favoris
             Favoris favoris = new Favoris( user.getId(),formation.getIdFormation());
             favoris.setDescription(formation.getDescription());
             favoris.setNiveau(formation.getNiveau());
             favoris.setNomCategorie(formation.getNomCategorie());
             favoris.setTitre(formation.getTitre());
             favoris.setDuree(formation.getDuree());
             ServiceFavoris serviceFavoris=new ServiceFavoris();
             System.out.println(favoris);
             serviceFavoris.ajouter(favoris);
         } catch (SQLException e) {
                throw new RuntimeException(e);
         }

    }

    @FXML
    void btn_AjouterInscrit(ActionEvent event) {
        User user =new User();
        user.setId(2);
        ServiceFormation serviceFormation=new ServiceFormation();
        try {
            Formation formation= serviceFormation.rechercherParTitre(idtitle.getText());
        //instance de formation type inscrit
        Inscrit inscrit = new Inscrit( user.getId(),formation.getIdFormation());
        inscrit.setDescription(formation.getDescription());
        inscrit.setNiveau(formation.getNiveau());
        inscrit.setNomCategorie(formation.getNomCategorie());
        inscrit.setTitre(formation.getTitre());
        inscrit.setDuree(formation.getDuree());
        ServiceInscrit serviceInscrit=new ServiceInscrit();
            System.out.println(inscrit);
            serviceInscrit.ajouter(inscrit);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}

