package com.next.graphics;

import javax.swing.JFrame;

public class ProgramWindow {

    private JFrame window;

    public ProgramWindow() {
        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Project Mercury");

        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}
