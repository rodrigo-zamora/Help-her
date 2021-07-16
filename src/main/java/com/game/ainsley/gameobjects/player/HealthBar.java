package com.game.ainsley.gameobjects.player;

import com.game.ainsley.main.Game;

import java.awt.*;
import java.net.URL;

public class HealthBar {

    private final Game game;

    private static final Toolkit toolkit = Toolkit.getDefaultToolkit();
    private static final URL heart = ClassLoader.getSystemResource("player/heart.png");
    private static final Image heartImage = toolkit.getImage(heart);

    public HealthBar() {
        game = Game.getInstance();
    }

    public void render(Graphics graphics) {
        int xOffset = 0;
        for (int i = 1; i <= game.getPlayer().getHealth(); i++) {
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
