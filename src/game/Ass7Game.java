package game;

import animation.AnimationRunner;
import animation.HighScoresAnimation;
import animation.KeyPressStoppableAnimation;
import animation.menu.MenuAnimation;
import animation.menu.Task;
import biuoop.DialogManager;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import game.score.HighScoresTable;
import game.score.ScoreInfo;

import java.io.File;

import static constants.Consts.ANIMATION_FPS;
import static constants.Consts.GUI_HEIGHT;
import static constants.Consts.GUI_WIDTH;

/**
 * Ass7Game class.
 * This class is used to run the game.
 *
 * @author Tomer Ovadia
 */
public class Ass7Game {

    /**
     * The entry point of application.
     *
     * @param args unused
     */
    public static void main(String[] args) {
        //Creating gui and animation objects
        GUI gui = new GUI("Space Invaders", GUI_WIDTH, GUI_HEIGHT);
        KeyboardSensor keyboardSensor = gui.getKeyboardSensor();
        AnimationRunner runner = new AnimationRunner(ANIMATION_FPS, gui);
        //high score table loading / creating
        File scoreFile = new File("highscores");
        HighScoresTable highScoresTable =
                HighScoresTable.loadFromFile(scoreFile);
        HighScoresAnimation highScoresAnimation =
                new HighScoresAnimation(highScoresTable,
                        KeyboardSensor.SPACE_KEY, keyboardSensor);
        //SUB MENU OPTIONS
        MenuAnimation<Task<Void>> levelSetMenu =
                new MenuAnimation<>("Start", runner,
                        keyboardSensor);
        //MAIN MENU OPTIONS
        MenuAnimation<Task<Void>> mainMenu =
                new MenuAnimation<>("Main Menu", runner,
                        keyboardSensor);

        mainMenu.addSelection("s", "START GAME",
                new Task<Void>() {
                    public Void run() {
                        //Game Flow
                        GameFlow gameFlow = new GameFlow(runner,
                                keyboardSensor);
                        gameFlow.runLevels();
                        //score table animation
                        //Add score dialog
                        int score =
                                gameFlow.getScoreCounter().getValue();
                        if (highScoresTable.needsUpdate(score)) {
                            DialogManager dialog =
                                    gui.getDialogManager();
                            String name = dialog.showQuestionDialog(
                                    "Name", "Enter Your Name",
                                    "Player's Name");
                            highScoresTable.add(new ScoreInfo(name,
                                    gameFlow.getScoreCounter().
                                            getValue()));
                        }
                        try {
                            highScoresTable.save(scoreFile);
                        } catch (Exception e2) {
                            System.out.println(e2.getMessage());
                        }
                        KeyPressStoppableAnimation tableStoppable =
                                new KeyPressStoppableAnimation(
                                        keyboardSensor,
                                        keyboardSensor.SPACE_KEY,
                                        highScoresAnimation);
                        //check if user pressed space
                        runner.run(tableStoppable);
                        return null;
                    }
                });

        mainMenu.addSelection("h", "HIGH SCORES",
                new Task<Void>() {
                    public Void run() {
                        runner.run(new KeyPressStoppableAnimation(
                                keyboardSensor, "space",
                                new HighScoresAnimation(highScoresTable,
                                        "space", keyboardSensor)));
                        return null;
                    }
                });
        mainMenu.addSelection("q", "EXIT", new Task<Void>() {
            public Void run() {
                System.exit(0);
                return null;
            }
        });

        //run game
        while (true) {
            runner.run(mainMenu);
            Task<Void> task = mainMenu.getStatus();
            task.run();
            mainMenu.resetMenu();
        }
    }
}
