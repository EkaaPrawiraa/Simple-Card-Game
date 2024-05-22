package org.example.tubes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class PluginController {

    @FXML
    private Label messageLabel;
    @FXML
    private Label filename;
    @FXML
    private void handlePlugin() {
        boolean addsuccess = performPlugin();

        if (addsuccess) {
            messageLabel.setText("Plugin Loaded Successfully");
            messageLabel.setTextFill(Color.GREEN);
        } else {
            messageLabel.setText("Error: File is not a valid JAR");
            messageLabel.setTextFill(Color.RED);
        }
    }
    private boolean performPlugin(){
        return false;
    }

    @FXML
    private void handleChooseFile(){
        FileChooser filechooser = new FileChooser();
        filechooser.setTitle("Chooser JAR File");
        filechooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JAR Files", "*.jar"));
        Stage stage = (Stage) filename.getScene().getWindow();

        File selectedFile = filechooser.showOpenDialog(stage);

        if (selectedFile != null) {
            filename.setText(selectedFile.getName());
        } else {
            filename.setText("No file chosen");
        }
    }
    @FXML
    private void switchMain(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("main.fxml")));
        stage.setScene(new Scene(root));
        stage.show();
    }
}
