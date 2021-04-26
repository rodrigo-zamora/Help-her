package com.game.juanito.screen.screens;

import com.game.juanito.main.Game;

import java.awt.*;
import java.net.URL;

public class Death {

    private static final Toolkit toolkit = Toolkit.getDefaultToolkit();
    private static final URL background = ClassLoader.getSystemResource("Screens/deadScreen.gif");
    private static final Image backgroundImage = toolkit.getImage(background);
    private static final URL backButton = ClassLoader.getSystemResource("buttons/backButtonPixel4.png");
    private static final Image backImage = toolkit.getImage(backButton);
    private static final URL restartButton = ClassLoader.getSystemResource("buttons/restartButtonPixel4.png");
    private static final Image restartImage = toolkit.getImage(restartButton);

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
