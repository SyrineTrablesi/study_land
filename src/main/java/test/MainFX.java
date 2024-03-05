package test;

import Controllers.AjouterFormationController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainFX extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/AfficherFormationApprenant.fxml"));
        try {
            Parent root =loader.load();
            Scene scene=new Scene(root);
            primaryStage.setTitle("gerer Formation");
            primaryStage.setScene(scene);
            primaryStage.show();
//            Parent root = FXMLLoader.load(getClass().getResource("/youtube_viewer.fxml"));
//            primaryStage.setScene(new Scene(root, 800, 600));
//            primaryStage.setTitle("YouTube Viewer");
//            primaryStage.show();


        } catch (IOException e) {
            System.out.println(e.getMessage());        }

    }
}
