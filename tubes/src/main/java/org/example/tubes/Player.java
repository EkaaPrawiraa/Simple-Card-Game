package org.example.tubes;

import java.util.*;


public class Player {
    private int gulden;
    private Ladang ladang;
    private List<Kartu> kartuList;

    private Player(){
        gulden = 0;
        ladang = new Ladang();

    }

    public Player(int gulden, Ladang ladang) {
        this.gulden = gulden;
        this.ladang = ladang;
    }
    public int getGulden() {
        return gulden;
    }
    public void setGulden(int gulden) {
        this.gulden = gulden;
    }

}
