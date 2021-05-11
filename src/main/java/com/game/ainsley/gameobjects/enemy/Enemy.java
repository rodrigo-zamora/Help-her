package com.game.ainsley.gameobjects.enemy;

import com.game.ainsley.gameobjects.GameObject;
import com.game.ainsley.gameobjects.ID;
import com.game.ainsley.map.Chunk;
import com.game.ainsley.player.Player;

public abstract class Enemy extends GameObject {

    /**
     * @param x
     * @param y
     * @param id
     */
    public Enemy(int x, int y, ID id) {
        super(x, y, id);
    }

    protected void moveEnemy() {
        if (Chunk.getSpeed() != 0)
            x += -Chunk.getSpeed();
    }

    protected void collisionIntersect() {
        // Move enemy out of player's view
        x = -200;

        Player.damage();
    }
}
