package com.game.ainsley.screen.screens;

import com.game.ainsley.main.Game;
import lib.ainsley.FileManager;

import java.awt.*;

public class Credits {

    private static final Image backgroundImage = FileManager.loadImage("screens/Credits2.png");
    private static final Image backImage = FileManager.loadImage("buttons/backButtonPixel4.png");

    public static void render(Graphics graphics) {

        graphics.drawImage(
                backgroundImage,
                0,
                0,
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
