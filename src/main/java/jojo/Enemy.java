package jojo;

import java.awt.Image;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Stack;
import java.util.stream.Collectors;

import lombok.Getter;

public class Enemy extends Sprite {

    private static final Random RANDOM = new Random(new Date().getTime());

    public Enemy() {
        width = ImageLoader.getEnemyWidth();
        height = ImageLoader.getEnemyHeight();
        speed = 1;
        moving = true;
    }

    @Override
    public Image getImage() {
        Integer level = Integer.valueOf(0);
        Image image = ImageLoader.getEnemyImages().get(level).get(frame);
        return image;
    }

    public void update(TileGroup tilegroup, BombGroup bombGroup) {
        if (isDied()) {
            if (frame == 8) {
                visible = false;
            }
            loopFrame(8, 200);
        } else {

            if (!isMoving()) {
                return;
            }
            loopFrame(3, 300);

            randomDirection();

            List<Sprite> tiles = tilegroup.getSurrounds(this).values().stream()
                    .filter(tile -> tile.getItem() == TileItem.WALL || tile.getItem() == TileItem.ICRONWALL)
                    .map(tile -> (Sprite) tile).collect(Collectors.toList());

            List<Sprite> bombs = bombGroup.getBombs().stream().filter(bomb -> bomb.isVisible())
                    .map(bomb -> (Sprite) bomb).collect(Collectors.toList());

            tiles.addAll(bombs);

            increaseDelta();

            List<? extends Sprite> groupCollide = Sprite.groupCollide(this, tiles);
            if (groupCollide.size() > 0) {
                decreaseDelta();
            }
        }
    }

    private List<Tile> map;
    private boolean visited[];
    private int dir[];
    @Getter
    private Stack<Integer> route;
    private TileGroup tileGroup;

    public Stack<Integer> findRoute(TileGroup tileGroup) {
        this.tileGroup = tileGroup;
        route = new Stack<>();
        map = tileGroup.getTiles();
        visited = new boolean[tileGroup.getRow() * tileGroup.getColumn()];
        dir = new int[] { -1, 1, -1 * tileGroup.getColumn(), tileGroup.getColumn() };
        int index = getIndexFromPosition(getCenter());

        dfs(index);
        return route;
    }

    private int getIndexFromPosition(Position position) {
        int x = position.getX();
        int y = position.getY();
        int col = tileGroup.getColumn(x);
        int row = tileGroup.getRow(y);
        int index = tileGroup.getIndex(col, row);
        return index;
    }

    private boolean checkEdge(Tile tile) {
        int index = getIndexFromPosition(tile.getCenter());
        if (!visited[index] && map.get(index).getItem() == TileItem.ROAD) {
            return true;
        } else {
            return false;
        }
    }

    private void dfs(int index) {

        visited[index] = true;
        if (map.get(index).getItem() == TileItem.WALL || map.get(index).getItem() == TileItem.ICRONWALL) {
            return;
        }

        for (int i = 0; i < 4; i++) {
            Tile next = map.get(index + dir[i]);
            if (checkEdge(next)) {
                route.push(Integer.valueOf(index));
                dfs(index + dir[i]);
            }
        }
    }

    private void randomDirection() {
        if (RANDOM.nextInt(10) > 8) {
            direction = Direction.toDirection(RANDOM.nextInt(3) + 1);
        }
    }

}