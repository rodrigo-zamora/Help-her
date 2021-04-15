package com.game.juanito.enemy;

import com.game.juanito.enemy.enemies.*;
import com.game.juanito.handler.GameObjectHandler;
import com.game.juanito.main.ID;

import java.util.Random;

public class SpawnEnemy {

    /**
     * Method to get a random enemy ID
     *
     * @return an ID of the random enemy
     */
    private ID getRandomEnemy() {
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
    private int getRandomY() {
        Random random = new Random();
        return random.nextInt(410 - 250) + 250;
    }

    /**
     * Method to spawn an enemy
     *
     * @param y receives the X to spawn the enemy
     * @return the enemy object
     */
    private Enemy spawnEnemy(int y) {
        ID enemyType = getRandomEnemy();
        Enemy enemy;
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
     * @param gameObjectHandler
     * @param x
     */
    public void tick(GameObjectHandler gameObjectHandler, int x) {
        if (x == -1115) {
            gameObjectHandler.addObject(spawnEnemy(getRandomY()));
        }
    }
}
