package animation.menu;

/**
 * Task interface.
 *
 * @param <T> generic object
 * @author Tomer Ovadia
 */
public interface Task<T> {
    /**
     * Run current task.
     *
     * @return object t
     */
    T run();
}
