package com.game.juanito.input;

import com.game.juanito.handler.SoundHandler;
import com.game.juanito.main.Game;
import com.game.juanito.map.Chunk;
import com.game.juanito.map.Door;
import com.game.juanito.player.Player;
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
    static URL dead;
    private int randomMusicNumber;

    private void randomMusic() {
        Random random = new Random();
        randomMusicNumber = random.nextInt(3 - 1) + 1;
    }

    public void mousePressed(MouseEvent event) {

        if (Game.screen == Screen.MAIN_MENU) {

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

                if (randomMusicNumber == 1) {
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
        } else if (Game.screen == Screen.DEATH) {
            dead = ClassLoader.getSystemResource("sounds/dead/deadSong.wav");

            Player.setHealth(6);
            Player.damageAnimation(false);

            Player.getInventory().setNotesCollected(0);
            Player.getInventory().setReadingNote(10);
            for (int i = 0; i < 9; i++) {
                Player.getInventory().getNote(i).setOpen(false);
                Player.getInventory().getNote(i).setBeenFound(false);
            }

            Door.setShouldRender(false);
            Chunk.setIterations(0);

            try {
                SoundHandler.playSound(dead, 0.1F, true);
            } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
                e.printStackTrace();
            }

            if (event.getX() >= 425 &&
                    event.getX() <= 655 &&
                    event.getY() >= (640 / 5) * 4 &&
                    event.getY() <= ((640 / 5) * 4) + 70
            ) {
                Game.screen = Screen.MAIN_MENU;

            } else if (event.getX() >= 425 &&
                    event.getX() <= 655 &&
                    event.getY() >= (640 / 5) * 3 &&
                    event.getY() <= ((640 / 5) * 3) + 70
            ) {
                Game.screen = Screen.GAME;
            }
        } else if (Game.screen == Screen.CREDITS) {
            if (event.getX() >= 425 &&
                    event.getX() <= 655 &&
                    event.getY() >= (640 / 5) * 4 &&
                    event.getY() <= ((640 / 5) * 4) + 70
            ) {
                Game.screen = Screen.MAIN_MENU;
            }
        }
    }
}
