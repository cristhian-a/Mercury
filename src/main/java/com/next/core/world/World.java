package com.next.core.world;

import com.next.graphics.GamePanel;
import com.next.graphics.SpriteLoader;
import com.next.io.FileReader;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class World {
    private SpriteLoader spriteLoader;
    private GamePanel panel;
    private Tile[] tiles;
    private int tileMap[][];

    public World(GamePanel panel, SpriteLoader loader) {
        this.spriteLoader = loader;
        this.panel = panel;
        this.tiles = new Tile[10];
        this.tileMap = new int[panel.MAX_WORLD_ROW][panel.MAX_WORD_COL];

        loadTiles();
        loadMap("/maps/world_01.txt");
    }

    private void loadTiles() {
        tiles[0] = new Tile(spriteLoader.getSprite(0), false);  // grass
        tiles[1] = new Tile(spriteLoader.getSprite(1), false);  // wall
        tiles[2] = new Tile(spriteLoader.getSprite(25), false); // water
        tiles[3] = new Tile(spriteLoader.getSprite(27), false); // dirt
        tiles[4] = new Tile(spriteLoader.getSprite(26), false); // tree
        tiles[5] = new Tile(spriteLoader.getSprite(28), false); // sand
    }

    public void loadMap(String path) {
        InputStream is = FileReader.getFile(path);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            int row = 0;

            while (row < panel.MAX_WORLD_ROW) {
                String line = br.readLine();
                int col = 0;

                while (col < panel.MAX_WORD_COL) {
                    String[] numbers = line.split(" ");
                    tileMap[row][col] = Integer.parseInt(numbers[col]);
                    col++;
                }

                row++;
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void render(Graphics2D g2) {
        int row = 0;

        while (row < panel.MAX_WORLD_ROW) {
            int col = 0;

            while (col < panel.MAX_WORD_COL) {
                int tileIndex = tileMap[row][col];
                var image = tiles[tileIndex].getImage();

                int worldX = col * panel.TILE_SIZE;
                int worldY = row * panel.TILE_SIZE;
                int x = worldX - panel.player.getWorldX() + panel.player.getScreenX();
                int y = worldY - panel.player.getWorldY() + panel.player.getScreenY();

                if (worldX + panel.TILE_SIZE > panel.player.getWorldX() - panel.player.getScreenX() &&
                        worldX - panel.TILE_SIZE < panel.player.getWorldX() + panel.player.getScreenX() &&
                        worldY + panel.TILE_SIZE > panel.player.getWorldY() - panel.player.getScreenY() &&
                        worldY - panel.TILE_SIZE < panel.player.getWorldY() + panel.player.getScreenY()
                ) {
                    g2.drawImage(image, x, y, panel.TILE_SIZE, panel.TILE_SIZE, null);
                }
                col++;
            }

            row++;
        }
    }
}
