package com.game.ainsley.screen.screens;

import com.game.ainsley.main.Game;
import lib.ainsley.FileManager;

import java.awt.*;

public class Win {

    private static final Image backgroundImage = FileManager.loadImage("screens/audioScreen.png");
    private static final Image menuImage = FileManager.loadImage("buttons/menuButtonPixel4.png");

    public static void render(Graphics graphics) {
        graphics.drawImage(
                backgroundImage,
                0,
                0,
                null
        );

        graphics.drawImage(
                menuImage,
                (Game.WIDTH / 2) - (menuImage.getWidth(null) / 2),
                (Game.HEIGHT / 5) * 3,
                null
        );
    }
}
