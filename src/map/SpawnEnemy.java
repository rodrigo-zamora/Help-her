package map;

import main.Enemy;
import main.Handler;
import main.ID;

import java.util.Random;

public class SpawnEnemy {

    /**
     * @return
     */
    private ID getEnemy() {
        Random random = new Random();
        return switch (random.nextInt(4)) {
            case 0 -> ID.Aarav;
            case 1 -> ID.Deidamia;
            case 2 -> ID.Gereon;
            case 3 -> ID.Nasra;
            case 4 -> ID.Sephtis;
            default -> throw new IllegalStateException("Unexpected value at getEnemy: " + random.nextInt(5));
        };
    }

    /**
     * @return
     */
    private int getRandomY() {
        Random random = new Random();
        return random.nextInt(430 - 260) + 260;
    }

    /**
     *
     */
    private Enemy spawnEnemy(int x, int y) {
        ID enemyType = getEnemy();
        Enemy enemy = new Enemy(x, y, enemyType);
        enemy.setSpeedX(-1);
        return enemy;
    }

    /**
     *
     * @param handler
     * @param x
     */
    public void tick(Handler handler, int x) {
        if (x < -1118) {
            handler.addObject(spawnEnemy(508, getRandomY()));
        }
    }
}
