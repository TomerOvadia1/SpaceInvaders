package game.listener;

import collidable.Alien;
import game.SpaceInvaderLevel;
import sprites.Ball;


/**
 * The class Alien remove.
 * @author Tomer Ovadia
 */
public class AlienRemove implements AlienListener {
    public static final int SCORE_INCREASE = 100;
    private SpaceInvaderLevel level;
    private Counter score;


    /**
     * Instantiates a new Alien remove.
     *
     * @param gameLevel    the game level
     * @param scoreCounter the score counter
     */
    public AlienRemove(SpaceInvaderLevel gameLevel, Counter scoreCounter) {
        this.level = gameLevel;
        this.score = scoreCounter;
    }

    /**
     * announce a hit event.
     * This function remove balls , alien , and add to score.
     *
     * @param beingHit the being hit
     * @param hitter   the hitter
     */
    @Override
    public void hitEvent(Alien beingHit, Ball hitter) {
        if (this.level.getPlayerShots().contains(hitter)) {
            //if hit count is greater than 0 decrease it to mark a hit
            if (beingHit.getHitCount() > 0) {
                beingHit.setHitCount(beingHit.getHitCount() - 1);
            }
            if (beingHit.getHitCount() == 0) {
                int size = this.level.getAlienFormation().getAliens().size();
                //loop on lines of aliens list only on an hit event
                for (int i = 0; i < size; i++) {
                    //remove given alien
                    this.level.getAlienFormation().getAliens().get(i).
                            remove(beingHit);
                    //check if given line is empty , if so remove it
                    if (this.level.getAlienFormation().getAliens().
                            get(i).size() == 0) {
                        this.level.getAlienFormation().getAliens().remove(i);
                        break;
                    }
                }
                //remove block from spaceInvaderLevel
                beingHit.removeFromGame(this.level);
                //remove this listener from the block
                beingHit.removeAlienListener(this);
                hitter.removeFromGame(this.level);
                this.level.getPlayerShots().remove(hitter);
                this.score.increase(SCORE_INCREASE);

            }
        } else { // alien hit another alien
            this.level.getAlienShots().remove(hitter);
            hitter.removeFromGame(this.level);
        }
        //if entire formation has been destroyed , set matching value
        if (this.level.getAlienFormation().getAliens().size() == 0) {
            this.level.getAlienFormation().setDestruction();
        }
    }

}

