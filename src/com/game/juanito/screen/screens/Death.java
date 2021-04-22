package com.game.juanito.screen.screens;

import com.game.juanito.main.Game;
import java.awt.*;
import java.net.URL;

public class Death {

    private static final Toolkit toolkit = Toolkit.getDefaultToolkit();
    private static final URL background = ClassLoader.getSystemResource("Screens/Credits1.png");
    private static final Image backgroundImage = toolkit.getImage(background);
    private static final URL backButton = ClassLoader.getSystemResource("buttons/backButtonPixel4.png");
    private static final Image backImage = toolkit.getImage(backButton);

    public static void render(Graphics graphics) {

        graphics.drawImage(
                backgroundImage,
                0,
                0,
                null
        );

        graphics.drawImage(
                backImage,
                (Game.WIDTH / 4) - (backImage.getWidth(null) / 2),
                (Game.HEIGHT / 2) - (backImage.getHeight(null) / 2),
                null
        );
    }
}
