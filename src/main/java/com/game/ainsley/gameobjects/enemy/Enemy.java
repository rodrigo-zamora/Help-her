package com.game.ainsley.gameobjects.enemy;

import com.game.ainsley.gameobjects.GameObject;
import com.game.ainsley.gameobjects.ID;
import com.game.ainsley.main.Game;

public abstract class Enemy extends GameObject {

    protected Game game;

    /**
     * @param x
     * @param y
     * @param id
     */
    public Enemy(int x, int y, ID id) {
        super(x, y, id);
        game = Game.getInstance();
    }

    protected void moveEnemy() {
        if (game.getMapManager().getSpeed() != 0)
            x += -game.getMapManager().getSpeed();
    }

    protected void collisionIntersect() {
        // Move enemy out of player's view
        x = -200;

        game.getPlayer().damage();
    }
}
