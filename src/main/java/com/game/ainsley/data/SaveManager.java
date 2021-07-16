package com.game.ainsley.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.game.ainsley.gameobjects.GameObject;
import com.game.ainsley.gameobjects.ID;
import com.game.ainsley.main.Game;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.util.LinkedList;

public class SaveManager {

    private final Game game;

    public SaveManager() {
        game = Game.getInstance();
    }

    public void saveGame() {

        ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        JSONArray dataArray = new JSONArray();

        JSONObject playerObject = new JSONObject();
        JSONObject player = new JSONObject();
        player.put("health", game.getPlayer().getHealth());
        player.put("shouldRender", game.getPlayer().shouldRender());
        player.put("notesCollected", game.getPlayer().getInventory().getNotesCollected());
        player.put("readingNote", game.getPlayer().getInventory().getReadingNote());
        player.put("y", game.getPlayer().getyS());

        JSONObject playerCollisionHandler = new JSONObject();
        playerCollisionHandler.put("x", game.getPlayer().getCollisionHandler().getX());
        playerCollisionHandler.put("y", game.getPlayer().getCollisionHandler().getY());
        player.put("collisionHandler", playerCollisionHandler);

        playerObject.put("player", player);

        dataArray.put(playerObject);

        JSONObject chunkObject = new JSONObject();
        JSONObject chunk = new JSONObject();
        chunk.put("x", game.getMapManager().getX());
        chunk.put("currentChunk", game.getMapManager().getCurrentChunk());
        chunk.put("iterations", game.getMapManager().getIterations());
        chunkObject.put("chunk", chunk);

        dataArray.put(chunkObject);

        JSONObject doorObject = new JSONObject();
        JSONObject door = new JSONObject();
        door.put("x", game.getDoor().getX());
        door.put("y", game.getDoor().getY());
        door.put("shouldRender", game.getDoor().shouldRender());

        JSONObject doorCollisionHandler = new JSONObject();
        doorCollisionHandler.put("x", game.getDoor().getCollisionHandler().getX());
        doorCollisionHandler.put("y", game.getDoor().getCollisionHandler().getY());
        door.put("collisionHandler", doorCollisionHandler);
        door.put("collisionHandler", doorCollisionHandler);

        doorObject.put("door", door);

        dataArray.put(doorObject);

        JSONObject inventoryObject = new JSONObject();
        JSONObject inventory = new JSONObject();

        for (int i = 0; i < 9; i++) {
            JSONObject inventoryNote = new JSONObject();
            inventoryNote.put("isOpen", game.getPlayer().getInventory().getNote(i).isOpen());
            inventoryNote.put("hasBeenFound", game.getPlayer().getInventory().getNote(i).hasBeenFound());
            inventory.put(String.valueOf(i), inventoryNote);
        }

        inventoryObject.put("inventory", inventory);

        dataArray.put(inventoryObject);

        JSONObject enemyObject = new JSONObject();
        JSONObject enemy = new JSONObject();

        // Create a temp linked list to avoid ConcurrentModificationException
        LinkedList<GameObject> temp;
        temp = (LinkedList<GameObject>) game.getGameObjectHandler().getObject().clone();
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
