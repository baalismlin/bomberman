package jojo;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class CustomKeyAdapter extends KeyAdapter {
    private Player player;
    // private Tile tile;
    // private TileGroup tilegroup;

    public CustomKeyAdapter(Player player) {
        this.player = player;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!player.isDied())
            player.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (!player.isDied())
            player.keyReleased(e);
    }
}
