package org.example.tubes;

import javafx.util.Pair;

import java.io.*;
import java.net.URI;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class IO {
    public static void save(String folderpath, GameState gamestate) {
        try {
            // Save GameState data to gamestate.txt
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(Paths.get(folderpath, "gamestate.txt").toString()))) {
                writer.write(String.valueOf(gamestate.getJumlahTurn()));
                writer.newLine();
                List<Pair<Kartu, Integer>> tokoItems = gamestate.getToko().getBarang();
                writer.write(String.valueOf(tokoItems.size()));
                writer.newLine();
                for (Pair<Kartu, Integer> item : tokoItems) {
                    writer.write(item.getKey().getName() + " " + item.getValue());
                    writer.newLine();
                }
            }

            // Save Player 1 data to player1.txt
            savePlayer(folderpath, gamestate.getPlayer1(), "player1.txt");

            // Save Player 2 data to player2.txt
            savePlayer(folderpath, gamestate.getPlayer2(), "player2.txt");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void savePlayer(String folderpath, Player player, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Paths.get(folderpath, filename).toString()))) {
            writer.write(String.valueOf(player.getGulden()));
            writer.newLine();
            // Save active deck
            List<Kartu> activeDeck = player.getActiveDeck();
            writer.write(String.valueOf(activeDeck.size()));
            writer.newLine();
            //tulis sisa deck
            writer.write(String.valueOf(player.getKartuList().size()));
            writer.newLine();
            int i = 0;
            for (Kartu kartu : activeDeck) {
                if (!Objects.equals(kartu, null)){
                    String characterString = Character.toString((char) (i + 'A'));
                    characterString+="01";
                    writer.write(characterString+" "+ kartu.getName());
                    writer.newLine();
                }
                i++;
            }
            // Save Ladang (assuming Ladang has a method to retrieve its items in a serializable format)
            List<Mahluk> ladangItems = player.getLadang().getMahluk();
            int count = 0;
            for (Mahluk m : ladangItems) {
                if (!Objects.equals(m, null)) {
                    count++;
                }
            }
            writer.write(String.valueOf(count));
            writer.newLine();
            int index = 0;
            for (Mahluk item : ladangItems) {
                if (!Objects.equals(item, null)) {
                    writer.write(idxtostring(index)+ " "+ item.getName());
                    writer.newLine();
                }
                index++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static String idxtostring(int idx){
        int row = idx/5;
        int col = idx%5;
        return (char) (row + 'A') + String.format("%02d", col);
    }
    public static GameState load(String folderpath) throws IOException {
        GameState gamestate = new GameState();
        try (Stream<Path> paths = Files.walk(Paths.get(folderpath))) {
            List<Path> files = paths
                    .filter(Files::isRegularFile)
                    .filter(path -> path.toString().toLowerCase().endsWith(".txt"))
                    .toList();
            if (files.size()!=3){
                throw new Exception();
            }

            for (Path path: files){
                String fileName = path.getFileName().toString();
                if (fileName.equalsIgnoreCase("gamestate.txt")){
                    try(BufferedReader bufferedReader = new BufferedReader(new FileReader(path.toFile()))) {
                        String line;
                        line = bufferedReader.readLine();
                        gamestate.jumlahTurn = Integer.parseInt(line);
                        line = bufferedReader.readLine();
                        int amount = Integer.parseInt(line);
                        List<Pair<Kartu, Integer>> toko= new ArrayList<>();
                        for (int i = 0; i<amount;i++){
                            line = bufferedReader.readLine();
                            String[] storepair = line.split(" ");
                            toko.add(new Pair<>(Utility.constructor(storepair[0]), Integer.parseInt(storepair[1])));
                        }
                        gamestate.setToko(new Store(toko));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }else if (fileName.equalsIgnoreCase("player1.txt")){
                    try(BufferedReader bufferedReader = new BufferedReader(new FileReader(path.toFile()))){
                        String line;
                        line = bufferedReader.readLine();
                        gamestate.player1.setGulden(Integer.parseInt(line));
                        line = bufferedReader.readLine();
                        //apain gitu
                        int len = Integer.parseInt(line);
                        System.out.println(len);
                        List<Kartu> listka = new ArrayList<>(6);
                        for (int i = 0; i<6;i++){
                            listka.add(null);
                        }
                        line = bufferedReader.readLine();
                        gamestate.player1.setKartuList(Main.generateAllCard());
                        while (gamestate.player1.getKartuList().size()>Integer.parseInt(line)) {
                            gamestate.player1.getKartuList().removeLast();
                        }
                        for (int i = 0; i< len;i++){
                            line = bufferedReader.readLine();
                            String[] parts = line.split(" ", 2);
                            char a = parts[0].charAt(0);
                            listka.set(a - 'A', Utility.constructor(parts[1]));
                        }
                        gamestate.player1.setActiveDeck(listka);
                        line = bufferedReader.readLine();
                        len = Integer.parseInt(line);

                        for (int i = 0; i <len; i++ ){
                            line = bufferedReader.readLine();
                            String[] parts = line.split(" ", 2);
                            char a = parts[0].charAt(0);
                            String b = parts[0].substring(1);
                            int index = ((int)(a-'A')*5) + Integer.parseInt(b);
                            gamestate.player1.getLadang().addMahluk(Utility.constructor(parts[1]), index);
                        }

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }else if (fileName.equalsIgnoreCase("player2.txt")){
                    try(BufferedReader bufferedReader = new BufferedReader(new FileReader(path.toFile()))){
                        String line;
                        line = bufferedReader.readLine();
                        gamestate.player2.setGulden(Integer.parseInt(line));
                        line = bufferedReader.readLine();
                        //apain gitu
                        int len = Integer.parseInt(line);
                        List<Kartu> listka = new ArrayList<>(len);
                        line = bufferedReader.readLine();
                        gamestate.player1.setKartuList(Main.generateAllCard());
                        while (gamestate.player1.getKartuList().size()>Integer.parseInt(line)) {
                            gamestate.player1.getKartuList().removeLast();
                        }
                        for (int i = 0; i< len;i++){
                            line = bufferedReader.readLine();
                            String[] parts = line.split(" ", 2);
                            char a = parts[0].charAt(0);
                            listka.set(a - 'A', Utility.constructor(parts[1]));
                        }
                        gamestate.player2.setActiveDeck(listka);
                        line = bufferedReader.readLine();
                        len = Integer.parseInt(line);

                        for (int i = 0; i <len; i++ ){
                            line = bufferedReader.readLine();
                            String[] parts = line.split(" ", 2);
                            char a = parts[0].charAt(0);
                            String b = parts[0].substring(1);
                            int index = ((int)a-'A'*5) + Integer.parseInt(b);
                            gamestate.player2.getLadang().addMahluk(Utility.constructor(parts[1]), index);
                        }

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return gamestate;
    }
//        try {
//            // Walk the directory tree and get a stream of all paths
////            Stream<Path> pathStream = Files.walk(Paths.get(folderpath), FileVisitOption.FOLLOW_LINKS);
//
//            // Filter the stream to include only regular files with .txt extension
//            pathStream.filter(Files::isRegularFile)
//                    .filter(path -> path.toString().toLowerCase().endsWith(".txt"))
//                    .forEach(path -> {
//                        // Filter by filename
//                        String fileName = path.getFileName().toString();
//                        if (fileName.equalsIgnoreCase("gamestate.txt") ||
//                                fileName.equalsIgnoreCase("player1.txt") ||
//                                fileName.equalsIgnoreCase("player2.txt")) {
//                            // Process each file path as needed
//                            System.out.println("File: " + path);
//                            // Read the contents of the file here
//                        }
//                    });
//        } catch (IOException e) {
//            System.err.println("Error accessing folder: " + folderpath);
//        }
}

