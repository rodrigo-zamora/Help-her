package com.game.juanito.enemy;

import com.game.juanito.main.Game;
import com.game.juanito.main.GameObject;
import com.game.juanito.main.ID;

import java.awt.*;
import java.net.URL;

public abstract class Enemy extends GameObject {

    /**
     *
     * @param x
     * @param y
     * @param id
     */
    public Enemy(int x, int y, ID id) {
        super(x, y, id);
    }

    @Override
    public abstract boolean tick();


    @Override
    public abstract void render(Graphics graphics);
}
