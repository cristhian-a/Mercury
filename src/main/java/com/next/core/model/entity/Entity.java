package com.next.core.model.entity;

import lombok.Data;

import java.awt.*;
import java.awt.image.BufferedImage;

@Data
public abstract class Entity {
    protected int worldX;
    protected int worldY;
    protected int screenX;
    protected int screenY;
    protected int speed;

    protected BufferedImage sprite;
    protected BufferedImage[] upSprites;
    protected BufferedImage[] downSprites;
    protected BufferedImage[] leftSprites;
    protected BufferedImage[] rightSprites;

    protected int spriteIndex;
    protected int maxIndex;
    protected int frame;
    protected int frameTransitionRate;

    protected Orientation direction;

    protected Rectangle collisionBox;
    protected int collisionBoxDefaultX;
    protected int collisionBoxDefaultY;
    protected boolean colliding;

    public abstract void tick();
    public void render(Graphics2D g2) {
        // drawing the hit box
        g2.setColor(Color.RED);
        if (isColliding())
            g2.setColor(Color.WHITE);
        g2.draw(collisionBox);
    }

    public enum Orientation {
        NONE, UP, DOWN, LEFT, RIGHT
    }
}
