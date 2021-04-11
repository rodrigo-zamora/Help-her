package com.game.juanito.handler;

import com.game.juanito.map.Chunk;
import com.game.juanito.player.Player;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyboardInput extends KeyAdapter {

    public void keyPressed(KeyEvent event) {
        int key = event.getKeyCode();
        if (key == 38 || key == 87) { // Up
            Player.setSpeedY(-5);
        } else if (key == 40 || key == 83) { // Down
            Player.setSpeedY(5);
        } else if (key == 39 || key == 68) { // Right
            Chunk.setSpeed(5);
        }
    }

    public void keyReleased(KeyEvent event) {
        int key = event.getKeyCode();
        if (key == 38 || key == 87) { // Up
            Player.setSpeedY(0);
        } else if (key == 40 || key == 83) { // Down
            Player.setSpeedY(0);
        } else if (key == 39 || key == 68) { // Right
            Chunk.setSpeed(0);
        }
    }
}
