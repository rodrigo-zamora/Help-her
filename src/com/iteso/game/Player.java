package com.iteso.game;

import java.awt.*;

/**
 *
 */
public class Player extends GameObject{

    /**
     *
     * @param x
     * @param y
     * @param id
     */
    public Player(int x, int y, ID id) {
        super(x, y, id);
    }

    /**
     *
     */
    @Override
    public void tick() {

    }

    /**
     *
     * @param graphics
     */
    @Override
    public void render(Graphics graphics) {
        graphics.setColor(Color.MAGENTA);
        graphics.fillRect(x, y, 32, 32);
    }
}
