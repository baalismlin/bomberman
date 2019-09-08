package jojo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import lombok.Getter;

public class BombGroup {
    @Getter
    private List<Bomb> bombs;
    private final int _bombCount = 3;
    private Background background;

    public BombGroup(Background background) {
        bombs = new ArrayList<>(10);

        for (int i = 0; i < _bombCount; i++) {
            bombs.add(new Bomb());
        }

        this.background = background;
    }

    public void dropBomb(int x, int y) {
        Optional<Bomb> availableBomb = bombs.stream().filter(bomb -> !bomb.isVisible()).findFirst();
        availableBomb.ifPresent(bomb -> {
            int currentCol = background.getColumn(x);
            int currentRow = background.getRow(y);
            int index = background.getIndex(currentCol, currentRow);
            bomb.position.set(background.getX(index), background.getY(index));
            bomb.setVisible(true);
        });
    }

    public void update() {
        bombs.forEach(bomb -> {
            bomb.update();
        });
    }
}