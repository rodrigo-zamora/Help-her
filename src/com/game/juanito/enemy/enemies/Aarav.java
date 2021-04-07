package com.game.juanito.enemy.enemies;

import com.game.juanito.enemy.Enemy;
import com.game.juanito.main.Game;
import com.game.juanito.main.ID;

import java.awt.*;
import java.net.URL;

public class Aarav extends Enemy {

    Toolkit toolkit = Toolkit.getDefaultToolkit();

    URL aarav = ClassLoader.getSystemResource("enemies/Aarav.gif");
    Image aaravLeftImage = toolkit.getImage(aarav);
    URL aaravIdle = ClassLoader.getSystemResource("enemies/AaravIdle.png");
    Image aaravIdleImage = toolkit.getImage(aaravIdle);

    /**
     * Constructor for Aarav class
     *
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
        return x >= -150;
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

    /**
     * Method to get the image of Aarav depending in the player movement
     *
     * @param isMovingLeft  receives a boolean
     * @param isMovingRight receives a boolean
     * @return              an Image
     */
    private Image getImage(boolean isMovingLeft, boolean isMovingRight) {
        if (isMovingLeft || isMovingRight) {
            return aaravLeftImage;
        } else return aaravIdleImage;
    }
}
