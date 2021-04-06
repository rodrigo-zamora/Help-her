package com.game.juanito.enemy.enemies;

import com.game.juanito.enemy.Enemy;
import com.game.juanito.main.Game;
import com.game.juanito.main.ID;

import java.awt.*;
import java.net.URL;

public class Deidamia extends Enemy {

    Toolkit toolkit = Toolkit.getDefaultToolkit();

    URL deidamiaLeft = ClassLoader.getSystemResource("enemies/DeidamiaL.gif");
    Image deidamiaLeftImage = toolkit.getImage(deidamiaLeft);
    URL deidamiaIdle = ClassLoader.getSystemResource("enemies/DeidamiaIdle.gif");
    Image deidamiaIdleImage = toolkit.getImage(deidamiaIdle);

    /**
     * @param x
     * @param y
     * @param id
     */
    public Deidamia(int x, int y, ID id) {
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
            return deidamiaLeftImage;
        } else return deidamiaIdleImage;
    }
}
