package org.example.tubes;

import java.util.ArrayList;
import java.util.List;

public class GameState {
    public Player player1;
    public Player player2;
    public Store Toko;
    public int jumlahTurn;
    public GameState(){
        this.player1 = new Player();
        this.player2 = new Player();
        this.Toko = new Store();
        this.jumlahTurn = 0;
    }
    public GameState(Player player1, Player player2, int jumlahTurn, Store toko) {
        this.player1 = player1;
        this.player2 = player2;
        this.jumlahTurn = jumlahTurn;
        this.Toko = toko;
    }
    public Player getPlayer1() {
        return player1;
    }
    public Player getPlayer2() {
        return player2;
    }
    public int getJumlahTurn() {
        return jumlahTurn;
    }
    public Store getToko() {
        return Toko;

    }

    public void setJumlahTurn() {
        this.jumlahTurn++;
    }
    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }
    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }
    public void setToko(Store toko) {
        this.Toko = toko;
    }

}
