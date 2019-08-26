package jojo;

import java.util.Date;
import java.util.Random;

public class Background {
    private int width;
    private int height;
    private int[] Maps;

    private Random random;

    public Background() {
        width = 25;
        height = 15;
        Maps = new int[width * height];
        random = new Random(new Date().getTime());
        InitMap();
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getValue(int x, int y) {
        int index = getIndex(x, y);
        return Maps[index];
    }

    public int getIndex(int x, int y) {
        return x + y * width;
    }

    private void InitMap() {
        // put surrounds
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int index = getIndex(x, y);
                if (x == 0 || y == 0 || x == width - 1 || y == height - 1 || (x % 2 == 0 && y % 2 == 0)) {
                    Maps[index] = TileItem.ICRONWALL.getValue();
                }
            }
        }

        Maps[getIndex(2, 1)] = TileItem.ICRONWALL.getValue();
        Maps[getIndex(1, 2)] = TileItem.ICRONWALL.getValue();
        Maps[getIndex(1, 1)] = TileItem.ICRONWALL.getValue();

        // put walls
        int maxItem = Math.round(width * height * 0.3f);
        int i = 0;
        while (i < maxItem) {
            int index = getRandomValue();
            if (Maps[index] == TileItem.ROAD.getValue()) {
                Maps[index] = TileItem.WALL.getValue();
                i++;
            }
        }

        Maps[getIndex(2, 1)] = TileItem.ROAD.getValue();
        Maps[getIndex(1, 2)] = TileItem.ROAD.getValue();
        Maps[getIndex(1, 1)] = TileItem.ROAD.getValue();

        // put a door
        maxItem = 1;
        i = 0;
        while (i < maxItem) {
            int index = getRandomValue();
            if (Maps[index] == TileItem.WALL.getValue()) {
                Maps[index] = Maps[index] + TileItem.DOOR.getValue();
                i++;
            }
        }

        // put game items
        maxItem = 10;
        i = 0;
        while (i < maxItem) {
            int index = getRandomValue();
            if (Maps[index] == TileItem.WALL.getValue()) {
                Maps[index] = Maps[index] + TileItem.BOMBUP.getValue();
                i++;
            }
        }

        maxItem = 5;
        i = 0;
        while (i < maxItem) {
            int index = getRandomValue();
            if (Maps[index] == TileItem.WALL.getValue()) {
                Maps[index] = Maps[index] + TileItem.POWERUP.getValue();
                i++;
            }
        }

        maxItem = 5;
        i = 0;
        while (i < maxItem) {
            int index = getRandomValue();
            if (Maps[index] == TileItem.WALL.getValue()) {
                Maps[index] = Maps[index] + TileItem.LIFEUP.getValue();
                i++;
            }
        }

        maxItem = 5;
        i = 0;
        while (i < maxItem) {
            int index = getRandomValue();
            if (Maps[index] == TileItem.WALL.getValue()) {
                Maps[index] = Maps[index] + TileItem.SPEEDUP.getValue();
                i++;
            }
        }

        maxItem = 3;
        i = 0;
        while (i < maxItem) {
            int index = getRandomValue();
            if (Maps[index] == TileItem.WALL.getValue()) {
                Maps[index] = Maps[index] + TileItem.REMOTECONTROL.getValue();
                i++;
            }
        }

    }

    private int getRandomValue() {
        return random.nextInt(width * height);
    }
}