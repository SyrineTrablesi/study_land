package controllers;

import entities.Project;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import services.ServiceProject;

import java.io.IOException;
import java.sql.SQLException;

public class CardYassController {

    @FXML
    private Text idDesc;
    @FXML
    private AnchorPane card;
    @FXML
    private Button idbouton;

    @FXML
    private Text idtache;

    @FXML
    private Label idtitle;

    @FXML
    private VBox vbox;
    public int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @FXML
    public void consulter(ActionEvent actionEvent) {
            System.out.println("Consulter clicked for Project ID: " + id);
            try {
                ServiceProject serviceProject = new ServiceProject();
                Project project = serviceProject.findProjectById(id);

                // Load the FXML file for the detail page
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/DetailProjet.fxml"));
                Parent root = loader.load();

                // Access the controller of DetailProjet after loading the FXML file
                DetailProjetControllers detailController = loader.getController();

                // Set the project details in the DetailProjet controller
                detailController.setProjectDetails(project);

                // Create a new scene with the loaded Parent
                Scene scene = new Scene(root);

                // Get the current stage from the button's scene
                Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

                // Replace the current scene with the new scene
                currentStage.setScene(scene);
            } catch (IOException | SQLException e) {
                e.printStackTrace();
            }
        }



    public Text getIdDesc() {
        return idDesc;
    }

    public void setIdDesc(Text idDesc) {
        this.idDesc = idDesc;
    }

    public Text getIdtache() {
        return idtache;
    }

    public void setIdtache(Text idtache) {
        this.idtache = idtache;
    }

    public Label getIdtitle() {
        return idtitle;
    }

    public void setIdtitle(String title) {
        idtitle.setText(title);
    }

    public void setIdtext(String text) {
        idDesc.setText(text);
    }

//    public void setProject(Project project) {
//        idtitle.setText(project.getNomProjet());
//        idDesc.setText("Description: " + project.getDescProjet());
//        idtache.setText("Nombre de Taches: " + project.getNb_taches());
//    }
}
