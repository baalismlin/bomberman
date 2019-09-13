package jojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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

    public int getIndex(int column, int row) {
        return column + row * this.column;
    }

    public int getX(int index) {
        return ImageLoader.getTileWidth() * (index % column);
    }

    public int getY(int index) {
        return ImageLoader.getTileHeight() * (index / column);
    }

    public void update() {
        tiles.stream().filter(tile -> tile.getFireWall() != null).forEach(tile -> tile.getFireWall().update());
    }

    public int getColumn(int x) {
        return x / ImageLoader.getTileWidth();
    }

    public int getRow(int y) {
        return y / ImageLoader.getTileHeight();
    }

    public HashMap<Direction, Tile> getSurrounds(Sprite sprite) {
        int currentCol = getColumn(sprite.getPosition().getX());
        int currentRow = getRow(sprite.getPosition().getY());
        int index = column * currentRow + currentCol;

        return new HashMap<Direction, Tile>(8) {
            private static final long serialVersionUID = 1159546289936330648L;

            {
                put(Direction.LEFT_UP, tiles.get(index - column - 1));
                put(Direction.UP, tiles.get(index - column));
                put(Direction.RIGHT_UP, tiles.get(index - column + 1));
                put(Direction.LEFT, tiles.get(index - 1));
                put(Direction.RIGHT, tiles.get(index + 1));
                put(Direction.LEFT_DOWN, tiles.get(index + column - 1));
                put(Direction.DOWN, tiles.get(index + column));
                put(Direction.RIGHT_DOWN, tiles.get(index + column + 1));
            }
        };
    }

    private void InitMap() {
        for (int i = 0; i < column * row; i++) {
            tiles.add(new Tile(getX(i), getY(i)));
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
        int maxItem = Math.round(column * row * 0.2f);
        int i = 0;
        while (i < maxItem) {
            int index = getRandomValue();
            Tile tile = tiles.get(index);
            if (tile.getValue() == TileItem.ROAD.getValue()) {
                tile.combineItem(TileItem.WALL);
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
                tile.combineItem(TileItem.DOOR);
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
                tile.combineItem(TileItem.BOMBUP);
                i++;
            }
        }

        maxItem = 5;
        i = 0;
        while (i < maxItem) {
            int index = getRandomValue();
            Tile tile = tiles.get(index);
            if (tile.getValue() == TileItem.WALL.getValue()) {
                tile.combineItem(TileItem.POWERUP);
                i++;
            }
        }

        maxItem = 5;
        i = 0;
        while (i < maxItem) {
            int index = getRandomValue();
            Tile tile = tiles.get(index);
            if (tile.getValue() == TileItem.WALL.getValue()) {
                tile.combineItem(TileItem.LIFEUP);
                i++;
            }
        }

        maxItem = 5;
        i = 0;
        while (i < maxItem) {
            int index = getRandomValue();
            Tile tile = tiles.get(index);
            if (tile.getValue() == TileItem.WALL.getValue()) {
                tile.combineItem(TileItem.SPEEDUP);
                i++;
            }
        }

        maxItem = 3;
        i = 0;
        while (i < maxItem) {
            int index = getRandomValue();
            Tile tile = tiles.get(index);
            if (tile.getValue() == TileItem.WALL.getValue()) {
                tile.combineItem(TileItem.REMOTECONTROL);
                i++;
            }
        }

    }

    private int getRandomValue() {
        return random.nextInt(column * row);
    }

}