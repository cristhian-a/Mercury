package com.next.core.model.entity;

import com.next.graphics.GamePanel;
import com.next.io.KeyHandler;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.awt.*;
import java.awt.image.BufferedImage;

@Data
@EqualsAndHashCode(callSuper = true)
public class Player extends Entity {

    private GamePanel panel;
    private KeyHandler keyHandler;

    public Player(GamePanel panel, KeyHandler keyHandler) {
        this.panel = panel;
        this.keyHandler = keyHandler;

        this.screenX = panel.WIDTH / 2 - (panel.TILE_SIZE / 2);
        this.screenY = panel.HEIGHT / 2 - (panel.TILE_SIZE / 2);

        // adjust box position in relation to the player
        this.collisionBox = new Rectangle(screenX + 8, screenY + 16, 32, 32);
        this.colliding = true;

        setDefaultValues();
    }

    public void setDefaultValues() {
        this.worldX = panel.TILE_SIZE * 23;
        this.worldY = panel.TILE_SIZE * 21;
        this.speed = 5;

        this.maxIndex = 1;
        this.frameTransitionRate = 20;
        this.direction = Orientation.NONE;
    }

    @Override
    public void tick() {
        this.direction = Orientation.NONE;
        if (keyHandler.upPressed) {
            this.direction = Orientation.UP;
        } else if (keyHandler.downPressed) {
            this.direction = Orientation.DOWN;
        } else if (keyHandler.leftPressed) {
            this.direction = Orientation.LEFT;
        } else if (keyHandler.rightPressed) {
            this.direction = Orientation.RIGHT;
        }

        this.colliding = false;
        panel.collisionChecker.checkTile(this);

        if (!this.colliding) {
            switch (this.direction) {
                case UP -> this.worldY -= this.speed;
                case DOWN -> this.worldY += this.speed;
                case LEFT -> this.worldX -= this.speed;
                case RIGHT -> this.worldX += this.speed;
            }
        }

        frame++;
        if (frame > frameTransitionRate) {
            frame = 0;
            spriteIndex++;
            if (spriteIndex > maxIndex) {
                spriteIndex = 0;
            }
        }
    }

    @Override
    public void render(Graphics2D g2) {
        BufferedImage image = switch (direction) {
            case UP -> this.upSprites[this.spriteIndex];
            case DOWN -> this.downSprites[this.spriteIndex];
            case LEFT -> this.leftSprites[this.spriteIndex];
            case RIGHT -> this.rightSprites[this.spriteIndex];
            default -> this.sprite;
        };

        g2.drawImage(image, screenX, screenY, panel.TILE_SIZE, panel.TILE_SIZE, null);

        // drawing the hit box
        super.render(g2);
    }
}
