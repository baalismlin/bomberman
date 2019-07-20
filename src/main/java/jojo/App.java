package jojo;

import java.awt.EventQueue;

/**
 * App entry
 *
 */
public class App {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            var game = new Game();
            game.setVisible(true);
        });

    }
}
