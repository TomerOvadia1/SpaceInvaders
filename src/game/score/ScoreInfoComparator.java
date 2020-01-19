package game.score;
import java.util.Comparator;

/**
 * ScoreInfoComparator Class.
 *
 * @author Tomer Ovadia
 */
public class ScoreInfoComparator implements Comparator<ScoreInfo> {

    @Override
    public int compare(ScoreInfo s1, ScoreInfo s2) {
        if (s1.getScore() > s2.getScore()) {
            return -1;
        } else if (s1.getScore() < s2.getScore()) {
            return 1;
        }
        return 0;
    }
}
