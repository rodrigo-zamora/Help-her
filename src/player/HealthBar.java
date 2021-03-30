package player;

import java.awt.*;

public class HealthBar {

    private int health;

    Image heart = Toolkit.getDefaultToolkit().getImage("res/player/heart.png");

    public void tick(int health){
        this.health = health;
    }

    public void render(Graphics graphics, int x, int y) {
        int xOffset = 0;
        for(int i = 1; i <= health; i++) {
            graphics.drawImage(
                    heart,
                    x + xOffset,
                    y,
                    null
            );
            xOffset += 68;
        }
    }
}
