package jojo;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Stack;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        Player player = new Player(null) {
            {
                position.set(32, 16);
            }
        };
        List<Enemy> enemies = Arrays.asList(new Enemy() {
            {
                position.set(32, 16);
            }
        }, new Enemy() {
            {
                position.set(16, 16);
            }
        });

        Enemy ss = Sprite.singleCollide(player, enemies);

        assertTrue(ss != null);
    }

    @Test
    public void test2() {
        TileGroup tilegroup = new TileGroup();
        Player player = new Player(tilegroup);
        EnemyGroup enemyGroup = new EnemyGroup(tilegroup, player.getBombGroup());
        Optional<Enemy> enemy = enemyGroup.getEnemies().stream().findAny();
        Stack<Integer> route = enemy.get().findRoute(tilegroup);
        assertTrue(route.size() > 0);
    }
}
