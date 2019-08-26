package jojo;

import javax.swing.JFrame;

public class Game extends JFrame {

    private static final long serialVersionUID = -3654694918062081841L;

    public Game() {
        add(new Board());
        setResizable(false);
        pack();
        setSize(400, 300);
        setTitle("Bomber Man");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

}