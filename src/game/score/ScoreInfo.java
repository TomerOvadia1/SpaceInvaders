package game.score;

/**
 * ScoreInfo class.
 *
 * @author Tomer Ovadia.
 */
public class ScoreInfo implements java.io.Serializable {
    private String playerName;
    private int score;

    /**
     * ScoreInfo constructor.
     *
     * @param name  player name.
     * @param score players score.
     */
    public ScoreInfo(String name, int score) {
        this.playerName = name;
        this.score = score;
    }

    /**
     * Gets name.
     *
     * @return player 's name.
     */
    public String getName() {
        return this.playerName;
    }

    /**
     * Gets score.
     *
     * @return player 's score.
     */
    public int getScore() {
        return this.score;
    }
    /*
    @Override
    public int compareTo(ScoreInfo s1) {
        Integer i=this.score;
        return i.compareTo(s1.getScore());
    }
    */

}
