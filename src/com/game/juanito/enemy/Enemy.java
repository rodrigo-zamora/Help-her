package com.game.juanito.enemy;

import com.game.juanito.handler.CollisionHandler;
import com.game.juanito.main.GameObject;
import com.game.juanito.main.ID;
import com.game.juanito.map.Chunk;
import com.game.juanito.player.Player;

import java.awt.*;

public abstract class Enemy extends GameObject {

    /**
     * @param x
     * @param y
     * @param id
     */
    public Enemy(int x, int y, ID id) {
        super(x, y, id);
    }

    protected void collisionCheck(CollisionHandler collisionHandler) {
        collisionHandler.setX(x);
        collisionHandler.setY(y);
        if (Chunk.getSpeed() != 0)
            x += -Chunk.getSpeed();
        collisionHandler.setRectangle(new Rectangle(collisionHandler.getX(), collisionHandler.getY(), collisionHandler.getWidth(), collisionHandler.getHeight()));
    }

    protected void collisionIntersect() {
        x = -150;
        Player.setHealth(Player.getHealth() - 1);
        Player.damageAnimation(true);
    }
}
