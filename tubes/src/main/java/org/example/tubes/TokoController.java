package org.example.tubes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.net.URL;
import java.util.*;

public class TokoController implements Initializable {

    @FXML
    private Label sirip_hiu;

    @FXML
    private Label susu;

    @FXML
    private Label daging_domba;

    @FXML
    private Label daging_kuda;

    @FXML
    private Label pumpkin;

    @FXML
    private Label jagung;

    @FXML
    private Label telur;

    @FXML
    private Label stroberi;

    @FXML
    private Label daging_beruang;

    @FXML
    private Label total_hiu;

    @FXML
    private Label total_susu;

    @FXML
    private Label total_domba;

    @FXML
    private Label total_kuda;

    @FXML
    private Label total_labu;

    @FXML
    private Label total_jagung;

    @FXML
    private Label total_telur;

    @FXML
    private Label total_stroberi;

    @FXML
    private Label total_beruang;



    private GameState gameState;

    private HashMap<String, Integer> barang_beli = new HashMap<>();

    public void setGameState(GameState gameState){
        this.gameState = gameState;
        System.out.println(this.gameState.getToko());
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public int getCount(String name){
        Map<Kartu, Integer> store = this.gameState.getToko().getBarang();
        for(Map.Entry<Kartu, Integer> entry : store.entrySet()){
            if (entry.getKey().getName().equals(name)){
                return entry.getValue();
            }
        }
        return 0;
    }

    public int getHarga(String name){
        Map<Kartu, Integer> store = this.gameState.getToko().getBarang();
        for(Map.Entry<Kartu, Integer> entry : store.entrySet()){
            if (entry.getKey().getName().equals(name)){
                Produk produk = (Produk) entry.getKey();
                return produk.getHarga();
            }
        }
        return 0;
    }

    public void setToko(Store toko){
        Map<Kartu, Integer> store = toko.getBarang();
        System.out.println(store.size());
        System.out.println(store);
        if(!store.isEmpty()){
            for(Map.Entry<Kartu, Integer> entry : store.entrySet()){
                if (entry.getKey().getName().equals("sirip hiu")){
                    sirip_hiu.setText(entry.getValue().toString());
                }
                if (entry.getKey().getName().equals("susu")){
                    susu.setText(entry.getValue().toString());
                }
                if (entry.getKey().getName().equals("daging domba")){
                    daging_domba.setText(entry.getValue().toString());
                }
                if (entry.getKey().getName().equals("daging kuda")){
                    daging_kuda.setText(entry.getValue().toString());
                }
                if (entry.getKey().getName().equals("labu")){
                    pumpkin.setText(entry.getValue().toString());
                }
                if (entry.getKey().getName().equals("jagung")){
                    jagung.setText(entry.getValue().toString());
                }
                if (entry.getKey().getName().equals("telur")){
                    telur.setText(entry.getValue().toString());
                }
                if (entry.getKey().getName().equals("stroberi")){
                    stroberi.setText(entry.getValue().toString());
                }
                if (entry.getKey().getName().equals("daging beruang")){
                    daging_beruang.setText(entry.getValue().toString());
                }
            }
        }
    }

    @FXML
    private void switchMain(ActionEvent event) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
        Parent root = loader.load();
        MainController mainController = loader.getController();
        mainController.setPlayerAndCards(this.gameState, false);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("main.css")).toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void incrementItem(ActionEvent event){
        Node node = (Node) event.getTarget();

        while (node != null && !(node.getId() != null)) {
            node = node.getParent();
        }
        if (node.getId().equals("sirip_hiu_beli")){
            if (getCount("sirip hiu") > Integer.parseInt(total_hiu.getText())){
                if (barang_beli.containsKey("sirip hiu")) {
                    int currentQuantity = barang_beli.get("sirip hiu");
                    int newQuantity = currentQuantity + 1; // Or modify the quantity as needed
                    barang_beli.put("sirip hiu", newQuantity);
                } else {
                    // Key doesn't exist, add a new entry
                    barang_beli.put("sirip hiu", 1);
                }
                total_hiu.setText(barang_beli.get("sirip hiu").toString());
            }

        }
        if (node.getId().equals("susu_beli")){
            if (getCount("susu") > Integer.parseInt(total_susu.getText())){
                if (barang_beli.containsKey("susu")) {
                    int currentQuantity = barang_beli.get("susu");
                    int newQuantity = currentQuantity + 1; // Or modify the quantity as needed
                    barang_beli.put("susu", newQuantity);
                } else {
                    // Key doesn't exist, add a new entry
                    barang_beli.put("susu", 1);
                }
                total_susu.setText(barang_beli.get("susu").toString());
            }
        }
        if (node.getId().equals("domba_beli")){
            if (getCount("daging domba") > Integer.parseInt(total_domba.getText())){
                if (barang_beli.containsKey("daging domba")) {
                    int currentQuantity = barang_beli.get("daging domba");
                    int newQuantity = currentQuantity + 1; // Or modify the quantity as needed
                    barang_beli.put("daging domba", newQuantity);
                } else {
                    // Key doesn't exist, add a new entry
                    barang_beli.put("daging domba", 1);
                }
                total_domba.setText(barang_beli.get("daging domba").toString());
            }
        }
        if (node.getId().equals("kuda_beli")){
            if (getCount("daging kuda") > Integer.parseInt(total_kuda.getText())){
                if (barang_beli.containsKey("daging kuda")) {
                    int currentQuantity = barang_beli.get("daging kuda");
                    int newQuantity = currentQuantity + 1; // Or modify the quantity as needed
                    barang_beli.put("daging kuda", newQuantity);
                } else {
                    // Key doesn't exist, add a new entry
                    barang_beli.put("daging kuda", 1);
                }
                total_kuda.setText(barang_beli.get("daging kuda").toString());
            }
        }
        if (node.getId().equals("labu_beli")){
            if (getCount("labu") > Integer.parseInt(total_labu.getText())){
                if (barang_beli.containsKey("labu")) {
                    int currentQuantity = barang_beli.get("labu");
                    int newQuantity = currentQuantity + 1; // Or modify the quantity as needed
                    barang_beli.put("labu", newQuantity);
                } else {
                    // Key doesn't exist, add a new entry
                    barang_beli.put("labu", 1);
                }
                total_labu.setText(barang_beli.get("labu").toString());
            }
        }
        if (node.getId().equals("jagung_beli")){
            if (getCount("jagung") > Integer.parseInt(total_jagung.getText())){
                if (barang_beli.containsKey("jagung")) {
                    int currentQuantity = barang_beli.get("jagung");
                    int newQuantity = currentQuantity + 1; // Or modify the quantity as needed
                    barang_beli.put("jagung", newQuantity);
                } else {
                    // Key doesn't exist, add a new entry
                    barang_beli.put("jagung", 1);
                }
                total_jagung.setText(barang_beli.get("jagung").toString());
            }
        }
        if (node.getId().equals("telur_beli")){
            if (getCount("telur") > Integer.parseInt(total_telur.getText())){
                if (barang_beli.containsKey("telur")) {
                    int currentQuantity = barang_beli.get("telur");
                    int newQuantity = currentQuantity + 1; // Or modify the quantity as needed
                    barang_beli.put("telur", newQuantity);
                } else {
                    // Key doesn't exist, add a new entry
                    barang_beli.put("telur", 1);
                }
                total_telur.setText(barang_beli.get("telur").toString());
            }
        }
        if (node.getId().equals("stroberi_beli")){
            if (getCount("strober") > Integer.parseInt(total_stroberi.getText())){
                if (barang_beli.containsKey("stroberi")) {
                    int currentQuantity = barang_beli.get("stroberi");
                    int newQuantity = currentQuantity + 1; // Or modify the quantity as needed
                    barang_beli.put("stroberi", newQuantity);
                } else {
                    // Key doesn't exist, add a new entry
                    barang_beli.put("stroberi", 1);
                }
                total_stroberi.setText(barang_beli.get("stroberi").toString());
            }
        }
        if (node.getId().equals("beruang_beli")){
            if (getCount("daging beruang") > Integer.parseInt(total_beruang.getText())){
                if (barang_beli.containsKey("daging beruang")) {
                    int currentQuantity = barang_beli.get("daging beruang");
                    int newQuantity = currentQuantity + 1; // Or modify the quantity as needed
                    barang_beli.put("daging beruang", newQuantity);
                } else {
                    // Key doesn't exist, add a new entry
                    barang_beli.put("daging beruang", 1);
                }
                total_beruang.setText(barang_beli.get("daging beruang").toString());
            }
        }
        System.out.println(this.barang_beli);

    }

    @FXML
    private void decrementItem(ActionEvent event){
        Node node = (Node) event.getTarget();

        while (node != null && !(node.getId() != null)) {
            node = node.getParent();
        }
        if (node.getId().equals("sirip_hiu_beli")){
            if ( 0 < Integer.parseInt(total_hiu.getText())){
                if (barang_beli.containsKey("sirip hiu") && barang_beli.get("sirip hiu") > 0) {
                    int currentQuantity = barang_beli.get("sirip hiu");
                    int newQuantity = currentQuantity - 1 ; // Or modify the quantity as needed
                    barang_beli.put("sirip hiu", newQuantity);
                }
                total_hiu.setText(barang_beli.get("sirip hiu").toString());
            }

        }
        if (node.getId().equals("susu_beli")){
            if (0 < Integer.parseInt(total_susu.getText())){
                if (barang_beli.containsKey("susu") && barang_beli.get("susu") > 0) {
                    int currentQuantity = barang_beli.get("susu");
                    int newQuantity = currentQuantity - 1; // Or modify the quantity as needed
                    barang_beli.put("susu", newQuantity);
                }
                total_susu.setText(barang_beli.get("susu").toString());
            }
        }
        if (node.getId().equals("domba_beli")){
            if (0 < Integer.parseInt(total_domba.getText())){
                if (barang_beli.containsKey("daging domba") && barang_beli.get("daging domba") > 0) {
                    int currentQuantity = barang_beli.get("daging domba");
                    int newQuantity = currentQuantity - 1; // Or modify the quantity as needed
                    barang_beli.put("daging domba", newQuantity);
                }
                total_domba.setText(barang_beli.get("daging domba").toString());
            }
        }
        if (node.getId().equals("kuda_beli")){
            if (0 < Integer.parseInt(total_kuda.getText())){
                if (barang_beli.containsKey("daging kuda") && barang_beli.get("daging kuda") > 0) {
                    int currentQuantity = barang_beli.get("daging kuda");
                    int newQuantity = currentQuantity - 1; // Or modify the quantity as needed
                    barang_beli.put("daging kuda", newQuantity);
                }
                total_kuda.setText(barang_beli.get("daging kuda").toString());
            }
        }
        if (node.getId().equals("labu_beli")){
            if (0 < Integer.parseInt(total_labu.getText())){
                if (barang_beli.containsKey("labu") && barang_beli.get("labu") > 0) {
                    int currentQuantity = barang_beli.get("labu");
                    int newQuantity = currentQuantity - 1; // Or modify the quantity as needed
                    barang_beli.put("labu", newQuantity);
                }
                total_labu.setText(barang_beli.get("labu").toString());
            }
        }
        if (node.getId().equals("jagung_beli")){
            if (0 < Integer.parseInt(total_jagung.getText())){
                if (barang_beli.containsKey("jagung") && barang_beli.get("jagung") > 0) {
                    int currentQuantity = barang_beli.get("jagung");
                    int newQuantity = currentQuantity - 1; // Or modify the quantity as needed
                    barang_beli.put("jagung", newQuantity);
                }
                total_jagung.setText(barang_beli.get("jagung").toString());
            }
        }
        if (node.getId().equals("telur_beli")){
            if (0 < Integer.parseInt(total_telur.getText())){
                if (barang_beli.containsKey("telur") && barang_beli.get("telur") > 0) {
                    int currentQuantity = barang_beli.get("telur");
                    int newQuantity = currentQuantity - 1; // Or modify the quantity as needed
                    barang_beli.put("telur", newQuantity);
                }
                total_telur.setText(barang_beli.get("telur").toString());
            }
        }
        if (node.getId().equals("stroberi_beli")){
            if (0 < Integer.parseInt(total_stroberi.getText())){
                if (barang_beli.containsKey("stroberi") && barang_beli.get("stroberi") > 0) {
                    int currentQuantity = barang_beli.get("stroberi");
                    int newQuantity = currentQuantity - 1; // Or modify the quantity as needed
                    barang_beli.put("stroberi", newQuantity);
                }
                total_stroberi.setText(barang_beli.get("stroberi").toString());
            }
        }
        if (node.getId().equals("beruang_beli")){
            if (0 < Integer.parseInt(total_beruang.getText())){
                if (barang_beli.containsKey("daging beruang") && barang_beli.get("daging_beruang") > 0) {
                    int currentQuantity = barang_beli.get("daging beruang");
                    int newQuantity = currentQuantity + 1; // Or modify the quantity as needed
                    barang_beli.put("daging beruang", newQuantity);
                } else {
                    // Key doesn't exist, add a new entry
                    barang_beli.put("daging beruang", 1);
                }
                total_beruang.setText(barang_beli.get("daging beruang").toString());
            }
        }
        System.out.println(this.barang_beli);
    }

    @FXML
    private void handleBeli(ActionEvent event) throws Exception{
        System.out.println("test");
        int total_harga = 0;
        for (Map.Entry<String, Integer> entry : this.barang_beli.entrySet()) {
            total_harga += getHarga(entry.getKey()) * entry.getValue();
        }

        if (this.gameState.getJumlahTurn() % 2 == 1){
            if(total_harga <= this.gameState.getPlayer1().getGulden()){
                System.out.println(total_harga);
                this.gameState.getPlayer1().setGulden(this.gameState.getPlayer1().getGulden() - total_harga);
                updateStorePLayer(this.gameState.getPlayer1());
                System.out.println(this.gameState.getToko().getBarang());
            }else{

            }
        }else{
            if(total_harga <= this.gameState.getPlayer2().getGulden()){
                System.out.println(total_harga);
                this.gameState.getPlayer2().setGulden(this.gameState.getPlayer2().getGulden() - total_harga);
                updateStorePLayer(this.gameState.getPlayer2());
                System.out.println(this.gameState.getToko().getBarang());
            }
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
        Parent root = loader.load();
        MainController mainController = loader.getController();
        mainController.setPlayerAndCards(this.gameState, false);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene  = new Scene(root);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("main.css")).toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    private void updateStorePLayer(Player player){
        for(Map.Entry<Kartu, Integer> entry : this.gameState.getToko().getBarang().entrySet()){
            if (this.barang_beli.containsKey(entry.getKey().getName())){
                if (this.barang_beli != null) {
                    for (int i = 0; i < this.barang_beli.get(entry.getKey().getName()); i++) {
                        player.addKartu(entry.getKey());
                    }
                }
                this.gameState.getToko().updateQuantity(entry.getKey(), entry.getValue() - this.barang_beli.get(entry.getKey().getName()));
            }
        }
        System.out.println(player.getKartuList());
    }
}


