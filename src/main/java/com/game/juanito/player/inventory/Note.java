package com.game.juanito.player.inventory;

import java.awt.*;
import java.net.URL;

public class Note {

    private Image noteImage, noteIconImage;

    private URL url;

    private boolean beenFound, open;

    public Note(URL imagePath, URL iconPath, boolean hasBeenFound) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        noteImage = toolkit.getImage(imagePath);
        noteIconImage = toolkit.getImage(iconPath);
        this.beenFound = hasBeenFound;
        this.open = true;
        this.url = imagePath;
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

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
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
