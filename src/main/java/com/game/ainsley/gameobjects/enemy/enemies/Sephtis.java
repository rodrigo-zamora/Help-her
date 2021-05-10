package com.game.ainsley.gameobjects.enemy.enemies;

import com.game.ainsley.gameobjects.ID;
import com.game.ainsley.gameobjects.enemy.Enemy;
import com.game.ainsley.handler.CollisionHandler;
import com.game.ainsley.player.Player;
import lib.ainsley.FileManager;
import lib.ainsley.Sound;

import java.awt.*;

public class Sephtis extends Enemy {

    Image sephtisLeftImage = FileManager.loadImage("enemies/SephtisL.gif");

    CollisionHandler collisionHandler = new CollisionHandler(164, 50);

    Sound sephtis = new Sound("sounds/enemies/sephtis.mp3", Sound.EFFECT);

    /**
     * Constructor for Sephtis class
     *
     * @param x
     * @param y
     * @param id
     */
    public Sephtis(int x, int y, ID id) {
        super(x, y, id);
        speedY = 1;
        sephtis.playSound(0.5F, false);
    }

    @Override
    public boolean tick() {
        if (y <= 200) {
            speedY = 1;
        } else if (y >= 400) {
            speedY = -1;
        }
        y += speedY;
        collisionHandler.setX(x);
        collisionHandler.setY(y + 108);
        moveEnemy();
        collisionHandler.updateRectangle();
        return x >= -200;
    }

    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(
                sephtisLeftImage,
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
