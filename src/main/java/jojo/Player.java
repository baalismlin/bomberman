package jojo;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.stream.Collectors;

import lombok.Getter;

public class Player extends Sprite {

    @Getter
    private BombGroup bombGroup;
    private TileGroup tilegroup;

    public Player(TileGroup tilegroup) {
        width = ImageLoader.getPlayerWidth();
        height = ImageLoader.getPlayerheight();
        speed = 2;
        position.set(ImageLoader.getTileWidth(), ImageLoader.getTileHeight());
        bombGroup = new BombGroup(tilegroup);
        this.tilegroup = tilegroup;
    }

    @Override
    public Image getImage() {
        Image image;
        if (isDied()) {
            image = ImageLoader.getPlayerDieImages().get(frame);
        } else {

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
        }
        return image;
    }

    public void update() {
        if (isDied()) {
            if (frame == 7) {
                visible = false;
            }
            loopFrame(7, 200);
        } else {

            if (!isMoving()) {
                return;
            }

            loopFrame(3, 50);

            increaseDelta();

            List<Sprite> tiles = tilegroup.getSurrounds(this).values().stream()
                    .filter(tile -> tile.getItem() == TileItem.WALL || tile.getItem() == TileItem.ICRONWALL)
                    .map(tile -> (Sprite) tile).collect(Collectors.toList());

            List<? extends Sprite> groupCollide = Sprite.groupCollide(this, tiles);
            if (groupCollide.size() > 0) {
                decreaseDelta();
            }
            if (groupCollide.size() == 1) {
                // 让角色在一定位置时能自动转弯
                Sprite tile = groupCollide.get(0);
                Position playerCenter = getCenter();
                Position tileCenter = tile.getCenter();
                int distanceX = Math.abs(playerCenter.getX() - tileCenter.getX());
                int distanceY = Math.abs(playerCenter.getY() - tileCenter.getY());
                if ((direction == Direction.UP || direction == Direction.DOWN) && distanceX >= width / 2) {
                    switch (getQuardrant(tile)) {
                    case 1:
                    case 4:
                        setDx(-1);
                        setDy(0);
                        updatePosition();
                        break;
                    case 2:
                    case 3:
                        setDx(1);
                        setDy(0);
                        updatePosition();
                        break;
                    default:
                        break;
                    }
                }

                if ((direction == Direction.LEFT || direction == Direction.RIGHT) && distanceY >= height / 2) {
                    switch (getQuardrant(tile)) {
                    case 1:
                    case 2:
                        setDx(0);
                        setDy(-1);
                        updatePosition();
                        break;
                    case 3:
                    case 4:
                        setDx(0);
                        setDy(1);
                        updatePosition();
                        break;
                    default:
                        break;
                    }
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
        case KeyEvent.VK_SPACE:
            dropBombEvent();
            break;
        }
    }

    private void dropBombEvent() {
        Position center = getCenter();
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

    public void enhance(Tile item) {
        System.out.println("get " + item.getItem().toString());

        // clear the item
        item.setValue(0);
    }

}