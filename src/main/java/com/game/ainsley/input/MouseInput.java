package com.game.ainsley.input;

import com.game.ainsley.data.Load;
import com.game.ainsley.data.Save;
import com.game.ainsley.main.Game;
import com.game.ainsley.map.Chunk;
import com.game.ainsley.map.Door;
import com.game.ainsley.player.Player;
import com.game.ainsley.screen.Screen;
import lib.ainsley.Numbers;
import lib.ainsley.Sound;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;

public class MouseInput extends MouseAdapter {

    static URL background;
    static URL credits;
    static URL dead;

    public void mousePressed(MouseEvent event) {

        if (Game.getScreen() == Screen.MAIN_MENU) {

            int randomMusicNumber = Numbers.randomNumberBetween(1, 3);

            if (event.getX() >= 155 &&
                    event.getX() <= 385 &&
                    event.getY() >= 285 &&
                    event.getY() <= 355
            ) {

                Game.setScreen(Screen.GAME);

                if (randomMusicNumber == 1) {
                    background = ClassLoader.getSystemResource("sounds/background/main.wav");
                } else if (randomMusicNumber == 2) {
                    background = ClassLoader.getSystemResource("sounds/background/background.wav");
                } else {
                    background = ClassLoader.getSystemResource("sounds/background/8bitSong.wav");
                }

                try {
                    Sound.playSound(background, 0.1F, true);
                } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
                    e.printStackTrace();
                }

            } else if (event.getX() >= 695 &&
                    event.getX() <= 925 &&
                    event.getY() >= 285 &&
                    event.getY() <= 355
            ) {

                Game.setScreen(Screen.CREDITS);

                if (randomMusicNumber == 1) {
                    credits = ClassLoader.getSystemResource("sounds/credits/EndCredits.wav");
                } else {
                    credits = ClassLoader.getSystemResource("sounds/credits/Credits.wav");
                }

                try {
                    Sound.playSound(credits, 0.1F, true);
                } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
                    e.printStackTrace();
                }
            }
        } else if (Game.getScreen() == Screen.DEATH) {
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
                Sound.playSound(dead, 0.1F, true);
            } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
                e.printStackTrace();
            }

            if (event.getX() >= 425 &&
                    event.getX() <= 655 &&
                    event.getY() >= (640 / 5) * 4 &&
                    event.getY() <= ((640 / 5) * 4) + 70
            ) {
                Game.setScreen(Screen.MAIN_MENU);

            } else if (event.getX() >= 425 &&
                    event.getX() <= 655 &&
                    event.getY() >= (640 / 5) * 3 &&
                    event.getY() <= ((640 / 5) * 3) + 70
            ) {
                Game.setScreen(Screen.GAME);
            }
        } else if (Game.getScreen() == Screen.CREDITS) {
            if (event.getX() >= 425 &&
                    event.getX() <= 655 &&
                    event.getY() >= (640 / 5) * 4 &&
                    event.getY() <= ((640 / 5) * 4) + 70
            ) {
                Game.setScreen(Screen.MAIN_MENU);
            }
        } else if (Game.getScreen() == Screen.PAUSED) {
            if (event.getX() >= 405 &&
                    event.getX() <= 675 &&
                    event.getY() >= (640 / 2) - 70 &&
                    event.getY() <= (640 / 2)
            ) {
                try {
                    Save.saveGame();
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            } else if (event.getX() >= 405 &&
                    event.getX() <= 675 &&
                    event.getY() >= (640 / 2) + 35 &&
                    event.getY() <= (640 / 2) + 35 + 70
            ) {
                try {
                    Load.loadGame();
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            }
        }
    }
}
