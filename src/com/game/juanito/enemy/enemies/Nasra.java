package com.game.juanito.enemy.enemies;

import com.game.juanito.enemy.Enemy;
import com.game.juanito.handler.CollisionHandler;
import com.game.juanito.main.ID;

import java.awt.*;
import java.net.URL;

public class Nasra extends Enemy {

    Toolkit toolkit = Toolkit.getDefaultToolkit();

    URL nasraLeft = ClassLoader.getSystemResource("enemies/NasraL.gif");
    Image nasraLeftImage = toolkit.getImage(nasraLeft);

    CollisionHandler collisionHandler = new CollisionHandler(70, 64);

    /**
     * Constructor for Nasra class
     *
     * @param x
     * @param y
     * @param id
     */
    public Nasra(int x, int y, ID id) {
        super(x, y, id);
        collisionHandler.setY(y + 20);
        collisionHandler.setX(x);
        speedY = 3;
    }

    @Override
    public boolean tick() {
        if (y <= 280) {
            speedY = 3;
        } else if (y >= 480) {
            speedY = -3;
        }
        y += speedY;
        collisionCheck(collisionHandler);
        return x >= -150;
    }

    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(
                nasraLeftImage,
                getX(),
                getY(),
                null
        );
        graphics.setColor(Color.RED);
        graphics.drawRect(
                collisionHandler.getX(),
                collisionHandler.getY(),
                collisionHandler.getWidth(),
                collisionHandler.getHeight());
    }

    @Override
    public void collision(Rectangle rectangle) {
        if (rectangle.intersects(collisionHandler.getRectangle())) {
            collisionIntersect();
        }
    }
}
