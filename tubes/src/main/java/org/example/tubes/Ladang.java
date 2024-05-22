package org.example.tubes;

import java.util.ArrayList;
import java.util.List;

public class Ladang {
    private List<Mahluk> mahluk;

    public Ladang() {
        mahluk = new ArrayList<Mahluk>(20);
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
        this.mahluk.set(location, mahluk); }
    public void dellMahluk(int location){
        this.mahluk.get(location).setName("");
    }
    public Mahluk getMahluk(int location){
        return this.mahluk.get(location);
    }
    public Produk Harvest(int location){
        if (mahluk.get(location) instanceof  Hewan ){
            Hewan hewan = (Hewan) mahluk;
            if(hewan.getWeight()==((Hewan) mahluk).getHarvest_value())
                return ((Hewan) mahluk).getHarvest_product();
            else{
                return null;
            }
        }
        else if (mahluk.get(location) instanceof  Tanaman ){
            Tanaman tanaman = (Tanaman) mahluk;
            if(tanaman.getAge()==((Tanaman) mahluk).getHarvest_value()){
                return ((Tanaman) mahluk).getHarvest_product();
            }
            else{
                return null;
            }
        }
        return null;
    }
}
