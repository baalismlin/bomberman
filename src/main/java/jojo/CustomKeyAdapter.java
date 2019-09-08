package jojo;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class CustomKeyAdapter extends KeyAdapter {
    private Player player;
    // private Tile tile;
    // private Background background;

    public CustomKeyAdapter(Player player) {
        this.player = player;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        player.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        player.keyReleased(e);
    }
}
