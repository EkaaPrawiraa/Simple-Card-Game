package org.example.tubes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.application.Platform;

import java.io.IOException;
import java.util.*;

public class ShuffleController {
    @FXML
    private ImageView Kartu1;

    @FXML
    private ImageView Kartu2;

    @FXML
    private ImageView Kartu3;

    @FXML
    private ImageView Kartu4;

    private Player player;
    private List<Kartu> shuffle;
    private GameState gameState;


    public void setPlayerAndCards(Player player, GameState gamestate) {
        this.player = player;
        this.shuffle = this.player.startTurn(this.player.getKartuList());
        displayCards(this.shuffle);
        this.gameState = gamestate;
    }

    private void displayCards(List<Kartu> cards){
        for(int i = 0; i < cards.size(); i++) {
            Kartu kartu = cards.get(i);
            Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(kartu.getPropertites())));
            switch (i) {
                case 0:
                    Kartu1.setImage(image);
                    break;
                case 1:
                    Kartu2.setImage(image);
                    break;
                case 2:
                    Kartu3.setImage(image);
                    break;
                case 3:
                    Kartu4.setImage(image);
                    break;
            }
        }
    }

    @FXML
    public void initialize() {}

    @FXML
    private void handleRefreshButton(ActionEvent event) {
        this.shuffle = this.player.startTurn(this.player.getKartuList());
        displayCards(this.shuffle);
    }

    @FXML
    private void switchMain(ActionEvent event) throws IOException {
//        // Hapus kartu dari pemain menggunakan salinan koleksi shuffle
        Iterator<Kartu> iterator = this.shuffle.iterator();
        while (iterator.hasNext()) {
            Kartu kartu = iterator.next();
            this.player.addActiveDeck(kartu);
//            this.player.removeKartu(kartu);
            iterator.remove(); // Menghapus item dari shuffle
        }

        iterator = this.shuffle.iterator();
        while (iterator.hasNext()) {
            Kartu kartu = iterator.next();
            this.player.removeKartu(kartu);
//            this.player.removeKartu(kartu);
            iterator.remove(); // Menghapus item dari shuffle
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
        Parent root = loader.load();
        MainController mainController = loader.getController();
        mainController.setPlayerAndCards(this.gameState);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}