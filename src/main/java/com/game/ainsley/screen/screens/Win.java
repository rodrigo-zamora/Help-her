package com.game.ainsley.screen.screens;

import lib.ainsley.FileManager;

import java.awt.*;

public class Win {

    private static final Image backgroundImage = FileManager.loadImage("screens/winScreen.gif");

    public static void render(Graphics graphics) {
        graphics.drawImage(
                backgroundImage,
                0,
                0,
                null
        );
    }
}
