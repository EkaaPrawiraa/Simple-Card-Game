package org.example.tubes;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class KartuApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        // Memuat file FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("info-kartu.fxml"));
        Parent root = loader.load();

        // Menyiapkan scene
        Scene scene = new Scene(root, 600, 400);

        // Menyiapkan stage
        primaryStage.setTitle("Kartu Application");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}