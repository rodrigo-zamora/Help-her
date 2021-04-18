package com.game.juanito.screen.screens;

import com.game.juanito.main.Game;

import java.awt.*;
import java.net.URL;

public class MainMenu {

    private static final Toolkit toolkit = Toolkit.getDefaultToolkit();
    private static final URL background = ClassLoader.getSystemResource("Screens/IntroLarge.gif");
    private static final Image backgroundImage = toolkit.getImage(background);
    private static final URL startButton = ClassLoader.getSystemResource("buttons/StartButtonPixel4.png");
    private static final Image startImage = toolkit.getImage(startButton);
    private static final URL creditsButton = ClassLoader.getSystemResource("buttons/CreditsButtonPixel4.png");
    private static final Image creditsImage = toolkit.getImage(creditsButton);


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
