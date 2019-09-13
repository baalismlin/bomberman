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

    public void dropBomb(int palyerX, int playerY) {

        Optional<Bomb> availableBomb = bombs.stream().filter(bomb -> !bomb.isVisible()).findFirst();
        availableBomb.ifPresent(bomb -> {
            bomb.startTimer(background, palyerX, playerY);
        });
    }

    public void update() {
        bombs.stream().filter(bomb -> bomb.isVisible()).forEach(bomb -> {
            bomb.update();
        });
    }
}