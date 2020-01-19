package collidable;

import biuoop.DrawSurface;
import constants.Consts;
import game.SpaceInvaderLevel;
import game.listener.AlienListener;
import game.listener.HitListener;
import sprites.Sprite;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * AlienFormation class.
 * @author Tomer Ovadia
 */
public class AlienFormation implements Sprite {
    private static final int START_X = 50;
    private static final int START_Y = 50;
    private static final int ALIEN_ROWS = 5;
    private static final int ALIEN_COLS = 10;
    private static final int SPACER = 10;
    private static final double ALIEN_ACCELERATION = 1.1;
    private List<List<Alien>> aliens;
    private double speed;
    private int levelNumber;
    private int leftmostX;
    private int rightmostX;
    private int botAlienY;
    private int leftBoundX;
    private int rightBoundX;
    private int botBoundY;
    private double originalSpeed;


    private boolean reachedShield = false;
    private boolean destroyed = false;


    /**
     * Instantiates a new Alien formation.
     *
     * @param movementSpeed the movement speed
     * @param leftBound     the left bound
     * @param rightBound    the right bound
     * @param botBound      the bot bound
     */
    public AlienFormation(double movementSpeed, int leftBound, int rightBound,
                          int botBound) {
        //alien formation initializing
        this.aliens = new ArrayList<>();
        for (int i = 0; i < ALIEN_COLS; i++) {
            List<Alien> alienCol = new ArrayList<>();
            int x = START_X + i * (Alien.WIDTH + SPACER);
            ;
            int y = START_Y;
            for (int j = 0; j < ALIEN_ROWS; j++) {
                Alien currAlien = new Alien(x, y);
                y += (Alien.HEIGHT + SPACER);
                alienCol.add(currAlien);
            }
            this.aliens.add(alienCol);
        }
        this.speed = movementSpeed;
        this.originalSpeed = movementSpeed;
        this.leftBoundX = leftBound;
        this.rightBoundX = rightBound;
        this.botBoundY = botBound;
        this.leftmostX = getLeftmostX();
        this.rightmostX = getRightmostX();
        this.botAlienY = getBotmostY();

        //

    }

    /**
     * Draw the current sprite on draw surface.
     *
     * @param d draw surface to draw object on.
     */
    public void drawOn(DrawSurface d) {
        //loop on blocks and draw them
        for (int i = 0; i < this.aliens.size(); i++) {
            for (int j = 0; j < this.aliens.get(i).size(); j++) {
                this.aliens.get(i).get(j).drawOn(d);
            }
        }
    }

    /**
     * notify the sprite that time has passed.
     *
     * @param dt the delta time
     */
    public void timePassed(double dt) {
        double dx = this.speed * dt;
        //check if formation have reached its x bounds
        if (this.getRightmostX() + dx >= this.rightBoundX
                || this.getLeftmostX() + dx <= this.leftBoundX) {
            //move down a step
            this.moveFormationDown(Alien.HEIGHT);
            this.speed *= (-1);
            this.speed *= ALIEN_ACCELERATION;
            this.rightmostX += this.speed * dt;
            this.leftmostX += this.speed * dt;
            this.resetEdgesInfo();
        }
        if (this.getBotmostY() >= this.botBoundY) {
            this.reachedShield = true;
        }
        for (int i = 0; i < this.aliens.size(); i++) {
            for (int j = 0; j < this.aliens.get(i).size(); j++) {
                this.aliens.get(i).get(j).moveStep(this.speed * dt, 0);
            }
        }

    }

    /**
     * Add Alien to given game.
     *
     * @param g SpaceInvaderLevel object to add to
     */
    public void addToGame(SpaceInvaderLevel g) {
        for (int i = 0; i < this.aliens.size(); i++) {
            for (int j = 0; j < this.aliens.get(i).size(); j++) {
                this.aliens.get(i).get(j).addToGame(g);
            }
        }
    }

    /**
     * Gets leftmost x.
     *
     * @return the leftmost x
     */
    public int getLeftmostX() {
        return (int)
                this.aliens.get(0).get(0).getRectangle().getUpperLeft().getX();
    }

    /**
     * Gets rightmost x.
     *
     * @return the rightmost x
     */
    public int getRightmostX() {
        int size = this.aliens.size() - 1;
        int max =
                (int) this.aliens.get(size).get(0).getRectangle().
                        getUpperLeft().getX();
        return max + Alien.WIDTH;
    }

    /**
     * Gets bottom-most y.
     *
     * @return the botmost y
     */
    public int getBotmostY() {
        double max = aliens.get(0).get(0).getRectangle().getUpperLeft().getY();
        for (int i = 0; i < this.aliens.size(); i++) {
            Alien currAlien = aliens.get(i).get(aliens.get(i).size() - 1);
            if (currAlien.getRectangle().getUpperLeft().getY() > max) {
                max = currAlien.getRectangle().getUpperLeft().getY();
            }
        }
        return (int) max + Alien.HEIGHT;
    }

    /**
     * Move formation down.
     *
     * @param units the units
     */
    public void moveFormationDown(int units) {
        for (int i = 0; i < this.aliens.size(); i++) {
            for (int j = 0; j < this.aliens.get(i).size(); j++) {
                this.aliens.get(i).get(j).moveStep(0, units);
            }
        }
    }

    /**
     * Reset edges info.
     */
    public void resetEdgesInfo() {
        this.leftmostX = getLeftmostX();
        this.rightmostX = getRightmostX();
        this.botAlienY = getBotmostY();
    }

    /**
     * Add hit listener.
     *
     * @param listener the listener
     */
    public void addHitListener(HitListener listener) {
        for (int i = 0; i < this.aliens.size(); i++) {
            for (int j = 0; j < this.aliens.get(i).size(); j++) {
                this.aliens.get(i).get(j).addHitListener(listener);
            }
        }
    }


    /**
     * @return current formation alien list.
     */
    public List<List<Alien>> getAliens() {
        return aliens;
    }


    /**
     * Add alien listener.
     *
     * @param l1 the l 1
     */
    public void addAlienListener(AlienListener l1) {
        for (int i = 0; i < this.aliens.size(); i++) {
            for (int j = 0; j < this.aliens.get(i).size(); j++) {
                this.aliens.get(i).get(j).addAlienListener(l1);
            }
        }
    }

    /**
     * return true if formation Have reached shield , false otherwise.
     *
     * @return the boolean
     */
    public boolean haveReachedShield() {
        return this.reachedShield;
    }

    /**
     * Reset position.
     */
    public void resetPosition() {
        int dx = START_X - (int) this.aliens.get(0).get(0).getRectangle().
                getUpperLeft().getX();
        int dy = START_Y - this.getUpperAlienY();
        for (int i = 0; i < this.aliens.size(); i++) {
            for (int j = 0; j < this.aliens.get(i).size(); j++) {
                Alien currAlien = this.aliens.get(i).get(j);
                currAlien.moveStep(dx, dy);
            }
        }
        this.resetEdgesInfo();
        this.reachedShield = false;
        this.speed = this.originalSpeed;
    }

    /**
     * return true if formation Is destroyed, false otherwise.
     *
     * @return the boolean
     */
    public boolean isDestroyed() {
        return this.destroyed;
    }

    /**
     * Sets destruction.
     */
    public void setDestruction() {
        this.destroyed = true;
    }

    /**
     * Gets random Alien from bot line.
     *
     * @return the random from bot line
     */
    public Alien getRandomFromBotLine() {
        Random rand = new Random();
        int i = rand.nextInt(this.aliens.size());
        int colSize = this.aliens.get(i).size() - 1;
        return this.aliens.get(i).get(colSize);

    }

    /**
     * @return minimum y position for an alien.
     */
    public int getUpperAlienY() {
        int min = Consts.GUI_HEIGHT;
        for (int i = 0; i < this.aliens.size(); i++) {
            Alien currAlien = this.aliens.get(i).get(0);
            if (currAlien.getRectangle().getUpperLeft().getY() < min) {
                min = (int) currAlien.getRectangle().getUpperLeft().getY();
            }
        }
        return min;
    }
}