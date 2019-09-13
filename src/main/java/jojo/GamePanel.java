package jojo;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
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
    private Background background;

    public GamePanel() {
        background = new Background();
        player = new Player(background);
        enemyGroup = new EnemyGroup(background);

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
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        // draw background
        drawImage(g2d, background.getTiles());

        // draw enemies
        drawImage(g2d, enemyGroup.getEnemies());

        // draw bombs
        drawImage(g2d, player.getBombGroup().getBombs());

        // draw bomb fires
        var fireBombs = player.getBombGroup().getBombs().stream()
                .flatMap(bomb -> bomb.getFireBombGroup().getFireBombs().stream()).collect(Collectors.toList());

        drawImage(g2d, fireBombs);

        var fireWalls = background.getTiles().stream().filter(tile -> tile.getFireWall() != null)
                .map(tile -> tile.getFireWall()).collect(Collectors.toList());
        drawImage(g2d, fireWalls);

        // draw player
        drawImage(g2d, List.of(player));

        Toolkit.getDefaultToolkit().sync();
    }

    public void update() {

        background.update();

        enemyGroup.update();

        player.update();

        player.getBombGroup().update();

        player.getBombGroup().getBombs().forEach(bomb -> {
            bomb.getFireBombGroup().update();
        });
    }

    private void drawImage(Graphics2D g2d, List<? extends Sprite> sprites) {
        sprites.forEach(sprite -> {
            if (sprite.isVisible()) {
                g2d.drawImage(sprite.getImage(), sprite.position.getX(), sprite.position.getY(), this);
            }
        });
    }
}