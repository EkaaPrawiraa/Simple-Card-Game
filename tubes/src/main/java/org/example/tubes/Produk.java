package org.example.tubes;

public class Produk extends Kartu{
    private int harga;
    private int weight_added;

    public Produk(int harga, String nama, String assets, String properties,int weight_added) {
        super(nama,assets,properties);
        this.harga = harga;
        this.weight_added = weight_added;
    }
    public int getHarga() {
        return harga;
    }
    public void setHarga(int harga) {
        this.harga = harga;
    }
    public int getWeight_added() {
        return weight_added;
    }
    public void setWeight_added(int weight_added) {
        this.weight_added = weight_added;
    }

}
