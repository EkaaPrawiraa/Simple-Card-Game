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
        System.out.println(jenis);
        if (jenis.equals("karnivora") && !p.getName().equals("labu") && !p.getName().equals("jagung") && !p.getName().equals("stroberi")){
            System.out.println(jenis);
            System.out.println(p.getName());
            this.weight += p.getWeight_added();
        }else if (jenis.equals("karnivora") && (p.getName().equals("labu") || p.getName().equals("jagung") || p.getName().equals("stroberi"))){
            System.out.println(jenis);
            System.out.println(p.getName());
            System.out.println("Salah makanan");
        }

        if (jenis.equals("herbivora") && (p.getName().equals("labu") || p.getName().equals("jagung") || p.getName().equals("stroberi"))){
            System.out.println(jenis);
            System.out.println(p.getName());
            this.weight += p.getWeight_added();
        }else if (jenis.equals("herbivora") && !p.getName().equals("labu") && !p.getName().equals("jagung") && !p.getName().equals("stroberi")){
            System.out.println(jenis);
            System.out.println(p.getName());
            System.out.println("Salah makanan");
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
            effects.add(item);
        }else if (Objects.equals(item.getName(), "trap")){
            trap++;
            effects.add(item);
        }else if (Objects.equals(item.getName(), "destroy")){
            System.out.println("belum");
        }

    }

}
