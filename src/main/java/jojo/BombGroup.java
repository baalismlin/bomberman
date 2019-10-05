package jojo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import lombok.Getter;

public class BombGroup {
    @Getter
    private List<Bomb> bombs;
    private final int _bombCount = 3;
    private TileGroup tilegroup;

    public BombGroup(TileGroup tilegroup) {
        bombs = new ArrayList<>(10);

        for (int i = 0; i < _bombCount; i++) {
            bombs.add(new Bomb());
        }

        this.tilegroup = tilegroup;
    }

    public void dropBomb(int palyerX, int playerY) {

        Optional<Bomb> availableBomb = bombs.stream().filter(bomb -> !bomb.isVisible()).findFirst();
        availableBomb.ifPresent(bomb -> {
            bomb.startTimer(tilegroup, palyerX, playerY);
        });
    }

    public void update() {
        bombs.stream().filter(bomb -> bomb.isVisible()).forEach(bomb -> {
            bomb.update();
        });
    }

    public List<Bomb> getVisibleBombs() {
        return bombs.stream().filter(bomb -> bomb.isVisible()).collect(Collectors.toList());
    }
}