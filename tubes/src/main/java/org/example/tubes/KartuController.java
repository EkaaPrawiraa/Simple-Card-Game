package org.example.tubes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Iterator;
import java.util.Objects;

public class KartuController {
    @FXML
    private ImageView image;

    @FXML
    private Label Kartu;

    @FXML
    private Label Textt;

    @FXML
    private Label Weight;

    @FXML
    private Label Count;

    @FXML
    private Label Active_Items;

    @FXML
    private Button Back;

    @FXML
    private Button Panen;

    private Hewan hewan;

    private Tanaman tanaman;

    private GameState gameState;

    public void setMahluk(Mahluk mahluk) {
        if (mahluk instanceof Hewan) {
            this.hewan = (Hewan) mahluk;
            this.tanaman = null; // Reset tanaman
            Textt.setText("Berat : ");
            updateHewanUI();
        } else if (mahluk instanceof Tanaman) {
            this.tanaman = (Tanaman) mahluk;
            this.hewan = null; // Reset hewan
            Textt.setText("Umur : ");
            updateTanamanUI();
        }
    }

    private void updateHewanUI() {
        if (hewan != null) {
            image.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(hewan.getPropertites()))));
            Weight.setText(String.valueOf(hewan.getWeight()));
            // Update other UI components related to Hewan
            // For example:
//            String message = "(" +
             Count.setText("(" + String.valueOf(hewan.getHarvest_value()) + ")");
            // activeItems.setText(hewan.getActiveItemsDescription());
        }
    }

    private void updateTanamanUI() {
        if (tanaman != null) {
            image.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(tanaman.getPropertites()))));
            // Assuming Tanaman has similar properties or you can add specific ones
            // For example:
            Weight.setText(String.valueOf(tanaman.getAge()));
            Count.setText("(" + String.valueOf(tanaman.getHarvest_value() + ")"));
//             activeItems.setText(tanaman.getActiveItemsDescription());
        }
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    @FXML
    public void initialize() {
        // Memuat gambar dari sumber daya
        Image img = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Hewan/sheep.png"))); // Ubah path ke gambar yang benar
        image.setImage(img);

        // Mengatur nilai label
        Weight.setText("5");
        Count.setText("(8)");
        Active_Items.setText("Accelerate(1), Delay(1)");
    }

    @FXML
    private void handleBackButton() {
        // Menutup stage saat tombol Back ditekan
        Stage stage = (Stage) Back.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handlePanenButton() {
        Kartu.setText("Panen Abangkuh!");
    }

    @FXML
    private void switchMain(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
        Parent root = loader.load();
        MainController mainController = loader.getController();
        mainController.setPlayerAndCards(this.gameState);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}