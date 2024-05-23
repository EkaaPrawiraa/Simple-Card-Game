package org.example.tubes;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class IO {
    public static void save(String folderpath, GameState gamestate){

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
                        for (int i = 0; i<amount;i++){
                            line = bufferedReader.readLine();
                            String[] storepair = line.split(" ");

                        }
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
                        line = bufferedReader.readLine();
                        int len = Integer.parseInt(line);
                        List<Kartu> listka = new ArrayList<>(len);
                        for (int i = 0; i< len;i++){
                            line = bufferedReader.readLine();
                            String[] parts = line.split(" ");
                            char a = parts[0].charAt(0);
                            listka.set(a - 'A', Utility.constructor(parts[1]));
                        }
                        gamestate.player1.setActiveDeck(listka);
                        line = bufferedReader.readLine();
                        len = Integer.parseInt(line);

                        for (int i = 0; i <len; i++ ){
                            line = bufferedReader.readLine();
                            String[] parts = line.split(" ");
                            char a = parts[0].charAt(0);
                            String b = parts[1].substring(1);
                            int index = ((int)a-'A'*5) + Integer.parseInt(b);
                            //tunggu implementasi ladang di player
                        }

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }else if (fileName.equalsIgnoreCase("player2.txt")){
                    try(BufferedReader bufferedReader = new BufferedReader(new FileReader(path.toFile()))){
                        String line;
                        line = bufferedReader.readLine();
                        gamestate.player1.setGulden(Integer.parseInt(line));
                        line = bufferedReader.readLine();
                        //apain gitu
                        line = bufferedReader.readLine();
                        int len = Integer.parseInt(line);
                        List<Kartu> listka = new ArrayList<>(len);
                        for (int i = 0; i< len;i++){
                            line = bufferedReader.readLine();
                            String[] parts = line.split(" ");
                            char a = parts[0].charAt(0);
                            listka.set(a - 'A', Utility.constructor(parts[1]));
                        }
                        gamestate.player1.setActiveDeck(listka);
                        line = bufferedReader.readLine();
                        len = Integer.parseInt(line);

                        for (int i = 0; i <len; i++ ){
                            line = bufferedReader.readLine();
                            String[] parts = line.split(" ");
                            char a = parts[0].charAt(0);
                            String b = parts[1].substring(1);
                            int index = ((int)a-'A'*5) + Integer.parseInt(b);
                            //tunggu implementasi ladang di player
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

