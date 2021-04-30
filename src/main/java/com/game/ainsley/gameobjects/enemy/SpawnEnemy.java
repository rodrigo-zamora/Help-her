package com.game.ainsley.gameobjects.enemy;

import com.game.ainsley.gameobjects.ID;
import com.game.ainsley.gameobjects.enemy.enemies.*;

import java.util.Random;

public class SpawnEnemy {

    /**
     * Method to get a random enemy ID
     *
     * @return an ID of the random enemy
     */
    private static ID getRandomEnemy() {
        Random random = new Random();
        int randomInteger = random.nextInt(5);
        return switch (randomInteger) {
            case 0 -> ID.Aarav;
            case 1 -> ID.Deidamia;
            case 2 -> ID.Gereon;
            case 3 -> ID.Nasra;
            case 4 -> ID.Sephtis;
            default -> throw new IllegalStateException("Unexpected value at getEnemy: " + randomInteger);
        };
    }

    /**
     * Method to get a random Y to spawn the enemy
     *
     * @return a random integer from 210 to 410
     */
    private static int getRandomY() {
        Random random = new Random();
        return random.nextInt(410 - 250) + 250;
    }

    /**
     * Method to spawn an enemy
     *
     * @return the enemy object
     */
    public static Enemy spawn() {
        ID enemyType = getRandomEnemy();
        Enemy enemy;
        int y = getRandomY();
        switch (enemyType) {
            case Aarav -> enemy = new Aarav(1016, y, ID.Aarav);
            case Nasra -> enemy = new Nasra(1016, y, ID.Nasra);
            case Gereon -> enemy = new Gereon(1016, y, ID.Gereon);
            case Sephtis -> enemy = new Sephtis(1016, y, ID.Sephtis);
            case Deidamia -> enemy = new Deidamia(1016, y, ID.Deidamia);
            default -> throw new IllegalStateException("Unexpected value at spawnEnemy: " + enemyType);
        }
        return enemy;
    }

    /**
     *
     * @param id
     * @param x
     * @param y
     * @return
     */
    public static Enemy spawn(String id, int x, int y) {
        Enemy enemy;
        switch (id) {
            case "Aarav" -> enemy = new Aarav(x, y, ID.Aarav);
            case "Nasra" -> enemy = new Nasra(x, y, ID.Nasra);
            case "Gereon" -> enemy = new Gereon(x, y, ID.Gereon);
            case "Sephtis" -> enemy = new Sephtis(x, y, ID.Sephtis);
            case "Deidamia" -> enemy = new Deidamia(x, y, ID.Deidamia);
            default -> throw new IllegalStateException("Unexpected value at spawnEnemy: " + id);
        }
        return enemy;
    }
}
