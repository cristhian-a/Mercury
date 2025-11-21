package com.next.graphics;

import com.next.game.Game;
import com.next.io.KeyHandler;
import lombok.Getter;

import javax.swing.JFrame;

@Getter
public class Window {

    private final JFrame window;
    private final Game game;
    private GamePanel panel;

    public Window(Game game) {
        window = new JFrame();
        this.game = game;
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Project Mercury");
    }

    public void open() {
        panel = new GamePanel(game);
        window.add(panel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

    public void repaint() {
        window.repaint();
    }
}
