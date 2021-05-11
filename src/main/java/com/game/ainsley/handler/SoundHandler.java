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

    public static void playRandomSound() {
        if (soundArrayList.size() == 0) {
            soundArrayList.add(new Sound("sounds/effects/backgroundEffects/1.mp3", Sound.BACKGROUND));
            soundArrayList.add(new Sound("sounds/effects/backgroundEffects/2.mp3", Sound.BACKGROUND));
            soundArrayList.add(new Sound("sounds/effects/backgroundEffects/3.mp3", Sound.BACKGROUND));
            soundArrayList.add(new Sound("sounds/effects/backgroundEffects/4.mp3", Sound.BACKGROUND));
            soundArrayList.add(new Sound("sounds/effects/backgroundEffects/5.mp3", Sound.BACKGROUND));
            soundArrayList.add(new Sound("sounds/effects/backgroundEffects/6.mp3", Sound.BACKGROUND));
            soundArrayList.add(new Sound("sounds/effects/backgroundEffects/7.mp3", Sound.BACKGROUND));
            soundArrayList.add(new Sound("sounds/effects/backgroundEffects/8.mp3", Sound.BACKGROUND));
            soundArrayList.add(new Sound("sounds/effects/backgroundEffects/9.mp3", Sound.BACKGROUND));
        }
        if (Game.getScreen() == Screen.GAME && !Game.isPaused()) {
            int index = Numbers.randomNumberBetween(0, soundArrayList.size());
            new java.util.Timer().schedule(
                    new java.util.TimerTask() {
                        @Override
                        public void run() {
                            soundArrayList.get(index).stopSound();
                        }
                    },
                    Numbers.randomNumberBetween(15000, 30000)
            );
            soundArrayList.get(index).playSound(Sound.EFFECT, false);
        } else {
            playRandomSound();
        }
    }

}
