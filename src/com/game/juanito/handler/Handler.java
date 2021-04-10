package com.game.juanito.handler;

import com.game.juanito.main.GameObject;
import com.game.juanito.main.ID;

import java.awt.*;
import java.util.LinkedList;

public class Handler {

    LinkedList<GameObject> object = new LinkedList<GameObject>();

    public void tick(Rectangle playerRectangle) {
        for (GameObject tempObject : object) {
            if(!tempObject.tick()){
                removeObject(tempObject);
            }
            if(tempObject.getID() != ID.Player){
                System.out.println("Enemy rectangle: " + new Rectangle(tempObject.getX(), tempObject.getY(), tempObject.getWidth(), tempObject.getHeight()));
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
