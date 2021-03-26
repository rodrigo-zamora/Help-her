package map;

import main.Game;
import main.Player;

import java.awt.*;

public class Map {

    Image chunk1 = Toolkit.getDefaultToolkit().getImage("res/map/1.2.png");
    Image chunk2 = Toolkit.getDefaultToolkit().getImage("res/map/2.2.png");
    Image chunk3 = Toolkit.getDefaultToolkit().getImage("res/map/3.2.png");
    Image chunk4 = Toolkit.getDefaultToolkit().getImage("res/map/4.2.png");
    Image chunk5 = Toolkit.getDefaultToolkit().getImage("res/map/5.2.png");
    Image chunk6 = Toolkit.getDefaultToolkit().getImage("res/map/6.2.png");
    Image chunk7 = Toolkit.getDefaultToolkit().getImage("res/map/7.2.png");
    Image chunk8 = Toolkit.getDefaultToolkit().getImage("res/map/8.2.png");

    public int currentChunk;
    public int x;

    public Map(){
        currentChunk = 1;
        x = 0;
    }

    public Image nextChunk(int currentChunk){
        return switch (currentChunk) {
            case 1 -> chunk2;
            case 2 -> chunk3;
            case 3 -> chunk4;
            case 4 -> chunk5;
            case 5 -> chunk6;
            case 6 -> chunk7;
            case 7 -> chunk8;
            case 8 -> chunk1;
            default -> throw new IllegalStateException("Unexpected value at nextChunk: " + currentChunk);
        };
    }

    public Image previousChunk(int currentChunk){
        return switch (currentChunk) {
            case 1 -> chunk8;
            case 2 -> chunk1;
            case 3 -> chunk2;
            case 4 -> chunk3;
            case 5 -> chunk4;
            case 6 -> chunk5;
            case 7 -> chunk6;
            case 8 -> chunk7;
            default -> throw new IllegalStateException("Unexpected value at previousChunk: " + currentChunk);
        };
    }

    public Image getChunk(int currentChunk){
        return switch (currentChunk) {
            case 1 -> chunk1;
            case 2 -> chunk2;
            case 3 -> chunk3;
            case 4 -> chunk4;
            case 5 -> chunk5;
            case 6 -> chunk6;
            case 7 -> chunk7;
            case 8 -> chunk8;
            default -> throw new IllegalStateException("Unexpected value at currentChunk: " + currentChunk);
        };
    }

    public int getX(){
        return x;
    }

    public void setX(int x){
        this.x = x;
    }

    public void addChunk(){
        if(currentChunk == 8){
            currentChunk = 1;
        } else {
            currentChunk++;
        }
    }

    public void addChunkNegative(){
        if(currentChunk == 1){
            currentChunk = 8;
        } else {
            currentChunk--;
        }
    }

    public void calculateChunk(int playerX, Player player) {
        x = -playerX  + 508;
        if(x <= -946){
            addChunk();
            player.setX(508);
        } else if(x >= 946){
            addChunkNegative();
            player.setX(508);
        }
    }
}
