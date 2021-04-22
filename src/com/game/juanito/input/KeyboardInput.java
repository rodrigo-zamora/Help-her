package com.game.juanito.input;

import com.game.juanito.map.Chunk;
import com.game.juanito.map.Door;
import com.game.juanito.player.Player;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyboardInput extends KeyAdapter {

    public void keyPressed(KeyEvent event) {
        int key = event.getKeyCode();
        switch (key) {
            case 38, 87 -> {
                if (Player.shouldRender() && Player.getInventory().getReadingNote() == 10)
                    Player.setSpeedY(-5);
            }
            case 40, 83 -> {
                if (Player.shouldRender() && Player.getInventory().getReadingNote() == 10)
                    Player.setSpeedY(5);
            }
            case 39, 68 -> {
                if (Player.shouldRender() && Player.getInventory().getReadingNote() == 10)
                    Chunk.setSpeed(5);
            }
            case 69 -> Door.collision(Player.getCollisionHandler().getRectangle());
            case 49, 50, 51, 52, 53, 54, 55, 56, 57 -> {
                if (Player.getInventory().getNote(key - 49).hasBeenFound()) {
                    Player.getInventory().getNote(key - 49).setOpen();
                    Player.setSpeedY(0);
                    Chunk.setSpeed(0);
                    if (!Player.getInventory().getNote(key - 49).isOpen()) {
                        Player.getInventory().setReadingNote(key - 49);
                    } else {
                        Player.getInventory().setReadingNote(10);
                    }
                }
            }
        }
    }

    public void keyReleased(KeyEvent event) {
        int key = event.getKeyCode();
        switch (key) {
            case 38, 87, 40, 83 -> Player.setSpeedY(0);
            case 39, 68 -> Chunk.setSpeed(0);
        }
    }
}
