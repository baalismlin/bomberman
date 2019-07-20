package jojo;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import jojo.tools.PathHelper;

public class Player {
    private Image bomber;

    private int speed;
    private boolean moving;
    private Direction direction;
    private int frame;
    private int width;
    private int height;

    private int x;
    private int y;
    private int dx;
    private int dy;

    public Player() {
        bomber = new ImageIcon(PathHelper.resourceURL("/images/play.png")).getImage();
        width = bomber.getWidth(null) / 4;
        height = bomber.getHeight(null) / 6;
        speed = 1;
        moving = false;
        direction = Direction.DOWN;
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

    public void startMove(Direction direction) {
        if (direction != this.direction) {
            this.direction = direction;
            frame = 0;
        } else {
            frame = frame == 4 ? 0 : frame + 1;
        }

    }

    public void stopMove() {
        dx = 0;
        dy = 0;
    }

    private void goLeft(Graphics g) {
        draw(g, 0);
        // g.drawImage(bomber, x, y, x + width, y + height, width * frame, 0, width *
        // frame + width, height, null);
    }

    private void goRight(Graphics g) {
        draw(g, 1);
        // g.drawImage(bomber, x, y, x + width, y + height, width * frame, height, width
        // * frame + width, 2 * height,
        // null);
    }

    private void goUp(Graphics g) {
        draw(g, 2);
        // g.drawImage(bomber, x, y, x + width, y + height, width * frame, 2 * height,
        // width * frame + width, 3 * height,
        // null);
    }

    private void goDown(Graphics g) {
        draw(g, 3);
        // g.drawImage(bomber, x, y, x + width, y + height, width * frame, 3 * height,
        // width * frame + width, 4 * height,
        // null);
    }

    public void draw(Graphics g) {
        switch (direction) {
        case LEFT:
            goLeft(g);
            break;
        case RIGHT:
            goRight(g);
            break;
        case UP:
            goUp(g);
            break;
        case DOWN:
            goDown(g);
            break;
        default:
            goDown(g);
            break;
        }
    }

    private void draw(Graphics g, int level) {
        x += dx;
        y += dy;
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(bomber, x, y, x + width, y + height, width * frame, level * height, width * frame + width,
                ++level * height, null);
    }

}