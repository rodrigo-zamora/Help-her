package com.game.ainsley.enemy;

import com.game.ainsley.main.GameObject;
import com.game.ainsley.main.ID;
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

        Player.setHealth(Player.getHealth() - 1);
        Player.damageAnimation(true);
    }
}
