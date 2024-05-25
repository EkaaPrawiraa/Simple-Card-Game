package org.example.tubes;

import java.util.HashMap;
import java.util.Map;

public class Store {
    private Map<Kartu, Integer> Barang;

    public Store() {
        Barang = new HashMap<>();
    }

    public Store(Map<Kartu, Integer> Barang) {
        this.Barang = Barang;
    }

    public Map<Kartu, Integer> getBarang() {
        return Barang;
    }

    public void addItem(Kartu kartu, int quantity) {
        Barang.put(kartu, quantity);
    }

    public void updateQuantity(Kartu kartu, int newQuantity) {
        Barang.put(kartu, newQuantity);
    }

    // You can add methods to remove items based on Kartu or other criteria
}
