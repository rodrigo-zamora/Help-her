package com.game.juanito.input;

import com.game.juanito.main.Game;
import com.game.juanito.screen.Screen;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter {

    public void mousePressed(MouseEvent event) {
        if (event.getX() >= 100 &&
                event.getX() <= 323 &&
                event.getY() >= 200 &&
                event.getY() <= 270 &&
                Game.screen == Screen.MAIN_MENU) {
            Game.screen = Screen.GAME;
        } else if (event.getX() >= 100 &&
                event.getX() <= 323 &&
                event.getY() >= 300 &&
                event.getY() <= 370 &&
                Game.screen == Screen.MAIN_MENU) {
            Game.screen = Screen.CREDITS;
        }
    }
}
