package com.game.ainsley.handler;

import com.game.ainsley.main.GameObject;
import com.game.ainsley.main.ID;

import java.awt.*;
import java.util.LinkedList;

public class GameObjectHandler {

    LinkedList<GameObject> object = new LinkedList<GameObject>();

    public void tick(Rectangle playerRectangle) {
        for (GameObject tempObject : object) {
            if (!tempObject.tick()) {
                removeObject(tempObject);
            }
            if (tempObject.getID() != ID.Player) {
                tempObject.collision(playerRectangle);
            }
        }
    }

    public void render(Graphics graphics) {
        for (GameObject tempObject : object) {
            tempObject.render(graphics);
        }
    }

    public void addObject(GameObject object) {
        this.object.addFirst(object);
    }

    public void removeObject(GameObject object) {
        this.object.remove(object);
    }
}
