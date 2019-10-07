package jojo;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;

import javax.swing.JPanel;

public class GamePanel extends JPanel {
    private static final long serialVersionUID = -200187670397841611L;
    private static final int FPS = 40;
    private Player player;
    private EnemyGroup enemyGroup;
    private TileGroup tileGroup;
    private AffineTransform at;

    public GamePanel() {
        tileGroup = new TileGroup();
        player = new Player(tileGroup);
        enemyGroup = new EnemyGroup(tileGroup, player.getBombGroup());

        addKeyListener(new CustomKeyAdapter(player));
        setFocusable(true);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                update();
                repaint();
            }
        }, 0, 1000 / FPS);

        at = new AffineTransform();
        at.scale(2, 2);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.transform(at);

        // draw tileGroup
        drawImage(g2d, tileGroup.getTiles());

        // draw firewalls
        drawImage(g2d, tileGroup.getFireWalls());

        // draw bombs
        drawImage(g2d, getBombs());

        // draw bomb fires
        drawImage(g2d, getFireBombs());

        // draw enemies
        drawImage(g2d, enemyGroup.getEnemies());

        // draw player
        drawImage(g2d, Arrays.asList(player));

        Toolkit.getDefaultToolkit().sync();
    }

    public void update() {

        tileGroup.update();

        enemyGroup.update();

        player.update();

        player.getBombGroup().update();

        getBombs().forEach(bomb -> {
            bomb.getFireBombGroup().update();
        });

        if (!player.isDied() && isPlayerDied()) {
            player.setDied(true);
            player.setFrame(0);
        }

        List<? extends Sprite> diedEnemies = Sprite.groupCollide(getFireBombs(), enemyGroup.getVisibleEnemies(), 3);
        diedEnemies.forEach(enemy -> {
            if (!enemy.isDied()) {
                enemy.setDied(true);
                enemy.setFrame(4);
            }
        });

        List<Tile> items = tileGroup.getTiles().stream().filter(tile -> tile.getValue() > TileItem.WALL.getValue())
                .collect(Collectors.toList());
        Tile item = Sprite.singleCollide(player, items, 3);
        if (item != null) {
            player.enhance(item);
        }
    }

    private boolean isPlayerDied() {
        Enemy collideEnemy = Sprite.singleCollide(player, enemyGroup.getVisibleEnemies(), 3);
        if (collideEnemy != null) {
            return true;
        }

        FireWall collideFireWall = Sprite.singleCollide(player, tileGroup.getVisibleFireWalls(), 3);
        if (collideFireWall != null) {
            return true;
        }

        FireBomb collideFireBomb = Sprite.singleCollide(player, getFireBombs(), 3);
        if (collideFireBomb != null) {
            return true;
        }
        return false;
    }

    private List<Bomb> getBombs() {
        return player.getBombGroup().getBombs();
    }

    private List<FireBomb> getFireBombs() {
        return getBombs().stream().flatMap(bomb -> bomb.getFireBombGroup().getVisibleFireBombs().stream())
                .collect(Collectors.toList());
    }

    private void drawImage(Graphics2D g2d, List<? extends Sprite> sprites) {
        sprites.forEach(sprite -> {
            if (sprite.isVisible()) {
                g2d.drawImage(sprite.getImage(), sprite.position.getX(), sprite.position.getY(), null);
            }
        });
    }
}