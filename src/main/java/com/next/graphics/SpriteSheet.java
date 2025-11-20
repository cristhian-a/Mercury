package com.next.graphics;

import com.next.io.FileReader;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SpriteSheet {
    private final BufferedImage spriteSheet;

    public SpriteSheet(String path) throws IOException {
        this.spriteSheet = ImageIO.read(FileReader.getFile(path));
    }

    public BufferedImage getSprite(int x, int y, int width, int height) {
        return spriteSheet.getSubimage(x, y, width, height);
    }
}
