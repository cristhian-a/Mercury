package com.next.core.model.entity;

import com.next.graphics.GamePanel;
import com.next.io.KeyHandler;
import com.next.io.Sound;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.awt.*;
import java.awt.image.BufferedImage;

@Data
@EqualsAndHashCode(callSuper = true)
public class Player extends Entity {

    private GamePanel panel;
    private KeyHandler keyHandler;
    private int keysHeld;

    private boolean playPluckSFX;

    public Player(GamePanel panel, KeyHandler keyHandler) {
        this.panel = panel;
        this.keyHandler = keyHandler;

        this.screenX = panel.WIDTH / 2 - (panel.TILE_SIZE / 2);
        this.screenY = panel.HEIGHT / 2 - (panel.TILE_SIZE / 2);

        // adjust box position in relation to the player
        this.collisionBox = new Rectangle(screenX + 8, screenY + 16, 32, 32);
        this.collisionBoxDefaultX = collisionBox.x;
        this.collisionBoxDefaultY = collisionBox.y;
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

    public void pickupObject(int i) {
        if (i <= panel.objects.length) {
            var obj = panel.objects[i];
            switch (obj.name) {
                case "Key" -> {
                    keysHeld++;
                    panel.objects[i] = null;
                    panel.playSFX(Sound.Track.PICK_UP);
                    panel.ui.displayMessage("You found a key!");
                    System.out.println("AAAAI CHAVES: " + keysHeld);
                }
                case "Door" -> {
                    if (keysHeld > 0) {
                        keysHeld--;
                        panel.objects[i] = null;
                        panel.playSFX(Sound.Track.DOOR);
                    } else {
                        panel.ui.displayMessage("Grab a key first!");
                    }
                    System.out.println("AAAAI CHAVES: " + keysHeld);
                }
                case "Spell" -> {
                    speed += 5;
                    panel.objects[i] = null;
                    panel.playSFX(Sound.Track.SPELL_UP);
                    panel.ui.displayMessage("Mercury bless!!");
                }
                case "Chest" -> {
                    panel.finishGame();
                }
            }
        }
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

        int objIndex = panel.collisionChecker.checkObjectCollision(this, true);
        pickupObject(objIndex);

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

            if (this.direction != Orientation.NONE) {
                panel.playSFX(Sound.Track.WALK);
            }

            if (playPluckSFX) {
                playPluckSFX = false;
                panel.playSFX(Sound.Track.PLUCK);
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
