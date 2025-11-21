package com.next.core.world;

import com.next.core.model.entity.Entity;
import com.next.graphics.GamePanel;

public class CollisionChecker {

    private GamePanel panel;

    public CollisionChecker(GamePanel panel) {
        this.panel = panel;
    }

    public void checkTile(Entity e) {
        var box = e.getCollisionBox();
        int entityLeftWorldX = e.getWorldX() + (box.x - e.getScreenX());
        int entityRightWorldX = e.getWorldX() + (box.x + box.width - e.getScreenX()) - panel.SCALE;
        int entityTopWorldY = e.getWorldY() + (box.y - e.getScreenY());
        int entityBottomWorldY = e.getWorldY() + (box.y + box.height - e.getScreenY()) - panel.SCALE;

        int entityLeftCol = entityLeftWorldX / panel.TILE_SIZE;
        int entityRightCol = entityRightWorldX / panel.TILE_SIZE;
        int entityTopRow = entityTopWorldY / panel.TILE_SIZE;
        int entityBottomRow = entityBottomWorldY / panel.TILE_SIZE;

        int tileNum1, tileNum2;

        switch (e.getDirection()) {
            case UP -> {
                entityTopRow = (entityTopWorldY - e.getSpeed()) / panel.TILE_SIZE;
                tileNum1 = panel.world.tileMap[entityTopRow][entityLeftCol];
                tileNum2 = panel.world.tileMap[entityTopRow][entityRightCol];
                if (panel.world.tiles[tileNum1].isSolid() || panel.world.tiles[tileNum2].isSolid()) {
                    e.setColliding(true);
                }
            }
            case DOWN -> {
                entityBottomRow = (entityBottomWorldY + e.getSpeed()) / panel.TILE_SIZE;
                tileNum1 = panel.world.tileMap[entityBottomRow][entityLeftCol];
                tileNum2 = panel.world.tileMap[entityBottomRow][entityRightCol];
                if (panel.world.tiles[tileNum1].isSolid() || panel.world.tiles[tileNum2].isSolid()) {
                    e.setColliding(true);
                }
            }
            case LEFT -> {
                entityLeftCol = (entityLeftWorldX - e.getSpeed()) / panel.TILE_SIZE;
                tileNum1 = panel.world.tileMap[entityTopRow][entityLeftCol];
                tileNum2 = panel.world.tileMap[entityBottomRow][entityLeftCol];
                if (panel.world.tiles[tileNum1].isSolid() || panel.world.tiles[tileNum2].isSolid()) {
                    e.setColliding(true);
                }
            }
            case RIGHT -> {
                entityRightCol = (entityRightWorldX + e.getSpeed()) / panel.TILE_SIZE;
                tileNum1 = panel.world.tileMap[entityTopRow][entityRightCol];
                tileNum2 = panel.world.tileMap[entityBottomRow][entityRightCol];
                if (panel.world.tiles[tileNum1].isSolid() || panel.world.tiles[tileNum2].isSolid()) {
                    e.setColliding(true);
                }
            }
        }
    }

    public int checkObjectCollision(Entity e, boolean isPlayer) {
        int index = 999;

        for (int i = 0; i < panel.objects.length; i++) {
            var o = panel.objects[i];
            var playerBox = e.getCollisionBox();
            if (o != null && e.getDirection() != Entity.Orientation.NONE) {

                int originalX = playerBox.x;
                int originalY = playerBox.y;
                int originalWidth = playerBox.width;
                int originalHeight = playerBox.height;

                // setting future position to check collision
                switch (e.getDirection()) {
                    case UP -> playerBox.y = playerBox.y - e.getSpeed();
                    case DOWN -> playerBox.y = playerBox.y + e.getSpeed();
                    case LEFT -> playerBox.x = playerBox.x - e.getSpeed();
                    case RIGHT -> playerBox.x = playerBox.x + e.getSpeed();
                }

                if (playerBox.intersects(o.getCollisionBox())) {
                    if (o.isSolid()) {
                        e.setColliding(true);
                    }

                    if (isPlayer) {
                        index = i;
                    }
                }

                // restoring values to their present state
                playerBox.x = originalX;
                playerBox.y = originalY;
                playerBox.width = originalWidth;
                playerBox.height = originalHeight;
            }
        }

        return index;
    }
}
