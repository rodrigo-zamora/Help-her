package com.game.juanito.handler;

import com.game.juanito.main.GameObject;
import com.game.juanito.main.ID;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyboardInput extends KeyAdapter {

    private final Handler handler;

    public KeyboardInput(Handler handler) {
        this.handler = handler;
    }

    public void keyPressed(KeyEvent event) {
        int key = event.getKeyCode();
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            if (tempObject.getID() == ID.Player) {
                if (key == 38 || key == 87) { // Up
                    tempObject.setSpeedY(-5);
                } else if (key == 40 || key == 83) { // Down
                    tempObject.setSpeedY(5);
                } else if (key == 39 || key == 68) { // Right
                    tempObject.setSpeedX(5);
                } else if (key == 37 || key == 65) { // Left
                    tempObject.setSpeedX(-5);
                }
            }
        }
    }

    public void keyReleased(KeyEvent event) {
        int key = event.getKeyCode();
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            if (tempObject.getID() == ID.Player) {
                if (key == 38 || key == 87) { // Up
                    tempObject.setSpeedY(0);
                } else if (key == 40 || key == 83) { // Down
                    tempObject.setSpeedY(0);
                } else if (key == 39 || key == 68) { // Right
                    tempObject.setSpeedX(0);
                } else if (key == 37 || key == 65) { // Left
                    tempObject.setSpeedX(0);
                }
            }
        }
    }
}
