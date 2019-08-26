package jojo;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import javax.swing.ImageIcon;

import jojo.tools.PathHelper;

public class Tile {
    private Image tile;

    private int width;
    private int height;

    private HashMap<TileItem, Image> tileItems;

    public Tile() {
        tile = new ImageIcon(PathHelper.resourceURL("/images/tile.png")).getImage();
        width = tile.getWidth(null) / 9;
        height = tile.getHeight(null);
        tileItems = new HashMap<TileItem, Image>(9);

        tileItems.put(TileItem.ROAD, getImage(0, 0));
        tileItems.put(TileItem.BOMBUP, getImage(0, 1));
        tileItems.put(TileItem.POWERUP, getImage(0, 2));
        tileItems.put(TileItem.SPEEDUP, getImage(0, 3));
        tileItems.put(TileItem.LIFEUP, getImage(0, 4));
        tileItems.put(TileItem.REMOTECONTROL, getImage(0, 5));
        tileItems.put(TileItem.DOOR, getImage(0, 6));
        tileItems.put(TileItem.WALL, getImage(0, 7));
        tileItems.put(TileItem.ICRONWALL, getImage(0, 8));
    }

    private Image getImage(int row, int col) {
        Image image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        var g2d = (Graphics2D) image.getGraphics();
        g2d.drawImage(tile, 0, 0, width, height, col * width, row * height, ++col * width, ++row * height, null);
        return image;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Image getImage(TileItem item) {
        return tileItems.get(item);
    }

    public void draw(Graphics g, TileItem item) {
        Graphics2D g2d = (Graphics2D) g;
        switch (item) {
        case ROAD:
            g2d.drawImage(tile, 0, 0, height, height, 0, 0, height, height, null);
            break;
        case BOMBUP:
            g2d.drawImage(tile, 0, 0, height, height, height, 0, height, height, null);
            break;
        case POWERUP:
            g2d.drawImage(tile, 0, 0, height, height, 2 * height, 0, height, height, null);
            break;
        case SPEEDUP:
            g2d.drawImage(tile, 0, 0, height, height, 3 * height, 0, height, height, null);
            break;
        case LIFEUP:
            g2d.drawImage(tile, 0, 0, height, height, 4 * height, 0, height, height, null);
            break;
        case REMOTECONTROL:
            g2d.drawImage(tile, 0, 0, height, height, 5 * height, 0, height, height, null);
            break;
        case DOOR:
            g2d.drawImage(tile, 0, 0, height, height, 6 * height, 0, height, height, null);
            break;
        case WALL:
            g2d.drawImage(tile, 0, 0, height, height, 7 * height, 0, height, height, null);
            break;
        case ICRONWALL:
            g2d.drawImage(tile, 0, 0, height, height, 8 * height, 0, height, height, null);
            break;
        }
    }
}
