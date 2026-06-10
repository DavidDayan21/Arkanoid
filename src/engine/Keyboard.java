package engine;
import java.awt.event.*;
import java.util.HashSet;
import java.util.Set;

public class Keyboard implements KeyListener {
    private final Set<String> pressed = new HashSet<>();

    public static final String LEFT_KEY  = "left";
    public static final String RIGHT_KEY = "right";

    @Override
    public void keyPressed(KeyEvent e) {
        pressed.add(normalize(e));
    }
    @Override
    public void keyReleased(KeyEvent e) {
        pressed.remove(normalize(e));
    }
    @Override public void keyTyped(KeyEvent e) {}

    public boolean isPressed(String key) {
        return pressed.contains(key.toLowerCase());
    }

    private String normalize(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:  return "left";
            case KeyEvent.VK_RIGHT: return "right";
            case KeyEvent.VK_ESCAPE: return "escape";
            case KeyEvent.VK_P:     return "p";
            case KeyEvent.VK_SPACE: return "space";
            default:
                String s = String.valueOf(e.getKeyChar()).toLowerCase();
                return s;
        }
    }
}
