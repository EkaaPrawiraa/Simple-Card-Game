package org.example.tubes;

import java.util.ArrayList;
import java.util.List;

public class Store {
    private List<Kartu> Barang;

    public Store(){
        Barang = new ArrayList<Kartu>();
    }
    public List<Kartu> getBarang(){
        return Barang;
    }
    public void deleteBarang(Kartu k){
        this.Barang.remove(k);
    }
    public void addBarang(Kartu k){
        this.Barang.add(k);
    }
}
