package com.game.ainsley.handler;

import com.game.ainsley.main.Game;
import com.game.ainsley.screen.Screen;
import lib.ainsley.Numbers;
import lib.ainsley.Sound;

import java.util.ArrayList;

public class SoundHandler {

    private static final Sound mainMenuBackground = new Sound("sounds/background/mainBackground.mp3", Sound.BACKGROUND);
    private static final Sound gameBackground = new Sound("sounds/background/gameBackground.mp3", Sound.BACKGROUND);
    private static final Sound creditsBackground = new Sound("sounds/background/creditsBackground.mp3", Sound.BACKGROUND);
    private static final Sound deathBackground = new Sound("sounds/background/deathBackground.mp3", Sound.BACKGROUND);
    private static final Sound winBackground = new Sound("sounds/background/winBackground.mp3", Sound.BACKGROUND);

    private static final ArrayList<Sound> soundArrayList = new ArrayList<>();

    private static int lastNumber = -1;

    /**
     * Method to play a sound depending on the current scene
     */
    public static void sceneSound() {
        Sound.stopAllSounds();
        switch (Game.getScreen()) {
            case MAIN_MENU -> mainMenuBackground.playSound(1, true);
            case GAME -> gameBackground.playSound(1, true);
            case CREDITS -> creditsBackground.playSound(1, true);
            case DEATH -> deathBackground.playSound(1, true);
            case WIN -> winBackground.playSound(1, true);
        }
    }

    /**
     * Method to load all the background effects into an array list
     */
    public static void addSounds() {
        for (int i = 0; i < 9; i++) {
            String index = i + 1 + ".mp3";
            soundArrayList.add(new Sound("sounds/effects/backgroundEffects/" + index, Sound.BACKGROUND));
        }
    }

    /**
     * Method to play a random sound from background effects array list
     * Sounds will be played all the time, while the player is in
     * the game scene, and the game isn't paused.
     */
    public static void playRandomSound() {
        if (Game.getScreen() == Screen.GAME && !Game.isPaused()) {
            int index = Numbers.randomNumberBetween(0, soundArrayList.size());
            while (lastNumber == index) {
                index = Numbers.randomNumberBetween(0, soundArrayList.size());
            }
            int finalIndex = index;
            lastNumber = index;
            new java.util.Timer().schedule(
                    new java.util.TimerTask() {
                        @Override
                        public void run() {
                            soundArrayList.get(finalIndex).stopSound();
                            playRandomSound();
                        }
                    },
                    Numbers.randomNumberBetween(30000, 45000)
            );
            soundArrayList.get(index).playSound(Sound.EFFECT, false);
        }
    }
}
