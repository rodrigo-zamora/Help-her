package com.game.juanito.map;

import java.awt.*;
import java.net.URL;

public class Door {

    Toolkit toolkit = Toolkit.getDefaultToolkit();

    URL door = ClassLoader.getSystemResource("map/door.png");
    Image doorImage = toolkit.getImage(door);

    private boolean shouldRender;
    private int x = 1500;

    /**
     *
     */
    public Door() {
        shouldRender = false;
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
     * @param iterations
     */
    public void tick(int iterations, boolean isMovingRight, boolean isMovingLeft) {
        if (iterations == 5) {
            shouldRender = true;
        }
        if (shouldRender) {
            if (isMovingRight) {
                x -= 5;
            } else if (isMovingLeft) {
                x += 5;
            }
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
                    260,
                    null
            );
        }
    }
}
