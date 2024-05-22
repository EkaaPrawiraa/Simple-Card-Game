package org.example.tubes;

public interface Plugin {
    public void save(String filename,GameState state);
    public GameState load(String filename);
}