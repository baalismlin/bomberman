package jojo;

import java.awt.Image;

public class FireWall extends Sprite {

    public FireWall(int x, int y) {
        width = ImageLoader.getTileWidth();
        height = ImageLoader.getTileHeight();
        position.set(x, y);
    }

    public void update() {
        if (!isVisible()) {
            return;
        }
        if (frame == 5) {
            visible = false;
        }
        loopFrame(5, 200);

    }

    @Override
    public Image getImage() {
        return ImageLoader.getWallImages().get(frame);
    }

}