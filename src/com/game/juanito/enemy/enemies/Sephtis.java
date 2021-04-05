package com.game.juanito.enemy.enemies;

import com.game.juanito.enemy.Enemy;
import com.game.juanito.main.Game;
import com.game.juanito.main.ID;

import java.awt.*;
import java.net.URL;

public class Sephtis extends Enemy {

    URL sephtisLeft = ClassLoader.getSystemResource("enemies/SephtisL.gif");
    URL sephtisIdle = ClassLoader.getSystemResource("enemies/SephtisIdle.gif");
    Image sephtisLeftImage = Toolkit.getDefaultToolkit().getImage(sephtisLeft);
    Image sephtisIdleImage = Toolkit.getDefaultToolkit().getImage(sephtisIdle);

    /**
     * @param x
     * @param y
     * @param id
     */
    public Sephtis(int x, int y, ID id) {
        super(x, y, id);
    }

    @Override
    public boolean tick() {
        if (Game.isMovingRight) {
            x += speedX;
        } else if (Game.isMovingLeft) {
            x -= speedX;
        }
        return x >= -100;
    }

    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(
                getImage(Game.isMovingLeft, Game.isMovingRight),
                getX(),
                getY(),
                null
        );
    }

    private Image getImage(boolean isMovingLeft, boolean isMovingRight) {
        if (isMovingLeft || isMovingRight) {
            return sephtisLeftImage;
        } else return sephtisIdleImage;
    }
}
