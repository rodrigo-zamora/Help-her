package com.game.ainsley.gameobjects.enemy.enemies;

import com.game.ainsley.gameobjects.ID;
import com.game.ainsley.gameobjects.enemy.Enemy;
import com.game.ainsley.handler.CollisionHandler;
import lib.ainsley.FileManager;
import lib.ainsley.Sound;

import java.awt.*;

public class Gereon extends Enemy {

    Image gereonLeftImage = FileManager.loadImage("enemies/GereonL.gif");

    CollisionHandler collisionHandler = new CollisionHandler(70, 28);

    Sound gereon = new Sound("sounds/enemies/gereon.mp3", Sound.EFFECT);

    /**
     * Constructor for Gereon class
     *
     * @param x
     * @param y
     * @param id
     */
    public Gereon(int x, int y, ID id) {
        super(x, y, id);
        gereon.playSound(0.8F, false);
    }

    @Override
    public boolean tick() {
        collisionHandler.setX(x);
        collisionHandler.setY(y + 100);
        moveEnemy();
        collisionHandler.updateRectangle();
        return x >= -200;
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

    @Override
    public void collision() {
        if (game.getPlayer().getCollisionHandler().getRectangle().intersects(collisionHandler.getRectangle())) {
            collisionIntersect();
        }
    }
}
