package com.game.juanito.input;

import com.game.juanito.handler.SoundHandler;
import com.game.juanito.main.Game;
import com.game.juanito.screen.Screen;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;

public class MouseInput extends MouseAdapter {

    static URL background = ClassLoader.getSystemResource("sounds/background/main.wav");

    public void mousePressed(MouseEvent event) {
        if (event.getX() >= 100 &&
                event.getX() <= 323 &&
                event.getY() >= 200 &&
                event.getY() <= 270 &&
                Game.screen == Screen.MAIN_MENU) {
            Game.screen = Screen.GAME;
            try {
                SoundHandler.playSound(background, 0.1F, true);
            } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
                e.printStackTrace();
            }
        } else if (event.getX() >= 100 &&
                event.getX() <= 323 &&
                event.getY() >= 300 &&
                event.getY() <= 370 &&
                Game.screen == Screen.MAIN_MENU) {
            Game.screen = Screen.CREDITS;
        }
    }
}
