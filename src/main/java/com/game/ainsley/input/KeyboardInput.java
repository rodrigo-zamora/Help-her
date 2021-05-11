package com.game.ainsley.input;

import com.game.ainsley.main.Game;
import com.game.ainsley.map.Chunk;
import com.game.ainsley.map.Door;
import com.game.ainsley.player.Player;
import com.game.ainsley.player.inventory.Inventory;
import com.game.ainsley.screen.Screen;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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
                        Door.collision();
                }

                // Inventory
                case 49, 50, 51, 52, 53, 54, 55, 56, 57 -> {
                    if (!Game.isPaused()) {
                        int note = key - 49;
                        Player.getInventory().closeAllNotesExcept(note);
                        if (Player.getInventory().getNote(note).hasBeenFound()) {
                            if (Player.getInventory().getNote(note).isOpen()) {
                                Player.getInventory().closeAllNotes();
                                Inventory.getPaper().stopSound();
                            } else {
                                Inventory.getPaper().playSound(0.8F, false);
                                Player.getInventory().getNote(note).setOpen(true);
                                Player.getInventory().setReadingNote(note);
                                Player.setSpeedY(0);
                                Chunk.setSpeed(0);
                            }
                        } else {
                            Player.getInventory().closeAllNotes();
                        }
                    }
                }

                // Pause menu
                case 112, 80 -> {
                    Game.setPaused();
                }
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
