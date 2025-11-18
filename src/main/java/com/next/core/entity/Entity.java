package com.next.core.entity;

import lombok.Data;

import java.awt.*;

@Data
public abstract class Entity {
    protected int x;
    protected int y;
    protected int speed;

    public abstract void tick();
    public abstract void render(Graphics2D g2);
}
