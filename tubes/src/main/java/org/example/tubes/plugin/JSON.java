package org.example.tubes.plugin;

import org.example.tubes.GameState;
import org.example.tubes.Plugin;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

public class JSON implements Plugin {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void save(String filename, GameState gameState) {
        try {
            objectMapper.writeValue(new File(filename), gameState);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public GameState load(String filename) {
        try {
            return objectMapper.readValue(new File(filename), GameState.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String getName() {
        return "JSON";
    }
}
