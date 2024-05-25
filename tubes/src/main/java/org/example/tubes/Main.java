package org.example.tubes;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Initialize all cards and game state
        List<Kartu> allcards1 = generateAllCard();
        List<Kartu> allcards2 = generateAllCard();
        Player player1 = new Player();
        Player player2 = new Player();
        player1.setKartuList(allcards1);
        player2.setKartuList(allcards2);

        int JumlahTurn = 0;
        Store Toko = new Store();
        Toko.getBarang().put(Utility.constructor("sirip hiu"), 2);
        List<Plugin> plugins = new ArrayList<>();
        GameState gameState = new GameState(player1, player2, JumlahTurn, Toko,plugins);

        // Set the number of turns and display the shuffle-card GUI if it's player1's turn
        gameState.setJumlahTurn();
        if (gameState.getJumlahTurn() % 2 == 1) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("shuffle-kartu.fxml")); // Ensure the path is correct
                    Parent root = loader.load();
                    ShuffleController shuffleController = loader.getController();
                    shuffleController.setPlayerAndCards(gameState.getPlayer1(), gameState);

                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    // Main method to launch the application
    public static void main(String[] args) {
        launch(args);
    }


    public static List<Kartu> generateAllCard(){
        List<Kartu> cards = new ArrayList<>();
//        cards.add(new Kartu("Bear", null, "/Hewan/bear.png"));
        cards.add(Utility.constructor("Ayam"));
        cards.add(Utility.constructor("Sapi"));
        cards.add(Utility.constructor("Hiu Darat"));
        cards.add(Utility.constructor("kuda"));
        cards.add(Utility.constructor("Domba"));
        cards.add(Utility.constructor("Accelerate"));
        cards.add(Utility.constructor("Bear Trap"));
        cards.add(Utility.constructor("Delay"));
        cards.add(Utility.constructor("Destroy"));
        cards.add(Utility.constructor("Instant Harvest"));
        cards.add(Utility.constructor("Protect"));
        cards.add(Utility.constructor("Biji Jagung"));
        cards.add(Utility.constructor("Biji Labu"));
        cards.add(Utility.constructor("Biji Stroberi"));
        cards.add(Utility.constructor("jagung"));
        cards.add(Utility.constructor("daging domba"));
        cards.add(Utility.constructor("daging kuda"));
        cards.add(Utility.constructor("Biji Labu"));
        cards.add(Utility.constructor("sirip hiu"));
        cards.add(Utility.constructor("stroberi"));
        cards.add(Utility.constructor("susu"));
        cards.add(Utility.constructor("telur"));
        cards.add(Utility.constructor("Ayam"));
        cards.add(Utility.constructor("Sapi"));
        cards.add(Utility.constructor("Hiu Darat"));
        cards.add(Utility.constructor("kuda"));
        cards.add(Utility.constructor("Domba"));
        cards.add(Utility.constructor("Biji Jagung"));
        cards.add(Utility.constructor("Biji Labu"));
        cards.add(Utility.constructor("Biji Stroberi"));
        cards.add(Utility.constructor("jagung"));
        cards.add(Utility.constructor("daging domba"));
        cards.add(Utility.constructor("daging kuda"));
        cards.add(Utility.constructor("Domba"));
        cards.add(Utility.constructor("Accelerate"));
        cards.add(Utility.constructor("Bear Trap"));
        cards.add(Utility.constructor("Delay"));
        cards.add(Utility.constructor("Destroy"));
        cards.add(Utility.constructor("Instant Harvest"));
        cards.add(Utility.constructor("Accelerate"));
        return cards;
    }
}
