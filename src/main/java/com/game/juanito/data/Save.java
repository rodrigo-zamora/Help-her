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
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();

        JSONObject player = new JSONObject();
        player.put("health", Player.getHealth());
        player.put("notesCollected", Player.getInventory().getNotesCollected());
        player.put("readingNote", Player.getInventory().getReadingNote());

        JSONObject playerCollisionHandler = new JSONObject();
        playerCollisionHandler.put("x", Player.getCollisionHandler().getX());
        playerCollisionHandler.put("y", Player.getCollisionHandler().getY());
        player.put("collisionHandler", playerCollisionHandler);

        jsonObject.put("player", player);

        JSONObject chunk = new JSONObject();
        chunk.put("x", Chunk.getX());
        chunk.put("currentChunk", Chunk.getCurrentChunk());
        chunk.put("iterations", Chunk.getIterations());

        jsonObject.put("chunk", chunk);

        JSONObject door = new JSONObject();
        door.put("x", Door.getX());
        door.put("y", Door.getY());
        door.put("shouldRender", Door.shouldRender());

        JSONObject doorCollisionHandler = new JSONObject();
        doorCollisionHandler.put("x", Door.getCollisionHandler().getX());
        doorCollisionHandler.put("y", Door.getCollisionHandler().getY());
        door.put("collisionHandler", doorCollisionHandler);
        jsonObject.put("door", door);

        jsonArray.put(jsonObject);

        JSONObject note = new JSONObject();
        JSONArray noteArray = new JSONArray();
        for (int i = 0; i < 9; i++) {
            JSONObject noteItem = new JSONObject();
            noteItem.put("isOpen", Player.getInventory().getNote(i).isOpen());
            noteItem.put("hasBeenFound", Player.getInventory().getNote(i).hasBeenFound());
            noteItem.put("id", i);
            noteArray.put(noteItem);
        }
        note.put("notes", noteArray);

        jsonArray.put(note);

        try (FileWriter file = new FileWriter("data.json")) {
            mapper.writeValue(file, jsonArray.toList());
        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }
}
