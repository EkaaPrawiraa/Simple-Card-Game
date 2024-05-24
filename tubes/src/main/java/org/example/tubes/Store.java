package org.example.tubes;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class Store {
    private List<Pair<Kartu, Integer>> Barang;

    public Store(){
        Barang = new ArrayList<Pair<Kartu, Integer>>();
    }
    public Store(List<Pair<Kartu, Integer>> Barang){
        this.Barang = Barang;
    }
    public List<Pair<Kartu, Integer>> getBarang(){
        return Barang;
    }

//    public void deleteBarang(Kartu k){
//        this.Barang.remove(k);
//    }
//    public void addBarang(Kartu k){
//        this.Barang.add(k);
//    }
}
