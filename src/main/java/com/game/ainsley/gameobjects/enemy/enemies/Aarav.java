package com.game.ainsley.gameobjects.enemy.enemies;

import com.game.ainsley.gameobjects.ID;
import com.game.ainsley.gameobjects.enemy.Enemy;
import com.game.ainsley.handler.CollisionHandler;
import com.game.ainsley.main.Game;
import com.game.ainsley.player.Player;
import lib.ainsley.FileManager;
import lib.ainsley.Sound;

import java.awt.*;

public class Aarav extends Enemy {

    Image aaravImage = FileManager.loadImage("enemies/Aarav.gif");

    CollisionHandler collisionHandler = new CollisionHandler(210, 70);

    Sound aarav = new Sound("sounds/enemies/aarav.mp3", Sound.EFFECT);

    /**
     * Constructor for Aarav class
     *
     * @param x
     * @param y
     * @param id
     */
    public Aarav(int x, int y, ID id) {
        super(x, y, id);
        aarav.playSound(1, false);
    }


    @Override
    public boolean tick() {
        collisionHandler.setX(x);
        collisionHandler.setY(y + 79);
        moveEnemy();
        collisionHandler.updateRectangle();
        return x >= -200;
    }

    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(
                aaravImage,
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
