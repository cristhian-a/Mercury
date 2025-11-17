package com.next.graphics;

import lombok.Getter;

import javax.swing.JFrame;

@Getter
public class Window {

    private final JFrame window;

    public Window() {
        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Project Mercury");
    }

    public void open() {
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

    public void repaint() {
        window.repaint();
    }
}
