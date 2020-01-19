package animation.menu;

import animation.AnimationRunner;
import animation.EndScreen;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.awt.Color;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * MenuAnimation class.
 *
 * @param <T> generic object
 * @author Tomer Ovadia
 */
public class MenuAnimation<T> implements Menu<T> {
    private AnimationRunner runner;
    private KeyboardSensor keyboardSensor;
    private String title;
    private List<T> returnValues;
    private List<String> itemNames;
    private List<String> itemKeys;
    private T status;
    private boolean stop;
    private Image logo;
    private List<Boolean> isSubMenu;
    private List<Menu<T>> subMenus;

    /**
     * Constructor.
     *
     * @param menuTitle title.
     * @param r         animation runner.
     * @param ks        keyboard sensor.
     */
    public MenuAnimation(String menuTitle, AnimationRunner r,
                         KeyboardSensor ks) {
        this.title = menuTitle;
        this.runner = r;
        this.keyboardSensor = ks;
        this.returnValues = new ArrayList<>();
        this.itemNames = new ArrayList<>();
        this.itemKeys = new ArrayList<>();
        this.isSubMenu = new ArrayList<>();
        this.subMenus = new ArrayList<>();
        this.logo = null;
        InputStream is = null;
        try {
            is = ClassLoader.getSystemClassLoader().
                    getResourceAsStream("images/logo.jpg");
            logo = ImageIO.read(is);
            is.close();
        } catch (IOException ex) {
            throw new RuntimeException("Logo loading error.");
        }
        this.resetMenu();

    }


    /**
     * Adds new selection to current menu.
     *
     * @param key       press for selection
     * @param message   to display
     * @param returnVal of selection
     */
    public void addSelection(String key, String message, T returnVal) {
        this.itemKeys.add(key);
        this.itemNames.add(message);
        this.returnValues.add(returnVal);
        this.isSubMenu.add(false);
        //add null for empty assigment to keep the right order
        this.subMenus.add(null);
    }


    /**
     * @return menu status.
     */
    public T getStatus() {
        return this.status;
    }

    /**
     * run a one frame animation.
     *
     * @param d  drawsurface
     * @param dt delta time
     */
    public void doOneFrame(DrawSurface d, double dt) {
        //draws logo
        this.drawLogo(d);
        //draws menu
        this.drawMenu(d);
        //check for key press
        for (int i = 0; i < this.returnValues.size(); i++) {
            if (this.keyboardSensor.isPressed(this.itemKeys.get(i))) {
                //if sub menu
                if (this.isSubMenu.get(i)) {
                    //run task
                    Menu<T> subMenu = this.subMenus.get(i);
                    this.runner.run(subMenu);
                    this.status = subMenu.getStatus();
                    this.stop = true;
                    subMenu.resetMenu();
                    break;
                } else {
                    this.stop = true;
                    this.status = this.returnValues.get(i);
                }
            }
        }
    }


    /**
     * draws current menu.
     *
     * @param d draw surface
     */
    public void drawMenu(DrawSurface d) {
        d.setColor(Color.WHITE);
        int x = d.getHeight() / 2;
        int y = d.getHeight() / 2;
        d.drawText(x + 40, y, this.title, 30);
        y += 35;

        for (int i = 0; i < this.itemNames.size(); i++) {
            String option = "(" + this.itemKeys.get(i) + ")" + "    "
                    + this.itemNames.get(i);
            d.drawText(x, y, option, 25);
            y += 30;

        }
    }

    /**
     * Draws arkanoid logo.
     *
     * @param d draw surface
     */
    public void drawLogo(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        EndScreen.drawMultStars(d, 1, Color.RED, 100);
        EndScreen.drawMultStars(d, 1, Color.BLUE, 100);
        EndScreen.drawMultStars(d, 1, Color.WHITE, 100);
        d.drawImage(d.getWidth() / 5, d.getHeight() / 8, this.logo);
    }

    /**
     * @return true if current animation should stop,false otherwise.
     */
    public boolean shouldStop() {
        return this.stop;
    }

    /**
     * Reset menu's status and stop status.
     */
    public void resetMenu() {
        this.status = null;
        this.stop = false;
    }

    /**
     * Adds a sub Menu to current menu.
     *
     * @param key     key press for selection
     * @param message to display
     * @param subMenu to add
     */
    public void addSubMenu(String key, String message, Menu<T> subMenu) {
        this.itemKeys.add(key);
        this.itemNames.add(message);
        this.returnValues.add(null);
        this.subMenus.add(subMenu);
        this.isSubMenu.add(true);
    }
}
