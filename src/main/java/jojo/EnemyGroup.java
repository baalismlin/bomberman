package jojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import lombok.Getter;

public class EnemyGroup {
    @Getter
    private List<Enemy> enemies;
    private final int _enemyCount = 10;
    private Background background;

    public EnemyGroup(Background background) {
        enemies = new ArrayList<>(_enemyCount);
        this.background = background;

        initEmemise();
    }

    private void initEmemise() {
        Random random = new Random(new Date().getTime());
        int i = 0;
        while (i < _enemyCount) {
            int index = random.nextInt(background.getRow() * background.getColumn());
            int x = background.getX(index);
            int y = background.getY(index);
            Tile tile = background.getTiles().get(index);
            if (x > 100 && y > 100 && tile.getItem() == TileItem.ROAD) {
                Enemy enemy = new Enemy();
                enemy.position.set(x, y);
                enemies.add(enemy);
                i++;
            }
        }
    }

    public void update() {
        enemies.forEach(enemy -> {
            enemy.update(background);
        });
    }

}