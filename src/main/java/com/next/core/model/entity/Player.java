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

        setDefaultValues();
    }

    public void setDefaultValues() {
        this.x = 100;
        this.y = 100;
        this.speed = 4;

        this.maxIndex = 1;
        this.frameTransitionRate = 20;
        this.direction = Orientation.NONE;
    }

    @Override
    public void tick() {
        this.direction = Orientation.NONE;

        if (keyHandler.upPressed) {
            this.y -= this.speed;
            this.direction = Orientation.UP;
        }

        if (keyHandler.downPressed) {
            this.y += this.speed;
            this.direction = Orientation.DOWN;
        }

        if (keyHandler.leftPressed) {
            this.x -= this.speed;
            this.direction = Orientation.LEFT;
        }

        if (keyHandler.rightPressed) {
            this.x += this.speed;
            this.direction = Orientation.RIGHT;
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

        g2.drawImage(image, x, y, panel.TILE_SIZE, panel.TILE_SIZE, null);
    }
}
