package engine;
import java.awt.*;

public class Surface {
    private Graphics2D g;
    private int width, height;

    public Surface(Graphics2D g, int width, int height) {
        this.g = g;
        this.width = width;
        this.height = height;
    }

    public void setColor(Color c) { g.setColor(c); }

    public void fillRectangle(int x, int y, int w, int h) {
        g.fillRect(x, y, w, h);
    }

    public void drawRectangle(int x, int y, int w, int h) {
        g.drawRect(x, y, w, h);
    }

    public void fillCircle(int x, int y, int r) {
        g.fillOval(x - r, y - r, r * 2, r * 2);
    }

    public void drawCircle(int x, int y, int r) {
        g.drawOval(x - r, y - r, r * 2, r * 2);
    }

    public void drawText(int x, int y, String text, int fontSize) {
        g.setFont(new Font("Arial", Font.PLAIN, fontSize));
        g.drawString(text, x, y);
    }

    public void drawTextCentered(int y, String text, int fontSize, Color color) {
        Font font = new Font("Arial", Font.BOLD, fontSize);
        g.setFont(font);
        g.setColor(color);
        FontMetrics fm = g.getFontMetrics(font);
        int x = (width - fm.stringWidth(text)) / 2;
        g.drawString(text, x, y);
    }

    public int centerX(int elementWidth) {
        return (width - elementWidth) / 2;
    }

    public void drawTextInRect(int rx, int ry, int rw, int rh,
                                String text, int fontSize, Color color) {
        Font font = new Font("Arial", Font.BOLD, fontSize);
        g.setFont(font);
        g.setColor(color);
        FontMetrics fm = g.getFontMetrics(font);
        int tx = rx + (rw - fm.stringWidth(text)) / 2;
        int ty = ry + (rh + fm.getAscent() - fm.getDescent()) / 2;
        g.drawString(text, tx, ty);
    }

    public void fillRoundRect(int x, int y, int w, int h, int arc) {
        g.fillRoundRect(x, y, w, h, arc, arc);
    }

    public int getWidth()  { return width; }
    public int getHeight() { return height; }
    public Graphics2D getGraphics() { return g; }
}
