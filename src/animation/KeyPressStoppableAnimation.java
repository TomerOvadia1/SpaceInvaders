package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * KeyPressStoppableAnimation Class.
 *
 * @author Tomer Ovadia
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor keyboardSensor;
    private String stopKey;
    private Animation animation;
    private boolean isAlreadyPressed;
    private boolean stop;

    /**
     * Constructor.
     *
     * @param sensor    keyboard sensor.
     * @param key       stop key
     * @param animation animation to run.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key,
                                      Animation animation) {
        this.animation = animation;
        this.keyboardSensor = sensor;
        this.stopKey = key;
        this.isAlreadyPressed = true;
        this.stop = false;
    }

    /**
     * run a one frame animation.
     *
     * @param d  drawsurface
     * @param dt delta time
     */
    public void doOneFrame(DrawSurface d, double dt) {
        this.animation.doOneFrame(d, dt);
        //check for key press
        if (this.keyboardSensor.isPressed(this.stopKey)) {
            if (!this.isAlreadyPressed) {
                this.stop = true;
            }
        } else {
            this.isAlreadyPressed = false;
        }
    }

    /**
     * @return true if current animation should stop,false otherwise.
     */
    public boolean shouldStop() {
        return this.stop;
    }

}
