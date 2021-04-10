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
    URL deidamiaIdle = ClassLoader.getSystemResource("enemies/DeidamiaIdle.png");
    Image deidamiaIdleImage = toolkit.getImage(deidamiaIdle);

    /**
     * Constructor for Deidamia class
     *
     * @param x
     * @param y
     * @param id
     */
    public Deidamia(int x, int y, ID id) {
        super(x, y, id);
        setWidth(149);
        setHeight(100);
    }

    @Override
    public void collision() {

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
                getImage(Game.isMoving),
                getX(),
                getY(),
                null
        );
    }

    /**
     * Method to get the image of Deidamia depending in the player movement
     *
     * @param isMoving  receives a boolean
     * @return an Image
     */
    private Image getImage(boolean isMoving) {
        if (isMoving) {
            return deidamiaLeftImage;
        } else return deidamiaIdleImage;
    }
}
