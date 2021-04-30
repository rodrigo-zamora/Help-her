package com.game.ainsley.screen.screens;

import java.awt.*;
import java.net.URL;

public class Loading {

    private static final Toolkit toolkit = Toolkit.getDefaultToolkit();
    private static final URL background = ClassLoader.getSystemResource("Screens/LoadingScreen2.gif");
    private static final Image backgroundImage = toolkit.getImage(background);

    public static void render(Graphics graphics) {

        graphics.drawImage(
                backgroundImage,
                0,
                0,
                null
        );
    }
}
