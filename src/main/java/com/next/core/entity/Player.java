package com.next.core.entity;

import com.next.graphics.GamePanel;
import com.next.io.KeyHandler;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.awt.*;

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
    }

    @Override
    public void tick() {
        if (keyHandler.upPressed) {
            this.y -= this.speed;
        }

        if (keyHandler.downPressed) {
            this.y += this.speed;
        }

        if (keyHandler.leftPressed) {
            this.x -= this.speed;
        }

        if (keyHandler.rightPressed) {
            this.x += this.speed;
        }
    }

    @Override
    public void render(Graphics2D g2) {
        g2.setColor(Color.WHITE);
        g2.fillRect(this.x, this.y, panel.TILE_SIZE, panel.TILE_SIZE);
    }
}
