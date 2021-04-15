package com.game.juanito.enemy.enemies;

import com.game.juanito.enemy.Enemy;
import com.game.juanito.handler.CollisionHandler;
import com.game.juanito.main.ID;
import com.game.juanito.map.Chunk;
import com.game.juanito.player.Player;

import java.awt.*;
import java.net.URL;

public class Nasra extends Enemy {

    Toolkit toolkit = Toolkit.getDefaultToolkit();

    URL nasraLeft = ClassLoader.getSystemResource("enemies/NasraL.gif");
    Image nasraLeftImage = toolkit.getImage(nasraLeft);

    CollisionHandler collisionHandler = new CollisionHandler(70, 64);

    private boolean shouldRender;

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
        shouldRender = true;
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
        if (shouldRender) {
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
    }

    @Override
    public void collision(Rectangle rectangle) {
        if (rectangle.intersects(collisionHandler.getRectangle())) {
            x = -150;
            shouldRender = false;
            Player.setHealth(Player.getHealth() - 1);
        }
    }
}
