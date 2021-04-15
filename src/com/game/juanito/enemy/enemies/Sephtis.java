package com.game.juanito.enemy.enemies;

import com.game.juanito.enemy.Enemy;
import com.game.juanito.handler.CollisionHandler;
import com.game.juanito.main.ID;
import com.game.juanito.map.Chunk;
import com.game.juanito.player.Player;

import java.awt.*;
import java.net.URL;

public class Sephtis extends Enemy {

    Toolkit toolkit = Toolkit.getDefaultToolkit();

    URL sephtisLeft = ClassLoader.getSystemResource("enemies/SephtisL.gif");
    Image sephtisLeftImage = toolkit.getImage(sephtisLeft);

    CollisionHandler collisionHandler = new CollisionHandler(164, 75);

    private boolean shouldRender;

    /**
     * Constructor for Sephtis class
     *
     * @param x
     * @param y
     * @param id
     */
    public Sephtis(int x, int y, ID id) {
        super(x, y, id);
        collisionHandler.setY(y + 85);
        collisionHandler.setX(x);
        shouldRender = true;
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
        collisionCheck(collisionHandler);
        return x >= -150;
    }

    @Override
    public void render(Graphics graphics) {
        if (shouldRender) {
            graphics.drawImage(
                    sephtisLeftImage,
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
