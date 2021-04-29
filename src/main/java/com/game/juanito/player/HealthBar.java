package com.game.juanito.player;

import java.awt.*;
import java.net.URL;

public class HealthBar {

    private static final Toolkit toolkit = Toolkit.getDefaultToolkit();
    private static final URL heart = ClassLoader.getSystemResource("player/heart.png");
    private static final Image heartImage = toolkit.getImage(heart);

    public static void render(Graphics graphics) {
        int xOffset = 0;
        for (int i = 1; i <= Player.getHealth(); i++) {
            graphics.drawImage(
                    heartImage,
                    975 + xOffset,
                    5,
                    null
            );
            xOffset -= 68;
        }
    }
}
