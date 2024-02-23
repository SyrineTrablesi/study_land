package controllers;

import entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import services.ServiceUser;

public class ChangerPassword {
    @FXML
    private Button btn_modifier_mdp;

    @FXML
    private PasswordField confirmer_new_password;

    @FXML
    private PasswordField new_password;
    static  public User user;

    @FXML
    void modifier_mdp(ActionEvent event) {
        String newPassword = new_password.getText();
        String confirmedPassword = confirmer_new_password.getText();

        if (!newPassword.isEmpty() && newPassword.equals(confirmedPassword)) {
            ServiceUser serviceUser = new ServiceUser();
            try {
                // Mettre à jour le mot de passe de l'utilisateur avec le nouveau mot de passe
               user=serviceUser.UpdateMdp(user,newPassword);
                System.out.println("Mot de passe mis à jour avec succès !");
                System.out.println(user +"aprés la modification");
            } catch (Exception e) {
                throw new RuntimeException("Erreur lors de la mise à jour du mot de passe : " + e.getMessage());
            }
        } else {
            System.out.println("Les champs de mot de passe ne correspondent pas ou sont vides.");
        }
    }}

