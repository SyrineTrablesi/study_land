package controllers;

        import entities.Formation;
        import entities.Inscrit;
        import entities.User;
        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.scene.control.Button;
        import javafx.scene.control.Label;
        import javafx.scene.text.Text;
        import services.ServiceFormation;
        import services.ServiceInscrit;

        import java.sql.SQLException;
        import java.text.Format;

public class CardHomeFormation {

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

    }

    @FXML
    void btn_AjouterInscrit(ActionEvent event) {
        User user =new User();
        user.setId(2);
        ServiceFormation serviceFormation=new ServiceFormation();
        try {
            Formation formation= serviceFormation.rechercherParTitre(idtitle.getText());
        //instance de formation typr inscrit
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

