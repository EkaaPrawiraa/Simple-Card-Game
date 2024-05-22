package org.example.tubes;

public class Item extends  Kartu {
    private String effect;

    public Item(String effect, String name, String asset,String prop){
        super(name, asset, prop);
        this.effect = effect;
    }
    public String getEffect() {
        return effect;
    }
    public void setEffect(String effect) {
        this.effect = effect;
    }

}
