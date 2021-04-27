package com.game.juanito.input;

import com.game.juanito.data.Save;
import com.game.juanito.main.Game;
import com.game.juanito.map.Chunk;
import com.game.juanito.map.Door;
import com.game.juanito.player.Player;
import com.game.juanito.screen.Screen;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class KeyboardInput extends KeyAdapter {

    public void keyPressed(KeyEvent event) {
        if (Game.getScreen() == Screen.GAME) {
            int key = event.getKeyCode();
            switch (key) {
                case 38, 87 -> {
                    if (Player.shouldRender() && Player.getInventory().getReadingNote() == 10 && !Game.isPaused())
                        Player.setSpeedY(-5);
                }
                case 40, 83 -> {
                    if (Player.shouldRender() && Player.getInventory().getReadingNote() == 10 && !Game.isPaused())
                        Player.setSpeedY(5);
                }

                // Movement to the right
                case 39, 68 -> {
                    if (Player.shouldRender() && Player.getInventory().getReadingNote() == 10 && !Game.isPaused())
                        Chunk.setSpeed(5);
                }

                // Open / close door
                case 69 -> {
                    if (!Game.isPaused())
                        Door.collision(Player.getCollisionHandler().getRectangle());
                }

                // Inventory
                case 49, 50, 51, 52, 53, 54, 55, 56, 57 -> {
                    try {
                        Save.saveGame();
                    } catch (IOException exception) {
                        exception.printStackTrace();
                    }
                    if (!Game.isPaused()) {
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

                // Pause menu
                case 27 -> Game.setPaused();
            }
        }
    }

    public void keyReleased(KeyEvent event) {
        if (Game.getScreen() == Screen.GAME) {
            int key = event.getKeyCode();
            switch (key) {
                case 38, 87, 40, 83 -> Player.setSpeedY(0);
                case 39, 68 -> Chunk.setSpeed(0);
            }
        }
    }
}
