package com.game.ainsley.gameobjects.enemy.enemies;

import com.game.ainsley.gameobjects.ID;
import com.game.ainsley.gameobjects.enemy.Enemy;
import com.game.ainsley.handler.CollisionHandler;
import com.game.ainsley.player.Player;

import java.awt.*;
import java.net.URL;

public class Sephtis extends Enemy {

    Toolkit toolkit = Toolkit.getDefaultToolkit();

    URL sephtisLeft = ClassLoader.getSystemResource("enemies/SephtisL.gif");
    Image sephtisLeftImage = toolkit.getImage(sephtisLeft);

    CollisionHandler collisionHandler = new CollisionHandler(164, 50);

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
