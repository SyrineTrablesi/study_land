package test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainFX extends Application {
    public static void main(String[] args) {launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/evaluationcards.fxml"));
        Parent root = loader.load();
        Scene sc = new Scene(root);

        // Utilisez sc au lieu de scene
        sc.getStylesheets().add(getClass().getResource("/vbxquesrep.css").toExternalForm());

        primaryStage.setScene(sc);
        primaryStage.show();
    }


}
