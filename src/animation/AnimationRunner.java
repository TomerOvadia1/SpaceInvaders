package animation;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * AnimationRunner Class.
 *
 * @author Tomer Ovadia
 */
public class AnimationRunner {
    private GUI gui;
    private int fps;

    /**
     * Constructor.
     *
     * @param framesPerSecond frames per second
     * @param gui             object to draw on
     */
    public AnimationRunner(int framesPerSecond, GUI gui) {
        this.gui = gui;
        this.fps = framesPerSecond;
    }

    /**
     * Gets gui.
     *
     * @return current gui object.
     */
    public GUI getGui() {
        return gui;
    }

    /**
     * Gets frames per second.
     *
     * @return number of frames per second.
     */
    public int getFramesPerSecond() {
        return fps;
    }

    /**
     * run the animation for the required animation object.
     *
     * @param animation object to run.
     */
    public void run(Animation animation) {
        Sleeper sleeper = new Sleeper();
        long milliSecPerFrame = (long) (1000 / this.fps);
        double dt;
        //loop until current animation stopped
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = this.gui.getDrawSurface();
            dt = milliSecPerFrame / 1000D;
            //do one frame each time
            animation.doOneFrame(d, dt);
            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = milliSecPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0L) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}
