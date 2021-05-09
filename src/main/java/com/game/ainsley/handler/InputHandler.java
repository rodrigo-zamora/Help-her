package com.game.ainsley.handler;

import com.game.ainsley.data.Load;
import com.game.ainsley.data.Save;
import com.game.ainsley.main.Game;
import com.game.ainsley.screen.Screen;
import lib.ainsley.Numbers;
import lib.ainsley.Sound;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.IOException;

public class InputHandler {

    private final Rectangle gameFromMenu = new Rectangle(155, 285, 230, 70);
    private final Rectangle creditsFromMenu = new Rectangle(695, 285, 230, 70);
    private final Rectangle mainMenuFromDeath = new Rectangle(425, 512, 230, 70);
    private final Rectangle gameFromDeath = new Rectangle(425, 384, 230, 70);
    private final Rectangle mainMenuCredits = new Rectangle(425, 512, 230, 70);
    private final Rectangle saveFromPause = new Rectangle(405, 250, 230, 70);
    private final Rectangle loadFromPause = new Rectangle(405, 355, 270, 70);
    private final Rectangle mainMenuFromPause = new Rectangle(405, 460, 270, 70);
    private final Sound background_1 = new Sound("sounds/background/main.wav");
    private final Sound background_2 = new Sound("sounds/background/background.wav");
    private final Sound background_3 = new Sound("sounds/background/8bitSong.wav");
    private final Sound credits_1 = new Sound("sounds/test.mp3");
    private final Sound credits_2 = new Sound("sounds/test.mp3");
    private Rectangle rectangle = new Rectangle(0, 0, 1, 1);

    public InputHandler() throws UnsupportedAudioFileException, LineUnavailableException, IOException {

    }

    /**
     * Method to check collision between the mouse and the buttons
     *
     * @throws UnsupportedAudioFileException if the audio file isn't valid
     * @throws LineUnavailableException      if the file can't be opened
     * @throws IOException                   if an I/O exception occurs
     */
    public void checkCollision() throws UnsupportedAudioFileException, LineUnavailableException, IOException {

        int randomMusicNumber = Numbers.randomNumberBetween(1, 3);
        switch (Game.getScreen()) {
            case MAIN_MENU -> {
                if (rectangle.intersects(gameFromMenu)) {
                    Game.setScreen(Screen.GAME);
                    Game.setPaused(false);
                    switch (randomMusicNumber) {
                        case 1 -> background_1.playSound(1, true);
                        case 2 -> background_2.playSound(1, true);
                        case 3 -> background_3.playSound(1, true);
                    }

                } else if (rectangle.intersects(creditsFromMenu)) {
                    Game.setScreen(Screen.CREDITS);
                    switch (randomMusicNumber) {
                        case 1 -> credits_1.playSound(1, true);
                        case 2 -> credits_2.playSound(1, true);
                    }
                }
            }

            case DEATH -> {
                Game.reset();
                if (rectangle.intersects(mainMenuFromDeath)) {
                    Game.setScreen(Screen.MAIN_MENU);
                } else if (rectangle.intersects(gameFromDeath)) {
                    Game.setScreen(Screen.GAME);
                    switch (randomMusicNumber) {
                        case 1 -> background_1.playSound(1, true);
                        case 2 -> background_2.playSound(1, true);
                        case 3 -> background_3.playSound(1, true);
                    }
                }

            }

            case CREDITS -> {
                if (rectangle.intersects(mainMenuCredits)) {
                    Game.setScreen(Screen.MAIN_MENU);
                }
            }

            case GAME -> {
                if (Game.isPaused()) {
                    if (rectangle.intersects(saveFromPause)) {
                        Save.saveGame();
                        Game.setPaused(false);
                    } else if (rectangle.intersects(loadFromPause)) {
                        Load.loadGame();
                        Game.setPaused(false);
                    } else if (rectangle.intersects(mainMenuFromPause)) {
                        Game.setScreen(Screen.MAIN_MENU);
                        Game.reset();
                        Game.setPaused(false);
                    }
                }
            }
        }

    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }
}

