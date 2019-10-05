package jojo;

import java.awt.Image;

import lombok.Getter;
import lombok.Setter;

public class Tile extends Sprite {

    @Getter
    @Setter
    private int value;

    public Tile(int x, int y) {
        this.position.set(x, y);
        width = ImageLoader.getTileWidth();
        height = ImageLoader.getTileHeight();
    }

    @Override
    public Image getImage() {
        TileItem item = getItem();
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

    }

}
