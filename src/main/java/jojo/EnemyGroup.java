package jojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import lombok.Getter;

public class EnemyGroup {
    @Getter
    private List<Enemy> enemies;
    private final int _enemyCount = 10;
    private TileGroup tilegroup;
    private BombGroup bombGroup;

    public EnemyGroup(TileGroup tilegroup, BombGroup bombGroup) {
        enemies = new ArrayList<>(_enemyCount);
        this.tilegroup = tilegroup;
        this.bombGroup = bombGroup;
        initEmemise();
    }

    private void initEmemise() {
        Random random = new Random(new Date().getTime());
        int i = 0;
        while (i < _enemyCount) {
            int index = random.nextInt(tilegroup.getRow() * tilegroup.getColumn());
            int x = tilegroup.getX(index);
            int y = tilegroup.getY(index);
            Tile tile = tilegroup.getTiles().get(index);
            if (x > 100 && y > 100 && tile.getItem() == TileItem.ROAD) {
                Enemy enemy = new Enemy();
                enemy.position.set(x, y);
                enemies.add(enemy);
                i++;
            }
        }
    }

    public void update() {
        enemies.stream().filter(enemy -> enemy.isVisible()).forEach(enemy -> {
            enemy.update(tilegroup, bombGroup);
        });
    }

    public List<Enemy> getVisibleEnemies() {
        return enemies.stream().filter(enemy -> enemy.isVisible()).collect(Collectors.toList());
    }

}