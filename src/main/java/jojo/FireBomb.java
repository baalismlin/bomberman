package jojo;

import java.awt.Image;

public class FireBomb extends Sprite {

    public FireBomb(int x, int y, Direction direction) {
        position.set(x, y);
        this.direction = direction;
    }

    public void update() {
        if (frame == 3) {
            visible = false;
        }
        loopFrame(3, 300);
    }

    @Override
    public Image getImage() {
        switch (direction) {
        case CENTER:
            return ImageLoader.getCenterFireImages().get(frame);
        case UP:
            return ImageLoader.getUpFireImages().get(frame);
        case DOWN:
            return ImageLoader.getDownFireImages().get(frame);
        case LEFT:
            return ImageLoader.getLeftFireImages().get(frame);
        case RIGHT:
            return ImageLoader.getRightFireImages().get(frame);
        default:
            return null;
        }
    }

}