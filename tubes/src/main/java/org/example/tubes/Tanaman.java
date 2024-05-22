package org.example.tubes;

import java.util.List;

public class Tanaman extends Mahluk{
    private int age;
    public Tanaman(int age, List<Item> effect, int harvest_min_value, Produk harvest_output, String nama, String asset, String prop) {
        super(effect, harvest_min_value, harvest_output, nama, asset, prop);
        this.age = age;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
}
