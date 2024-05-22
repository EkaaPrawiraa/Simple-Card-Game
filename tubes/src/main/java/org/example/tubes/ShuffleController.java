package org.example.tubes;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class ShuffleController {
    @FXML
    private ImageView Kartu1;

    @FXML
    private ImageView Kartu2;

    @FXML
    private ImageView Kartu3;

    @FXML
    private ImageView Kartu4;

    private List<Image> cards;

    @FXML
    public void initialize() {
        // Load images
        cards = new ArrayList<>();
        cards.add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Hewan/sheep.png"))));
        cards.add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Hewan/bear.png"))));
        cards.add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Hewan/chicken.png"))));
        cards.add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Hewan/cow.png"))));

        // Shuffle images
        Collections.shuffle(cards);

        // Display shuffled images
//        Image img = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Hewan/sheep.png"))); // Ubah path ke gambar yang benar
        Kartu1.setImage(cards.get(0));
        Kartu2.setImage(cards.get(1));
        Kartu3.setImage(cards.get(2));
        Kartu4.setImage(cards.get(3));
    }

    @FXML
    private void handleRefreshButton() {
        // Shuffle images again when Refresh button is clicked
        Collections.shuffle(cards);

        // Display shuffled images
        Kartu1.setImage(cards.get(0));
        Kartu2.setImage(cards.get(1));
        Kartu3.setImage(cards.get(2));
        Kartu4.setImage(cards.get(3));
    }
}