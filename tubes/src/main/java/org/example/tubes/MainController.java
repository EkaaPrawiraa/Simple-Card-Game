package org.example.tubes;

import javafx.event.ActionEvent;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
    private Label turn_label;

    @FXML
    private Label deck_label;

    @FXML
    private Label PLayer1;

    @FXML
    private Label PLayer2;

    @FXML
    private Label Gulden1;

    @FXML
    private Label Gulden2;

    private List<Image> cards;

    private Player player;

    private Player musuh;

    private GameState gamestate;

    private Map<ImageView, Kartu> kartuMap = new HashMap<>();

    private Kartu card;

    private int row_origin, col_origin;

    private String grid_origin;

    private boolean isladangmusuh = false;


    public void setPlayerAndCards(GameState gamestate) {
        this.gamestate = gamestate;
        if(this.gamestate.getJumlahTurn() % 2 == 1){
            this.player = this.gamestate.getPlayer1();
            this.musuh = this.gamestate.getPlayer2();
            PLayer1.setStyle("-fx-text-fill: red;");
        }
        else{
            this.player = this.gamestate.getPlayer2();
            this.musuh = this.gamestate.getPlayer1();
            PLayer2.setStyle("-fx-text-fill: red;");
        }
        Gulden1.setText(String.valueOf(this.player.getGulden())+ "$");
        Gulden2.setText(String.valueOf(this.musuh.getGulden())+ "$");
        turn_label.setText(String.valueOf(this.gamestate.getJumlahTurn()));
        deck_label.setText(String.valueOf(this.player.getKartuList().size()) + "/40");
        setDeckActive(this.player);
        setLadang(this.player);
    }


    private void setDeckActive(Player player){
        List<Kartu> dek = player.getActiveDeck();
        int row = 0;
        for (int col = 0; col < dek.size(); col++) {
            Kartu kartu = dek.get(col);
            if(kartu != null){
                Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(kartu.getPropertites())));
                StackPane stackPane = getStackPane(deck, row, col);
                if (stackPane != null) {
                    ImageView imageView = new ImageView(image);
                    imageView.setFitWidth(98); // Set width to 98
                    imageView.setFitHeight(115); // Set height to 115
                    imageView.setOnDragDetected(this::handleCardDragDetection);
                    stackPane.getChildren().add(imageView);
                    addKartuMapping(imageView, kartu);
                }
            }
        }

    }

    private void setLadang(Player player){
        Ladang dek = player.getLadang();
        for (int location = 0; location < 20; location++) {
            if(dek.getMahluk(location) != null){
                Kartu kartu = dek.getMahluk(location);
                Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(kartu.getPropertites())));
                StackPane stackPane = getStackPane(ladang, (int ) location/5, location%5);
                if (stackPane != null) {
                    ImageView imageView = new ImageView(image);
                    imageView.setFitWidth(98); // Set width to 98
                    imageView.setFitHeight(115); // Set height to 115
                    imageView.setOnDragDetected(this::handleCardDragDetection);
                    stackPane.getChildren().add(imageView);
                    addKartuMapping(imageView, kartu);
                }
            }

        }

    }

    private void clearpage_ladang(){
        for (Node node : ladang.getChildren()) {
            if (node instanceof StackPane) {
                StackPane stackPane = (StackPane) node;
                if (!stackPane.getChildren().isEmpty()) {
                    ImageView imageView = (ImageView) stackPane.getChildren().remove(0);
                    if (imageView != null && imageView.getParent() != null) {
                        ((StackPane) imageView.getParent()).getChildren().remove(imageView);
                    }
                }
            }
        }

        System.out.println("All ImageViews have been removed and ladang is cleared.");
    }

    private void clearPage_deck(){
        for (Node node : deck.getChildren()) {
            if (node instanceof StackPane) {
                StackPane stackPane = (StackPane) node;
                if (!stackPane.getChildren().isEmpty()) {
                    ImageView imageView = (ImageView) stackPane.getChildren().remove(0);
                    if (imageView != null && imageView.getParent() != null) {
                        ((StackPane) imageView.getParent()).getChildren().remove(imageView);
                    }
                }
            }
        }

        System.out.println("All ImageViews have been removed and deck is cleared.");
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

    }

    @FXML
    private void switchload(ActionEvent event) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("load.fxml"));
        Parent root = loader.load();
        LoadController mainController = loader.getController();
        mainController.setGamestate(this.gamestate);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
    @FXML
    private void switchsave(ActionEvent event) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("save.fxml"));
        Parent root = loader.load();
        SaveController mainController = loader.getController();
        mainController.setGamestate(this.gamestate);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void switchplugin(ActionEvent event) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("plugin.fxml"));
        Parent root = loader.load();
        PluginController mainController = loader.getController();
        mainController.setGamestate(this.gamestate);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
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
                    if(isladangmusuh && this.card.getName() == "Destroy" || this.card.getName() == "Delay"){
                        event.acceptTransferModes(TransferMode.MOVE);
                    }
                    else if(!isladangmusuh){
                        event.acceptTransferModes(TransferMode.MOVE);
                    }

                }
            } else {
                if(!isladangmusuh){
                    event.acceptTransferModes(TransferMode.MOVE);
                }
            }
        }
    }

    @FXML
    private void handleCardDrop(DragEvent event){
        StackPane card_holder_target = (StackPane) event.getTarget();
        int row = GridPane.getRowIndex(card_holder_target);
        int col = GridPane.getColumnIndex(card_holder_target);
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
                            if(isladangmusuh){
                                this.musuh.getLadang().dellMahluk(row * 4 + col + row);
                            }
                            else{
                                this.player.getLadang().dellMahluk(row * 4 + col + row);
                            }
                        }
                        ((Mahluk) targetKartu).addEffect((Item) this.card);
                        this.player.delActiveDeck(this.card);
                    } else if (this.card instanceof Produk && targetKartu instanceof Hewan) {
                        System.out.println(targetKartu.getClass());
                        ((Hewan) targetKartu).feed((Produk) this.card);
                        this.player.delActiveDeck(this.card);
                    }
                } else {
                    // No card in the target holder, add the dragged card
                    card_holder_target.getChildren().add(cardImageView);
                    Kartu kartu = getKartuFromImageView(cardImageView);
                    System.out.println(kartu.getClass());
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
    private void handleladangku(){
        this.isladangmusuh = false;
        clearpage_ladang();
        setLadang(this.player);
        System.out.print("Player");
    }

    @FXML
    private void handleladangmusuh(){
        this.isladangmusuh = true;
        clearpage_ladang();
        setLadang(this.musuh);
        System.out.print("Musuh");
    }

    @FXML
    private void handleStackPaneClick(MouseEvent event) throws IOException {
        System.out.println("StackPane clicked!");

        Node node = (Node) event.getTarget();

        while (node != null && !(node instanceof StackPane)) {
            node = node.getParent();
        }

        if (node == null || !(node instanceof StackPane)) {
            System.out.println("No StackPane found in the event target hierarchy.");
            return;
        }

        StackPane card_holder_target = (StackPane) node;

        if (!card_holder_target.getChildren().isEmpty() && card_holder_target.getChildren().get(0) instanceof ImageView) {
            ImageView targetCardImageView = (ImageView) card_holder_target.getChildren().get(0);
            Kartu kartu = getKartuFromImageView(targetCardImageView);

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
        if (this.gamestate.getJumlahTurn() == 20) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            if (this.player.getGulden() > this.musuh.getGulden()) {
                alert.setTitle("Hasil Permainan");
                alert.setHeaderText(null);
                alert.setContentText("Player2 menang");
                alert.showAndWait();
            } else if (this.player.getGulden() < this.musuh.getGulden()) {
                alert.setTitle("Hasil Permainan");
                alert.setHeaderText(null);
                alert.setContentText("Player1 menang");
                alert.showAndWait();
            } else {
                alert.setTitle("Hasil Permainan");
                alert.setHeaderText(null);
                alert.setContentText("Yahhh Seri nihh");
                alert.showAndWait();
            }

            // Menghentikan permainan dengan menutup jendela
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
        }
        else if(this.gamestate.getJumlahTurn() % 2 == 1){
            this.player = this.gamestate.getPlayer1();
            this.musuh = this.gamestate.getPlayer2();
        }
        else{
            this.player = this.gamestate.getPlayer2();
            this.musuh = this.gamestate.getPlayer1();
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
            clearPage_deck();
            clearpage_ladang();
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