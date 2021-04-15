package com.game.juanito.player;

import java.awt.*;
import java.net.URL;

public class HealthBar {

    Toolkit toolkit = Toolkit.getDefaultToolkit();
    URL heart = ClassLoader.getSystemResource("player/heart.png");
    Image heartImage = toolkit.getImage(heart);

    public void render(Graphics graphics) {
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
