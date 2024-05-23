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
import javafx.scene.input.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
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
    private GridPane deck;

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

    @FXML
    private void handleCardDragDetection(MouseEvent event){
        Node card = (Node) event.getTarget();
        Dragboard db = card.startDragAndDrop(TransferMode.MOVE);
        ClipboardContent content = new ClipboardContent();
        System.out.println(card.getParent().getParent().getId() + " " + GridPane.getRowIndex(card.getParent()) + " " + GridPane.getColumnIndex(card.getParent()));
        content.putString(card.getParent().getParent().getId() + " " + GridPane.getRowIndex(card.getParent()) + " " + GridPane.getColumnIndex(card.getParent()));
        db.setContent(content);
        event.consume();
    }

    @FXML
    private void handleCardDragOver(DragEvent event){
        if(event.getDragboard().hasString()){
            event.acceptTransferModes(TransferMode.MOVE);
        }
    }

    @FXML
    private void handleCardDrop(DragEvent event){
        String message = event.getDragboard().getString();
        String[] cardID = message.split(" ");
        if(cardID[0].equals("deck")){
            StackPane card_holder_target = (StackPane) event.getTarget();
            StackPane card = (StackPane) getContent(deck, Integer.parseInt(cardID[1]), Integer.parseInt(cardID[2]));
            card_holder_target.getChildren().add(card.getChildren().getFirst());
        }
        if(cardID[0].equals("ladang")){
            StackPane card_holder_target = (StackPane) event.getTarget();
            StackPane card = (StackPane) getContent(ladang, Integer.parseInt(cardID[1]), Integer.parseInt(cardID[2]));
            card_holder_target.getChildren().add(card.getChildren().getFirst());
        }
    }

    private Node getContent(GridPane grid, int row, int col){
        for(Node child : grid.getChildren()){
            int gridrow = GridPane.getRowIndex(child);
            int gridcol = GridPane.getColumnIndex(child);
            if (gridcol == col && gridrow == row){
                return child;
            }
        }
        return null;
    }

}