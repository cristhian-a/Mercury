package com.next.graphics;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    private final int originalTileSize;
    private final int scale;
    private final int tilesize;
    private final int maxScreenCol;
    private final int maxScreenRow;
    private final int screenWidth;
    private final int screenHeight;

    public GamePanel() {
        super();
        originalTileSize = 16;  // 16x16
        scale = 3;
        tilesize = originalTileSize * scale;

        maxScreenCol = 16;
        maxScreenRow = 12;
        screenWidth = tilesize * maxScreenCol;
        screenHeight = tilesize * maxScreenRow;

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
    }
}
