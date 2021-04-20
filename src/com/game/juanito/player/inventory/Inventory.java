package com.game.juanito.player.inventory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Inventory {

    private List<Note> inventory = new ArrayList<>();

    public Inventory() {

        File folder = new File("res/player/inventoryItems");
        File[] listOfFiles = folder.listFiles();

        for (File listOfFile : listOfFiles) {
            inventory.add(new Note(listOfFile.getPath(), false));
        }
    }

    public List<Note> getInventory() {
        return inventory;
    }

    public void setInventory(List<Note> notes) {
        this.inventory = notes;
    }



}
