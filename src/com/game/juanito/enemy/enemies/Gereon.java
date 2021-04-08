package com.game.juanito.enemy.enemies;

import com.game.juanito.enemy.Enemy;
import com.game.juanito.main.Game;
import com.game.juanito.main.ID;

import java.awt.*;
import java.net.URL;

public class Gereon extends Enemy {

    Toolkit toolkit = Toolkit.getDefaultToolkit();

    URL gereonLeft = ClassLoader.getSystemResource("enemies/GereonL.gif");
    Image gereonLeftImage = toolkit.getImage(gereonLeft);

    /**
     * Constructor for Gereon class
     *
     * @param x
     * @param y
     * @param id
     */
    public Gereon(int x, int y, ID id) {
        super(x, y, id);
    }

    @Override
    public boolean tick() {
        if (Game.isMoving)
            x += speedX;
        return x >= -150;
    }

    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(
                gereonLeftImage,
                getX(),
                getY(),
                null
        );
    }
}
