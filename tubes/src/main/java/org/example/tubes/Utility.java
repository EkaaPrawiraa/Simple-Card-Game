package org.example.tubes;

import java.util.ArrayList;
import java.util.List;

public class Utility {
    public static Kartu constructor(String namakartu){
        if (namakartu.equalsIgnoreCase("Hiu Darat")){
            return new Hewan(0, "karnivora", new ArrayList<>(), 20, (Produk) constructor("Sirip Hiu"), "Hiu Darat", "", "/Hewan/hiu darat.png");
        }else if (namakartu.equalsIgnoreCase("Sapi")){
            return new Hewan(0, "herbivora", new ArrayList<>(), 10, (Produk)constructor("susu"), "Sapi", "", "/Hewan/cow.png");
        }else if (namakartu.equalsIgnoreCase("Domba")){
            return new Hewan(0, "herbivora", new ArrayList<>(), 12, (Produk)constructor("daging domba"), "Domba", "", "/Hewan/sheep.png");
        }else if (namakartu.equalsIgnoreCase("kuda")){
            return new Hewan(0, "herbivora", new ArrayList<>(), 14, (Produk)constructor("daging kuda"), "kuda", "", "/Hewan/horse.png");
        }else if (namakartu.equalsIgnoreCase("Ayam")){
            return new Hewan(0, "omnivora", new ArrayList<>(), 5, (Produk)constructor("telur"), "Ayam", "", "/Hewan/chicken.png");
        }else if (namakartu.equalsIgnoreCase("beruang")){
            return new Hewan(0, "omnivora", new ArrayList<>(), 25, (Produk)constructor("daging beruang"),"Beruang", "", "/Hewan/bear.png" );
        }else if (namakartu.equalsIgnoreCase("Biji Jagung")){
            return new Tanaman(0, new ArrayList<>(), 3, (Produk)constructor("jagung"), "Biji Jagung", "", "/Tanaman/corn seeds.png");
        }else if (namakartu.equalsIgnoreCase("Biji Stroberi")){
            return new Tanaman(0, new ArrayList<>(), 4, (Produk)constructor("Stroberi"), "Biji Stroberi", "", "/Tanaman/strawberry seeds.png");
        }else if (namakartu.equalsIgnoreCase("Biji Labu")){
            return new Tanaman(0, new ArrayList<>(), 5, (Produk)constructor("Labu"), "Biji Labu", "", "/Tanaman/pumpkin seeds.png");
        }else if (namakartu.equalsIgnoreCase("sirip hiu")){
            return new Produk(500, "sirip hiu", "", "/Produk/shark-fin.png", 12);
        }else if (namakartu.equalsIgnoreCase("susu")){
            return new Produk(100, "susu", "", "/Produk/susu.png", 4);
        }else if (namakartu.equalsIgnoreCase("daging domba")){
            return new Produk(120, "daging domba", "", "/Produk/Daging Domba.png", 6);
        }else if (namakartu.equalsIgnoreCase("daging kuda")){
            return new Produk(150, "daging kuda", "", "/Produk/Daging Kuda.png", 8);
        }else if (namakartu.equalsIgnoreCase("telur")){
            return new Produk(50, "telur", "", "/Produk/telur.png", 2);
        }else if (namakartu.equalsIgnoreCase("daging beruang")){
            return new Produk(500, "daging beruang", "", "/Produk/Daging Beruang.png", 12);
        }else if (namakartu.equalsIgnoreCase("jagung")){
            return new Produk(150, "jagung", "", "/Produk/corn.png", 3);
        }else if (namakartu.equalsIgnoreCase("labu")){
            return new Produk(500, "labu", "", "/Produk/pumpkin.png", 10);
        }else if (namakartu.equalsIgnoreCase("stroberi")){
            return new Produk(350, "stroberi", "", "/Produk/strawberry.png", 5);
        }else if (namakartu.equalsIgnoreCase("Accelerate")){
            return new Item("Accelerate", "Accelerate", "", "/Item/Accelerate.png");
        }else if (namakartu.equalsIgnoreCase("Delay")){
            return new Item("Delay", "Delay", "", "/Item/Delay.png");
        }else if (namakartu.equalsIgnoreCase("Instant Harvest")){
            return new Item("Instant Harvest", "Instant Harvest", "", "/Item/Instant Harvest.png");
        }else if (namakartu.equalsIgnoreCase("Destroy")){
            return new Item("Destroy", "Destroy", "", "/Item/Destroy.png");
        }else if (namakartu.equalsIgnoreCase("Protect")){
            return new Item("Protect", "Protect", "", "/Item/Protect.png");
        } else if (namakartu.equalsIgnoreCase("Bear Trap")) {
            return new Item("Bear Trap", "Bear Trap", "", "/Item/bear trap.png");
        }else{
            System.out.println(namakartu);
            return null;
        }
    }
}
