package game;
import java.util.ArrayList;
import java.util.List;
import engine.Surface;

/**
 * SpriteCollection class contains a list of all sprite objects of the game.
 */
public class SpriteCollection {
    private List<Sprite> sprites = new ArrayList<>();

    /**
     * SpriteCollection Constructor for new and empty collection.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<>();
    }

    /**
     * Adds a sprite to the collection.
     * @param s the sprite to add.
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }

    /**
     * Notify sprites that a unit of time has passed by calling the timePassed method.
     * Then they can update their state.
     */
    public void notifyAllTimePassed() {
        java.util.List<Sprite> listCopy = new java.util.ArrayList<>(this.sprites);
        for (Sprite s : listCopy) {
            s.timePassed();
        }
    }

    /**
     * Calls the drawOn method on all sprites in the collection.
     * @param d the surface to draw.
     */
    public void drawAllOn(Surface d) {
        for (Sprite s : this.sprites) {
            s.drawOn(d);
        }
    }

    /**
     * Removes a sprite object from the game.
     * @param s the Sprite object to remove.
     */
    public void removeSprite(Sprite s) {
        this.sprites.remove(s);
    }

}
