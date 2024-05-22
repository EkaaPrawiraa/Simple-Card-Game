package org.example.tubes;

import java.util.ArrayList;
import java.util.List;

public class Ladang {
    private List<Mahluk> mahluk;

    public Ladang() {
        mahluk = new ArrayList<Mahluk>();
    }
    public Ladang(List<Mahluk> mahluk) {
        this.mahluk = mahluk;
    }
    public List<Mahluk> getMahluk() {
        return mahluk;
    }
    public void setMahluk(List<Mahluk> mahluk) {
        this.mahluk = mahluk;
    }
    public void addMahluk(Mahluk mahluk,int location) {
        this.mahluk.add(location, mahluk);
    }
    public void dellMahluk(int location){
        this.mahluk.remove(location);
    }
    public Mahluk getMahluk(int location){
        return this.mahluk.get(location);
    }
}
