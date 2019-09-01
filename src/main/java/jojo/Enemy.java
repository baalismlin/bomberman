package jojo;

import java.awt.Image;

import javax.swing.ImageIcon;

import jojo.tools.PathHelper;

public class Enemy extends Sprite {
    private Image enemy;
    private int speed;

    private int x;
    private int y;
    private int dx;
    private int dy;

    private Direction prevDirection;
    private Direction direction;

    public Enemy() {
        var resource = PathHelper.resourceURL("/images/enemy.png");
        enemy = new ImageIcon(resource).getImage();

        position = new Position();
        width = enemy.getWidth(null) / 9;
        height = enemy.getHeight(null) / 7;
        speed = 1;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void startMove() {

    }

    public void stopMove() {
        dx = 0;
        dy = 0;
    }

}