package engine;
import java.awt.event.*;

public class Mouse implements MouseListener {
    private volatile int clickX = -1;
    private volatile int clickY = -1;
    private volatile int currentX = 0;
    private volatile int currentY = 0;
    private volatile boolean hasPendingClick = false;

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            clickX = e.getX();
            clickY = e.getY();
            currentX = e.getX();
            currentY = e.getY();
            hasPendingClick = true;
        }
    }
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override public void mouseClicked(MouseEvent e)  {}
    @Override public void mouseEntered(MouseEvent e)  {}
    @Override public void mouseExited(MouseEvent e)   {}

    public void mouseMoved(int x, int y) {
        currentX = x;
        currentY = y;
    }

    /** Current mouse position X (for hover effects) */
    public int getX() { return currentX; }
    /** Current mouse position Y (for hover effects) */
    public int getY() { return currentY; }

    /**
     * Returns true if a click occurred inside the given rectangle,
     * and consumes the click so it won't fire again.
     * Safe to call multiple times per frame — only the FIRST matching
     * zone consumes the click.
     */
    public boolean clickedInside(int rx, int ry, int rw, int rh) {
        if (!hasPendingClick) return false;
        if (clickX >= rx && clickX <= rx + rw && clickY >= ry && clickY <= ry + rh) {
            hasPendingClick = false;
            return true;
        }
        return false;
    }

    /**
     * Consumes any pending click regardless of position.
     * Used for "click anywhere" screens.
     */
    public boolean consumeAnyClick() {
        if (hasPendingClick) {
            hasPendingClick = false;
            return true;
        }
        return false;
    }

    /** True if mouse cursor is currently inside the rectangle (for hover) */
    public boolean isInside(int rx, int ry, int rw, int rh) {
        return currentX >= rx && currentX <= rx + rw
            && currentY >= ry && currentY <= ry + rh;
    }
}
