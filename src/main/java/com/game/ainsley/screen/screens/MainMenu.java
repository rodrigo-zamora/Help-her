package com.game.ainsley.screen.screens;

import com.game.ainsley.main.Game;
import lib.ainsley.FileManager;

import java.awt.*;

public class MainMenu {

    private static final Image backgroundImage = FileManager.loadImage("Screens/IntroLarge.gif");
    private static final Image startImage = FileManager.loadImage("buttons/StartButtonPixel4.png");
    private static final Image creditsImage = FileManager.loadImage("buttons/CreditsButtonPixel4.png");

    public static void render(Graphics graphics) {

        graphics.drawImage(
                backgroundImage,
                0,
                0,
                null
        );

        graphics.drawImage(
                startImage,
                (Game.WIDTH / 4) - (startImage.getWidth(null) / 2),
                (Game.HEIGHT / 2) - (startImage.getHeight(null) / 2),
                null
        );

        graphics.drawImage(
                creditsImage,
                (Game.WIDTH / 2) + (Game.WIDTH / 4) - (creditsImage.getWidth(null) / 2),
                (Game.HEIGHT / 2) - (creditsImage.getHeight(null) / 2),
                null
        );
    }
}
