package animation;
import biuoop.DrawSurface;

/**
 * Animation Interface.
 *
 * @author Tomer Ovadia
 */
public interface Animation {
    /**
     * run a one frame animation.
     *
     * @param d  drawsurface
     * @param dt the dt
     */
    void doOneFrame(DrawSurface d, double dt);

    /**
     * Should stop boolean.
     *
     * @return true if current animation should stop,false otherwise.
     */
    boolean shouldStop();

}