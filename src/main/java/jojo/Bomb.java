package jojo;

import java.awt.Image;
import java.util.Timer;
import java.util.TimerTask;

import lombok.Getter;

public class Bomb extends Sprite {

    private Timer timer;
    @Getter
    private FireBombGroup fireBombGroup;

    public Bomb() {
        width = ImageLoader.getBombWidth();
        height = ImageLoader.getBombHeight();
        fireBombGroup = new FireBombGroup();
        visible = false;
    }

    public void update() {
        loopFrame(3, 200);

    }

    public void startTimer(TileGroup tilegroup, int palyerX, int playerY) {
        int currentCol = tilegroup.getColumn(palyerX);
        int currentRow = tilegroup.getRow(playerY);
        int index = tilegroup.getIndex(currentCol, currentRow);
        position.set(tilegroup.getX(index), tilegroup.getY(index));
        visible = true;
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                visible = false;
                fireBombGroup.addFires(tilegroup, index);
            }
        }, 3000);
    }

    @Override
    public Image getImage() {
        return ImageLoader.getBombImages().get(frame);
    }

}