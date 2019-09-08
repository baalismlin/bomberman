package jojo;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener {
    private static final long serialVersionUID = -200187670397841611L;
    private Player player;
    private EnemyGroup enemyGroup;
    private Background background;
    private Timer timer;

    public Board() {
        background = new Background();
        player = new Player(background);
        enemyGroup = new EnemyGroup(background);

        addKeyListener(new CustomKeyAdapter(player));
        setFocusable(true);
        timer = new Timer(40, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        // draw background
        drawImage(g2d, background.getTiles());

        // draw enemies
        drawImage(g2d, enemyGroup.getEnemies());

        // draw bombs
        drawImage(g2d, player.getBombGroup().getBombs());

        // draw player
        drawImage(g2d, List.of(player));

        Toolkit.getDefaultToolkit().sync();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        background.update();

        enemyGroup.update();

        player.update();

        player.getBombGroup().update();

        repaint();
    }

    private void drawImage(Graphics2D g2d, List<? extends Sprite> sprites) {
        sprites.forEach(sprite -> {
            if (sprite.isVisible()) {
                g2d.drawImage(sprite.getImage(), sprite.position.getX(), sprite.position.getY(), this);
            }
        });
    }
}