package org.example.tubes;


import java.util.List;
import java.util.Objects;

public class Hewan extends Mahluk implements Doping {
    private int weight;
    private String jenis;

    public Hewan(int weight, String jenis, List<Item> effect, int harvest_min_value, Produk harvest_output, String nama, String asset, String prop) {
        super(effect,harvest_min_value,harvest_output,nama,asset,prop);
        this.weight = weight;
        this.jenis = jenis;
    }
    public int getWeight() {
        return weight;
    }
    public String getJenis() {
        return jenis;
    }
    public void setWeight(int weight) {
        this.weight = weight;
    }
    public void setJenis(String jenis) {
        this.jenis = jenis;
    }
    public void feed(Produk p){
        if (jenis.equals("KARNIVORA") && (!p.getName().equals("labu")||!p.getName().equals("jagung")||!p.getName().equals("stroberi") ) ){
            this.weight +=p.getWeight_added();
        }
        else if(jenis.equals("KARNIVORA")){
            System.out.println("Salah makanan");
        }
        else if (jenis.equals("HERBIVORA") && (p.getName().equals("labu")||p.getName().equals("jagung")||p.getName().equals("stroberi") ) ){
            this.weight +=p.getWeight_added();
        }
        else if (jenis.equals("HERBIVORA")){
            System.out.println("Salah makanan");
        }
        else{
            this.weight +=p.getWeight_added();
        }
    }
    public void acceptItem(Item item){
        if (Objects.equals(item.getEffect(), "accelerate")){
            accelerate++;
            setWeight(getWeight()+8);
        }else if (Objects.equals(item.getEffect(), "delay")){
            delay++;
            if (getWeight()<5){
                setWeight(0);
            }else{
                setWeight(getWeight()-5);
            }
        }else if (Objects.equals(item.getName(), "instant_harvest")){
            //not implemented yet
            setWeight(getHarvest_value());
            System.out.println("belum");
        }else if (Objects.equals(item.getName(), "protect")){
            protect++;
        }else if (Objects.equals(item.getName(), "trap")){
            trap++;
        }else if (Objects.equals(item.getName(), "destroy")){
            System.out.println("belum");
        }

    }
}
