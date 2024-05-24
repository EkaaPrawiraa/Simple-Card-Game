package org.example.tubes;

import java.util.*;
public class Player {
    private int gulden;
    private Ladang ladang;
    private List<Kartu> kartuList;
    private List<Kartu> activeDeck;
    public Player(){
        gulden = 0;
        ladang = new Ladang();
        kartuList = new ArrayList<>();
        activeDeck = new ArrayList<>();
    }

    public Player(int gulden, Ladang ladang, List<Kartu> kartuList, List<Kartu> activeDeck) {
        this.gulden = gulden;
        this.ladang = ladang;
        this.kartuList = kartuList;
        this.activeDeck = activeDeck;
    }
    public int getGulden() {
        return gulden;
    }
    public void setGulden(int gulden) {
        this.gulden = gulden;
    }
    public Ladang getLadang() {
        return ladang;
    }
    public void setLadang(Ladang ladang) {
        this.ladang = ladang;
    }

    public List<Kartu> generateRandomKartu(List<Kartu> cards) {
        Collections.shuffle(cards);
        int size = 4;
        int availableSlots = 6 - activeDeck.size();
        if (availableSlots < size) {
            size = availableSlots;
        }
        if (availableSlots == 0) {
            return Collections.emptyList();
        }
        return cards.subList(0, size);
    }

    public List<Kartu> startTurn(List<Kartu> cards) {
        if (activeDeck.size() == 6) {
            return null;
        }
//        List<Kartu> ShuffleCards = generateRandomKartu(cards);

//        //Algoritma milih kartu dari random kartulist player
//        activeDeck.addAll(newCards);
//        kartuList.removeAll(newCards);

        return generateRandomKartu(cards);
    }

    public List<Kartu> getKartuList() {
        return kartuList;
    }

    public void setKartuList(List<Kartu> kartuList) {
        this.kartuList = kartuList;
    }

    public List<Kartu> getActiveDeck() {
        return activeDeck;
    }

    public void setActiveDeck(List<Kartu> activeDeck) {
        this.activeDeck = activeDeck;
    }
    public void addActiveDeck(Kartu kartu) {
        if (activeDeck.size() < 6) {
            activeDeck.add(kartu);
        }
        else{
            System.out.println("Penuh");
        }
    }
    public void delActiveDeck(Kartu kartu) {
        if(activeDeck.size() == 0){
            System.out.println("BEAK");
        }
        else{
            activeDeck.remove(kartu);
            System.out.println(kartu.getName());
        }
    }
    public void addKartu(Kartu kartu) {
        this.kartuList.add(kartu);
    }
    public void removeKartu(Kartu kartu) {
        this.kartuList.remove(kartu);
    }
    public void panen(int location){
        Produk hasil = ladang.Harvest(location);
        if (hasil != null) {
            kartuList.add(hasil);
        }
    }

}
