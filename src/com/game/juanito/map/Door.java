package com.game.juanito.map;

import java.awt.*;
import java.net.URL;

public class Door {

    private boolean shouldRender;
    private int x;

    URL doorImage = ClassLoader.getSystemResource("map/door.png");

    /**
     *
     */
    public Door(){
        shouldRender = false;
    }

    /**
     *
     * @return
     */
    public int getX() {
        return x;
    }

    /**
     *
     * @param x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     *
     * @param iterations
     */
    public void tick(int iterations, boolean isMoving){
        if(iterations == 10){
            shouldRender = true;
        }
        if(shouldRender && isMoving){
            x += 5;
        }
    }

    /**
     *
     * @param graphics
     */
    public void render(Graphics graphics){
        if(shouldRender){
            graphics.drawImage(
                    Toolkit.getDefaultToolkit().getImage(doorImage),
                    x,
                    430,
                    null
            );
        }
    }
}
