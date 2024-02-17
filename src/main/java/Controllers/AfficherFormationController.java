package Controllers;

import entities.Formation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import entities.Formation;
import java.util.List;

import Controllers.AjouterFormationController;
import services.ServiceFormation;


public class AfficherFormationController {

    @FXML
    private Label DD;

    @FXML
    private Label DF;

    @FXML
    private Label Description;

    @FXML
    private Label Duree;

    @FXML
    private Label Niveau;

    @FXML
    private Label NomC;

    @FXML
    private Label NomF;

    @FXML
    private Label Prix;

    @FXML
    private Label FromDB;

    public String getDescription() {
        return Description.getText();
    }

    public void setDescription(String description) {
        Description.setText(description);
    }

    public String getDuree() {
        return Duree.getText();
    }

    public void setDuree(String duree) {
        Duree.setText(duree);
    }

    public String getNiveau() {
        return Niveau.getText();
    }

    public void setNiveau(String niveau) {
        Niveau.setText(niveau);
    }

    public String getNomF() {
        return NomF.getText();
    }

    public void setNomF(String nomF) {
        NomF.setText(nomF);
    }

    public String getPrix() {
        return Prix.getText();
    }

    public void setPrix(String prix) {
        Prix.setText(prix);
    }

    public String getNomC() {
        return NomC.getText();
    }

    public void setNomC(String nomC) {
        NomC.setText(nomC);
    }

    public LocalDate getDD() {
        return LocalDate.parse(DD.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public void setDD(LocalDate dd) {
        DD.setText(dd.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
    }

    public LocalDate getDF() {
        return LocalDate.parse(DF.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public void setDF(LocalDate df) {
        DF.setText(df.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

    }


    ServiceFormation FS= new ServiceFormation();
    @FXML
    void AfficherDB(ActionEvent event) {
        try {
            // Assuming FS.afficher() returns a List<Formation>
            List<Formation> formations = FS.afficher();

            // Now you need to convert the List<Formation> into a readable format
            StringBuilder stringBuilder = new StringBuilder();
            for (Formation formation : formations) {
                stringBuilder.append(formation.toString()).append("\n");
            }

            // Set the text of FromDB label to the concatenated string
            FromDB.setText(stringBuilder.toString());

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
