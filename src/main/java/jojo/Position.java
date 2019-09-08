package jojo;

import lombok.Getter;
import lombok.Setter;

public class Position {
    @Getter
    @Setter
    private int x;
    @Getter
    @Setter
    private int y;

    public Position() {
    }

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void update(int dx, int dy) {
        x += dx;
        y += dy;
    }

    public void set(int x, int y) {
        this.x = x;
        this.y = y;
    }

}