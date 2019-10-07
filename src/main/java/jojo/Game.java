package jojo;

import javax.swing.JFrame;

public class Game extends JFrame {

    private static final long serialVersionUID = -3654694918062081841L;

    public Game() {
        add(new GamePanel());
        setResizable(false);
        pack();
        setSize(800, 500);
        setTitle("Bomber Man");
        setLocationRelativeTo(null);
        setAutoRequestFocus(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}