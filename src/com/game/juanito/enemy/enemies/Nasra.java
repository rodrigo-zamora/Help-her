package com.game.juanito.enemy.enemies;

import com.game.juanito.enemy.Enemy;
import com.game.juanito.main.Game;
import com.game.juanito.main.ID;

import java.awt.*;
import java.net.URL;

public class Nasra extends Enemy {

    Toolkit toolkit = Toolkit.getDefaultToolkit();

    URL nasraLeft = ClassLoader.getSystemResource("enemies/NasraL.gif");
    Image nasraLeftImage = toolkit.getImage(nasraLeft);
    URL nasraIdle = ClassLoader.getSystemResource("enemies/NasraIdle.gif");
    Image nasraIdleImage = toolkit.getImage(nasraIdle);

    /**
     * @param x
     * @param y
     * @param id
     */
    public Nasra(int x, int y, ID id) {
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
            return nasraLeftImage;
        } else return nasraIdleImage;
    }
}
