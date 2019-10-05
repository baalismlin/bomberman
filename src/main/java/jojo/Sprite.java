package jojo;

import java.awt.Image;
import java.util.ArrayList;
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

    private long before = System.nanoTime();

    public void loopFrame(int limit, int delay) {
        long current = System.nanoTime();
        if ((current - before) / 1000000L > delay) {
            frame = frame == limit ? 0 : frame + 1;
            before = current;
        }
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

    // 1:第一象限 2:第二象限 3:第三象限 4:第四象限
    // 0:坐标轴上
    public int getQuardrant(Sprite another) {
        Position center = getCenter();
        Position anotherCenter = another.getCenter();
        int differenceX = anotherCenter.getX() - center.getX();
        int differenceY = anotherCenter.getY() - center.getY();

        if (differenceX == 0 || differenceY == 0) {
            return 0;
        }

        return differenceX > 0 ? (differenceY > 0 ? 1 : 4) : (differenceY > 0 ? 2 : 3);

        // if (differenceX > 0) {
        // if (differenceY > 0) {
        // return 1;
        // } else {
        // return 4;
        // }
        // } else {
        // if (differenceY > 0) {
        // return 2;
        // } else {
        // return 3;
        // }
        // }

    }

    public static boolean collide(Sprite left, Sprite right, int offset) {
        Position leftCenter = left.getCenter();
        Position rightCenter = right.getCenter();
        boolean xC = Math
                .abs(leftCenter.getX() - rightCenter.getX()) < ((left.getWidth() + right.getWidth()) / 2 - offset);
        boolean yC = Math
                .abs(leftCenter.getY() - rightCenter.getY()) < ((left.getHeight() + right.getHeight()) / 2 - offset);
        return xC && yC;
    }

    public static boolean collide(Sprite left, Sprite right) {

        return collide(left, right, 0);

    }

    public static <T extends Sprite> T singleCollide(Sprite sprite, List<T> sprites, int offset) {
        for (T right : sprites) {
            if (collide(sprite, right, offset)) {
                return right;
            }
        }
        return null;
    }

    public static <T extends Sprite> T singleCollide(Sprite sprite, List<T> sprites) {
        return singleCollide(sprite, sprites, 0);
    }

    public static List<? extends Sprite> groupCollide(Sprite sprite, List<? extends Sprite> sprites, int offset) {
        return sprites.stream().filter(item -> collide(sprite, item, offset)).collect(Collectors.toList());
    }

    public static List<? extends Sprite> groupCollide(Sprite sprite, List<? extends Sprite> sprites) {
        return groupCollide(sprite, sprites, 0);
    }

    public static List<? extends Sprite> groupCollide(List<? extends Sprite> leftSprites,
            List<? extends Sprite> rightSprites, int offset) {
        List<Sprite> result = new ArrayList<>();
        for (Sprite left : leftSprites) {
            for (Sprite right : rightSprites) {
                if (collide(left, right, offset)) {
                    result.add(right);
                }
            }
        }
        return result;
    }

    public static List<? extends Sprite> groupCollide(List<? extends Sprite> leftSprites,
            List<? extends Sprite> rightSprites) {
        return groupCollide(leftSprites, rightSprites, 0);
    }

}