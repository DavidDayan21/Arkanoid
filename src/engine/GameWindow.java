package engine;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GameWindow {
    private JFrame frame;
    private JPanel panel;
    private Keyboard keyboard;
    private Mouse mouse;
    private BufferedImage buffer;
    private final int width;
    private final int height;

    public GameWindow(String title, int width, int height) {
        this.width  = width;
        this.height = height;
        this.keyboard = new Keyboard();
        this.mouse    = new Mouse();
        this.buffer   = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        panel = new JPanel() {
            @Override protected void paintComponent(java.awt.Graphics g) {
                super.paintComponent(g);
                g.drawImage(buffer, 0, 0, null);
            }
        };
        panel.setPreferredSize(new Dimension(width, height));
        panel.setFocusable(true);
        panel.addKeyListener(keyboard);
        panel.addMouseListener(mouse);
        panel.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            @Override
            public void mouseMoved(java.awt.event.MouseEvent e) {
                mouse.mouseMoved(e.getX(), e.getY());
            }
            @Override
            public void mouseDragged(java.awt.event.MouseEvent e) {
                mouse.mouseMoved(e.getX(), e.getY());
            }
        });

        frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        panel.requestFocusInWindow();
    }

    public Surface getSurface() {
        Graphics2D g = buffer.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                           RenderingHints.VALUE_ANTIALIAS_ON);
        return new Surface(g, width, height);
    }

    public void show(Surface s) {
        s.getGraphics().dispose();
        panel.repaint();
    }

    public BufferedImage getLastFrame() {
        BufferedImage copy = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = copy.createGraphics();
        g2.drawImage(buffer, 0, 0, null);
        g2.dispose();
        return copy;
    }

    public Keyboard getKeyboard() { return keyboard; }
    public Mouse    getMouse()    { return mouse;    }
    public int      getWidth()    { return width;    }
    public int      getHeight()   { return height;   }
}
