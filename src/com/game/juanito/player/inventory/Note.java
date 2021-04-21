package com.game.juanito.player.inventory;

import java.awt.*;
import java.net.URL;

public class Note {

    private final Toolkit toolkit = Toolkit.getDefaultToolkit();

    private Image noteImage;

    private URL url;

    private boolean beenFound, open;

    public Note(String path, boolean hasBeenFound) {
        url = ClassLoader.getSystemResource(path);
        noteImage = toolkit.getImage(url);
        this.beenFound = hasBeenFound;
        this.open = false;
    }

    public Image getNoteImage() {
        return noteImage;
    }

    public void setNoteImage(Image noteImage) {
        this.noteImage = noteImage;
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
                "url=" + url +
                ", hasBeenFound=" + beenFound +
                ", isOpen=" + open +
                '}';
    }
}
