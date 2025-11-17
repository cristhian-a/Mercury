package com.next.graphics;

import javax.swing.JFrame;

public class ProgramWindow {

    private JFrame window;

    public ProgramWindow() {
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
}
