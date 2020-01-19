package game.levels;

/**
 * The type Level set.
 *
 * @author Tomer Ovadia
 */
public class LevelSet {
    private String key;
    private String description;
    private String path;

    /**
     * Sets description.
     *
     * @param s the description
     */
    public void setDescription(String s) {
        this.description = s;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets key.
     *
     * @param k the key
     */
    public void setKey(String k) {
        this.key = k;
    }

    /**
     * Gets key.
     *
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * Sets path.
     *
     * @param filePath the path
     */
    public void setPath(String filePath) {
        this.path = filePath;
    }

    /**
     * Gets path.
     *
     * @return the path
     */
    public String getPath() {
        return path;
    }
}
