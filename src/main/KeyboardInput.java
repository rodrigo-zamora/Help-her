package main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyboardInput extends KeyAdapter {

    private Handler handler;

    public KeyboardInput(Handler handler) {
        this.handler = handler;
    }

    public void keyPressed(KeyEvent event) {
        int key = event.getKeyCode();
        System.out.println("keyPressed: " + key);
        for(int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);
            if(tempObject.getId() == ID.Player){
                if(key == 38){ // 38 = flecha arriba
                    tempObject.setSpeedY(-tempObject.SPEED);
                } if(key == 40){ // 40 = flecha abajo
                    tempObject.setSpeedY(tempObject.SPEED);
                } if(key == 39){ // 39 = flecha derecha
                    tempObject.setSpeedX(tempObject.SPEED);
                } if(key == 37) { // 37 = flecha izquierda
                    tempObject.setSpeedX(-tempObject.SPEED);
                }
            }
        }
    }

    public void keyReleased(KeyEvent event) {
        int key = event.getKeyCode();
        System.out.println("keyReleased: " + key);
        for(int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);
            if(tempObject.getId() == ID.Player){
                if(key == 38){ // 38 = flecha arriba
                    tempObject.setSpeedY(0);
                } if(key == 40){ // 40 = flecha abajo
                    tempObject.setSpeedY(0);
                } if(key == 39){ // 39 = flecha derecha
                    tempObject.setSpeedX(0);
                } if(key == 37) { // 37 = flecha izquierda
                    tempObject.setSpeedX(0);
                }
            }
        }
    }
}
