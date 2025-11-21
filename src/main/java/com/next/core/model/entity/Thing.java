package com.next.core.model.entity;

import com.next.graphics.GamePanel;
import lombok.Data;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Class for objects that are displayed in the scene and are neither tiles nor entities
 */
@Data
public class Thing {
    protected BufferedImage image;
    protected String name;
    protected boolean solid;
    protected int worldX;
    protected int worldY;
    protected Rectangle collisionBox;
    protected int collisionBoxDefaultX;
    protected int collisionBoxDefaultY;

    public Thing() {
        collisionBox = new Rectangle(worldX, worldY, 48, 48);
    }

    public void render(Graphics2D g2, GamePanel panel) {
        int x = worldX - panel.player.getWorldX() + panel.player.getScreenX();
        int y = worldY - panel.player.getWorldY() + panel.player.getScreenY();

        if (worldX + panel.TILE_SIZE > panel.player.getWorldX() - panel.player.getScreenX() &&
                worldX - panel.TILE_SIZE < panel.player.getWorldX() + panel.player.getScreenX() &&
                worldY + panel.TILE_SIZE > panel.player.getWorldY() - panel.player.getScreenY() &&
                worldY - panel.TILE_SIZE < panel.player.getWorldY() + panel.player.getScreenY()
        ) {
            g2.drawImage(image, x, y, panel.TILE_SIZE, panel.TILE_SIZE, null);

            // updating collision box values
            collisionBox.x = x;
            collisionBox.y = y;

            // drawing the box
            g2.setColor(Color.RED);
            g2.draw(collisionBox);
        }
    }
}
