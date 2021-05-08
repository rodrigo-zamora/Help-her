package com.game.ainsley.gameobjects;

import java.awt.*;

public abstract class GameObject {
    protected static int speedY;
    protected int x, y;
    protected ID id;

    public GameObject(int x, int y, ID id) {
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public static int getSpeedY() {
        return speedY;
    }

    public static void setSpeedY(int speedY) {
        GameObject.speedY = speedY;
    }

    // Setters and getters

    public abstract boolean tick();

    public abstract void render(Graphics graphics);

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

    public abstract void collision();
}
