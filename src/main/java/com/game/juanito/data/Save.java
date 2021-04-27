package com.game.juanito.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.game.juanito.player.Player;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class Save {

    public static void saveGame() throws IOException {

        JSONObject jsonObject = new JSONObject();
        JSONArray inventory = new JSONArray();

        jsonObject.put("health", Player.getHealth());

        /*for (int i = 0; i < 9; i++) {
            JSONObject note = new JSONObject();
            note.put("isOpen", Player.getInventory().getNote(i).isOpen());
            note.put("hasBeenFound", Player.getInventory().getNote(i).hasBeenFound());
            inventory.put(note);
        }
        jsonObject.put("inventory", inventory);*/

        System.out.println(jsonObject);

        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonObject));
    }
}
