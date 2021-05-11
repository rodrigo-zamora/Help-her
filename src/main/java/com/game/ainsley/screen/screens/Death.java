package com.game.ainsley.screen.screens;

import com.game.ainsley.main.Game;
import lib.ainsley.FileManager;

import java.awt.*;

public class Death {

    private static final Image backgroundImage = FileManager.loadImage("screens/deadScreen.gif");
    private static final Image backImage = FileManager.loadImage("buttons/backButtonPixel4.png");
    private static final Image restartImage = FileManager.loadImage("buttons/restartButtonPixel4.png");

    public static void render(Graphics graphics) {

        graphics.drawImage(
                backgroundImage,
                0,
                0,
                null
        );

        graphics.drawImage(
                restartImage,
                (Game.WIDTH / 2) - (restartImage.getWidth(null) / 2),
                (Game.HEIGHT / 5) * 3,
                null
        );

        graphics.drawImage(
                backImage,
                (Game.WIDTH / 2) - (backImage.getWidth(null) / 2),
                (Game.HEIGHT / 5) * 4,
                null
        );
    }
}

