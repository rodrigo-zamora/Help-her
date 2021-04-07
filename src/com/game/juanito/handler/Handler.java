package com.game.juanito.handler;

import com.game.juanito.main.GameObject;

import java.awt.*;
import java.util.LinkedList;

public class Handler {

    LinkedList<GameObject> object = new LinkedList<GameObject>();

    public void tick() {
        for (GameObject tempObject : object) {
            if(!tempObject.tick()){
                removeObject(tempObject);
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
