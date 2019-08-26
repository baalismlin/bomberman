package jojo;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import jojo.tools.PathHelper;

public class Player {
    private Image source;

    private int speed;
    private Direction direction;
    private int frame;
    private int width;
    private int height;

    private int x;
    private int y;

    private List<Image> leftImages;
    private List<Image> rightImages;
    private List<Image> upImages;
    private List<Image> downImages;
    private List<Image> dieImages;

    public Player() {
        source = new ImageIcon(PathHelper.resourceURL("/images/play.png")).getImage();

        width = source.getWidth(null) / 4;
        height = source.getHeight(null) / 6;
        speed = 1;
        direction = Direction.DOWN;

        leftImages = new ArrayList<>(4);
        rightImages = new ArrayList<>(4);
        upImages = new ArrayList<>(4);
        downImages = new ArrayList<>(4);
        dieImages = new ArrayList<>(8);

        for (int col = 0; col < 4; col++) {
            // line 1 left moving
            leftImages.add(getImage(0, col));

            // line 2 right moving
            rightImages.add(getImage(1, col));

            // line 3 up moving
            upImages.add(getImage(2, col));

            // line 4 down moving
            downImages.add(getImage(3, col));

            // line 5
            dieImages.add(getImage(4, col));

        }

        for (int col = 0; col < 4; col++) {
            dieImages.add(getImage(4, col));
        }

    }

    private Image getImage(int row, int col) {
        Image image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        var g2d = (Graphics2D) image.getGraphics();
        g2d.drawImage(source, 0, 0, width, height, col * width, row * height, ++col * width, ++row * height, null);
        return image;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void startMove(Direction direction, int hDirection, int vDirection) {
        x += hDirection * speed;
        y += vDirection * speed;
        if (direction != this.direction) {
            this.direction = direction;
            frame = 0;
        } else {
            frame = frame == 3 ? 0 : frame + 1;
        }

    }

    public Image getImage() {
        Image image;
        switch (direction) {
        case LEFT:
            image = leftImages.get(frame);
            break;
        case RIGHT:
            image = rightImages.get(frame);
            break;
        case UP:
            image = upImages.get(frame);
            break;
        case DOWN:
        default:
            image = downImages.get(frame);
            break;
        }
        return image;
    }

}