package com.next.core.model.entity;

import lombok.Data;

import java.awt.*;
import java.awt.image.BufferedImage;

@Data
public abstract class Entity {
    protected int worldX;
    protected int worldY;
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

    public abstract void tick();
    public abstract void render(Graphics2D g2);

    public enum Orientation {
        NONE, UP, DOWN, LEFT, RIGHT
    }
}
