package animation.menu;

import animation.Animation;

/**
 * Menu interface.
 *
 * @param <T> generic object
 * @author Tomer Ovadia
 */
public interface Menu<T> extends Animation {
    /**
     * Adds new selection to current menu.
     *
     * @param key       press for selection
     * @param message   to display
     * @param returnVal of selection
     */
    void addSelection(String key, String message, T returnVal);

    /**
     * Gets status.
     *
     * @return menu status.
     */
    T getStatus();

    /**
     * Adds a sub Menu to current menu.
     *
     * @param key     key press for selection
     * @param message to display
     * @param subMenu to add
     */
    void addSubMenu(String key, String message, Menu<T> subMenu);

    /**
     * reset current menu status.
     */
    void resetMenu();
}