package com.game.ainsley.data;

import com.game.ainsley.gameobjects.ID;
import com.game.ainsley.gameobjects.enemy.SpawnEnemy;
import com.game.ainsley.handler.GameObjectHandler;
import com.game.ainsley.map.Chunk;
import com.game.ainsley.map.Door;
import com.game.ainsley.player.Player;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Load {

    /**
     * @throws IOException
     */
    public static void loadGame() throws IOException {

        // Read file into a string
        BufferedReader reader = new BufferedReader(new FileReader("data.json"));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        String ls = System.getProperty("line.separator");
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
            stringBuilder.append(ls);
        }
        // delete the last new line separator
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        reader.close();

        String content = stringBuilder.toString();
        // convert to json array
        JSONArray json = new JSONArray(content);

        for (int i = 0; i < json.length(); i++) {
            parseData(json.getJSONObject(i));
        }
    }

    /**
     * @param jsonObject
     */
    private static void parseData(JSONObject jsonObject) {
        Chunk.setSpeed(0);
        Player.setSpeedY(0);
        if (jsonObject.has("door")) {
            JSONObject door = jsonObject.getJSONObject("door");
            Door.setX((Integer) door.get("x"));
            Door.setY((Integer) door.get("y"));
            Door.setShouldRender((Boolean) door.get("shouldRender"));
            JSONObject collisionHandler = door.getJSONObject("collisionHandler");
            Door.getCollisionHandler().setX((Integer) collisionHandler.get("x"));
            Door.getCollisionHandler().setY((Integer) collisionHandler.get("y"));
        } else if (jsonObject.has("player")) {
            JSONObject player = jsonObject.getJSONObject("player");
            Player.setHealth((Integer) player.get("health"));
            Player.damageAnimation((Boolean) player.get("damageAnimation"));
            Player.setyS((Integer) player.get("y"));
            Player.setShouldRender((Boolean) player.get("shouldRender"));
            Player.getInventory().setReadingNote((Integer) player.get("readingNote"));
            Player.getInventory().setNotesCollected((Integer) player.get("notesCollected"));
        } else if (jsonObject.has("chunk")) {
            JSONObject chunk = jsonObject.getJSONObject("chunk");
            Chunk.setX((Integer) chunk.get("x"));
            Chunk.setCurrentChunk((Integer) chunk.get("currentChunk"));
            Chunk.setIterations((Integer) chunk.get("iterations"));
        } else if (jsonObject.has("inventory")) {
            JSONObject inventory = jsonObject.getJSONObject("inventory");
            for (int i = 0; i < 9; i++) {
                JSONObject note = inventory.getJSONObject(String.valueOf(i));
                Player.getInventory().getNote(i).setBeenFound((Boolean) note.get("hasBeenFound"));
                Player.getInventory().getNote(i).setOpen((Boolean) note.get("isOpen"));
            }
        } else if (jsonObject.has("enemy")) {
            JSONObject enemy = jsonObject.getJSONObject("enemy");
            if (!enemy.get("id").equals("null")) {
                GameObjectHandler.getObject().removeIf(tempObject -> tempObject.getID() != ID.Player);
                GameObjectHandler.addObject(SpawnEnemy.spawn((String) enemy.get("id"), (Integer) enemy.get("x"), (Integer) enemy.get("y")));
            }
        }
    }
}
