package com.game.ainsley.handler;

import com.game.ainsley.main.Game;
import lib.ainsley.Sound;

public class SoundHandler {

    private static final Sound mainMenuBackground = new Sound("sounds/background/mainBackground.mp3", Sound.BACKGROUND);
    private static final Sound gameBackground = new Sound("sounds/background/gameBackground.mp3", Sound.BACKGROUND);
    private static final Sound creditsBackground = new Sound("sounds/background/creditsBackground.mp3", Sound.BACKGROUND);
    private static final Sound deathBackground = new Sound("sounds/background/deathBackground.mp3", Sound.BACKGROUND);
    private static final Sound winBackground = new Sound("sounds/background/winBackground.mp3", Sound.BACKGROUND);

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

}
