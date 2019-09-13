package jojo;

import java.awt.Image;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class Enemy extends Sprite {

    private static final Random RANDOM = new Random(new Date().getTime());

    public Enemy() {
        width = ImageLoader.getEnemyWidth();
        height = ImageLoader.getEnemyHeight();
        speed = 1;
        moving = true;
    }

    @Override
    public Image getImage() {
        Integer level = Integer.valueOf(0);
        Image image = ImageLoader.getEnemyImages().get(level).get(frame);
        return image;
    }

    public void update(Background background) {
        if (!isMoving()) {
            return;
        }
        loopFrame(3, 300);

        randomDirection();

        var tiles = background.getSurrounds(this);

        increaseDelta();

        var groupCollide = Sprite.groupCollide(this, List.copyOf(tiles.values()));

        boolean noMoving = groupCollide.stream().anyMatch(item -> {
            switch (((Tile) item).getItem()) {
            case WALL:
            case ICRONWALL:
                return true;
            default:
                return false;
            }
        });

        if (noMoving) {
            decreaseDelta();
        }
    }

    private void randomDirection() {
        if (RANDOM.nextInt(10) > 8) {
            direction = Direction.toDirection(RANDOM.nextInt(3) + 1);
        }
    }

}