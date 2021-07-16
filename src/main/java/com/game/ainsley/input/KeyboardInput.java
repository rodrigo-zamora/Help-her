package com.game.ainsley.input;

import com.game.ainsley.gameobjects.player.inventory.Inventory;
import com.game.ainsley.main.Game;
import com.game.ainsley.screen.Screen;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyboardInput extends KeyAdapter {

    private final Game game;

    public KeyboardInput() {
        game = Game.getInstance();
    }

    public void keyPressed(KeyEvent event) {
        if (Game.getScreen() == Screen.GAME) {
            int key = event.getKeyCode();
            switch (key) {
                case 38, 87 -> {
                    if (game.getPlayer().shouldRender() && game.getPlayer().getInventory().getReadingNote() == 10 && !Game.isPaused())
                        game.getPlayer().setSpeedY(-5);
                }
                case 40, 83 -> {
                    if (game.getPlayer().shouldRender() && game.getPlayer().getInventory().getReadingNote() == 10 && !Game.isPaused())
                        game.getPlayer().setSpeedY(5);
                }

                // Movement to the right
                case 39, 68 -> {
                    if (game.getPlayer().shouldRender() && game.getPlayer().getInventory().getReadingNote() == 10 && !Game.isPaused())
                        game.getMapManager().setSpeed(5);
                }

                // Open / close door
                case 69 -> {
                    if (!Game.isPaused())
                        game.getDoor().collision();
                }

                // Inventory
                case 49, 50, 51, 52, 53, 54, 55, 56, 57 -> {
                    if (!Game.isPaused()) {
                        int note = key - 49;
                        game.getPlayer().getInventory().closeAllNotesExcept(note);
                        if (game.getPlayer().getInventory().getNote(note).hasBeenFound()) {
                            if (game.getPlayer().getInventory().getNote(note).isOpen()) {
                                game.getPlayer().getInventory().closeAllNotes();
                                Inventory.getPaper().stopSound();
                            } else {
                                Inventory.getPaper().stopSound();
                                Inventory.getPaper().playSound(0.8F, false);
                                game.getPlayer().getInventory().getNote(note).setOpen(true);
                                game.getPlayer().getInventory().setReadingNote(note);
                                game.getPlayer().setSpeedY(0);
                                game.getMapManager().setSpeed(0);
                            }
                        } else {
                            game.getPlayer().getInventory().closeAllNotes();
                        }
                    }
                }

                // Pause menu
                case 27, 80 -> Game.setPaused();
            }
        }
    }

    public void keyReleased(KeyEvent event) {
        if (Game.getScreen() == Screen.GAME) {
            int key = event.getKeyCode();
            switch (key) {
                case 38, 87, 40, 83 -> game.getPlayer().setSpeedY(0);
                case 39, 68 -> game.getMapManager().setSpeed(0);
            }
        }
    }
}
