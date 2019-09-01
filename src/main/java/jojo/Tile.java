package jojo;

import java.awt.Image;

import lombok.Getter;
import lombok.Setter;

public class Tile extends Sprite {

    @Getter
    @Setter
    private int value;

    public Tile(Position position) {
        this.position = position;
        width = ImageLoader.getTileWidth();
        height = ImageLoader.getTileHeight();
    }

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
