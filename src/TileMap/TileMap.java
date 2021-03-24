package TileMap;

import main.Game;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;

public class TileMap extends Game {

    // Position
    private double x, y;

    // Bounds
    private int xMin, xMax, yMin, yMax;

    // Camera fix
    private double tween;

    // Map
    private int[][] map;
    private int tileSize;
    private int numRows;
    private int numCols;
    private int width;
    private int height;

    // Tileset
    private BufferedImage tileset;
    private int numTilesAcross;
    private Tile[][] tiles;

    // Drawing
    private int rowOffset;
    private int colOffset;
    private int numRowsToDraw;
    private int numColsToDraw;

    // Constructor
    public TileMap(int tileSize){
        this.tileSize = tileSize;
        numRowsToDraw = HEIGHT / tileSize + 2;
        numColsToDraw = WIDTH / tileSize + 2;
        tween = 0.07;
    }

    public void loadTiles(String s){
        try {
            tileset = ImageIO.read(getClass().getResourceAsStream(s));
            numTilesAcross = tileset.getWidth() / tileSize;
            tiles = new Tile[2][numTilesAcross];

            BufferedImage subImage;
            for(int col = 0; col < numTilesAcross; col++){
                subImage = tileset.getSubimage(col * tileSize, 0, tileSize, tileSize);
                tiles[0][col] = new Tile(subImage, Tile.NORMAL);
                subImage = tileset.getSubimage(col * tileSize, tileSize, tileSize, tileSize);
                tiles[1][col] = new Tile(subImage, Tile.BLOCKED);
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void loadMap(String s){
        try {
            InputStream in = getClass().getResourceAsStream(s);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            numCols = Integer.parseInt(br.readLine());
            numRows = Integer.parseInt(br.readLine());
            map = new int[numRows][numCols];
            width = numCols * tileSize;
            height = numRows * tileSize;

            String delims = "\\s+";
            for(int row = 0; row < numRows; row++){
                String line = br.readLine();
                String[] tokens = line.split(delims);
                for(int col = 0; col < numCols; col++){
                    map[row][col] = Integer.parseInt(tokens[col]);
                }
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public int getTileSize(){
        return tileSize;
    }

    public int getX() {
        return (int)x;
    }

    public int getY() {
        return (int)y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getType(int row, int col){
        int rc = map[row][col];
        int r = rc / numTilesAcross;
        int c = rc % numTilesAcross;
        return tiles[r][c].getType();
    }

     public void setPosition(double x, double y){
        this.x += (x - this.x) * tween;
        this.y += (y - this.y) * tween;
        fixBounds();

        colOffset = (int)-this.x / tileSize;
        rowOffset = (int)-this.y / tileSize;
     }

     private void fixBounds(){
        if(x < xMin){
            x = xMin;
        } if(y < yMin){
            y = yMin;
         } if(x > xMax){
            x = xMax;
         } if(y > yMax){
            y = yMax;
         }
     }

     public void draw(Graphics g){
        for(int row = rowOffset; row < rowOffset + numRowsToDraw; row++){
            if(row >= numRows) break;
            for(int col = colOffset; col < colOffset + numColsToDraw; col++){
                if(col >= numCols) break;
                if(map[row][col] == 0) continue;
                int rc = map[row][col];
                int r = rc / numTilesAcross;
                int c = rc % numTilesAcross;
                g.drawImage(tiles[r][c].getImage(),
                        (int)x + col * tileSize,
                        (int)y + row * tileSize,
                        null);
            }
        }
     }
}
