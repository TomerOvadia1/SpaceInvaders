package collidable;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import constants.Consts;
import game.SpaceInvaderLevel;
import geometry.Point;
import geometry.Rectangle;
import sprites.Ball;
import sprites.Sprite;
import sprites.Velocity;
import java.awt.Color;

/**
 * Paddle class.
 *
 * @author Tomer Ovadia
 */
public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private Block paddleBlock;
    private Point upperLeft;
    private int paddleSpeed;


    /**
     * Constructor for a paddle.
     *
     * @param block    to use as a paddle
     * @param keyboard sensor to get user keyboard usage.
     * @param speed    of paddle
     */
    public Paddle(Block block, biuoop.KeyboardSensor keyboard, int speed) {
        this.paddleBlock = block;
        this.keyboard = keyboard;
        this.upperLeft =
                this.paddleBlock.getCollisionRectangle().getUpperLeft();
        this.paddleSpeed = speed;
    }

    /**
     * Moves paddle position to the left by constant units -
     * paddle's speed is a constant on Const class.
     *
     * @param dt the delta time
     */
    public void moveLeft(double dt) {
        //create a new upperLeft point for a paddle (after movement)
        Point newUpperLeft = new Point(this.upperLeft.getX()
                - this.paddleSpeed * dt, this.upperLeft.getY());
        //check if new position is within bounds
        if (newUpperLeft.getX() >= 0) {
            //set a new upperLeft point
            this.upperLeft = newUpperLeft;
            //create a new block on new position
            Rectangle rec = this.paddleBlock.getCollisionRectangle();
            this.paddleBlock = new Block(newUpperLeft, rec.getWidth(),
                    rec.getHeight(), this.paddleBlock.getColor());
        } else {
            newUpperLeft = new Point(0, this.upperLeft.getY());
            this.upperLeft = newUpperLeft;
            Rectangle rec = this.paddleBlock.getCollisionRectangle();
            this.paddleBlock = new Block(newUpperLeft, rec.getWidth(),
                    rec.getHeight(), this.paddleBlock.getColor());
        }

    }

    /**
     * Moves paddle position to the right by constant units -
     * paddle's speed is a constant on Const class.
     *
     * @param dt the dt
     */
    public void moveRight(double dt) {
        //create a new upperLeft point for a paddle (after movement)
        Point newUpperLeft = new Point(this.upperLeft.getX()
                + this.paddleSpeed * dt, this.upperLeft.getY());
        //check if new position is within bounds
        if (newUpperLeft.getX() <= Consts.RIGHT_BOUND_POINT.getX()
                - this.paddleBlock.getWidth()) {
            //set a new upperLeft point
            this.upperLeft = newUpperLeft;
            //create a new block on new position
            Rectangle rec = this.paddleBlock.getCollisionRectangle();
            this.paddleBlock = new Block(newUpperLeft, rec.getWidth(),
                    rec.getHeight(), this.paddleBlock.getColor());
        } else {
            newUpperLeft = new Point(Consts.RIGHT_BOUND_POINT.getX()
                    - this.paddleBlock.getWidth(), this.upperLeft.getY());
            this.upperLeft = newUpperLeft;
            Rectangle rec = this.paddleBlock.getCollisionRectangle();
            this.paddleBlock = new Block(newUpperLeft, rec.getWidth(),
                    rec.getHeight(), this.paddleBlock.getColor());
        }

    }


    /**
     * Announce paddle for a time pass. functions checks if user pressed
     * right/left key , and moves paddle according to matching key presses.
     *
     * @param dt delta time
     */
    public void timePassed(double dt) {
        //check which key has been pressed and send to matching function
        if (this.keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft(dt);
        }
        if (this.keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight(dt);
        }
    }

    /**
     * draws paddle on given draw surface.
     *
     * @param d draw surface to draw object on.
     */
    public void drawOn(DrawSurface d) {
        Rectangle rec = this.paddleBlock.getCollisionRectangle();
        //get coordinates of a block
        int x = (int) rec.getUpperLeft().getX();
        int y = (int) rec.getUpperLeft().getY();
        int width = (int) rec.getWidth();
        int height = (int) rec.getHeight();
        //fill block
        d.setColor(this.paddleBlock.getColor());
        d.fillRectangle(x, y, width, height);
        //shading effect
        d.setColor(this.paddleBlock.getColor().brighter());
        d.fillRectangle(x, y, width, height / 2);
        //draw rectangle edges
        d.setColor(Color.BLACK);
        d.drawRectangle(x, y, width, height);
    }

    // Collidable

    /**
     * @return the paddle's rectangle.
     */
    public Rectangle getCollisionRectangle() {

        return this.paddleBlock.getCollisionRectangle();
    }

    /**
     * If ball and paddle have collided , function returns a matching velocity
     * according to the position of the collision.
     *
     * @param hitter          ball
     * @param collisionPoint  Point of intersection
     * @param currentVelocity of Ball
     * @return a matching velocity object according to the position
     * of the collision.
     */
    public Velocity hit(Ball hitter, Point collisionPoint,
                        Velocity currentVelocity) {
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        // same size for each region
        double regSize = this.paddleBlock.getWidth() / (Consts.PADDLE_REG_NUM);
        //array of regions
        //we create number of regions + 1 to bound each region with 2 points
        double[] region = new double[Consts.PADDLE_REG_NUM + 1];
        //matching angles for each region
        double[] regVelocity = new double[Consts.PADDLE_REG_NUM];
        //set region 0 as upperleft corner of a paddle
        region[0] = this.upperLeft.getX();
        //increase each region by region size
        for (int i = 1; i < region.length; i++) {
            region[i] = region[i - 1] + regSize;
        }
        //set first velocity angle
        regVelocity[0] = Consts.PADDLE_REG1_ANGLE;
        //increase and set each angle by given difference between angles
        for (int i = 1; i < regVelocity.length; i++) {
            regVelocity[i] = regVelocity[i - 1] + Consts.PADDLE_REG_ANGLE_DIFF;
        }
        //set a default velocity
        Velocity v = this.paddleBlock.hit(hitter, collisionPoint,
                currentVelocity);
        //iterate on each region to check for collision
        for (int i = 0; i < Consts.PADDLE_REG_NUM; i++) {
            if (collisionPoint.getX() > region[i]
                    && collisionPoint.getX() < region[i + 1]) {
                //if ball is on mid region we return the current velocity
                if (i == (Consts.PADDLE_REG_NUM / 2)) {
                    return v;
                }
                //set speed (pythagoras)
                double speed = Math.sqrt((dx * dx) + (dy * dy));
                //set new velocity from matching angle
                v = Velocity.fromAngleAndSpeed(regVelocity[i], speed);
                break;
            }
        }
        return v;
    }

    /**
     * Add this paddle to the game.
     *
     * @param g SpaceInvaderLevel object to add to
     */
    public void addToGame(SpaceInvaderLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * Removes paddle from given game level.
     *
     * @param spaceInvaderLevel to remove object from.
     */
    public void removeFromGame(SpaceInvaderLevel spaceInvaderLevel) {
        spaceInvaderLevel.removeCollidable(this);
        spaceInvaderLevel.removeSprite(this);
    }

    /**
     * Get paddle block block.
     *
     * @return the block
     */
    public Block getPaddleBlock() {
        return this.paddleBlock;
    }
}