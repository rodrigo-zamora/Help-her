package com.game.ainsley.handler;

import com.game.ainsley.gameobjects.GameObject;
import com.game.ainsley.gameobjects.ID;

import java.awt.*;
import java.util.Iterator;
import java.util.LinkedList;

public class GameObjectHandler {

    private final LinkedList<GameObject> object = new LinkedList<>();

    public void tick() {
        Iterator<GameObject> iterator = object.iterator();
        while (iterator.hasNext()) {
            GameObject tempObject = iterator.next();
            if (!tempObject.tick()) {
                iterator.remove();
            }
            if (tempObject.getID() != ID.Player) {
                tempObject.collision();
            }
        }
    }

    @SuppressWarnings("unchecked")
    public void render(Graphics graphics) {

        // Create a temp linked list to avoid ConcurrentModificationException
        LinkedList<GameObject> temp;
        temp = (LinkedList<GameObject>) object.clone();
        for (GameObject gameObject : temp) {
            gameObject.render(graphics);
        }
    }

    public void addObject(GameObject object) {
        this.object.add(object);
    }

    public LinkedList<GameObject> getObject() {
        return object;
    }

}
