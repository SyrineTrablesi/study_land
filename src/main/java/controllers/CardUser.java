package controllers;

import entities.Apprenant;
import entities.User;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class CardUser{
    @FXML
    private Label email;
    @FXML
     private VBox cardsContainer;

    @FXML
    private Label nom_user;

    @FXML
    private Label role;
    @FXML
    private Label prenom_user;


    public void initData(User user) {
        nom_user.setText(user.getNom());
        prenom_user.setText(user.getPrenom());
        email.setText(user.getEmail());
        role.setText(user.getRole());
    }
    public Label getEmail() {
        return email;
    }

    public Label getNom_user() {
        return nom_user;
    }

    public Label getPrenom_user() {
        return prenom_user;
    }

    public void setEmail(Label email) {
        this.email = email;
    }

    public void setNom_user(Label nom_user) {
        this.nom_user = nom_user;
    }

    public void setPrenom_user(Label prenom_user) {
        this.prenom_user = prenom_user;
    }

    public Label getRole() {
        return role;
    }

    public void setRole(Label role) {
        this.role = role;
    }
}
