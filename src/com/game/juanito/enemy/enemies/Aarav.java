package com.game.juanito.enemy.enemies;

import com.game.juanito.enemy.Enemy;
import com.game.juanito.main.Game;
import com.game.juanito.main.ID;

import java.awt.*;
import java.net.URL;

public class Aarav extends Enemy {

    URL aarav = ClassLoader.getSystemResource("enemies/Aarav.gif");
    URL aaravIdle = ClassLoader.getSystemResource("enemies/AaravIdle.gif");
    Image aaravLeftImage = Toolkit.getDefaultToolkit().getImage(aarav);
    Image aaravIdleImage = Toolkit.getDefaultToolkit().getImage(aaravIdle);

    /**
     * @param x
     * @param y
     * @param id
     */
    public Aarav(int x, int y, ID id) {
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
            return aaravLeftImage;
        } else return aaravIdleImage;
    }
}
