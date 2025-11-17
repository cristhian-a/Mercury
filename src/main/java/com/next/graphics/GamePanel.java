package com.next.graphics;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    private final int ORIGINAL_TILE_SIZE = 16;  // 16x16
    private final int SCALE = 3;
    private final int MAX_SCREEN_COL = 16;
    private final int MAX_SCREEN_ROW = 12;

    private final int TILE_SIZE;
    private final int WIDTH;
    private final int HEIGHT;

    public GamePanel() {
        super();
        TILE_SIZE = ORIGINAL_TILE_SIZE * SCALE;
        WIDTH = TILE_SIZE * MAX_SCREEN_COL;
        HEIGHT = TILE_SIZE * MAX_SCREEN_ROW;

        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.WHITE);
        g2.fillRect(100, 100, TILE_SIZE, TILE_SIZE);
        g2.dispose();
    }
}
