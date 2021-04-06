package com.game.juanito.player;

import java.awt.*;
import java.net.URL;

public class HealthBar {

    private int health;

    Toolkit toolkit = Toolkit.getDefaultToolkit();

    URL heart = ClassLoader.getSystemResource("player/heart.png");
    Image heartImage = toolkit.getImage(heart);

    public void tick(int health){
        this.health = health;
    }

    public void render(Graphics graphics, int x, int y) {
        int xOffset = 0;
        for(int i = 1; i <= health; i++) {
            graphics.drawImage(
                    heartImage,
                    x + xOffset,
                    y,
                    null
            );
            xOffset += 68;
        }
    }
}
