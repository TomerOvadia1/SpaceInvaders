package game;

import animation.Animation;
import animation.AnimationRunner;
import animation.KeyPressStoppableAnimation;
import animation.PauseScreen;
import animation.CountdownAnimation;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import collidable.AlienFormation;
import collidable.Block;
import collidable.SpaceShip;
import constants.Consts;
import factories.BlockColorDrawer;
import factories.BlockDrawer;
import factories.BlockFactory;
import factories.ImageParser;
import game.levels.ImageBackground;
import game.listener.AlienRemove;
import game.listener.BallRemover;
import game.listener.BlockRemover;
import game.listener.Counter;
import game.listener.SpaceShipHitListener;
import geometry.Point;
import collidable.Collidable;
import geometry.Rectangle;
import sprites.InfoBlock;
import sprites.LivesIndicator;
import sprites.ScoreIndicator;
import sprites.SpriteCollection;
import sprites.Ball;
import sprites.Velocity;
import sprites.LevelNameIndicator;
import sprites.Sprite;

import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

/**
 * SpaceInvaderLevel class.
 *
 * @author Tomer Ovadia
 */
public class SpaceInvaderLevel implements Animation {
    private static final int SHIELD_START_Y = 500;
    private static final double PLAYER_CD = 0.35;
    private static final double ENEMY_CD = 0.5;
    private static final int SHIELD_START_X = 120;
    private static final int NUMBER_OF_SHIELDS = 3;
    private static final int SHIELDS_ROWS = 5;
    private static final int BLOCK_PER_SHIELD_ROW = 25;
    private static final int SHOT_DY = 400;
    private static final Color ENEMY_SHOT_COLOR = Color.RED;
    private static final Color PLAYER_SHOT_COLOR = Color.YELLOW;

    private AlienFormation alienFormation;
    private SpriteCollection sprites;
    private GameEnvironment gameEnvironment;
    private AnimationRunner runner;
    private KeyboardSensor keyboardSensor;
    private boolean running;
    private SpaceShip spaceShip;
    private Counter scoreCounter;
    private Counter livesCounter;
    private long lastPlayerShot = System.currentTimeMillis();
    private long lastEnemyShot = System.currentTimeMillis();
    private List<Ball> playerShots;
    private List<Ball> alienShots;
    private int levelNum;
    private double formationSpeed;


    /**
     * SpaceInvaderLevel constructor.
     *
     * @param numLevel   the num level
     * @param startSpeed the start speed
     * @param ks         keyboard sensor
     * @param ar         animation runner object
     * @param score      given score
     * @param lives      given lives counter
     */
    public SpaceInvaderLevel(int numLevel, double startSpeed,
                             KeyboardSensor ks,
                             AnimationRunner ar, Counter score,
                             Counter lives) {
        this.runner = ar;
        this.keyboardSensor = ks;
        this.sprites = new SpriteCollection();
        this.gameEnvironment = new GameEnvironment();
        this.running = false;
        this.scoreCounter = score;
        this.livesCounter = lives;
        this.alienFormation = null;
        this.playerShots = new ArrayList<>();
        this.alienShots = new ArrayList<>();
        this.levelNum = numLevel;
        this.formationSpeed = startSpeed;
    }

    /**
     * Gets score counter.
     *
     * @return current score counter.
     */
    public Counter getScoreCounter() {
        return this.scoreCounter;
    }

    /**
     * Gets lives counter.
     *
     * @return current lives counter.
     */
    public Counter getLivesCounter() {
        return this.livesCounter;
    }

    /**
     * Adds collidable object to game environment.
     *
     * @param c Collidable object to add.
     */
    public void addCollidable(Collidable c) {
        this.gameEnvironment.addCollidable(c);
    }

    /**
     * Adds given Sprite to sprite collection.
     *
     * @param s Sprite object to add.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Initialize a new game: create the Blocks and Ball (and SpaceShip)
     * and add them to the game.
     */
    public void initialize() {
        //background
        this.createBackground();
        //screen bounds
        this.createBounds();
        //Information block
        this.createInfoBlock();
        //score indicator
        this.createScoreIndicator();
        //lives indicator
        this.createLivesIndicator();
        //level name indicator
        this.createLevelNameIndicator();
        //Shields
        this.createShields(NUMBER_OF_SHIELDS, SHIELD_START_X,
                SHIELD_START_Y, BLOCK_PER_SHIELD_ROW, SHIELDS_ROWS);

        initializeAlienFormation();

    }


    /**
     * Initialize a new Space-Ship.
     */
    public void initializeSpaceShip() {
        this.spaceShip = this.createSpaceShip();
        this.spaceShip.addHitListener(new
                SpaceShipHitListener(this));
        this.spaceShip.addHitListener(new BallRemover(this));
    }

    /**
     * runs the game animation.
     */
    public void run() {
        while (this.getLivesCounter().getValue() > 0
                && !this.alienFormation.isDestroyed()) {

            this.playOneTurn();
        }

    }

    /**
     * Run the game for one turn.
     */
    public void playOneTurn() {
        this.initializeSpaceShip();
        // countdown before turn starts.
        this.runner.run(new CountdownAnimation(Consts.COUNT_DOWN_SEC,
                Consts.COUNT_DOWN_FROM, this.sprites));
        this.running = true;
        // use our runner to run the game animation
        this.runner.run(this);
        if (this.alienFormation.haveReachedShield()
                || this.spaceShip.beenHit()) {
            this.livesCounter.decrease(1);
        }
        if (!this.alienFormation.isDestroyed()) {
            this.alienFormation.resetPosition();
        }
        //remove SpaceShip from game
        spaceShip.removeFromGame(this);


    }


    /**
     * runs one frame of animation.
     *
     * @param d  drawsurface
     * @param dt delta time
     */
    public void doOneFrame(DrawSurface d, double dt) {
        //shoot at proper timing
        this.enemyShoot(dt);
        // draw sprites
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed(dt);
        //stopping conditions
        if (this.keyboardSensor.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboardSensor,
                    "space", new PauseScreen(this.keyboardSensor)));
        }
        if (this.keyboardSensor.isPressed("space")) {
            this.playerShoot(dt);
        }
        //if either happened level must be restarted with current formation
        //and lives
        if (this.alienFormation.haveReachedShield()
                || this.spaceShip.beenHit()) {
            this.running = false;
            this.sprites.getSpriteList().removeAll(this.playerShots);
            this.sprites.getSpriteList().removeAll(this.alienShots);
            this.playerShots = new ArrayList<>();
            this.alienShots = new ArrayList<>();
        }
        //if all aliens were destroyed
        if (this.alienFormation.isDestroyed()) {
            this.running = false;
            this.sprites.getSpriteList().remove(this.alienFormation);
        }
    }

    /**
     * Creates a background for a game.
     */
    public void createBackground() {
        String path = "background_images/background.jpg";
        Image image = ImageParser.imageFromString(path);
        Sprite background = new ImageBackground(image);
        background.addToGame(this);
    }

    /**
     * Creates an Information block on top part of the screen.
     */
    public void createInfoBlock() {
        InfoBlock infoBlock = new InfoBlock(Consts.INFO_REC,
                Consts.INFO_BLOCK_COLOR);
        infoBlock.addToGame(this);
    }

    /**
     * Creates score indicator.
     */
    public void createScoreIndicator() {
        ScoreIndicator scoreIndicator =
                new ScoreIndicator(Consts.SCORE_INDICATOR_POINT,
                        this.getScoreCounter());
        scoreIndicator.addToGame(this);
    }

    /**
     * Creates lives indicator.
     */
    public void createLivesIndicator() {
        LivesIndicator livesIndicator =
                new LivesIndicator(Consts.LIVES_INDICATOR_POINT,
                        this.getLivesCounter());
        livesIndicator.addToGame(this);
    }

    /**
     * Creates level name indicator.
     */
    public void createLevelNameIndicator() {
        LevelNameIndicator levelName =
                new LevelNameIndicator(Consts.LEVEL_NAME_INDICATOR_POINT,
                        "Space Invaders " + "Level " + this.levelNum);
        levelName.addToGame(this);
    }


    /**
     * Creates bound blocks for the current game.
     */
    public void createBounds() {
        //top block
        Block topBlock =
                new Block(Consts.TOP_BOUND_POINT, Consts.TOP_BOUND_WIDTH,
                        Consts.TOP_BOUND_HEIGHT, Color.GRAY);
        topBlock.addToGame(this);
        topBlock.addHitListener(new BallRemover(this));
        //left block
        Block leftBlock = new Block(Consts.POINT_LEFT_BOUND,
                Consts.SIDE_BOUND_WIDTH, Consts.SIDE_BOUND_HEIGHT,
                Color.GRAY);
        leftBlock.addToGame(this);
        //right block
        Block rightBlock = new Block(Consts.RIGHT_BOUND_POINT,
                Consts.SIDE_BOUND_WIDTH, Consts.SIDE_BOUND_HEIGHT, Color.GRAY);
        rightBlock.addToGame(this);
        //bottom block/DEATH BLOCK
        Block botBlock = new Block(Consts.BOT_BOUND_POINT,
                Consts.BOT_BOUND_WIDTH, Consts.SIDE_BOUND_WIDTH, Color.GRAY);
        //add Ball remover to death block
        botBlock.addHitListener(new BallRemover(this));
        botBlock.addToGame(this);
    }

    /**
     * Creates a SpaceShip.
     *
     * @return a new SpaceShip object.
     */
    public SpaceShip createSpaceShip() {
        //x and y coordinates
        int x = (Consts.GUI_WIDTH - Consts.PADDLE_DEFAULT_WIDTH) / 2;
        int y = (int) Consts.PADDLE_DEFAULT_POINT.getY();
        geometry.Point p = new Point(x, y);
        //space ship block
        Block b = new Block(p,
                Consts.PADDLE_DEFAULT_WIDTH,
                Consts.PADDLE_DEFAULT_HEIGHT, Color.ORANGE);
        SpaceShip sp = new SpaceShip(b, this.keyboardSensor,
                Consts.PADDLE_DEFAULT_SPEED);
        sp.addToGame(this);
        return sp;
    }

    /**
     * removes colldiable object from current game level.
     *
     * @param c colldiable object to remove.
     */
    public void removeCollidable(Collidable c) {
        this.gameEnvironment.getCollidableObj().remove(c);
    }

    /**
     * removes Sprite object from current game level.
     *
     * @param s Sprite object to remove.
     */
    public void removeSprite(Sprite s) {
        this.sprites.getSpriteList().remove(s);
    }

    /**
     * @return true if animation should stop , false otherwise.
     */
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * Initialize alien formation.
     */
    public void initializeAlienFormation() {
        this.alienFormation = new AlienFormation(this.formationSpeed, 0,
                Consts.GUI_WIDTH, SHIELD_START_Y);
        //add to current game
        this.addSprite(alienFormation);
        alienFormation.addToGame(this);
        this.alienFormation.addAlienListener(new AlienRemove(
                this, this.scoreCounter));

    }

    /**
     * Create shields.
     *
     * @param numOfShields the num of shields
     * @param startX       the start x
     * @param startY       the start y
     * @param blocksPerRow the blocks per row
     * @param rows         the rows
     */
    public void createShields(int numOfShields, int startX, int startY,
                              int blocksPerRow, int rows) {
        //blocks array
        java.util.List<Block> blocks = new ArrayList<>();
        BlockRemover blockRemover =
                new BlockRemover(this);
        int width = 5;
        int height = 5;
        int spacer = 100;
        //block creation
        for (int k = 0; k < numOfShields; k++) {
            int y = startY;
            for (int i = 0; i < rows; i++) {
                int x = startX;
                for (int j = 0; j < blocksPerRow; j++) {
                    //create current block
                    BlockFactory blockFactory = new BlockFactory();
                    List<BlockDrawer> blockDrawers = new ArrayList<>();
                    //add block drawers
                    blockDrawers.add(new BlockColorDrawer(Color.WHITE));
                    //set info
                    blockFactory.setBlockDrawers(blockDrawers);
                    blockFactory.setHeight(height);
                    blockFactory.setWidth(width);
                    Block b = blockFactory.create(x, y);
                    b.setHitCount(1);
                    //add to current game
                    b.addToGame(this);
                    b.addHitListener(blockRemover);
                    b.addHitListener(new BallRemover(this));
                    //step forward in position to create another block
                    x += width;
                }
                y += height;
            }
            startX += (blocksPerRow * width) + spacer;

        }
    }


    /**
     * Player shoot.
     *
     * @param dt the dt
     */
    public void playerShoot(double dt) {
        //get system current time
        long currTime = System.currentTimeMillis();
        //check if cool down has passed
        if (currTime - this.lastPlayerShot >= PLAYER_CD * 1000) {
            //create a new shot
            Velocity shotVelo = new Velocity(0, -SHOT_DY);
            Point spaceShipPoint =
                    this.spaceShip.getCollisionRectangle().getUpperLeft();
            Rectangle spaceShipRec = this.spaceShip.getCollisionRectangle();
            Ball b = new Ball(new Point(spaceShipPoint.getX()
                    + spaceShipRec.getWidth() / 2, spaceShipPoint.getY()
                    - spaceShipRec.getHeight() / 2),
                    Consts.BALL_DEFAULT_RADIUS,
                    PLAYER_SHOT_COLOR, this.gameEnvironment);
            b.setVelocity(shotVelo);
            b.addToGame(this);
            this.playerShots.add(b);
            this.lastPlayerShot = System.currentTimeMillis();
        }

    }

    /**
     * Gets alien formation.
     *
     * @return the alien formation
     */
    public AlienFormation getAlienFormation() {
        return alienFormation;
    }

    /**
     * Enemy shoot.
     *
     * @param dt the dt
     */
    public void enemyShoot(double dt) {
        //get system current time
        long currTime = System.currentTimeMillis();
        //check if cool down has passed
        if (currTime - this.lastEnemyShot >= ENEMY_CD * 1000) {
            //create a new shot
            Velocity shotVelo = new Velocity(0, SHOT_DY);
            Rectangle alienRec =
                    this.alienFormation.getRandomFromBotLine().getRectangle();
            Point alienPoint = alienRec.getUpperLeft();

            Ball b = new Ball(new Point(alienPoint.getX()
                    + alienRec.getWidth() / 2, alienPoint.getY()
                    + alienRec.getHeight() * 2),
                    Consts.BALL_DEFAULT_RADIUS,
                    ENEMY_SHOT_COLOR, this.gameEnvironment);
            b.setVelocity(shotVelo);
            b.addToGame(this);
            this.alienShots.add(b);
            this.lastEnemyShot = System.currentTimeMillis();
        }

    }

    /**
     * Gets space ship.
     *
     * @return the space ship
     */
    public SpaceShip getSpaceShip() {
        return spaceShip;
    }

    /**
     * Gets alien shots.
     *
     * @return the alien shots
     */
    public List<Ball> getAlienShots() {
        return alienShots;
    }

    /**
     * Gets player shots.
     *
     * @return the player shots
     */
    public List<Ball> getPlayerShots() {
        return playerShots;
    }
}

