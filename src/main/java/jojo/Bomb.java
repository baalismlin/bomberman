package jojo;

import java.awt.Image;

public class Bomb extends Sprite {

    public Bomb() {
        width = ImageLoader.getBombWidth();
        height = ImageLoader.getBombHeight();

        visible = false;
    }

    public void update() {
        loopFrame(3);
    }

    @Override
    public Image getImage() {
        return ImageLoader.getBombImages().get(frame);
    }

}