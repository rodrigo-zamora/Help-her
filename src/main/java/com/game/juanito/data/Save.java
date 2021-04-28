package com.game.juanito.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.game.juanito.player.Player;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class Save {

    public static void saveGame() throws IOException {

        ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < 9; i++) {
            JSONObject note = new JSONObject();
            JSONObject noteItem = new JSONObject();
            noteItem.put("isOpen", Player.getInventory().getNote(i).isOpen());
            noteItem.put("hasBeenFound", Player.getInventory().getNote(i).hasBeenFound());
            noteItem.put("id", i);
            note.put("note", noteItem);
            jsonArray.put(note);
        }

        try (FileWriter file = new FileWriter("data.json")) {
            System.out.println("Successfully Copied JSON Object to File...");
            System.out.println(jsonArray);
            mapper.writeValue(file, jsonArray.toList());
        } catch(Exception exception){
            System.out.println(exception);
        }
        
    }
}
