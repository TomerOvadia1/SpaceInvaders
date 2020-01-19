package game.levels;

import constants.Consts;
import constants.LevelsConsts;
import sprites.Velocity;

import java.util.ArrayList;
import java.util.List;

/**
 * VelocityConstructor Class.
 *
 * @author Tomer Ovadia
 */
public class VelocityConstructor {
    private int ballNumber;

    /**
     * Constructor.
     *
     * @param num number of balls to create velocities to.
     */
    public VelocityConstructor(int num) {
        this.ballNumber = num;
    }

    /**
     * Create a list of velocities so balls spread in arch form.
     *
     * @return List of velocities.
     */
    public List<Velocity> create() {
        List<Velocity> velocityList = new ArrayList<>(this.ballNumber);
        //set a base angle
        int baseAngle = LevelsConsts.BASE_VELOCITY_ANGLE;
        //set an angle difference according to number of balls
        int angleDiff = 90 / this.ballNumber;
        //loop to create balls with proper velocity
        for (int i = 0; i < this.ballNumber; i++) {
            if (this.ballNumber % 2 != 0) {
                //set velocity acording to number of balls
                //if it divides by 2 we do not set a middle ball
                if (i % 2 == 0) {
                    velocityList.add(Velocity.fromAngleAndSpeed(-baseAngle,
                            Consts.BALL_DEFAULT_SPEED));
                } else {
                    baseAngle += angleDiff;
                    velocityList.add(Velocity.fromAngleAndSpeed(baseAngle,
                            Consts.BALL_DEFAULT_SPEED));
                }
                continue;
            }
            //set velocity acording to number of balls
            //if it divides by 2 we do not set a middle ball
            if (i % 2 == 0) {
                baseAngle += angleDiff;
                velocityList.add(Velocity.fromAngleAndSpeed(baseAngle,
                        Consts.BALL_DEFAULT_SPEED));
            } else {
                velocityList.add(Velocity.fromAngleAndSpeed(-baseAngle,
                        Consts.BALL_DEFAULT_SPEED));
            }
        }
        return velocityList;

    }
}
