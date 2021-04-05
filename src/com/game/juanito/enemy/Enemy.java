package com.game.juanito.enemy;

import com.game.juanito.main.GameObject;
import com.game.juanito.main.ID;

public abstract class Enemy extends GameObject {

    /**
     * @param x
     * @param y
     * @param id
     */
    public Enemy(int x, int y, ID id) {
        super(x, y, id);
    }
}
