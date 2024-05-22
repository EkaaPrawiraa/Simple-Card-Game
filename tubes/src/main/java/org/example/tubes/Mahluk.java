package org.example.tubes;

import org.controlsfx.control.PropertySheet;

import java.util.ArrayList;
import java.util.List;

public class Mahluk extends Kartu{
    private List<Item> effects;
    private int harvest_value;
    private Produk harvet_product;
    protected int accelerate = 0;
    protected int delay = 0;
    protected int protect = 0;
    protected int trap = 0;

    public Mahluk(List<Item> effect,int harvest_min_value,Produk harvest_output,String nama,String asset,String prop){
        super(nama,asset,prop);
        this.effects = new ArrayList<Item>(effect);
        this.harvest_value = harvest_min_value;
        this.harvet_product = harvest_output;
    }
    public Mahluk(){
        super();
        this.effects = null;
        this.harvest_value = 0;
        this.harvet_product = null;
    }

    public List<Item> getEffects() {
        return effects;
    }
    public void setEffects(List<Item> effects) {
        this.effects = effects;
    }
    public void deleteEffects(Item i){
        effects.remove(i);
    }
    public void addEffect(Item i){
        effects.add(i);
    }
    public int getHarvest_value() {
        return harvest_value;
    }
    public void setHarvest_value(int harvest_value) {
        this.harvest_value = harvest_value;
    }
    public void setHarvest_product(Produk p){
        this.harvet_product = p;
    }
    public Produk getHarvest_product(){
        return harvet_product;
    }
}

