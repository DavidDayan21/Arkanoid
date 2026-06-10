package game;

import engine.GameWindow;
import engine.Surface;
import engine.Mouse;
import java.awt.Color;
public class ThemeScreen {
    private final GameWindow window;

    private static final int CARD_W = 180, CARD_H = 110;
    private static final int NAME_STRIP_H = 20;
    private static final int PREVIEW_H = CARD_H - NAME_STRIP_H;
    private static final int[] COL_X = {60, 310, 560};
    private static final int[] ROW_Y = {120, 255};

    private static final int BACK_X = 25, BACK_Y = 20, BACK_W = 90, BACK_H = 35;

    public ThemeScreen(GameWindow window) {
        this.window = window;
    }

    public void show() {
        Mouse mouse = window.getMouse();
        int frameTime = 1000 / GameConfig.FPS;
        Theme[] themes = Theme.values();

        while (true) {
            long t0 = System.currentTimeMillis();

            Surface d = window.getSurface();
            d.setColor(new Color(20, 20, 30));
            d.fillRectangle(0, 0, GameConfig.WIDTH, GameConfig.HEIGHT);

            // Back button
            d.setColor(new Color(60, 60, 80));
            d.fillRoundRect(BACK_X, BACK_Y, BACK_W, BACK_H, 8);
            d.drawTextInRect(BACK_X, BACK_Y, BACK_W, BACK_H, "← Back", 14, Color.WHITE);

            // Title
            d.drawTextCentered(95, "THEMES", 26, Color.WHITE);

            // Cards
            for (int i = 0; i < themes.length; i++) {
                int col = i % 3;
                int row = i / 3;
                int cx = COL_X[col];
                int cy = ROW_Y[row];
                Theme t = themes[i];
                boolean selected = (t == Settings.getTheme());
                drawThemeCard(d, cx, cy, t, selected);
            }

            window.show(d);

            // Back click
            if (mouse.clickedInside(BACK_X, BACK_Y, BACK_W, BACK_H)) return;

            // Card clicks
            for (int i = 0; i < themes.length; i++) {
                int col = i % 3;
                int row = i / 3;
                int cx = COL_X[col];
                int cy = ROW_Y[row];
                if (mouse.clickedInside(cx, cy, CARD_W, CARD_H)) {
                    Settings.setTheme(themes[i]);
                }
            }

            long elapsed = System.currentTimeMillis() - t0;
            long sleep = frameTime - elapsed;
            if (sleep > 0) {
                try { Thread.sleep(sleep); } catch (Exception e) {}
            }
        }
    }

    private void drawThemeCard(Surface d, int cardX, int cardY, Theme theme, boolean selected) {
        // Score bar strip (top 12px of card)
        d.setColor(theme.scoreBar());
        d.fillRectangle(cardX, cardY, CARD_W, 12);

        // Background (remaining preview area)
        d.setColor(theme.background());
        d.fillRectangle(cardX, cardY + 12, CARD_W, PREVIEW_H - 12);

        // Mini blocks: 3 cols x 2 rows
        int bw = 28, bh = 11, hgap = 5, vgap = 5;
        int gridW = 3 * bw + 2 * hgap;
        int gridH = 2 * bh + vgap;
        int previewTop = cardY + 12;
        int previewAreaH = PREVIEW_H - 12 - 20;
        int bStartX = cardX + (CARD_W - gridW) / 2;
        int bStartY = previewTop + (previewAreaH - gridH) / 2;

        Color[] cols = theme.blockColors();
        int idx = 0;
        for (int row = 0; row < 2; row++) {
            for (int col = 0; col < 3; col++) {
                d.setColor(cols[idx % cols.length]);
                d.fillRectangle(bStartX + col * (bw + hgap),
                                bStartY + row * (bh + vgap), bw, bh);
                idx++;
            }
        }

        // Paddle (centered, near bottom of preview area)
        int paddleW = 44, paddleH = 6;
        int paddleX = cardX + (CARD_W - paddleW) / 2;
        int paddleY = cardY + PREVIEW_H - 14;
        d.setColor(theme.paddle());
        d.fillRectangle(paddleX, paddleY, paddleW, paddleH);

        // Ball (small, above paddle center)
        int ballX = paddleX + paddleW / 2;
        int ballY = paddleY - 8;
        d.setColor(theme.ball().equals(new Color(220, 240, 255))
                   ? new Color(90, 110, 140) : theme.ball());
        d.fillCircle(ballX, ballY, 4);

        // Border highlight
        if (selected) {
            d.setColor(new Color(255, 220, 0));
        } else {
            d.setColor(new Color(60, 60, 70));
        }
        d.drawRectangle(cardX, cardY, CARD_W, CARD_H);

        // Name strip
        d.drawTextInRect(cardX, cardY + CARD_H - NAME_STRIP_H,
                         CARD_W, NAME_STRIP_H, theme.displayName(), 12, Color.WHITE);
    }
}
