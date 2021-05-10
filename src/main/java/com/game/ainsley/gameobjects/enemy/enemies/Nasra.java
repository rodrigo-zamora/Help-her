package com.game.ainsley.gameobjects.enemy.enemies;

import com.game.ainsley.gameobjects.ID;
import com.game.ainsley.gameobjects.enemy.Enemy;
import com.game.ainsley.handler.CollisionHandler;
import com.game.ainsley.player.Player;
import lib.ainsley.FileManager;
import lib.ainsley.Sound;

import java.awt.*;

public class Nasra extends Enemy {

    Image nasraLeftImage = FileManager.loadImage("enemies/NasraL.gif");

    CollisionHandler collisionHandler = new CollisionHandler(62, 38);

    Sound nasra = new Sound("sounds/enemies/nasra.mp3", Sound.EFFECT);

    /**
     * Constructor for Nasra class
     *
     * @param x
     * @param y
     * @param id
     */
    public Nasra(int x, int y, ID id) {
        super(x, y, id);
        speedY = 3;
        nasra.playSound(0.5F, false);
    }

    @Override
    public boolean tick() {
        if (y <= 280) {
            speedY = 3;
        } else if (y >= 480) {
            speedY = -3;
        }
        y += speedY;
        collisionHandler.setX(x + 9);
        collisionHandler.setY(y + 41);
        moveEnemy();
        collisionHandler.updateRectangle();
        return x >= -200;
    }

    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(
                nasraLeftImage,
                getX(),
                getY(),
                null
        );
    }

    @Override
    public void collision() {
        if (Player.getCollisionHandler().getRectangle().intersects(collisionHandler.getRectangle())) {
            collisionIntersect();
        }
    }
}
