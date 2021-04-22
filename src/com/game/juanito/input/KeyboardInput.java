package com.game.juanito.input;

import com.game.juanito.map.Chunk;
import com.game.juanito.map.Door;
import com.game.juanito.player.Player;
import com.game.juanito.player.inventory.Inventory;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyboardInput extends KeyAdapter {

    public void keyPressed(KeyEvent event) {
        int key = event.getKeyCode();
        switch (key) {
            case 38, 87 -> {
                if (Player.shouldRender())
                    Player.setSpeedY(-5);
            }
            case 40, 83 -> {
                if (Player.shouldRender())
                    Player.setSpeedY(5);
            }
            case 39, 68 -> {
                if (Player.shouldRender())
                    Chunk.setSpeed(5);
            }
            case 69 -> Door.collision(Player.getCollisionHandler().getRectangle());
            case 49 - 57 -> {
                Player.getInventory().getNote(key - 48).setOpen();
                if (!Player.getInventory().getNote(key - 48).isOpen()) {
                    Player.getInventory().setReadingNote(key - 48);
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
