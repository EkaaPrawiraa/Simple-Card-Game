package org.example.tubes;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ShuffleApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        // Load the FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("shuffle-kartu.fxml"));
        Parent root = loader.load();

        // Set up the scene
        Scene scene = new Scene(root, 600, 400);

        // Set up the stage
        primaryStage.setTitle("Image Shuffle Application");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {

        launch(args);
    }
}