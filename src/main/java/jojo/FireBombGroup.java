package jojo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.ArrayList;
import java.util.stream.Collectors;

import lombok.Getter;

public class FireBombGroup {
    @Getter
    private List<FireBomb> fireBombs;

    public FireBombGroup() {
        fireBombs = new ArrayList<>();
    }

    public void addFires(TileGroup tilegroup, int index) {
        List<Tile> tiles = tilegroup.getTiles();

        Set<Map.Entry<Direction, Tile>> surrounds = new HashMap<Direction, Tile>() {
            private static final long serialVersionUID = 1L;
            {
                put(Direction.UP, tiles.get(index - tilegroup.getColumn()));
                put(Direction.DOWN, tiles.get(index + tilegroup.getColumn()));
                put(Direction.LEFT, tiles.get(index - 1));
                put(Direction.RIGHT, tiles.get(index + 1));
                put(Direction.CENTER, tiles.get(index));
            }
        }.entrySet();

        fireBombs = surrounds.stream().filter(entry -> entry.getValue().getItem() == TileItem.ROAD).map(entry -> {
            Tile tile = entry.getValue();
            Direction direction = entry.getKey();
            return new FireBomb(tile.getPosition().getX(), tile.getPosition().getY(), direction);
        }).collect(Collectors.toList());

        surrounds.stream().filter(entry -> entry.getValue().getValue() >= TileItem.WALL.getValue())
                .map(entry -> entry.getValue()).forEach(wall -> {
                    if (wall.getItem() == TileItem.WALL) {
                        FireWall fireWall = new FireWall(wall.getPosition().getX(), wall.getPosition().getY());
                        tilegroup.getFireWalls().add(fireWall);
                        wall.removeItem(TileItem.WALL);
                    }
                });

    }

    public void update() {
        fireBombs.stream().filter(fire -> fire.isVisible()).forEach(fire -> {
            fire.update();
        });
    }

    public List<FireBomb> getVisibleFireBombs() {
        return fireBombs.stream().filter(fireBomb -> fireBomb.isVisible()).collect(Collectors.toList());
    }
}