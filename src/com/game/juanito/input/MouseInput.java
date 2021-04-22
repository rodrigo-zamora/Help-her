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
import java.util.Random;

public class MouseInput extends MouseAdapter {

    static URL background;
    static URL credits;
    static int contadorMusica = 1;
    private int randomMusicNumber;

    private void increaseContadorMusica() {
        contadorMusica++;
        if (contadorMusica == 3) {
            contadorMusica = 1;
        }
    }

    private void randomMusic() {
        Random random = new Random();
        randomMusicNumber = random.nextInt(3 - 1) + 1;
    }

    public void mousePressed(MouseEvent event) {

        if (Game.screen == Screen.MAIN_MENU) {

            //increaseContadorMusica();
            randomMusic();

            if (event.getX() >= 155 &&
                    event.getX() <= 385 &&
                    event.getY() >= 285 &&
                    event.getY() <= 355
            ) {

                Game.screen = Screen.GAME;

                if (randomMusicNumber == 1) {
                    background = ClassLoader.getSystemResource("sounds/background/main.wav");
                } else if (randomMusicNumber == 2) {
                    background = ClassLoader.getSystemResource("sounds/background/background.wav");
                } else {
                    background = ClassLoader.getSystemResource("sounds/background/8bitSong.wav");
                }


                try {
                    SoundHandler.playSound(background, 0.1F, true);
                } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
                    e.printStackTrace();
                }

            } else if (event.getX() >= 695 &&
                    event.getX() <= 925 &&
                    event.getY() >= 285 &&
                    event.getY() <= 355
            ) {

                Game.screen = Screen.CREDITS;

                if (contadorMusica == 1) {
                    credits = ClassLoader.getSystemResource("sounds/credits/EndCredits.wav");
                } else {
                    credits = ClassLoader.getSystemResource("sounds/credits/Credits.wav");
                }

                try {
                    SoundHandler.playSound(credits, 0.1F, true);
                } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
