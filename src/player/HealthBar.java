package player;

import java.awt.*;

public class HealthBar {

    Image heart = Toolkit.getDefaultToolkit().getImage("res/player/heart.png");

    private void drawHealth(Graphics graphics, int x, int y, int health){
        graphics.drawImage(
                heart,
                x,
                y,
                null
        );
    }

    public void draw(Graphics graphics, int x, int y, int health) {
        int xOffset = 0;
        for(int i = 1; i <= health; i++) {
            drawHealth(graphics, x + xOffset, y, i % 2);
            xOffset += 68;
        }
    }
}
