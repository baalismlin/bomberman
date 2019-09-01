package jojo;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

public class Sprite {

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

    public Sprite() {
        visible = true;
    }

    protected Position getCenter() {
        return new Position(position.getX() + width / 2, position.getY() + height / 2);
    }

    protected void updatePosition(int x, int y) {
        position.update(x, y);
    }

    public void setDirection(Direction direction) {
        this.prevDirection = this.direction;
        this.direction = direction;
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

    public static Sprite collideany(Sprite sprite, List<Sprite> sprites) {
        for (Sprite right : sprites) {
            if (collide(sprite, right)) {
                return right;
            }
        }
        return null;
    }
}