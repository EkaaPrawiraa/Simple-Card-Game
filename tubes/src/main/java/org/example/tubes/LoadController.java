package org.example.tubes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.DirectoryChooser;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LoadController {
    private GameState gamestate;
    private Runnable mainSceneAction;
    @FXML
    private ComboBox<String> dropdown;

    public LoadController() {
        // Constructor is called when the controller is instantiated
        // Initialize ComboBox items here
        initializeComboBox();
    }

    public void setGamestate(GameState gamestate) {
        this.gamestate = gamestate;
        // Re-initialize ComboBox items here if necessary
        initializeComboBox();
    }
    @FXML
    private Label messageLabel;

    @FXML
    private void handleSave() {
        boolean saveSuccessful = performLoadOperation();

        if (saveSuccessful) {
            messageLabel.setText("Loaded Successfully");
            messageLabel.setTextFill(Color.GREEN);
        } else {
            messageLabel.setText("Failed to load");
            messageLabel.setTextFill(Color.RED);
        }
    }



    @FXML
    private Button selectedFolderButton;

    @FXML
    private void chooseFolder() {
        if (dropdown.getValue() != null && !dropdown.getValue().equals("txt")) {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select File");
            Stage stage = (Stage) selectedFolderButton.getScene().getWindow();
            File selectedFile = fileChooser.showOpenDialog(stage);
            if (selectedFile != null) {
                selectedFolderButton.setText(selectedFile.getPath());
            }
        } else {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            directoryChooser.setTitle("Select Folder");
            Stage stage = (Stage) selectedFolderButton.getScene().getWindow();
            File selectedDirectory = directoryChooser.showDialog(stage);
            if (selectedDirectory != null) {
                selectedFolderButton.setText(selectedDirectory.getPath());
            }
        }
    }


    private boolean performLoadOperation() {
        try{
            List<Plugin> plugins = this.gamestate.availPlugin;
            this.gamestate = IO.load(selectedFolderButton.getText());
            this.gamestate.availPlugin = plugins;
            return true;
        } catch (IOException e) {
            return false;
        }
    }
    @FXML
    private void switchMain(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
        Parent root = loader.load();
        MainController mainController = loader.getController();
        mainController.setPlayerAndCards(this.gamestate, false);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("main.css")).toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
    private void initializeComboBox() {
        if (gamestate != null && dropdown != null) {
            List<String> options = new ArrayList<>();
            options.add("txt");
            for (Plugin plugin: this.gamestate.availPlugin){
                options.add(plugin.getName());
            }
            dropdown.getItems().addAll(options);
            dropdown.setValue("txt");  // Set default value
        }
    }

}
