package com.game.juanito.map;

import com.game.juanito.handler.CollisionHandler;

import java.awt.*;
import java.net.URL;

public class Door {

    Toolkit toolkit = Toolkit.getDefaultToolkit();

    URL door = ClassLoader.getSystemResource("map/door.png");
    Image doorImage = toolkit.getImage(door);

    private boolean shouldRender;
    private int x = 1500, y;

    // Replace with actual door width and height
    CollisionHandler collisionHandler = new CollisionHandler(50, 50);

    /**
     *
     */
    public Door() {
        shouldRender = false;
        collisionHandler.setX(x);
        collisionHandler.setY(y);
    }

    /**
     * @return
     */
    public int getX() {
        return x;
    }

    /**
     * @param x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     *
     * @return
     */
    public int getY() {
        return y;
    }

    /**
     *
     * @param y
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     *
     */
    public void tick() {
        if (Chunk.getIterations() == 2) {
            shouldRender = true;
        }
        if (shouldRender) {
            collisionHandler.setX(x);
            collisionHandler.setRectangle(new Rectangle(collisionHandler.getX(), collisionHandler.getY(), collisionHandler.getWidth(), collisionHandler.getHeight()));
            if (Chunk.getSpeed() > 0) {
                x -= 5;
            }
        }
        if (x == -300) {
            x = 1500;
            Chunk.setIterations(0);
            shouldRender = false;
        }
    }

    /**
     * @param graphics
     */
    public void render(Graphics graphics) {
        if (shouldRender) {
            graphics.drawImage(
                    doorImage,
                    x,
                    220,
                    null
            );
            graphics.setColor(Color.WHITE);
            graphics.setFont(new Font("TimesRoman", Font.PLAIN, 20));
            graphics.drawString(
                    "Presiona 'E' para entrar",
                    x - 50,
                    200
            );
        }
    }
}
