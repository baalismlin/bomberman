package jojo;

import lombok.Getter;

public enum Direction {

    CENTER(0), LEFT(1), RIGHT(2), UP(3), DOWN(4), LEFT_UP(5), RIGHT_UP(6), LEFT_DOWN(7), RIGHT_DOWN(8);

    @Getter
    private final int value;

    private Direction(int value) {
        this.value = value;
    }

    public static Direction toDirection(int value) {
        switch (value) {
        case 0:
            return CENTER;
        case 1:
            return LEFT;
        case 2:
            return RIGHT;
        case 3:
            return UP;
        case 4:
            return DOWN;
        default:
            return DOWN;
        }
    }

}
