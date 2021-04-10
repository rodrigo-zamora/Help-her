package com.game.juanito.main;

import com.game.juanito.handler.CollisionHandler;

import java.awt.*;

public abstract class GameObject {
    protected int x, y;
    protected ID id;
    protected int speedX, speedY;

    public GameObject(int x, int y, ID id) {
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public abstract boolean tick();

    public abstract void render(Graphics graphics);

    // Setters and getters

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public ID getID() {
        return id;
    }

    public void setID(ID id) {
        this.id = id;
    }

    public int getSpeedX() {
        return speedX;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    public int getSpeedY() {
        return speedY;
    }

    public void setSpeedY(int speedY) {
        this.speedY = speedY;
    }

    public abstract void collision(Rectangle rectangle);

    public abstract int getWidth();

    public abstract int getHeight();
}
