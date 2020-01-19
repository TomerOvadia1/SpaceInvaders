package sprites;

import biuoop.DrawSurface;
import java.util.ArrayList;
import java.util.List;

/**
 * SpriteCollection class.
 *
 * @author Tomer Ovadia
 */
public class SpriteCollection {
    private List<Sprite> spriteList;

    /**
     * Constructor.
     */
    public SpriteCollection() {
        this.spriteList = new ArrayList<>();

    }

    /**
     * Adds a given sprite to Sprite list in spriteCollection.
     *
     * @param s Sprite to add to list.
     */
    public void addSprite(Sprite s) {
        this.spriteList.add(s);
    }

    /**
     * function calls timePassed() on all sprites on sprite list.
     *
     * @param dt the dt
     */
    public void notifyAllTimePassed(double dt) {
        // Make a copy of the spriteList before iterating .
        ArrayList<Sprite> sprites = new ArrayList<>(this.spriteList);
        //iterate on all sprites on collection
        for (Sprite spriteObj : sprites) {
            //notify for a time pass
            spriteObj.timePassed(dt);
        }
    }

    /**
     * Function calls drawOn(d) on all sprites on sprite list.
     *
     * @param d draw surface.
     */
    public void drawAllOn(DrawSurface d) {
        // Make a copy of the spriteList before iterating.
        ArrayList<Sprite> sprites = new ArrayList<>(this.spriteList);
        //iterate on all sprites on collection
        for (Object spriteObj : sprites) {
            //check if given object is indeed a sprite
            //downcast
            Sprite sprite = (Sprite) spriteObj;
            //notify for a time pass
            sprite.drawOn(d);
        }
    }

    /**
     * Gets sprite list.
     *
     * @return this sprite collection's list
     */
    public List<Sprite> getSpriteList() {
        return this.spriteList;
    }

}