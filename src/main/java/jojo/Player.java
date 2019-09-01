package jojo;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Player extends Sprite {

    private int frame;

    public Player() {
        position = new Position(ImageLoader.getTileWidth(), ImageLoader.getTileHeight());
        width = ImageLoader.getPlayerWidth();
        height = ImageLoader.getPlayerheight();
        speed = 1;
        direction = Direction.DOWN;

    }

    public Image getImage() {
        Image image;
        switch (direction) {
        case LEFT:
            image = ImageLoader.getPlayerLeftImages().get(frame);
            break;
        case RIGHT:
            image = ImageLoader.getPlayerRightImages().get(frame);
            break;
        case UP:
            image = ImageLoader.getPlayerUpImages().get(frame);
            break;
        case DOWN:
        default:
            image = ImageLoader.getPlayerDownImages().get(frame);
            break;
        }
        return image;
    }

    public void update(List<Tile> tiles) {
        if (!isMoving()) {
            return;
        }

        frame = frame == 3 ? 0 : frame + 1;

        switch (direction) {
        case RIGHT:
            updatePosition(speed, 0);
            break;
        case LEFT:
            updatePosition(-1 * speed, 0);
            break;
        case UP:
            updatePosition(0, -1 * speed);
            break;
        case DOWN:
            updatePosition(0, speed);
            break;
        }

        for (int i = 0; i < tiles.size(); i++) {
            Tile tile = tiles.get(i);
            if (Sprite.collide(this, tile)) {

                switch (tile.getItem()) {
                case WALL:
                case ICRONWALL:
                    switch (direction) {
                    case RIGHT:
                        updatePosition(-1 * speed, 0);
                        break;
                    case LEFT:
                        updatePosition(speed, 0);
                        break;
                    case UP:
                        updatePosition(0, speed);
                        break;
                    case DOWN:
                        updatePosition(0, -1 * speed);
                        break;
                    }
                    break;
                default:
                    break;
                }

            }
        }
    }

    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
        case KeyEvent.VK_LEFT:
            directionEvent(Direction.LEFT);
            break;
        case KeyEvent.VK_RIGHT:
            directionEvent(Direction.RIGHT);
            break;
        case KeyEvent.VK_UP:
            directionEvent(Direction.UP);
            break;
        case KeyEvent.VK_DOWN:
            directionEvent(Direction.DOWN);
            break;
        }
    }

    private void directionEvent(Direction direction) {
        moving = true;
        if (this.direction != direction) {
            frame = 0;
        }
        this.direction = direction;
    }

    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
        case KeyEvent.VK_LEFT:
        case KeyEvent.VK_RIGHT:
        case KeyEvent.VK_UP:
        case KeyEvent.VK_DOWN:
            setMoving(false);
            break;
        }
    }

}