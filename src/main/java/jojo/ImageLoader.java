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

    // load player image
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
            playerLeftImages.add(getPlayerImage(palyerSource, 0, col));

            // line 2 right moving
            playerRightImages.add(getPlayerImage(palyerSource, 1, col));

            // line 3 up moving
            playerUpImages.add(getPlayerImage(palyerSource, 2, col));

            // line 4 down moving
            playerDownImages.add(getPlayerImage(palyerSource, 3, col));

        }

        for (int row = 4; row < 6; row++) {
            for (int col = 0; col < 4; col++) {
                playerDieImages.add(getPlayerImage(palyerSource, row, col));
            }
        }

    }

    private static Image getPlayerImage(Image source, int row, int col) {

        Image image = new BufferedImage(playerWidth, playerheight, BufferedImage.TYPE_INT_ARGB);
        var g2d = (Graphics2D) image.getGraphics();
        g2d.drawImage(source, 0, 0, playerWidth, playerheight, col * playerWidth, row * playerheight,
                ++col * playerWidth, ++row * playerheight, null);
        return image;
    }

    // load tile image
    @Getter
    private static HashMap<TileItem, Image> tileItems = new HashMap<TileItem, Image>(9);;
    @Getter
    private static int tileWidth;
    @Getter
    private static int tileHeight;

    static {
        Image tileSource = new ImageIcon(PathHelper.resourceURL("/images/tile.png")).getImage();
        tileWidth = tileSource.getWidth(null) / 9;
        tileHeight = tileSource.getHeight(null);
        tileItems.put(TileItem.ROAD, getTileImage(tileSource, 0, 0));
        tileItems.put(TileItem.BOMBUP, getTileImage(tileSource, 0, 1));
        tileItems.put(TileItem.POWERUP, getTileImage(tileSource, 0, 2));
        tileItems.put(TileItem.SPEEDUP, getTileImage(tileSource, 0, 3));
        tileItems.put(TileItem.LIFEUP, getTileImage(tileSource, 0, 4));
        tileItems.put(TileItem.REMOTECONTROL, getTileImage(tileSource, 0, 5));
        tileItems.put(TileItem.DOOR, getTileImage(tileSource, 0, 6));
        tileItems.put(TileItem.WALL, getTileImage(tileSource, 0, 7));
        tileItems.put(TileItem.ICRONWALL, getTileImage(tileSource, 0, 8));
    }

    private static Image getTileImage(Image source, int row, int col) {

        Image image = new BufferedImage(tileWidth, tileHeight, BufferedImage.TYPE_INT_ARGB);
        var g2d = (Graphics2D) image.getGraphics();
        g2d.drawImage(source, 0, 0, tileWidth, tileHeight, col * tileWidth, row * tileHeight, ++col * tileWidth,
                ++row * tileHeight, null);
        return image;
    }

    // load enemy image
}