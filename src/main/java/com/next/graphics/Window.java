package com.next.graphics;

import com.next.io.KeyHandler;
import lombok.Getter;

import javax.swing.JFrame;

@Getter
public class Window {

    private final JFrame window;
    private GamePanel panel;

    public Window() {
        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Project Mercury");
    }

    public void open(KeyHandler keyHandler) {
        panel = new GamePanel(keyHandler);
        window.add(panel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

    public void repaint() {
        window.repaint();
    }
}
