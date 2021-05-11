package com.game.ainsley.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.game.ainsley.gameobjects.GameObject;
import com.game.ainsley.gameobjects.ID;
import com.game.ainsley.handler.GameObjectHandler;
import com.game.ainsley.map.Chunk;
import com.game.ainsley.map.Door;
import com.game.ainsley.player.Player;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.util.LinkedList;

public class Save {

    @SuppressWarnings("unchecked")
    public static void saveGame() {

        ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        JSONArray dataArray = new JSONArray();

        JSONObject playerObject = new JSONObject();
        JSONObject player = new JSONObject();
        player.put("health", Player.getHealth());
        player.put("shouldRender", Player.shouldRender());
        player.put("notesCollected", Player.getInventory().getNotesCollected());
        player.put("readingNote", Player.getInventory().getReadingNote());
        player.put("y", Player.getyS());

        JSONObject playerCollisionHandler = new JSONObject();
        playerCollisionHandler.put("x", Player.getCollisionHandler().getX());
        playerCollisionHandler.put("y", Player.getCollisionHandler().getY());
        player.put("collisionHandler", playerCollisionHandler);

        playerObject.put("player", player);

        dataArray.put(playerObject);

        JSONObject chunkObject = new JSONObject();
        JSONObject chunk = new JSONObject();
        chunk.put("x", Chunk.getX());
        chunk.put("currentChunk", Chunk.getCurrentChunk());
        chunk.put("iterations", Chunk.getIterations());
        chunkObject.put("chunk", chunk);

        dataArray.put(chunkObject);

        JSONObject doorObject = new JSONObject();
        JSONObject door = new JSONObject();
        door.put("x", Door.getX());
        door.put("y", Door.getY());
        door.put("shouldRender", Door.shouldRender());

        JSONObject doorCollisionHandler = new JSONObject();
        doorCollisionHandler.put("x", Door.getCollisionHandler().getX());
        doorCollisionHandler.put("y", Door.getCollisionHandler().getY());
        door.put("collisionHandler", doorCollisionHandler);
        door.put("collisionHandler", doorCollisionHandler);

        doorObject.put("door", door);

        dataArray.put(doorObject);

        JSONObject inventoryObject = new JSONObject();
        JSONObject inventory = new JSONObject();

        for (int i = 0; i < 9; i++) {
            JSONObject inventoryNote = new JSONObject();
            inventoryNote.put("isOpen", Player.getInventory().getNote(i).isOpen());
            inventoryNote.put("hasBeenFound", Player.getInventory().getNote(i).hasBeenFound());
            inventory.put(String.valueOf(i), inventoryNote);
        }

        inventoryObject.put("inventory", inventory);

        dataArray.put(inventoryObject);

        JSONObject enemyObject = new JSONObject();
        JSONObject enemy = new JSONObject();

        // Create a temp linked list to avoid ConcurrentModificationException
        LinkedList<GameObject> temp;
        temp = (LinkedList<GameObject>) GameObjectHandler.getObject().clone();
        for (GameObject tempObject : temp) {
            if (temp.size() != 1) {
                if (tempObject.getID() != ID.Player) {
                    enemyObject.put("id", tempObject.getID());
                    enemyObject.put("x", tempObject.getX());
                    enemyObject.put("y", tempObject.getY());
                    enemy.put("enemy", enemyObject);
                }
            } else {
                enemyObject.put("id", "null");
                enemyObject.put("x", "null");
                enemyObject.put("y", "null");
                enemy.put("enemy", enemyObject);
            }
        }

        dataArray.put(enemy);

        try (FileWriter file = new FileWriter("data.json")) {
            mapper.writeValue(file, dataArray.toList());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
