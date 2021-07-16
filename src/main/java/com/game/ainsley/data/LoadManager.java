package com.game.ainsley.data;

import com.game.ainsley.gameobjects.ID;
import com.game.ainsley.gameobjects.enemy.SpawnEnemy;
import com.game.ainsley.main.Game;
import lib.ainsley.FileManager;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class LoadManager {

    private final Game game;

    public LoadManager() {
        game = Game.getInstance();
    }

    /**
     * @throws IOException
     */
    public void loadGame() throws IOException {

        JSONArray json = new JSONArray(FileManager.readJSONfile("data.json"));

        for (int i = 0; i < json.length(); i++) {
            parseData(json.getJSONObject(i));
        }
    }

    /**
     * @param jsonObject
     */
    private void parseData(JSONObject jsonObject) {
        game.getMapManager().setSpeed(0);
        game.getPlayer().setSpeedY(0);
        if (jsonObject.has("door")) {
            JSONObject door = jsonObject.getJSONObject("door");
            game.getDoor().setX((Integer) door.get("x"));
            game.getDoor().setY((Integer) door.get("y"));
            game.getDoor().setShouldRender((Boolean) door.get("shouldRender"));
            JSONObject collisionHandler = door.getJSONObject("collisionHandler");
            game.getDoor().getCollisionHandler().setX((Integer) collisionHandler.get("x"));
            game.getDoor().getCollisionHandler().setY((Integer) collisionHandler.get("y"));

        } else if (jsonObject.has("player")) {
            JSONObject player = jsonObject.getJSONObject("player");
            game.getPlayer().setHealth((Integer) player.get("health"));
            game.getPlayer().setyS((Integer) player.get("y"));
            game.getPlayer().setShouldRender((Boolean) player.get("shouldRender"));
            game.getPlayer().getInventory().setReadingNote((Integer) player.get("readingNote"));
            game.getPlayer().getInventory().setNotesCollected((Integer) player.get("notesCollected"));

        } else if (jsonObject.has("chunk")) {
            JSONObject chunk = jsonObject.getJSONObject("chunk");
            game.getMapManager().setX((Integer) chunk.get("x"));
            game.getMapManager().setCurrentChunk((Integer) chunk.get("currentChunk"));
            game.getMapManager().setIterations((Integer) chunk.get("iterations"));

        } else if (jsonObject.has("inventory")) {
            JSONObject inventory = jsonObject.getJSONObject("inventory");
            for (int i = 0; i < 9; i++) {
                JSONObject note = inventory.getJSONObject(String.valueOf(i));
                game.getPlayer().getInventory().getNote(i).setBeenFound((Boolean) note.get("hasBeenFound"));
                game.getPlayer().getInventory().getNote(i).setOpen((Boolean) note.get("isOpen"));
            }

        } else if (jsonObject.has("enemy")) {
            JSONObject enemy = jsonObject.getJSONObject("enemy");
            if (!enemy.get("id").equals("null")) {
                game.getGameObjectHandler().getObject().removeIf(tempObject -> tempObject.getID() != ID.Player);
                game.getGameObjectHandler().addObject(SpawnEnemy.spawn((String) enemy.get("id"), (Integer) enemy.get("x"), (Integer) enemy.get("y")));
            }
        }
    }
}
