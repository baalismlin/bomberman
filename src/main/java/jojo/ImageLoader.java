package jojo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.ImageIcon;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;

import jojo.tools.PathHelper;
import lombok.Getter;

public final class ImageLoader {

    // load player.png
    @Getter
    private static List<Image> playerLeftImages = new ArrayList<>(4);
    @Getter
    private static List<Image> playerRightImages = new ArrayList<>(4);
    @Getter
    private static List<Image> playerUpImages = new ArrayList<>(4);
    @Getter
    private static List<Image> playerDownImages = new ArrayList<>(4);
    @Getter
    private static List<Image> playerDieImages = new ArrayList<>(8);
    @Getter
    private static int playerWidth;
    @Getter
    private static int playerheight;

    static {
        Image palyerSource = new ImageIcon(PathHelper.resourceURL("/images/play.png")).getImage();
        playerWidth = palyerSource.getWidth(null) / 4;
        playerheight = palyerSource.getHeight(null) / 6;
        for (int col = 0; col < 4; col++) {
            // line 1 left moving
            playerLeftImages.add(getImage(palyerSource, playerWidth, playerheight, 0, col));

            // line 2 right moving
            playerRightImages.add(getImage(palyerSource, playerWidth, playerheight, 1, col));

            // line 3 up moving
            playerUpImages.add(getImage(palyerSource, playerWidth, playerheight, 2, col));

            // line 4 down moving
            playerDownImages.add(getImage(palyerSource, playerWidth, playerheight, 3, col));

        }

        for (int row = 4; row < 6; row++) {
            for (int col = 0; col < 4; col++) {
                playerDieImages.add(getImage(palyerSource, playerWidth, playerheight, row, col));
            }
        }

    }

    // load tile.png
    @Getter
    private static HashMap<TileItem, Image> itemImages = new HashMap<>(9);
    @Getter
    private static int tileWidth;
    @Getter
    private static int tileHeight;

    static {
        Image tileSource = new ImageIcon(PathHelper.resourceURL("/images/tile.png")).getImage();
        tileWidth = tileSource.getWidth(null) / 9;
        tileHeight = tileSource.getHeight(null);
        itemImages.put(TileItem.ROAD, getImage(tileSource, tileWidth, tileHeight, 0, 0));
        itemImages.put(TileItem.BOMBUP, getImage(tileSource, tileWidth, tileHeight, 0, 1));
        itemImages.put(TileItem.POWERUP, getImage(tileSource, tileWidth, tileHeight, 0, 2));
        itemImages.put(TileItem.SPEEDUP, getImage(tileSource, tileWidth, tileHeight, 0, 3));
        itemImages.put(TileItem.LIFEUP, getImage(tileSource, tileWidth, tileHeight, 0, 4));
        itemImages.put(TileItem.REMOTECONTROL, getImage(tileSource, tileWidth, tileHeight, 0, 5));
        itemImages.put(TileItem.DOOR, getImage(tileSource, tileWidth, tileHeight, 0, 6));
        itemImages.put(TileItem.WALL, getImage(tileSource, tileWidth, tileHeight, 0, 7));
        itemImages.put(TileItem.ICRONWALL, getImage(tileSource, tileWidth, tileHeight, 0, 8));
    }

    // load enemy.png
    @Getter
    private static HashMap<Integer, List<Image>> enemyImages = new HashMap<>(7);
    @Getter
    private static int enemyWidth;
    @Getter
    private static int enemyHeight;

    static {
        Image enemySource = new ImageIcon(PathHelper.resourceURL("/images/enemy.png")).getImage();
        enemyWidth = enemySource.getWidth(null) / 9;
        enemyHeight = enemySource.getHeight(null) / 7;
        for (int row = 0; row < 7; row++) {
            ArrayList<Image> temp = new ArrayList<Image>(9);
            for (int col = 0; col < 9; col++) {
                temp.add(getImage(enemySource, enemyWidth, enemyHeight, row, col));
            }
            enemyImages.put(Integer.valueOf(row), temp);
        }
    }

    // load bomb.png
    @Getter
    private static List<Image> bombImages = new ArrayList<>(4);
    @Getter
    private static int bombWidth;
    @Getter
    private static int bombHeight;

    static {
        Image bombSource = new ImageIcon(PathHelper.resourceURL("/images/bomb.png")).getImage();
        bombWidth = bombSource.getWidth(null) / 4;
        bombHeight = bombSource.getHeight(null);
        for (int col = 0; col < 4; col++) {
            bombImages.add(getImage(bombSource, bombWidth, bombHeight, 0, col));
        }
    }

    // load fire.png
    @Getter
    private static List<Image> centerFireImages = new ArrayList<>(4);
    @Getter
    private static List<Image> leftFireImages = new ArrayList<>(4);
    @Getter
    private static List<Image> rightFireImages = new ArrayList<>(4);
    @Getter
    private static List<Image> upFireImages = new ArrayList<>(4);
    @Getter
    private static List<Image> downFireImages = new ArrayList<>(4);
    @Getter
    private static List<Image> horizontalFireImages = new ArrayList<>(4);
    @Getter
    private static List<Image> verticalFireImages = new ArrayList<>(4);

    static {
        Image bombSource = new ImageIcon(PathHelper.resourceURL("/images/fire.png")).getImage();
        for (int col = 0; col < 4; col++) {
            centerFireImages.add(getImage(bombSource, bombWidth, bombHeight, 0, col));
            leftFireImages.add(getImage(bombSource, bombWidth, bombHeight, 1, col));
            rightFireImages.add(getImage(bombSource, bombWidth, bombHeight, 2, col));
            upFireImages.add(getImage(bombSource, bombWidth, bombHeight, 3, col));
            downFireImages.add(getImage(bombSource, bombWidth, bombHeight, 4, col));
            horizontalFireImages.add(getImage(bombSource, bombWidth, bombHeight, 5, col));
            verticalFireImages.add(getImage(bombSource, bombWidth, bombHeight, 6, col));
        }
    }

    // load wall.png
    @Getter
    private static List<Image> wallImages = new ArrayList<>(6);

    static {
        Image wallSource = new ImageIcon(PathHelper.resourceURL("/images/wall.png")).getImage();
        for (int col = 0; col < 6; col++) {
            wallImages.add(getImage(wallSource, tileWidth, tileHeight, 0, col));
        }
    }

    private static Image getImage(Image source, int width, int height, int row, int col) {
        Image image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = (Graphics2D) image.getGraphics();
        g2d.drawImage(source, 0, 0, width, height, col * width, row * height, ++col * width, ++row * height, null);
        return image;
    }
}