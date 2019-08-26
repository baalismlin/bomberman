package jojo;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class CustomKeyAdapter extends KeyAdapter {
    private Player player;

    public CustomKeyAdapter(Player player) {
        this.player = player;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
        case KeyEvent.VK_LEFT:
            player.startMove(Direction.LEFT, -1, 0);
            break;
        case KeyEvent.VK_RIGHT:
            player.startMove(Direction.RIGHT, 1, 0);
            break;
        case KeyEvent.VK_UP:
            player.startMove(Direction.UP, 0, -1);
            break;
        case KeyEvent.VK_DOWN:
            player.startMove(Direction.DOWN, 0, 1);
            break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
        case KeyEvent.VK_LEFT:
        case KeyEvent.VK_RIGHT:
        case KeyEvent.VK_UP:
        case KeyEvent.VK_DOWN:
            break;
        }
    }
}
