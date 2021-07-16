package com.game.ainsley.gameobjects.player.inventory;

import lib.ainsley.FileManager;

import java.awt.*;

public class Note {

    private Image noteImage, noteIconImage;

    private boolean beenFound, open;

    public Note(String imagePath, String iconPath, boolean hasBeenFound) {
        noteImage = FileManager.loadImage(imagePath);
        noteIconImage = FileManager.loadImage(iconPath);
        this.beenFound = hasBeenFound;
        this.open = false;
    }

    public Note(boolean isOpen, boolean beenFound) {
        this.open = isOpen;
        this.beenFound = beenFound;
    }

    public Image getNoteImage() {
        return noteImage;
    }

    public void setNoteImage(Image noteImage) {
        this.noteImage = noteImage;
    }

    public Image getNoteIconImage() {
        return noteIconImage;
    }

    public void setNoteIconImage(Image image) {
        this.noteIconImage = image;
    }

    public boolean hasBeenFound() {
        return beenFound;
    }

    public void setBeenFound(boolean beenFound) {
        this.beenFound = beenFound;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public void setOpen() {
        this.open = !this.open;
    }

    @Override
    public String toString() {
        return "Note{" +
                "hasBeenFound=" + beenFound +
                ", isOpen=" + open +
                '}';
    }
}
