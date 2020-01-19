package game.listener;

/**
 * HitNotifier Interface.
 *
 * @author Tomer Ovadia.
 */
public interface HitNotifier {
    /**
     * Add hl as a listener to hit events.
     *
     * @param hl object to add
     */
    void addHitListener(HitListener hl);

    /**
     * Remove hl from the list of listeners to hit events.
     *
     * @param hl object to remove
     */
    void removeHitListener(HitListener hl);
}