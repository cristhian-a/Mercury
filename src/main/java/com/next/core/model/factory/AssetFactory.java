package com.next.core.model.factory;

import com.next.core.model.entity.Chest;
import com.next.core.model.entity.Door;
import com.next.core.model.entity.Key;
import com.next.core.model.entity.Thing;
import com.next.graphics.GamePanel;
import com.next.graphics.SpriteLoader;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AssetFactory {
    private GamePanel panel;
    private SpriteLoader spriteLoader;

    public Thing[] getObjects(int length) {
        Thing[] t = new Thing[length];

        Thing obj0 = new Key();
        obj0.setWorldX(23 * panel.TILE_SIZE);
        obj0.setWorldY(7 * panel.TILE_SIZE);
        obj0.setImage(spriteLoader.getSprite(31));  // key

        Thing obj1 = new Key();
        obj1.setWorldX(23 * panel.TILE_SIZE);
        obj1.setWorldY(40 * panel.TILE_SIZE);
        obj1.setImage(spriteLoader.getSprite(31));  // key

        Thing obj2 = new Key();
        obj2.setWorldX(37 * panel.TILE_SIZE);
        obj2.setWorldY(9 * panel.TILE_SIZE);
        obj2.setImage(spriteLoader.getSprite(31));  // key

        Thing obj3 = new Door();
        obj3.setWorldX(10 * panel.TILE_SIZE);
        obj3.setWorldY(11 * panel.TILE_SIZE);
        obj3.setImage(spriteLoader.getSprite(29));   // Door

        Thing obj4 = new Door();
        obj4.setWorldX(8 * panel.TILE_SIZE);
        obj4.setWorldY(28 * panel.TILE_SIZE);
        obj4.setImage(spriteLoader.getSprite(29));   // Door

        Thing obj5 = new Door();
        obj5.setWorldX(12 * panel.TILE_SIZE);
        obj5.setWorldY(22 * panel.TILE_SIZE);
        obj5.setImage(spriteLoader.getSprite(29));   // Door

        Thing obj6 = new Chest();
        obj6.setWorldX(9 * panel.TILE_SIZE);
        obj6.setWorldY(7 * panel.TILE_SIZE);
        obj6.setImage(spriteLoader.getSprite(30));   // Chest

        t[0] = obj0;
        t[1] = obj1;
        t[2] = obj2;
        t[3] = obj3;
        t[4] = obj4;
        t[5] = obj5;
        t[6] = obj6;

        return t;
    }
}
