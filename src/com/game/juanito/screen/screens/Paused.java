package com.game.ainsley.screen.screens;

import com.game.ainsley.main.Game;

import java.awt.*;
import java.net.URL;

public class Paused {
    private static final Toolkit toolkit = Toolkit.getDefaultToolkit();
    private static final URL saveButton = ClassLoader.getSystemResource("buttons/saveButtonPixel4.png");
    private static final Image saveImage = toolkit.getImage(saveButton);
    private static final URL loadButton = ClassLoader.getSystemResource("buttons/loadButtonPixel4.png");
    private static final Image loadImage = toolkit.getImage(loadButton);

    public static void render(Graphics graphics) {

        graphics.drawImage(
                saveImage,
                (Game.WIDTH / 2) - (saveImage.getWidth(null) / 2),
                (Game.HEIGHT / 2) - 70,
                null
        );

        graphics.drawImage(
                loadImage,
                (Game.WIDTH / 2) - (loadImage.getWidth(null) / 2),
                (Game.HEIGHT / 2) + 35,
                null
        );
    }
}
