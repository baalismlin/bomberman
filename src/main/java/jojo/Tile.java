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
        var item = getItem();
        return ImageLoader.getTileItems().get(item);
    }

    public void updateValue(int value) {
        this.value += value;
    }

    public TileItem getItem() {
        return TileItem.toTileItem(value);
    }

}
