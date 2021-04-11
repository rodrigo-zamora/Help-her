package com.game.juanito.enemy.enemies;

import com.game.juanito.enemy.Enemy;
import com.game.juanito.handler.CollisionHandler;
import com.game.juanito.main.ID;
import com.game.juanito.map.Chunk;

import java.awt.*;
import java.net.URL;

public class Gereon extends Enemy {

    Toolkit toolkit = Toolkit.getDefaultToolkit();

    URL gereonLeft = ClassLoader.getSystemResource("enemies/GereonL.gif");
    Image gereonLeftImage = toolkit.getImage(gereonLeft);

    CollisionHandler collisionHandler = new CollisionHandler(70, 128);

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
        collisionHandler.setY(y);
        if (Chunk.getSpeed() != 0)
            x += -Chunk.getSpeed();
        collisionHandler.setRectangle(new Rectangle(collisionHandler.getX(), collisionHandler.getY(), collisionHandler.getWidth(), collisionHandler.getHeight()));
        return x >= -150;
    }

    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(
                gereonLeftImage,
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
            System.out.println("Colision from Gereon!");
        }
    }
}
