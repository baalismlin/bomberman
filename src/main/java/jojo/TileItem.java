package jojo;

/*
road: 1
icronwall: 2
wall: 4
door: 8
REMOTECONTROL: 16,
LIFEUP: 32
SPEEDUP: 64
POWERUP: 128
BOMBUP: 256
*/
public enum TileItem {
    ROAD(0), ICRONWALL(1), WALL(2), DOOR(4), REMOTECONTROL(8), LIFEUP(16), SPEEDUP(32), POWERUP(64), BOMBUP(128);

    private final int value;

    private TileItem(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static TileItem toTileItem(int value) {

        if ((value & WALL.getValue()) != 0) {
            return WALL;
        } else if ((value & ICRONWALL.getValue()) != 0) {
            return ICRONWALL;
        } else if ((value & BOMBUP.getValue()) != 0) {
            return BOMBUP;
        } else if ((value & POWERUP.getValue()) != 0) {
            return POWERUP;
        } else if ((value & SPEEDUP.getValue()) != 0) {
            return SPEEDUP;
        } else if ((value & REMOTECONTROL.getValue()) != 0) {
            return REMOTECONTROL;
        } else if ((value & LIFEUP.getValue()) != 0) {
            return LIFEUP;
        } else if ((value & DOOR.getValue()) != 0) {
            return DOOR;
        } else {
            return ROAD;
        }
    }
}