package com.game.juanito.enemy.enemies;

import com.game.juanito.enemy.Enemy;
import com.game.juanito.main.Game;
import com.game.juanito.main.ID;

import java.awt.*;
import java.net.URL;

public class Deidamia extends Enemy {

    URL deidamiaLeft = ClassLoader.getSystemResource("enemies/DeidamiaL.gif");
    URL deidamiaIdle = ClassLoader.getSystemResource("enemies/DeidamiaIdle.gif");
    Image deidamiaLeftImage = Toolkit.getDefaultToolkit().getImage(deidamiaLeft);
    Image deidamiaIdleImage = Toolkit.getDefaultToolkit().getImage(deidamiaIdle);

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
                getImage(getID(), Game.isMovingLeft, Game.isMovingRight),
                getX(),
                getY(),
                null
        );
    }

    private Image getImage(ID id, boolean isMovingLeft, boolean isMovingRight) {
        if (isMovingLeft || isMovingRight) {
            return deidamiaLeftImage;
        } else return deidamiaIdleImage;
    }
}
