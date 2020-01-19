package animation;

import biuoop.DrawSurface;
import constants.Consts;
import game.listener.Counter;
import sprites.SpriteCollection;

import java.awt.Color;

/**
 * CountdownAnimation class.
 *
 * @author Tomer Ovadia
 */
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private boolean stop;
    private Counter countsPassed;

    /**
     * Constructor.
     *
     * @param numOfSeconds to display countdown
     * @param countFrom    given number
     * @param gameScreen   background to draw
     */
    public CountdownAnimation(double numOfSeconds, int countFrom,
                              SpriteCollection gameScreen) {
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.numOfSeconds = numOfSeconds;
        //used to tell how many counts this object counted
        this.countsPassed = new Counter(0);


    }

    /**
     * run a one frame animation.
     *
     * @param d  drawsurface
     * @param dt delta time
     */
    public void doOneFrame(DrawSurface d, double dt) {
        //if we passed the number of counts
        if (this.countsPassed.getValue() > this.countFrom) {
            this.stop = true;
        }
        //current time
        long startTime = System.currentTimeMillis();
        //draw game screen
        this.gameScreen.drawAllOn(d);
        long drawAllTime = System.currentTimeMillis() - startTime;
        //required waiting seconds
        long appearFor = (long) ((this.numOfSeconds / this.countFrom) * 1000)
                - drawAllTime;
        //position
        int x = (int) (d.getWidth() / 2) - 10;
        int y = (int) (d.getHeight() / 2) - 10;
        int fontSize = Consts.COUNT_DOWN_FONT_SIZE;
        //draws countdown
        //if we reached 0 (or-if we passed all the seconds)
        if (this.countFrom == this.countsPassed.getValue()) {
            d.setColor(Color.BLACK);
            TextBorder tb = new TextBorder();
            //draw "GO" onscreen
            tb.textEffect(d, x - (fontSize) / 2, y, "GO !", fontSize);
            d.setColor(Color.WHITE);
            d.drawText(x - (fontSize) / 2, y,
                    "GO !", 42);
        } else if (this.countFrom > this.countsPassed.getValue()) {
            //display each second
            int secDisplay = this.countFrom - this.countsPassed.getValue();
            d.setColor(Color.BLACK);
            TextBorder tb = new TextBorder();
            tb.textEffect(d, x, y, String.valueOf(secDisplay), fontSize);
            d.setColor(Color.WHITE);
            d.drawText(x, y,
                    String.valueOf(secDisplay), fontSize);

        }
        //loop until number appeared for right amount of milliseconds
        long endTime = System.currentTimeMillis() - startTime;
        while (endTime <= appearFor) {
            endTime = System.currentTimeMillis() - startTime;
        }
        //increase counts passed
        this.countsPassed.increase(1);

    }

    /**
     * @return true if current animation should stop,false otherwise.
     */
    public boolean shouldStop() {
        return this.stop;
    }
}
