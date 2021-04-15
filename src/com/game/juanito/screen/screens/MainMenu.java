package com.game.juanito.screen.screens;

import com.game.juanito.main.Game;

import java.awt.*;
import java.net.URL;

public class MainMenu {

    private static final Toolkit toolkit = Toolkit.getDefaultToolkit();
    private static final URL background = ClassLoader.getSystemResource("Menu/Intro.png");
    private static final Image backgroundImage = toolkit.getImage(background);
    private static final URL start = ClassLoader.getSystemResource("Menu/Empezar.png");
    private static final Image startImage = toolkit.getImage(start);
    private static final URL credits = ClassLoader.getSystemResource("Menu/Creditos.png");
    private static final Image creditsImage = toolkit.getImage(credits);

    public static void render(Graphics graphics) {

        graphics.drawImage(
                backgroundImage,
                0,
                0,
                null
        );

        graphics.drawImage(
                startImage,
                100,
                200,
                null
        );

        graphics.drawImage(
                creditsImage,
                100,
                300,
                null
        );

        // FPS
        graphics.setColor(Color.WHITE);
        graphics.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        graphics.drawString(
                "FPS: " + Game.FPS,
                15,
                20
        );
    }

}
