package org.example.tubes;

import javafx.event.ActionEvent;
import javafx.application.Platform;
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
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class MainController {
    @FXML
    private GridPane ladang;

    @FXML
    private GridPane deck;

    @FXML
    private ImageView Grid21;

    @FXML
    private ImageView Grid22;

    @FXML
    private ImageView Grid23;

    @FXML
    private ImageView Grid24;

    @FXML
    private ImageView Grid25;

    @FXML
    private ImageView Grid26;

    private List<Image> cards;

    private Player player;

    private GameState gamestate;

    private Map<ImageView, Kartu> kartuMap = new HashMap<>();


    public void setPlayerAndCards(Player player, GameState gamestate) {
        this.player = player;
        this.gamestate = gamestate;
        setDeckActive();
    }

    private void setDeckActive(){
        List<Kartu> dek = this.player.getActiveDeck();
        int row = 0;
        for (int col = 0; col < dek.size(); col++) {
            Kartu kartu = dek.get(col);
            Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(kartu.getPropertites())));
            StackPane stackPane = getStackPane(deck, row, col);
            if (stackPane != null) {
                System.out.println("kontol");
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(98); // Set width to 98
                imageView.setFitHeight(115); // Set height to 115
                imageView.setOnDragDetected(this::handleCardDragDetection);
                stackPane.getChildren().add(imageView);
                addKartuMapping(imageView, kartu);
            }
        }

    }

    private void moveToLadang(ImageView imageView, int row, int col) {
        // Remove ImageView from its current parent StackPane
        StackPane currentParent = (StackPane) imageView.getParent();
        if (currentParent != null) {
            currentParent.getChildren().remove(imageView);
        }

        // Find the target StackPane in ladang
        StackPane targetPane = getStackPane(ladang, row, col);

        // Add the ImageView to the target StackPane
        if (targetPane != null) {
            targetPane.getChildren().add(imageView);
        } else {
            // Create a new StackPane if not found
            StackPane newStackPane = new StackPane(imageView);
            GridPane.setRowIndex(newStackPane, row);
            GridPane.setColumnIndex(newStackPane, col);
            ladang.getChildren().add(newStackPane);
        }
    }

    private void moveToDeck(ImageView imageView, int row, int col) {
        // Remove ImageView from its current parent StackPane
        StackPane currentParent = (StackPane) imageView.getParent();
        if (currentParent != null) {
            currentParent.getChildren().remove(imageView);
        }

        // Find the target StackPane in deck
        StackPane targetPane = getStackPane(deck, row, col);

        // Add the ImageView to the target StackPane
        if (targetPane != null) {
            targetPane.getChildren().add(imageView);
        } else {
            // Create a new StackPane if not found
            StackPane newStackPane = new StackPane(imageView);
            GridPane.setRowIndex(newStackPane, row);
            GridPane.setColumnIndex(newStackPane, col);
            deck.getChildren().add(newStackPane);
        }
    }

    private StackPane getStackPane(GridPane grid, int row, int col) {
        for (Node child : grid.getChildren()) {
            if (GridPane.getRowIndex(child) == row && GridPane.getColumnIndex(child) == col) {
                if (child instanceof StackPane) {
                    return (StackPane) child;
                }
            }
        }
        return null;
    }

    @FXML
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
//            System.out.println(card.getChildren().getFirst().getClass());
//            Kartu kartu = getKartuFromImageView((ImageView)card.getChildren().getFirst());
//            System.out.println(kartu.getName());
//            System.out.print(GridPane.getRowIndex((Node)event.getTarget()) + " ");
//            System.out.println(GridPane.getColumnIndex((Node)event.getTarget()));
//            this.player.getLadang().addMahluk(kartu, GridPane.getRowIndex((Node)event.getTarget())*4 + GridPane.getColumnIndex((Node)event.getTarget()));
//            this.player.delActiveDeck(kartu);
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

    @FXML
    private void handlenextbutton(ActionEvent event)  throws IOException {
        this.gamestate.setJumlahTurn();
        if(this.gamestate.getJumlahTurn() % 2 == 1){
            this.player = this.gamestate.getPlayer1();
        }
        else{
            this.player = this.gamestate.getPlayer2();
        }

        if(this.player.startTurn(this.player.getKartuList()) != null){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("shuffle-kartu.fxml"));
            Parent root = loader.load();
            ShuffleController shuffleController = loader.getController();
            shuffleController.setPlayerAndCards(this.player, this.gamestate);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        }
        else{
            setPlayerAndCards(this.player, this.gamestate);
        }


    }

    // Metode untuk menambahkan hubungan antara ImageView dan Kartu ke dalam map
    private void addKartuMapping(ImageView imageView, Kartu kartu) {
        kartuMap.put(imageView, kartu);
    }

    // Metode untuk mendapatkan Kartu yang terkait dengan ImageView tertentu
    private Kartu getKartuFromImageView(ImageView imageView) {
        return kartuMap.get(imageView);
    }

    // Metode untuk menghapus pemetaan ImageView dan Kartu dari map
    private void removeKartuMapping(ImageView imageView) {
        kartuMap.remove(imageView);

    }

}