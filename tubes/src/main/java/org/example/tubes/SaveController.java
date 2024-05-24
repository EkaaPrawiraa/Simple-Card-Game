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
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class SaveController {
    private GameState gamestate;
    public void setGamestate(GameState gamestate) {
        this.gamestate = gamestate;
    }
    @FXML
    private Label messageLabel;

    @FXML
    private void handleSave() {
        boolean saveSuccessful = performSaveOperation();

        if (saveSuccessful) {
            messageLabel.setText("Saved Successfully");
            messageLabel.setTextFill(Color.GREEN);
        } else {
            messageLabel.setText("Failed to save");
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

    private boolean performSaveOperation() {
        try{
            IO.save(selectedFolderButton.getText(), gamestate);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    @FXML
    private void switchMain(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
        Parent root = loader.load();
        MainController mainController = loader.getController();
        mainController.setPlayerAndCards(this.gamestate);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
