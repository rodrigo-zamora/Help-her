package com.game.ainsley.handler;

import com.game.ainsley.gameobjects.GameObject;
import com.game.ainsley.gameobjects.ID;
import com.game.ainsley.player.Player;

import java.awt.*;
import java.util.Iterator;
import java.util.LinkedList;

public class GameObjectHandler {

    private static LinkedList<GameObject> object = new LinkedList<>();

    public static void tick() {
        Iterator<GameObject> iterator = object.iterator();
        while(iterator.hasNext()) {
            GameObject tempObject = iterator.next();
            if (!tempObject.tick()) {
                iterator.remove();
            }
            if (tempObject.getID() != ID.Player) {
                tempObject.collision(Player.getCollisionHandler().getRectangle());
            }
        }
    }

    public static void render(Graphics graphics) {
        LinkedList<GameObject> temp;
        temp = (LinkedList<GameObject>) object.clone();
        Iterator<GameObject> iterator = temp.iterator();
        while(iterator.hasNext()) {
            iterator.next().render(graphics);
        }
    }

    public static void addObject(GameObject object) {
        GameObjectHandler.object.add(object);
    }

    public static LinkedList<GameObject> getObject() {
        return object;
    }

}
