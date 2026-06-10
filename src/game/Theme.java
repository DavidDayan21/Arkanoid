package game;
import java.awt.Color;

public enum Theme {
    CYBER, GRASSLAND, DARK, SUNSET, ARCTIC, LAVA;

    public Color background() {
        switch (this) {
            case CYBER:     return new Color(5, 5, 20);
            case GRASSLAND: return new Color(135, 195, 235);
            case DARK:      return new Color(18, 18, 24);
            case SUNSET:    return new Color(40, 15, 40);
            case ARCTIC:    return new Color(220, 235, 245);
            case LAVA:      return new Color(10, 5, 5);
            default:        return Color.DARK_GRAY;
        }
    }

    public Color scoreBar() {
        switch (this) {
            case CYBER:     return new Color(10, 10, 35);
            case GRASSLAND: return new Color(80, 140, 80);
            case DARK:      return new Color(25, 25, 35);
            case SUNSET:    return new Color(60, 20, 60);
            case ARCTIC:    return new Color(180, 210, 230);
            case LAVA:      return new Color(30, 10, 5);
            default:        return Color.DARK_GRAY;
        }
    }

    public Color scoreText() {
        switch (this) {
            case CYBER:     return new Color(0, 255, 200);
            case GRASSLAND: return Color.WHITE;
            case DARK:      return new Color(180, 180, 200);
            case SUNSET:    return new Color(255, 200, 100);
            case ARCTIC:    return new Color(40, 60, 100);
            case LAVA:      return new Color(255, 120, 30);
            default:        return Color.BLACK;
        }
    }

    public Color border() {
        switch (this) {
            case CYBER:     return new Color(20, 20, 60);
            case GRASSLAND: return new Color(90, 60, 30);
            case DARK:      return new Color(35, 35, 50);
            case SUNSET:    return new Color(70, 30, 70);
            case ARCTIC:    return new Color(160, 195, 220);
            case LAVA:      return new Color(50, 15, 5);
            default:        return Color.LIGHT_GRAY;
        }
    }

    public Color[] blockColors() {
        switch (this) {
            case CYBER:
                return new Color[]{
                    new Color(0, 255, 255),   new Color(180, 0, 255),
                    new Color(255, 0, 180),   new Color(0, 200, 255),
                    new Color(255, 60, 255),  new Color(100, 0, 255)};
            case GRASSLAND:
                return new Color[]{
                    new Color(60, 160, 60),   new Color(100, 190, 80),
                    new Color(200, 180, 60),  new Color(160, 110, 40),
                    new Color(80, 180, 140),  new Color(40, 120, 200)};
            case DARK:
                return new Color[]{
                    new Color(70, 70, 100),   new Color(55, 55, 85),
                    new Color(85, 55, 100),   new Color(50, 80, 100),
                    new Color(95, 65, 65),    new Color(60, 90, 80)};
            case SUNSET:
                return new Color[]{
                    new Color(255, 80, 30),   new Color(255, 130, 20),
                    new Color(230, 60, 100),  new Color(200, 40, 130),
                    new Color(255, 180, 40),  new Color(160, 30, 160)};
            case ARCTIC:
                return new Color[]{
                    new Color(180, 220, 240), new Color(140, 195, 230),
                    new Color(160, 210, 245), new Color(120, 180, 220),
                    new Color(200, 225, 240), new Color(100, 160, 210)};
            case LAVA:
                return new Color[]{
                    new Color(255, 60, 0),    new Color(220, 30, 0),
                    new Color(255, 140, 0),   new Color(180, 20, 0),
                    new Color(255, 200, 0),   new Color(140, 10, 0)};
            default:
                return new Color[]{Color.WHITE, Color.YELLOW, Color.ORANGE,
                                   Color.RED, Color.PINK, Color.MAGENTA};
        }
    }

    public Color paddle() {
        switch (this) {
            case CYBER:     return new Color(0, 220, 255);
            case GRASSLAND: return new Color(120, 80, 30);
            case DARK:      return new Color(100, 100, 140);
            case SUNSET:    return new Color(255, 120, 40);
            case ARCTIC:    return new Color(100, 160, 220);
            case LAVA:      return new Color(255, 80, 0);
            default:        return Color.GRAY;
        }
    }

    public Color ball() {
        switch (this) {
            case CYBER:     return new Color(255, 255, 100);
            case GRASSLAND: return Color.WHITE;
            case DARK:      return new Color(200, 200, 220);
            case SUNSET:    return Color.WHITE;
            case ARCTIC:    return new Color(90, 110, 140);
            case LAVA:      return new Color(255, 240, 100);
            default:        return Color.BLACK;
        }
    }

    public String displayName() {
        switch (this) {
            case CYBER:     return "Cyber";
            case GRASSLAND: return "Grassland";
            case DARK:      return "Dark";
            case SUNSET:    return "Sunset";
            case ARCTIC:    return "Arctic";
            case LAVA:      return "Lava";
            default:        return name();
        }
    }
}
