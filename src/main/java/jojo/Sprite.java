package jojo;

import java.awt.Image;
import java.util.List;
import java.util.stream.Collectors;

import lombok.Getter;
import lombok.Setter;

public abstract class Sprite {

    @Getter
    @Setter
    protected Position position;
    @Getter
    @Setter
    protected int width;
    @Getter
    @Setter
    protected int height;
    @Getter
    @Setter
    protected boolean visible;
    @Getter
    @Setter
    protected boolean moving;
    @Getter
    @Setter
    protected int speed;
    @Getter
    protected Direction direction;
    @Getter
    @Setter
    protected Direction prevDirection;
    @Getter
    @Setter
    protected boolean died;
    @Getter
    @Setter
    protected int frame;
    @Getter
    @Setter
    private int dx;
    @Getter
    @Setter
    private int dy;

    public abstract Image getImage();

    public Sprite() {
        position = new Position();
        direction = Direction.DOWN;
        prevDirection = Direction.DOWN;
        visible = true;
    }

    public Position getCenter() {
        return new Position(position.getX() + width / 2, position.getY() + height / 2);
    }

    public void updatePosition() {
        position.update(dx, dy);
    }

    public void setDirection(Direction direction) {
        this.prevDirection = this.direction;
        this.direction = direction;
    }

    public void loopFrame(int limit) {
        frame = frame == limit ? 0 : frame + 1;
    }

    public void setDelta(int dxCoefficient, int dyCoefficient) {
        this.dx = dxCoefficient * speed;
        this.dy = dyCoefficient * speed;
    }

    private void updateDelta(int coefficient) {
        switch (direction) {
        case RIGHT:
            setDelta(1 * coefficient, 0);
            break;
        case LEFT:
            setDelta(-1 * coefficient, 0);
            break;
        case UP:
            setDelta(0, -1 * coefficient);
            break;
        case DOWN:
            setDelta(0, 1 * coefficient);
            break;
        default:
            break;
        }
    }

    public void increaseDelta() {
        updateDelta(1);
        updatePosition();
    }

    public void decreaseDelta() {
        updateDelta(-1);
        updatePosition();
    }

    public static boolean collide(Sprite left, Sprite right) {
        boolean xCollision = Math.abs((left.getPosition().getX() + left.getWidth() / 2)
                - (right.getPosition().getX() + right.getWidth() / 2)) < Math
                        .abs((left.getWidth() + right.getWidth()) / 2);
        boolean yCollision = Math.abs((left.getPosition().getY() + left.getHeight() / 2)
                - (right.getPosition().getY() + right.getHeight() / 2)) < Math
                        .abs((left.getHeight() + right.getHeight()) / 2);
        return xCollision && yCollision;
    }

    public static <T extends Sprite> T collideany(Sprite sprite, List<T> sprites) {
        for (T right : sprites) {
            if (collide(sprite, right)) {
                return right;
            }
        }
        return null;
    }

    public static List<? extends Sprite> groupCollide(Sprite sprite, List<? extends Sprite> sprites) {
        return sprites.stream().filter(item -> collide(sprite, item)).collect(Collectors.toList());
    }

}