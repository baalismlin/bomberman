package jojo;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import jojo.tools.PathHelper;

public class Tile {
    private Image tile;

    private int width;
    private int height;

    public Tile() {
        tile = new ImageIcon(PathHelper.resourceURL("/images/tile.png")).getImage();
        width = tile.getWidth(null) / 9;
        height = tile.getHeight(null);
    }

    public void draw(Graphics g, GameItem item) {
        Graphics2D g2d = (Graphics2D) g;
        switch (item) {
        case CHANEL:
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
        case REMOTECONTROL:
            g2d.drawImage(tile, 0, 0, height, height, 4 * height, 0, height, height, null);
            break;
        case DOOR:
            g2d.drawImage(tile, 0, 0, height, height, 5 * height, 0, height, height, null);
            break;
        case WALL:
            g2d.drawImage(tile, 0, 0, height, height, 6 * height, 0, height, height, null);
            break;
        case ICRONWALL:
            g2d.drawImage(tile, 0, 0, height, height, 7 * height, 0, height, height, null);
            break;
        }
    }
}
