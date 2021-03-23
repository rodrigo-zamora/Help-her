package main;

import java.awt.*;

/**
 *
 */
public class Player extends GameObject{

    /**
     *
     * @param x
     * @param y
     * @param id
     */
    public Player(int x, int y, ID id) {
        super(x, y, id);
    }

    /**
     *
     */
    @Override
    public void tick() {
        x += speedX;
        y += speedY;
    }

    /**
     *
     * @param graphics
     */
    @Override
    public void render(Graphics graphics) {
        if(id == ID.Player) {
            graphics.setColor(Color.MAGENTA);
            graphics.fillRect(x, y, 32, 32);
        } else if(id == ID.Enemy) {
            // ENEMY
        }
    }
}
