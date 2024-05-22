package org.example.tubes;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.util.Objects;

public class KartuController {
    @FXML
    private ImageView image;

    @FXML
    private Label Kartu;

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
}