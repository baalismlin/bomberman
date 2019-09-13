package jojo;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.List;

import lombok.Getter;

public class Player extends Sprite {

    @Getter
    private BombGroup bombGroup;
    private Background background;

    public Player(Background background) {
        width = ImageLoader.getPlayerWidth();
        height = ImageLoader.getPlayerheight();
        speed = 2;
        position.set(ImageLoader.getTileWidth(), ImageLoader.getTileHeight());
        bombGroup = new BombGroup(background);
        this.background = background;
    }

    @Override
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

    public void update() {
        if (!isMoving()) {
            return;
        }

        var tiles = background.getSurrounds(this);

        loopFrame(3, 100);

        increaseDelta();

        var groupCollide = Sprite.groupCollide(this, List.copyOf(tiles.values()));

        boolean flag = groupCollide.stream().anyMatch(item -> {
            switch (((Tile) item).getItem()) {
            case WALL:
            case ICRONWALL:
                return true;
            default:
                return false;
            }
        });

        if (flag) {
            decreaseDelta();
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
        case KeyEvent.VK_SPACE:
            dropBombEvent();
            break;
        }
    }

    private void dropBombEvent() {
        var center = getCenter();
        bombGroup.dropBomb(center.getX(), center.getY());
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
            moving = false;
            break;
        }
    }

}