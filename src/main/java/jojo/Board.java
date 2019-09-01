package jojo;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener {
    private static final long serialVersionUID = -200187670397841611L;
    private Player player;
    private Background background;
    private Timer timer;

    public Board() {
        player = new Player();
        background = new Background();

        addKeyListener(new CustomKeyAdapter(player));
        setFocusable(true);
        timer = new Timer(42, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        // draw background

        background.getTiles().forEach(tile -> {
            if (tile.isVisible())
                g2d.drawImage(tile.getImage(), tile.getPosition().getX(), tile.getPosition().getY(), this);
        });

        // draw player
        if (player.isVisible()) {
            g2d.drawImage(player.getImage(), player.position.getX(), player.position.getY(), this);
        }
        Toolkit.getDefaultToolkit().sync();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        background.update();

        var tiles = background.getSurrounds(player);
        player.update(tiles);

        repaint();
    }
}