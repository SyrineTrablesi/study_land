package controllers;

import entities.Favoris;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import services.ServiceFavoris;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
public class CardPanierFavoris {
    @FXML
    private Button btn_supprimerFavoris;

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

    @FXML
    private Label labelAjoutDate;

    @FXML
    private AnchorPane cardsContainer;
    private Favoris favoris;
    private FlowPane cardsFlowPane;

    public void setCardsFlowPane2(FlowPane cardsFlowPane) {
        this.cardsFlowPane = cardsFlowPane;
    }


    private ServiceFavoris serviceFavoris = new ServiceFavoris();

    public void setData(String title, String description, String niveau, int duree, float prix, String categorie, Date dateAjout) {
        idtitle.setText(title);
        idtext.setText(description);
        niveauLabel.setText(niveau);
        dureeLabel.setText(Integer.toString(duree));
        prixLabel.setText(Float.toString(prix));
        categorieLabel.setText(categorie);

        // Si vous devez formater la date, vous pouvez le faire ici
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ");
        String formattedDate = sdf.format(dateAjout);

        // Affichez la date d'ajout dans votre vue
        labelAjoutDate.setText(formattedDate);

    }


    public void setFavoris(Favoris favoris) {
        this.favoris = favoris;

    }

    @FXML
    public void supprimerFormationFavoris(ActionEvent actionEvent) {
        if (favoris != null) {
            try {
                serviceFavoris.supprimer(favoris); // Utiliser l'objet Favoris pour supprimer
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            System.out.println("formation supprimée");
            if (cardsFlowPane != null) {
                cardsFlowPane.getChildren().remove(cardsContainer);
            } else {
                System.out.println("cardsFlowPane est null");
            }
        }
    }


        @FXML
        void InfoFormation(ActionEvent event) {
            String title = idtitle.getText(); // Obtenez le titre de la carte

            try {
                getInfoFromWikipedia(title);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    void getInfoFromWikipedia(String title) throws IOException {
        OkHttpClient client = new OkHttpClient();

        // Construisez l'URL pour rechercher l'article Wikipedia basé sur le titre
        String encodedTitle = URLEncoder.encode(title, "UTF-8");
        String url = "https://en.wikipedia.org/w/api.php?action=query&format=json&prop=extracts&exintro&explaintext&titles=" + encodedTitle;

        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String responseBody = response.body().string();
                // Vérifiez si l'article est manquant
                if (responseBody.contains("\"missing\"")) {
                    // Afficher une alerte avec le message désiré
                    showAlert("L'article ", "La programmation et le développement sont des disciplines fondamentales dans le domaine de l'informatique. La programmation consiste à écrire des instructions pour ordinateurs dans un langage compréhensible par ces derniers, tandis que le développement englobe un processus plus large qui inclut la conception, la planification et la mise en œuvre de logiciels ou d'applications. Les programmeurs utilisent des langages de programmation tels que Python, Java, C++ ou JavaScript pour créer des solutions informatiques, tandis que les développeurs travaillent souvent en équipe pour concevoir des produits logiciels, en tenant compte des besoins des utilisateurs et des contraintes techniques. Ces domaines sont en constante évolution, avec de nouvelles technologies et méthodologies émergentes régulièrement, exigeant ainsi des professionnels une capacité d'adaptation continue pour rester compétitifs sur le marché.");
                } else {
                    // Traitez la réponse ici, vous pouvez l'afficher dans une boîte de dialogue ou une autre interface utilisateur
                    System.out.println(responseBody);
                }
            } else {
                System.out.println("Erreur: " + response.code() + " - " + response.message());
            }
        }
    }

    // Méthode pour afficher une alerte
    void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        // Obtenir la fenêtre de l'alerte
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();

        // Définir la taille de la fenêtre
        stage.setResizable(true);
        stage.setWidth(200);
        stage.setHeight(200);

        // Afficher l'alerte
        alert.showAndWait();
    }
}


