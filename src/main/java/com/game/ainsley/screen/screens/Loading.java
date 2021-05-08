package com.game.ainsley.screen.screens;

import lib.ainsley.FileManager;

import java.awt.*;

public class Loading {

    private static final Image backgroundImage = FileManager.loadImage("Screens/LoadingScreen2.gif");

    public static void render(Graphics graphics) {

        graphics.drawImage(
                backgroundImage,
                0,
                0,
                null
        );
    }
}
