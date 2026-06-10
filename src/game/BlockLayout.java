package game;

public enum BlockLayout {
    CLASSIC, PYRAMID, CHECKERBOARD, WALL, BIG_BLOCKS, DIAMOND;

    public String displayName() {
        switch (this) {
            case CLASSIC:      return "Classic";
            case PYRAMID:      return "Pyramid";
            case CHECKERBOARD: return "Checker";
            case WALL:         return "Wall";
            case BIG_BLOCKS:   return "Big";
            case DIAMOND:      return "Diamond";
            default:           return name();
        }
    }
}
