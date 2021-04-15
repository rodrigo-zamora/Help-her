package com.game.juanito.screen;

import com.game.juanito.main.Game;

import java.awt.*;
import java.net.URL;

public class MainMenu {

    Toolkit toolkit = Toolkit.getDefaultToolkit();
    URL background = ClassLoader.getSystemResource("Menu/Intro.png");
    Image backgroundImage = toolkit.getImage(background);
    URL start = ClassLoader.getSystemResource("Menu/Empezar.png");
    Image startImage = toolkit.getImage(start);

    public void tick(){

    }

    public void render(Graphics graphics){

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
