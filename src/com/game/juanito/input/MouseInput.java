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
import java.util.concurrent.ThreadLocalRandom;

public class MouseInput extends MouseAdapter {

    static URL background;
    static URL credits;

    public void mousePressed(MouseEvent event) {
        int randomNum = ThreadLocalRandom.current().nextInt(1, 3 + 1);

        if (event.getX() >= 155 &&
                event.getX() <= 385 &&
                event.getY() >= 285 &&
                event.getY() <= 355 &&
                Game.screen == Screen.MAIN_MENU
        ) {
            if (randomNum == 1) {
                background = ClassLoader.getSystemResource("sounds/background/main.wav");
            } else if( randomNum == 2 ) {
                background = ClassLoader.getSystemResource("sounds/background/background.wav");
            } else {
                background = ClassLoader.getSystemResource("sounds/background/8bitSong.wav");
            }

            Game.screen = Screen.GAME;

            try {
                SoundHandler.playSound(background, 0.1F, true);
            } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
                e.printStackTrace();
            }
        } else if (event.getX() >= 695 &&
                event.getX() <= 925 &&
                event.getY() >= 285 &&
                event.getY() <= 355 &&
                Game.screen == Screen.MAIN_MENU
        ) {
            if (randomNum == 1) {
                credits = ClassLoader.getSystemResource("sounds/credits/EndCredits.wav");
            } else {
                credits = ClassLoader.getSystemResource("sounds/credits/Credits.wav");
            }

            Game.screen = Screen.CREDITS;

            try {
                SoundHandler.playSound(credits, 0.1F, true);
            } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
                e.printStackTrace();
            }
        }
    }
}
