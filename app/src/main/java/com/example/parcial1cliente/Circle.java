package com.example.parcial1cliente;

public class Circle {

    private int x, y;
    private int type;
    private int velX,velY;
    private String groupName;
    private boolean move;

    public Circle (int x, int y,int type, int velX, int velY, String groupName) {

        this.x = x;
        this.y = y;
        this.type = type;
        this.velX = velX;
        this.velY = velY;
        this.groupName = groupName;

        move = true;


    }
    public Circle(){

    }

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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getVelX() {
        return velX;
    }

    public void setVelX(int velX) {
        this.velX = velX;
    }

    public int getVelY() {
        return velY;
    }

    public void setVelY(int velY) {
        this.velY = velY;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public boolean isMove() {
        return move;
    }

    public void setMove(boolean move) {
        this.move = move;
    }
}
