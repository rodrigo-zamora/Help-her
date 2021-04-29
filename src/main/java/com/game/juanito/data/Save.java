package com.game.juanito.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.game.juanito.map.Chunk;
import com.game.juanito.map.Door;
import com.game.juanito.player.Player;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class Save {

    public static void saveGame() throws IOException {

        ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        JSONArray dataArray = new JSONArray();

        JSONObject playerObject = new JSONObject();
        JSONObject player = new JSONObject();
        player.put("health", Player.getHealth());
        player.put("damageAnimation", Player.getDamageAnimation());
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

        try (FileWriter file = new FileWriter("data.json")) {
            mapper.writeValue(file, dataArray.toList());
        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }
}
