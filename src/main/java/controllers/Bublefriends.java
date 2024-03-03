package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.geometry.Pos;

import java.util.ArrayList;
import java.util.List;

public class Bublefriends {

    @FXML
    private StackPane rootPane;

    @FXML
    private TextField serchbyname;

    private List<Friend> friendsList; // Declare friendsList as a class variable

    @FXML
    private void initialize() {
        friendsList = new ArrayList<>();
        // Populate your friends list here, for example:
        friendsList.add(new Friend("Alice", 25));
        friendsList.add(new Friend("Bob", 30));
        friendsList.add(new Friend("Charlie", 28));
        // Add more friends as needed...

        // Display all friends initially
        displayFriends(friendsList);
    }

    @FXML
    void searchByName(KeyEvent event) {
        String searchTerm = serchbyname.getText().trim().toLowerCase();
        List<Friend> filteredList = new ArrayList<>();

        // Filter friends based on search term
        for (Friend friend : friendsList) {
            if (friend.getName().toLowerCase().contains(searchTerm)) {
                filteredList.add(friend);
            }
        }

        // Update displayed friends based on filtered list
        displayFriends(filteredList);
    }

    // Method to display friends in the VBox
    private void displayFriends(List<Friend> friendList) {
        // Create VBox to hold friends
        VBox containerPane = new VBox();
        containerPane.setSpacing(10); // Set vertical spacing between friend panes
        containerPane.setPrefWidth(100); // Set fixed width
        containerPane.setMinWidth(70); // Set minimum width
        containerPane.setMaxHeight(Double.MAX_VALUE); // Set maximum height
        containerPane.setAlignment(Pos.CENTER); // Center the content vertically

        // Create Label objects for each friend and add them to the containerPane
        for (Friend friend : friendList) {
            VBox friendPane = new VBox();
            friendPane.setPrefWidth(100);
            friendPane.setPrefHeight(70);
            friendPane.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 20;");

            Label nameLabel = new Label(friend.getName());
            nameLabel.setFont(Font.font("System Bold", 13)); // Set font to bold
            nameLabel.setStyle("-fx-font-weight: bold;"); // Alternate way to set font to bold

            Label ageLabel = new Label("Age: " + friend.getAge());

            friendPane.setOnMouseClicked(event -> {
                System.out.println("Clicked on: " + friend.getName());
            });

            friendPane.getChildren().addAll(nameLabel, ageLabel);
            friendPane.setAlignment(Pos.CENTER); // Center the content vertically
            containerPane.getChildren().add(friendPane);
        }

        // Create ScrollPane and add the containerPane to it
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(containerPane);
        scrollPane.setFitToWidth(true); // Allow horizontal scrolling if needed

        // Set fixed size for ScrollPane
        scrollPane.setPrefSize(100, Double.MAX_VALUE);
        scrollPane.setMinSize(70, 0);

        // Add the ScrollPane to the rootPane (centered)
        rootPane.getChildren().clear(); // Clear previous content
        rootPane.getChildren().add(scrollPane);
        StackPane.setAlignment(scrollPane, javafx.geometry.Pos.CENTER);
    }

    // Define a class to represent a friend
    static class Friend {
        private String name;
        private int age;

        public Friend(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }
    }
}
