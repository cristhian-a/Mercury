package com.next.graphics;

import com.next.core.model.entity.Entity;
import com.next.core.model.entity.Player;

import java.awt.image.BufferedImage;

public class SpriteLoader {
    private final SpriteSheet spriteSheet;
    private final int tileWidth;
    private final int tileHeight;
    private final int tiles;
    private final int columns;
    private final int rows;
    private final int scale;

    private final BufferedImage[] images;

    public SpriteLoader(SpriteSheet sheet, int tiles, int tileWidth, int tileHeight, int columns, int rows, int scale) {
        this.spriteSheet = sheet;
        this.tiles = tiles;
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;
        this.columns = columns;
        this.rows = rows;
        this.scale = scale;

        images = new BufferedImage[tiles];
        splitImages();
    }

    private void splitImages() {
        int y = 0;
        int rowCount = 0;
        int index = 0;

        while (rowCount < rows) {
            int x = 0;
            int columnCount = 0;
            while (columnCount < columns) {
                if (index == tiles) {
                    break;
                }

                var img = spriteSheet.getSprite(x, y, tileWidth, tileHeight, scale);
                images[index] = img;
                index++;

                x += tileWidth;
                columnCount++;
            }

            y += tileHeight;
            rowCount++;
        }
    }

    public BufferedImage[] getSprites() {
        return images;
    }

    public BufferedImage getSprite(int index) {
        return images[index];
    }

    public BufferedImage getSprite(int x, int y) {
        if (x % tileWidth != 0 || y % tileHeight != 0) {
            throw new RuntimeException("Invalid coordinates!");
        }

        int col = x / tileWidth;
        int row = y / tileHeight;

        int index = row * rows + col;

        return images[index];
    }

    public void setMainSprite(Entity e, int index) {
        e.setSprite(images[index]);
    }

    public void setPlayerSprites(Player player) {
        player.setSprite(getSprite(32, 0));

        player.setDownSprites(new BufferedImage[] { images[3], images[4] });
        player.setUpSprites(new BufferedImage[] { images[6], images[7] });
        player.setRightSprites(new BufferedImage[] { images[9], images[10] });
        player.setLeftSprites(new BufferedImage[] { images[12], images[13] });
    }
}
