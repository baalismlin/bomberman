package jojo;

import java.awt.Image;

import lombok.Getter;
import lombok.Setter;

public class Tile extends Sprite {

    @Getter
    @Setter
    private int value;

    @Getter
    @Setter
    private FireWall fireWall;

    public Tile(int x, int y) {
        this.position.set(x, y);
        width = ImageLoader.getTileWidth();
        height = ImageLoader.getTileHeight();
    }

    @Override
    public Image getImage() {
        var item = getItem();
        return ImageLoader.getItemImages().get(item);
    }

    public void combineItem(TileItem item) {
        this.value = this.value | item.getValue();
    }

    public void removeItem(TileItem item) {
        this.value = this.value ^ item.getValue();
    }

    public TileItem getItem() {
        return TileItem.toTileItem(value);
    }

    public void update() {
        // if (frame == 5) {
        // died = false;
        // value = value ^ TileItem.WALL.getValue();
        // }

        // if (isDied() && getItem() == TileItem.WALL) {
        // loopFrame(5, 200);
        // }
    }

}
