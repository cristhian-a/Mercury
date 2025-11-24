package com.next.graphics;

import com.next.io.FileReader;

import javax.imageio.ImageIO;
import java.awt.*;
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

    public BufferedImage getSprite(int x, int y, int width, int height, int scale) {
        var subImage = spriteSheet.getSubimage(x, y, width, height);

        int scaledWidth = width * scale;
        int scaledHeight = height * scale;

        var scaledImage = new BufferedImage(scaledWidth, scaledHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = scaledImage.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
        g2.drawImage(subImage, 0, 0, scaledWidth, scaledHeight, null);
        return scaledImage;
    }
}
