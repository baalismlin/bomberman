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
            player.setDx(-1);
            player.startMove(Direction.LEFT);
            break;
        case KeyEvent.VK_RIGHT:
            player.setDx(1);
            player.startMove(Direction.RIGHT);
            break;
        case KeyEvent.VK_UP:
            player.setDy(-1);
            player.startMove(Direction.UP);
            break;
        case KeyEvent.VK_DOWN:
            player.setDy(1);
            player.startMove(Direction.DOWN);
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
            player.setDx(0);
            player.setDy(0);
            break;
        }
    }
}
