package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import constants.Consts;
import java.awt.Color;

/**
 * PauseScreen class.
 *
 * @author Tomer Ovadia
 */
public class PauseScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;

    /**
     * Constructor.
     *
     * @param k keyboard sensor.
     */
    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
        this.stop = false;
    }

    /**
     * run a one frame animation.
     *
     * @param d  drawsurface
     * @param dt delta time
     */
    public void doOneFrame(DrawSurface d, double dt) {
        //draw background
        d.setColor(new Color(186, 200, 255));
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        //draw pause graphics
        int x = d.getWidth() / 2;
        int y = d.getHeight() / 3;
        int radius = Consts.PAUSE_SCREEN_CIRC_RADIUS;
        Color[] colors = {Color.black, Color.white, Color.DARK_GRAY,
                Color.gray};
        int i = 1;
        //CIRCLES-----------------------------------
        for (Color c : colors) {
            d.setColor(c);
            d.fillCircle(x, y, radius);
            radius -= 5 * (i);
            i++;
        }
        //RECTANGLES--------------------------------
        int width = Consts.PAUSE_SCREEN_REC_WIDTH;
        int height = Consts.PAUSE_SCREEN_REC_HEIGHT;
        x -= (radius / 2) + (width / 3);
        y -= radius / 2 + (height / 6);
        //rectangle
        d.setColor(Color.black);
        d.fillRectangle(x, y, width, height);
        //shading
        d.setColor(Color.lightGray);
        d.drawRectangle(x, y, width, height);
        //move in x position
        x += width + (width / 2);
        //rectangle
        d.setColor(Color.black);
        d.fillRectangle(x, y, width, height);
        //shading
        d.setColor(Color.lightGray);
        d.drawRectangle(x, y, width, height);
        //TEXT--------------------------------------
        x = d.getWidth() / 4;
        y = (d.getHeight() / 5) * 4;
        TextBorder tb = new TextBorder();
        String text = "paused -- press space to continue";
        d.setColor(Color.black);
        //text border
        tb.textEffect(d, x, y, text, 32);
        //text
        d.setColor(Color.white);
        d.drawText(x, y, text, 32);

    }

    /**
     * @return true if current animation should stop,false otherwise.
     */
    public boolean shouldStop() {
        return false;
    }
}