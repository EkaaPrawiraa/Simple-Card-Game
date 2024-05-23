package org.example.tubes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private GridPane ladang;

    @FXML
    private Label deck_label;

//    @FXML
//    private ImageView Grid1;

    @FXML
    private ImageView Grid2;

    private List<Image> cards;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cards = new ArrayList<>();
        cards.add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Hewan/sheep.png"))));
        cards.add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Hewan/bear.png"))));
        cards.add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Hewan/chicken.png"))));
        cards.add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Hewan/cow.png"))));

    }
    @FXML
    private void switchload(ActionEvent event) throws Exception{
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("load.fxml")));
        stage.setScene(new Scene(root));
        stage.show();
    }
    @FXML
    private void switchsave(ActionEvent event) throws Exception{
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("save.fxml")));
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void switchplugin(ActionEvent event) throws Exception{
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("plugin.fxml")));
        stage.setScene(new Scene(root));
        stage.show();
    }

}