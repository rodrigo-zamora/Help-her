package com.game.ainsley.gameobjects.player.inventory;

import lib.ainsley.Sound;

import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Inventory {

    private static final Sound paper = new Sound("sounds/effects/note.mp3", Sound.EFFECT);
    private final Toolkit toolkit = Toolkit.getDefaultToolkit();
    private final URL container = ClassLoader.getSystemResource("player/inventory/container.png");
    private final Image containerImage = toolkit.getImage(container);
    private final List<Note> inventory = new ArrayList<>();
    private int notesCollected;
    private int readingNote;

    public Inventory() {
        notesCollected = 0;
        readingNote = 10;
        String index;
        for (int i = 1; i <= 9; i++) {
            index = i + ".png";
            inventory.add(new Note("player/inventoryItems/" + index, "player/inventoryItems/icon-" + index, false));
        }
    }

    public static Sound getPaper() {
        return paper;
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

    public void closeAllNotes() {
        readingNote = 10;
        for (int i = 0; i < 9; i++) {
            inventory.get(i).setOpen(false);
        }
    }

    public void closeAllNotesExcept(int index) {
        for (int i = 0; i < 9; i++) {
            if (i != index) {
                inventory.get(i).setOpen(false);
            }
        }
    }
}
