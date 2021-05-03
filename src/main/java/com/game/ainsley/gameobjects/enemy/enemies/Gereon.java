package com.game.ainsley.gameobjects.enemy.enemies;

import com.game.ainsley.gameobjects.ID;
import com.game.ainsley.gameobjects.enemy.Enemy;
import com.game.ainsley.handler.CollisionHandler;
import com.game.ainsley.player.Player;

import java.awt.*;
import java.net.URL;

public class Gereon extends Enemy {

    Toolkit toolkit = Toolkit.getDefaultToolkit();

    URL gereonLeft = ClassLoader.getSystemResource("enemies/GereonL.gif");
    Image gereonLeftImage = toolkit.getImage(gereonLeft);

    CollisionHandler collisionHandler = new CollisionHandler(70, 28);

    /**
     * Constructor for Gereon class
     *
     * @param x
     * @param y
     * @param id
     */
    public Gereon(int x, int y, ID id) {
        super(x, y, id);
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
        if (Player.getCollisionHandler().getRectangle().intersects(collisionHandler.getRectangle())) {
            collisionIntersect();
        }
    }
}
