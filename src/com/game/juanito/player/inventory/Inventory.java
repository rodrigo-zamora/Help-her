package com.game.juanito.player.inventory;

import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Inventory {

    private List<Note> inventory = new ArrayList<>();

    private int notesCollected;

    private int readingNote;

    private final Toolkit toolkit = Toolkit.getDefaultToolkit();

    private final URL container  = ClassLoader.getSystemResource("player/inventory/container.png");

    private final Image containerImage = toolkit.getImage(container);

    public Inventory() {
        notesCollected = 0;
        readingNote = 10;
        String index;
        URL imagePath, iconPath;
        for(int i = 1; i <= 9; i++) {
            index = i + ".png";
            imagePath = ClassLoader.getSystemResource("player/inventoryItems/" + index);
            iconPath = ClassLoader.getSystemResource("player/inventoryItems/icon-" + index);
            inventory.add(new Note(imagePath, iconPath, false));
        }
    }

    public int getNotesCollected() {
        return notesCollected;
    }

    public void setNotesCollected(int notesCollected) {
        this.notesCollected = notesCollected;
    }

    public int getReadingNote() {
        return readingNote;
    }

    public void setReadingNote(int readingNote) {
        this.readingNote = readingNote;
    }

    public Image getInventoryImage(int inventoryIndex) {
        Note note = inventory.get(inventoryIndex);
        return note.getNoteImage();
    }

    public Image getInventoryIconImage(int inventoryIndex) {
        Note note = inventory.get(inventoryIndex);
        return note.getNoteIconImage();
    }

    public Image getContainerImage() {
        return containerImage;
    }

    public Note getNote(int index) {
        return inventory.get(index);
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "inventory=" + inventory +
                '}';
    }
}
