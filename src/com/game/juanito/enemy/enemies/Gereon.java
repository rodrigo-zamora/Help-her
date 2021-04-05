package com.game.juanito.enemy.enemies;

import com.game.juanito.enemy.Enemy;
import com.game.juanito.main.Game;
import com.game.juanito.main.ID;

import java.awt.*;
import java.net.URL;

public class Gereon extends Enemy {

    URL gereonLeft = ClassLoader.getSystemResource("enemies/GereonL.gif");
    URL gereonIdle = ClassLoader.getSystemResource("enemies/GereonIdle.gif");
    Image gereonLeftImage = Toolkit.getDefaultToolkit().getImage(gereonLeft);
    Image gereonIdleImage = Toolkit.getDefaultToolkit().getImage(gereonIdle);

    /**
     * @param x
     * @param y
     * @param id
     */
    public Gereon(int x, int y, ID id) {
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
                getImage(getID(), Game.isMovingLeft, Game.isMovingRight),
                getX(),
                getY(),
                null
        );
    }

    private Image getImage(ID id, boolean isMovingLeft, boolean isMovingRight) {
        if (isMovingLeft || isMovingRight) {
            return gereonLeftImage;
        } else return gereonIdleImage;
    }
}
