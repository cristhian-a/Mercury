package com.next.graphics;

import java.awt.*;
import java.text.DecimalFormat;

public class UI {

    private final GamePanel panel;
    private final Font arial_30;
    private final Font arial_80B;
    private boolean messageOn;
    private String message;
    private int messageDisplayedFrames;

    private float playtime;
    private final DecimalFormat decimalFormat;

    public UI(GamePanel panel) {
        this.panel = panel;
        arial_30 = new Font("Arial", Font.PLAIN, 30);
        arial_80B = new Font("Arial", Font.BOLD, 80);
        decimalFormat = new DecimalFormat("#0.0");
    }

    public void displayMessage(String message) {
        this.message = message;
        messageOn = true;
    }

    public void render(Graphics2D g2) {
        if (panel.finished) {
            renderFinishMessage(g2);
            return;
        }

        g2.setFont(arial_30);
        g2.setColor(Color.WHITE);

        var image_key = panel.game.spriteLoader.getSprite(31);

        int baseX = 25;
        for (int i = 0; i < panel.player.getKeysHeld(); i++) {
            int x = baseX + ((panel.TILE_SIZE + 10) * i);
            g2.drawImage(image_key, x, 10, null);
        }

        if (messageOn) {
            g2.drawString(message, panel.player.getScreenX() - 90, panel.player.getScreenY() - 24);

            messageDisplayedFrames++;
            if (messageDisplayedFrames > 180) {
                messageOn = false;
                messageDisplayedFrames = 0;
            }
        }

        // displaying playtime
        playtime += 1f / 60;
//        String s = "Time: " + decimalFormat.format(playtime) + "s";
//        g2.drawString(s, panel.TILE_SIZE * 12, 46);
    }

    public void renderFinishMessage(Graphics2D g2) {
        g2.setColor(new Color(0, 0, 0, 100));
        g2.fillRect(0, 0, panel.WIDTH, panel.HEIGHT);

        g2.setFont(arial_30);
        g2.setColor(Color.WHITE);

        String text = "You are a treasure hunter master!";
        int textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();

        int x = panel.WIDTH / 2 - textLength / 2;
        int y = panel.HEIGHT / 2 - panel.TILE_SIZE;
        g2.drawString(text, x, y);

        text = "Final time: " + decimalFormat.format(playtime) + "s";
        textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();

        x = panel.WIDTH / 2 - textLength / 2;
        y = panel.HEIGHT / 2 + panel.TILE_SIZE * 2;
        g2.drawString(text, x, y);

        g2.setFont(arial_80B);
        g2.setColor(Color.ORANGE);

        text = "Congratulations!";
        textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();

        x = panel.WIDTH / 2 - textLength / 2;
        y = panel.HEIGHT / 2 - panel.TILE_SIZE * 2;
        g2.drawString(text, x, y);
    }
}
