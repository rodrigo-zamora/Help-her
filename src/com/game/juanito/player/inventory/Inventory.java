package com.game.juanito.player.inventory;

import java.awt.*;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Inventory {

    private List<Note> inventory = new ArrayList<>();

    private int notesCollected;

    private final Toolkit toolkit = Toolkit.getDefaultToolkit();

    private final URL container  = ClassLoader.getSystemResource("player/inventory/container.png");;

    private final Image containerImage = toolkit.getImage(container);;

    public Inventory() {

        notesCollected = 0;

        File folder = new File("res/player/inventoryItems");
        File[] listOfFiles = folder.listFiles();

        if (listOfFiles != null) {
            for (File listOfFile : listOfFiles) {
                inventory.add(new Note(listOfFile.getPath(), false));
            }
        }
    }

    public List<Note> getInventory() {
        return inventory;
    }

    public void setInventory(List<Note> notes) {
        this.inventory = notes;
    }

    public Image getInventoryImage(int inventoryIndex) {
        Note note = inventory.get(inventoryIndex);
        return note.getNoteImage();
    }

    public int getNotesCollected() {
        return notesCollected;
    }

    public void setNotesCollected(int notesCollected) {
        this.notesCollected = notesCollected;
    }

    public Image getContainerImage() {
        return containerImage;
    }
}
