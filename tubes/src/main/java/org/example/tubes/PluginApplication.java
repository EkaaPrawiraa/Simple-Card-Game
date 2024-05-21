package org.example.tubes;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;

import java.io.IOException;
import java.util.Objects;

public class PluginApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("plugin.fxml")));

        stage.setTitle("Plugin");
        stage.setScene(new Scene(root));
        stage.show();
    }
    public static void main(String[] args) {launch(args);}
}
