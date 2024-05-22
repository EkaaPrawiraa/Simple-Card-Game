package org.example.tubes;

import java.util.List;
import java.util.Objects;

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
    public void acceptItem(Item item){
        if (Objects.equals(item.getName(), "accelerate")){
            accelerate++;
            setAge(getAge()+2);
        }else if (Objects.equals(item.getName(), "delay")){
            delay++;
            if (getAge()<2){
                setAge(0);
            }else{
                setAge(getAge()-2);
            }
        }else if (Objects.equals(item.getName(), "instant_harvest")){
            //not implemented yet
            System.out.println("belum");
        }else if (Objects.equals(item.getName(), "protect")){
            protect++;
        }else if (Objects.equals(item.getName(), "trap")){
            trap++;
        }else if (Objects.equals(item.getName(), "destroy")){
            System.out.println("belum");
            this.setName("");
        }

    }
}
