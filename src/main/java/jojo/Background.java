package jojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import lombok.Getter;

public class Background {
    @Getter
    private int column;
    @Getter
    private int row;
    @Getter
    private List<Tile> tiles;

    private Random random;

    public Background() {
        column = 25;
        row = 15;
        random = new Random(new Date().getTime());
        tiles = new ArrayList<>(column * row);

        InitMap();
    }

    private int getIndex(int x, int y) {
        return x + y * column;
    }

    public Position getPosition(int index) {
        int height = ImageLoader.getTileHeight() * (index / column);
        int width = ImageLoader.getTileWidth() * (index % column);

        return new Position(width, height);
    }

    public void update() {

    }

    public List<Tile> getSurrounds(Player player) {
        int currentCol = player.getPosition().getX() / ImageLoader.getTileWidth();
        int currentRow = player.getPosition().getY() / ImageLoader.getTileHeight();
        int index = column * currentRow + currentCol;

        return new ArrayList<Tile>(9) {
            {
                add(tiles.get(index - column - 1));
                add(tiles.get(index - column));
                add(tiles.get(index - column + 1));
                add(tiles.get(index - 1));
                add(tiles.get(index));
                add(tiles.get(index + 1));
                add(tiles.get(index + column - 1));
                add(tiles.get(index + column));
                add(tiles.get(index + column + 1));

            }
        };
    }

    private void InitMap() {
        for (int i = 0; i < column * row; i++) {
            tiles.add(new Tile(getPosition(i)));
        }

        // put surrounds
        for (int x = 0; x < column; x++) {
            for (int y = 0; y < row; y++) {
                int index = getIndex(x, y);
                if (x == 0 || y == 0 || x == column - 1 || y == row - 1 || (x % 2 == 0 && y % 2 == 0)) {
                    Tile tile = tiles.get(index);
                    tile.setValue(TileItem.ICRONWALL.getValue());
                }
            }
        }

        tiles.get(getIndex(2, 1)).setValue(TileItem.ICRONWALL.getValue());
        tiles.get(getIndex(1, 2)).setValue(TileItem.ICRONWALL.getValue());
        tiles.get(getIndex(1, 1)).setValue(TileItem.ICRONWALL.getValue());

        // put walls
        int maxItem = Math.round(column * row * 0.3f);
        int i = 0;
        while (i < maxItem) {
            int index = getRandomValue();
            Tile tile = tiles.get(index);
            if (tile.getValue() == TileItem.ROAD.getValue()) {
                tile.updateValue(TileItem.WALL.getValue());
                i++;
            }
        }
        tiles.get(getIndex(2, 1)).setValue(TileItem.ROAD.getValue());
        tiles.get(getIndex(1, 2)).setValue(TileItem.ROAD.getValue());
        tiles.get(getIndex(1, 1)).setValue(TileItem.ROAD.getValue());

        // put a door
        maxItem = 1;
        i = 0;
        while (i < maxItem) {
            int index = getRandomValue();
            Tile tile = tiles.get(index);
            if (tile.getValue() == TileItem.WALL.getValue()) {
                tile.updateValue(TileItem.DOOR.getValue());
                i++;
            }
        }

        // put game items
        maxItem = 10;
        i = 0;
        while (i < maxItem) {
            int index = getRandomValue();
            Tile tile = tiles.get(index);
            if (tile.getValue() == TileItem.WALL.getValue()) {
                tile.updateValue(TileItem.BOMBUP.getValue());
                i++;
            }
        }

        maxItem = 5;
        i = 0;
        while (i < maxItem) {
            int index = getRandomValue();
            Tile tile = tiles.get(index);
            if (tile.getValue() == TileItem.WALL.getValue()) {
                tile.updateValue(TileItem.POWERUP.getValue());
                i++;
            }
        }

        maxItem = 5;
        i = 0;
        while (i < maxItem) {
            int index = getRandomValue();
            Tile tile = tiles.get(index);
            if (tile.getValue() == TileItem.WALL.getValue()) {
                tile.updateValue(TileItem.LIFEUP.getValue());
                i++;
            }
        }

        maxItem = 5;
        i = 0;
        while (i < maxItem) {
            int index = getRandomValue();
            Tile tile = tiles.get(index);
            if (tile.getValue() == TileItem.WALL.getValue()) {
                tile.updateValue(TileItem.SPEEDUP.getValue());
                i++;
            }
        }

        maxItem = 3;
        i = 0;
        while (i < maxItem) {
            int index = getRandomValue();
            Tile tile = tiles.get(index);
            if (tile.getValue() == TileItem.WALL.getValue()) {
                tile.updateValue(TileItem.REMOTECONTROL.getValue());
                i++;
            }
        }

    }

    private int getRandomValue() {
        return random.nextInt(column * row);
    }
}