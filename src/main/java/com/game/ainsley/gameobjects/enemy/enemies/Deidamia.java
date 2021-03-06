package com.game.ainsley.gameobjects.enemy.enemies;

import com.game.ainsley.gameobjects.ID;
import com.game.ainsley.gameobjects.enemy.Enemy;
import com.game.ainsley.handler.CollisionHandler;
import com.game.ainsley.main.Game;
import lib.ainsley.FileManager;
import lib.ainsley.Sound;

import java.awt.*;

public class Deidamia extends Enemy {

    Image deidamiaLeftImage = FileManager.loadImage("enemies/DeidamiaL.gif");
    Image deidamiaIdleImage = FileManager.loadImage("enemies/DeidamiaIdle.png");

    CollisionHandler collisionHandler = new CollisionHandler(150, 40);

    Sound deidamia = new Sound("sounds/enemies/deidamia.mp3", Sound.EFFECT);

    /**
     * Constructor for Deidamia class
     *
     * @param x
     * @param y
     * @param id
     */
    public Deidamia(int x, int y, ID id) {
        super(x, y, id);
        deidamia.playSound(1, false);
    }

    @Override
    public boolean tick() {
        collisionHandler.setX(x);
        collisionHandler.setY(y + 57);
        moveEnemy();
        collisionHandler.updateRectangle();
        return x >= -200;
    }

    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(
                getImage(Game.isMoving()),
                getX(),
                getY(),
                null
        );
    }

    @Override
    public void collision() {
        if (game.getPlayer().getCollisionHandler().getRectangle().intersects(collisionHandler.getRectangle())) {
            collisionIntersect();
        }
    }

    /**
     * Method to get the image of Deidamia depending in the player movement
     *
     * @param isMoving receives a boolean
     * @return an Image
     */
    private Image getImage(boolean isMoving) {
        if (isMoving) {
            return deidamiaLeftImage;
        } else return deidamiaIdleImage;
    }
}
