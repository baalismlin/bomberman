package jojo;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Image;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener {
    private static final long serialVersionUID = -200187670397841611L;
    private Player player;
    private Tile tile;
    private Background background;
    private Timer timer;

    public Board() {
        player = new Player();
        tile = new Tile();
        background = new Background();

        player.setX(tile.getWidth());
        player.setY(tile.getHeight());

        addKeyListener(new CustomKeyAdapter(player));
        setFocusable(true);
        timer = new Timer(10, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        // draw background

        for (int x = 0; x < background.getWidth(); x++) {
            for (int y = 0; y < background.getHeight(); y++) {

                int value = background.getValue(x, y);
                TileItem item = TileItem.toTileItem(value);
                Image imgTile = tile.getImage(item);
                int currentX = tile.getWidth() * x;
                int currentY = tile.getHeight() * y;

                g2d.drawImage(imgTile, currentX, currentY, this);

            }
        }

        // draw player
        g2d.drawImage(player.getImage(), player.getX(), player.getY(), this);
        Toolkit.getDefaultToolkit().sync();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}