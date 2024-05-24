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

    private Kartu card;

    private int row_origin, col_origin;

    private String grid_origin;

    private List<ImageView> listOfImageViews = new ArrayList<>();

    public void setPlayerAndCards(GameState gamestate) {
        this.gamestate = gamestate;
        if(this.gamestate.getJumlahTurn() % 2 == 1){
            this.player = this.gamestate.getPlayer1();
        }
        else{
            this.player = this.gamestate.getPlayer2();
        }
//        if(this.player.getLadang().getMahluk(0) != null){
//
//        }
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
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(98); // Set width to 98
                imageView.setFitHeight(115); // Set height to 115
                imageView.setOnDragDetected(this::handleCardDragDetection);
                listOfImageViews.add(imageView);
                stackPane.getChildren().add(imageView);
                addKartuMapping(imageView, kartu);
            }
        }

    }

    private void clearpage(){
        for (ImageView imageView : listOfImageViews) {
            // Ensure the imageView is not null and has a parent
            if (imageView != null && imageView.getParent() != null) {
                ((StackPane) imageView.getParent()).getChildren().remove(imageView);
            }
        }

        // Clear the list
        listOfImageViews.clear();

        System.out.println("All ImageViews have been removed and listOfImageViews is cleared.");
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
    private void switchToko(ActionEvent event) throws Exception{
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("toko.fxml")));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("toko.css")).toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void handleCardDragDetection(MouseEvent event){
        Node card = (Node) event.getTarget();
        Dragboard db = card.startDragAndDrop(TransferMode.MOVE);
        ClipboardContent content = new ClipboardContent();
        this.card = getKartuFromImageView((ImageView)card);
        System.out.println(this.card.getClass());
        this.grid_origin = card.getParent().getParent().getId();
        this.row_origin = GridPane.getRowIndex(card.getParent());
        this.col_origin = GridPane.getColumnIndex(card.getParent());
        content.putString("dummy");
        db.setContent(content);
        event.consume();
    }

    @FXML
    private void handleCardDragOver(DragEvent event){
        if(event.getDragboard().hasString()){
            if (this.card instanceof Item || this.card instanceof Produk){
                StackPane target = (StackPane) event.getTarget();
                if (event.getTarget() instanceof ImageView||!target.getChildren().isEmpty()){
                    event.acceptTransferModes(TransferMode.MOVE);
                }
            } else {
                event.acceptTransferModes(TransferMode.MOVE);
            }
        }
    }

    @FXML
    private void handleCardDrop(DragEvent event){
        StackPane card_holder_target = (StackPane) event.getTarget();
        if(this.grid_origin.equals("deck")){
            StackPane card = (StackPane) getContent(deck, this.row_origin, this.col_origin);
//            card_holder_target.getChildren().add((ImageView)card.getChildren().getFirst());

            if (card != null && !card.getChildren().isEmpty()) {
                ImageView cardImageView = (ImageView) card.getChildren().remove(0);

                if (!card_holder_target.getChildren().isEmpty()) {
                    // There is already a card in the target holder
                    ImageView targetCardImageView = (ImageView) card_holder_target.getChildren().get(0);
                    Kartu targetKartu = getKartuFromImageView(targetCardImageView);

                    if (this.card instanceof Item && targetKartu instanceof Mahluk) {
                        // Add the effect of the Item to the existing Mahluk in the target holder
                        if(this.card.getName().equals("Destroy")){
                            //remove dari list of ladang
                            card_holder_target.getChildren().clear();
                        }
                        ((Mahluk) targetKartu).addEffect((Item) this.card);
                    } else if (this.card instanceof Produk && targetKartu instanceof Hewan) {
                        System.out.println(targetKartu.getClass());
                        ((Hewan) targetKartu).feed((Produk) this.card);
                    }
                } else {
                    // No card in the target holder, add the dragged card
                    card_holder_target.getChildren().add(cardImageView);
                    Kartu kartu = getKartuFromImageView(cardImageView);
                    System.out.println(kartu.getClass());
                    int row = GridPane.getRowIndex(card_holder_target);
                    int col = GridPane.getColumnIndex(card_holder_target);
                    this.player.getLadang().addMahluk(kartu, row * 4 + col + row);
                    this.player.delActiveDeck(kartu);
                }
            }
        }
        if(this.grid_origin.equals("ladang")){
            StackPane card = (StackPane) getContent(ladang, this.row_origin, this.col_origin);

            if (card != null && !card.getChildren().isEmpty()) {

                if (card_holder_target.getChildren().isEmpty()) {
                    ImageView cardImageView = (ImageView) card.getChildren().remove(0);
                    card_holder_target.getChildren().add(cardImageView);
                    Kartu kartu = getKartuFromImageView(cardImageView);
                    System.out.println(kartu.getClass());
                    int row = GridPane.getRowIndex(card_holder_target);
                    int col = GridPane.getColumnIndex(card_holder_target);
                    this.player.getLadang().getMahluk().set(this.row_origin * 4 + this.col_origin + this.row_origin, null);
                    this.player.getLadang().addMahluk(kartu, row * 4 + col + row);
                }
            }
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
    private void handleStackPaneClick(MouseEvent event) throws IOException {
        System.out.println("StackPane clicked!");

        Node node = (Node) event.getTarget();

        // Ensure we get the StackPane, even if the click is on the ImageView
        while (node != null && !(node instanceof StackPane)) {
            node = node.getParent();
        }

        if (node == null || !(node instanceof StackPane)) {
            System.out.println("No StackPane found in the event target hierarchy.");
            return;
        }

        StackPane card_holder_target = (StackPane) node;

        // Ensure the StackPane has children and the first child is an ImageView
        if (!card_holder_target.getChildren().isEmpty() && card_holder_target.getChildren().get(0) instanceof ImageView) {
            ImageView targetCardImageView = (ImageView) card_holder_target.getChildren().get(0);
            Kartu kartu = getKartuFromImageView(targetCardImageView);
            clearpage();

            if (kartu instanceof Mahluk) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("info-kartu.fxml"));
                Parent root = loader.load();
                KartuController kartuController = loader.getController();
                kartuController.setMahluk((Mahluk) kartu);
                kartuController.setGameState(this.gamestate);

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
            } else {
                System.out.println("The selected card is not a Mahluk.");
            }
        } else {
            System.out.println("No card found in the target StackPane or the card is not an ImageView.");
        }
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
            setPlayerAndCards(this.gamestate);
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