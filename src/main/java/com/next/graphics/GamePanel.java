package com.next.graphics;

import com.next.core.model.entity.Player;
import com.next.core.world.World;
import com.next.io.KeyHandler;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    public final int SCALE = 3;
    public final int MAX_SCREEN_COL = 16;
    public final int MAX_SCREEN_ROW = 12;
    public final int ORIGINAL_TILE_SIZE = 16;  // 16x16

    public final int TILE_SIZE;
    public final int WIDTH;
    public final int HEIGHT;

    private SpriteLoader spriteLoader;
    public World world;
    private KeyHandler keyHandler;
    public Player player;

    public GamePanel(KeyHandler keyHandler, SpriteLoader spriteLoader) {
        super();
        TILE_SIZE = ORIGINAL_TILE_SIZE * SCALE;
        WIDTH = TILE_SIZE * MAX_SCREEN_COL;
        HEIGHT = TILE_SIZE * MAX_SCREEN_ROW;

        this.keyHandler = keyHandler;

        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);

        player = new Player(this, keyHandler);
        world = new World(this, spriteLoader);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        world.render(g2);

        player.render(g2);
        g2.dispose();
    }
}
