package game;

import animation.AnimationRunner;
import animation.EndScreen;
import animation.KeyPressStoppableAnimation;
import biuoop.KeyboardSensor;
import constants.Consts;
import game.listener.Counter;


/**
 * GameFlow Class.
 *
 * @author Tomer Ovadia.
 */
public class GameFlow {
    /**
     * The constant FORMATION_SPEED.
     */
    public static final int FORMATION_SPEED = 80;
    /**
     * The constant LEVEL_SPEED_INCREASE.
     */
    public static final double LEVEL_SPEED_INCREASE = 1.5;
    private KeyboardSensor keyboardSensor;
    private AnimationRunner animationRunner;
    private Counter scoreCounter;
    private Counter livesCounter;

    /**
     * Constructor.
     *
     * @param ar animation runner object
     * @param ks keyboard sensor
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks) {
        this.keyboardSensor = ks;
        this.animationRunner = ar;
        this.scoreCounter = new Counter(0);
        this.livesCounter = new Counter(Consts.LIVES_NUM);
    }

    /**
     * Gets score counter.
     *
     * @return score counter.
     */
    public Counter getScoreCounter() {
        return this.scoreCounter;
    }

    /**
     * Gets lives counter.
     *
     * @return lives counter.
     */
    public Counter getLivesCounter() {
        return this.livesCounter;
    }

    /**
     * Run Animation/Game for each level in given list.
     */
    public void runLevels() {
        int levelNum = 1;
        double formationSpeed = FORMATION_SPEED;
        while (true) {
            SpaceInvaderLevel level = new SpaceInvaderLevel(levelNum,
                    formationSpeed, this.keyboardSensor, this.animationRunner,
                    this.scoreCounter, this.livesCounter);
            //initialize each level according to its data
            level.initialize();
            //while blocks and lives are left
            while (level.getLivesCounter().getValue() > 0
                    && !level.getAlienFormation().isDestroyed()) {
                level.playOneTurn();
            }
            //if no more lives-player lost
            if (level.getLivesCounter().getValue() == 0) {
                break;
            }
            levelNum++;
            formationSpeed *= LEVEL_SPEED_INCREASE;

        }
        //draw end screen
        EndScreen es = new EndScreen(keyboardSensor, this.getLivesCounter(),
                this.getScoreCounter());
        KeyPressStoppableAnimation endScreenStoppable =
                new KeyPressStoppableAnimation(keyboardSensor,
                        keyboardSensor.SPACE_KEY, es);
        this.animationRunner.run(endScreenStoppable);

    }
}
