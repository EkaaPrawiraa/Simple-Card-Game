package org.example.tubes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.DirectoryChooser;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class LoadController {
    private GameState gamestate;
    private Runnable mainSceneAction;

    public void setGamestate(GameState gamestate) {
        this.gamestate = gamestate;
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
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Select Folder");
        Stage stage = (Stage) selectedFolderButton.getScene().getWindow();
        File selectedDirectory = directoryChooser.showDialog(stage);
        if (selectedDirectory != null) {
            selectedFolderButton.setText(selectedDirectory.getPath());
        }
    }

    private boolean performLoadOperation() {
        try{
            this.gamestate = IO.load(selectedFolderButton.getText());
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
        stage.setScene(new Scene(root));
        stage.show();
    }

}
