package com.next.graphics;

import com.next.core.model.entity.Player;
import com.next.core.model.entity.Thing;
import com.next.core.model.factory.AssetFactory;
import com.next.core.world.CollisionChecker;
import com.next.core.world.World;
import com.next.game.Game;
import com.next.io.Sound;

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

    public final Game game;

    // World setting
    public final int MAX_WORD_COL;
    public final int MAX_WORLD_ROW;
    public final int WORLD_WIDTH;
    public final int WORLD_HEIGHT;
    public World world;
    public CollisionChecker collisionChecker;

    public Player player;

    public AssetFactory assetFactory;
    public Thing[] objects;

    public GamePanel(Game game) {
        super();
        TILE_SIZE = ORIGINAL_TILE_SIZE * SCALE;
        WIDTH = TILE_SIZE * MAX_SCREEN_COL;
        HEIGHT = TILE_SIZE * MAX_SCREEN_ROW;

        MAX_WORD_COL = 50;
        MAX_WORLD_ROW = 50;
        WORLD_WIDTH = TILE_SIZE * MAX_WORD_COL;
        WORLD_HEIGHT = TILE_SIZE * MAX_WORLD_ROW;

        this.game = game;

        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(game.keyHandler);
        this.setFocusable(true);

        this.player = new Player(this, game.keyHandler);
        this.world = new World(this, game.spriteLoader);
        this.assetFactory = new AssetFactory(this, game.spriteLoader);

        this.collisionChecker = new CollisionChecker(this);
    }

    public void setup() {
        objects = assetFactory.getObjects(10);
        playMusic(Sound.Track.WIND);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        world.render(g2);

        for (Thing object : objects) {
            if (object != null) {
                object.render(g2, this);
            }
        }

        player.render(g2);
        g2.dispose();
    }

    public void playMusic(Sound.Track track) {
        game.sound.setFile(track);
        game.sound.play();
        game.sound.loop();
    }

    public void stopMusic() {
        game.sound.stop();
    }

    public void playSFX(Sound.Track track) {
        game.sound.setFile(track);
        game.sound.play();
    }
}
