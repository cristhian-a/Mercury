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
        this.tileMap = new int[panel.MAX_SCREEN_ROW][panel.MAX_SCREEN_COL];

        loadTiles();
        loadMap("/maps/map1.txt");
    }

    private void loadTiles() {
        tiles[0] = new Tile(spriteLoader.getSprite(0), false);
        tiles[1] = new Tile(spriteLoader.getSprite(1), false);
        tiles[2] = new Tile(spriteLoader.getSprite(25), false);
    }

    public void loadMap(String path) {
        InputStream is = FileReader.getFile(path);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            int row = 0;

            while (row < panel.MAX_SCREEN_ROW) {
                String line = br.readLine();
                int col = 0;

                while (col < panel.MAX_SCREEN_COL) {
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
        int y = 0;

        while (row < panel.MAX_SCREEN_ROW) {
            int col = 0;
            int x = 0;

            while (col < panel.MAX_SCREEN_COL) {
                int tileIndex = tileMap[row][col];
                var image = tiles[tileIndex].getImage();

                g2.drawImage(image, x, y, panel.TILE_SIZE, panel.TILE_SIZE, null);
                x += panel.TILE_SIZE;
                col++;
            }

            y += panel.TILE_SIZE;
            row++;
        }
    }
}
