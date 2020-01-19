package animation;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import constants.Consts;
import game.score.HighScoresTable;
import game.score.ScoreInfo;

import java.awt.Color;

/**
 * HighScoresAnimation Class.
 *
 * @author Tomer Ovadia.
 */
public class HighScoresAnimation implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;
    private String endKey;
    private HighScoresTable scoreTable;

    /**
     * Instantiates a new High scores animation.
     *
     * @param scores the scores
     * @param key    the key
     * @param k      the keyboard sensor
     */
    public HighScoresAnimation(HighScoresTable scores, String key,
                               KeyboardSensor k) {
        this.keyboard = k;
        this.endKey = key;
        this.scoreTable = scores;

    }

    /**
     * run a one frame animation.
     *
     * @param d  drawsurface
     * @param dt delta time
     */
    public void doOneFrame(DrawSurface d, double dt) {
        this.drawTable(d);
    }

    /**
     * Draw table.
     *
     * @param d the draw surface
     */
    public void drawTable(DrawSurface d) {
        Color[] colors = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN,
                Color.CYAN};
        //use end screen animation
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        EndScreen.drawMultStars(d, 1, Color.WHITE, 100);
        //draw table
        int rankX = d.getWidth() / 5;
        int rankY = d.getHeight() / 4;
        int nameX = rankX + Consts.TABLE_COL_SPACE;
        int scoreX = nameX + Consts.TABLE_COL_SPACE;
        int midDiff = Consts.TABLE_FONT_SIZE;
        TextBorder tb = new TextBorder();
        d.setColor(Color.BLACK);
        tb.textEffect(d, nameX, rankY, "NAME",
                Consts.TABLE_FONT_SIZE + 7);
        tb.textEffect(d, rankX, rankY, "RANK",
                Consts.TABLE_FONT_SIZE + 7);
        tb.textEffect(d, scoreX, rankY, "SCORE",
                Consts.TABLE_FONT_SIZE + 7);
        d.setColor(Color.WHITE);
        d.drawText(rankX, rankY, "RANK", Consts.TABLE_FONT_SIZE + 7);
        d.drawText(nameX, rankY, "NAME", Consts.TABLE_FONT_SIZE + 7);
        d.drawText(scoreX, rankY, "SCORE", Consts.TABLE_FONT_SIZE + 7);

        int colorIndex = 0;
        int rank = 1;
        for (ScoreInfo scoreInfo : this.scoreTable.getHighScores()) {
            rankY += Consts.TABLE_LINE_SPACE;
            if (colorIndex + 1 > colors.length) {
                colorIndex = 0;
            }
            d.setColor(colors[colorIndex]);
            d.drawText(rankX + midDiff, rankY, String.valueOf(rank),
                    Consts.TABLE_FONT_SIZE);

            d.drawText(nameX, rankY, scoreInfo.getName(),
                    Consts.TABLE_FONT_SIZE);
            d.drawText(scoreX, rankY,
                    String.valueOf(scoreInfo.getScore()),
                    Consts.TABLE_FONT_SIZE);

            colorIndex++;
            rank++;
        }
        int x = (d.getWidth() / 5) * 2;
        int y = (d.getHeight() / 20) * 19;
        String text = "press space to continue";
        d.setColor(Color.black);
        //text border
        tb.textEffect(d, x, y, text, 15);
        //text
        d.setColor(Color.white);
        d.drawText(x, y, text, 15);
    }

    /**
     * @return true if current animation should stop,false otherwise.
     */
    public boolean shouldStop() {
        return false;

    }
}
