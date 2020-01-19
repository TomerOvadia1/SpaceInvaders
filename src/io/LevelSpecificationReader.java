
package io;

import collidable.Block;
import factories.BlocksFromSymbolsFactory;
import factories.ColorsParser;
import factories.ImageParser;
import factories.LevelFactory;
import game.levels.ColorBackground;
import game.levels.ImageBackground;
import game.levels.LevelInformation;
import sprites.Sprite;
import sprites.Velocity;
import java.awt.Color;
import java.awt.Image;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * The type Level specification reader.
 *
 * @author Tomer Ovadia
 */
public class LevelSpecificationReader {
    //CONSTANTS
    private static final String START_LEVEL = "START_LEVEL";
    private static final String LEVEL_NAME = "level_name";
    private static final String BALL_VELOCITIES = "ball_velocities";
    //background definitions
    private static final String BACKGROUND = "background";
    private static final String RGB_PRE = "color(RGB(";
    private static final String RGB_POST = "))";
    private static final String COLOR_PRE = "color(";
    private static final String COLOR_POST = ")";
    private static final String IMAGE_PRE = "image(";
    private static final String IMAGE_POST = ")";
    //----------------------
    private static final String PADDLE_SPEED = "paddle_speed";
    private static final String PADDLE_WIDTH = "paddle_width";
    private static final String BLOCK_DEFINITIONS = "block_definitions";
    private static final String BLOCKS_START_X = "blocks_start_x";
    private static final String BLOCKS_START_Y = "blocks_start_y";
    private static final String ROW_HEIGHT = "row_height";
    private static final String NUM_BLOCKS = "num_blocks";
    private static final String START_BLOCKS = "START_BLOCKS";
    private static final String END_BLOCKS = "END_BLOCKS";
    private static final String END_LEVEL = "END_LEVEL";
    //private String levelName;
    //private int numberOfBlocksToClear;
    //private int paddleSpeed;
    //private int paddleWidth;
    private List<Velocity> velocities;
    private Sprite background;
    private List<Block> blocks;
    private Map<String, Boolean> loadListStatus;
    private int blockStartX;
    private int blockStartY;
    private int rowHeight;
    private BlocksFromSymbolsFactory blockDef;
    //private String blockDefPath;

    /**
     * Instantiates a new Level specification reader.
     */
    public LevelSpecificationReader() {
        this.velocities = new ArrayList<>();
        this.background = null;
        this.blocks = new ArrayList<>();
        this.loadListStatus = new TreeMap<>();
        this.blockStartX = 0;
        this.blockStartY = 0;
        this.rowHeight = 0;
        this.blockDef = null;
    }

    /**
     * From reader list.
     *
     * @param reader the reader
     * @return the list
     */
    public List<LevelInformation> fromReader(java.io.Reader reader) {
        List<LevelInformation> levels = new ArrayList<>();
        LineNumberReader lineReader = null;
        boolean isBlockSection = false;
        LevelFactory currLevel = null;
        this.loadListStatus = newLoadListStatus();
        int lineNumber = 0;
        int x = 0;
        int y = 0;
        try {
            lineReader = new LineNumberReader(reader);
            String line = null;
            line = lineReader.readLine();
            //loop until EOF
            while (line != null) {
                //skip on empty lines or comments
                while (line.equals("") || line.trim().startsWith("#")) {
                    line = lineReader.readLine();
                    if (line == null) {
                        reader.close();
                        return levels;
                    }
                }
                //trim spaces
                line = line.trim();
                //check for block section
                if (!isBlockSection) {
                    if (line.startsWith(START_LEVEL)) {
                        //create a new level
                        currLevel = new LevelFactory();
                        //reset load List Status
                        this.loadListStatus = newLoadListStatus();
                    } else if (line.startsWith(BLOCK_DEFINITIONS)) {
                        String[] lineSplit = line.split(":");
                        //not in the form of key:value
                        if (lineSplit.length != 2) {
                            throw new RuntimeException("Wrong Usage."
                                    + "Loading Failed");
                        }
                        String defPath = lineSplit[1];
                        /*not a file/does not exist
                        if (!defPath.isFile()) {
                            throw new RuntimeException("Error in loading Block"
                                    + " Definition file.");
                        }*/
                        try {
                            InputStream is =
                                    ClassLoader.getSystemClassLoader().
                                            getResourceAsStream(defPath);
                            this.blockDef = BlocksDefinitionReader.fromReader(
                                    new InputStreamReader(is));
                            is.close();
                            //mark as loaded
                            loadListStatus.put(BLOCK_DEFINITIONS, true);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }

                    } else if (line.startsWith(END_LEVEL)) {
                        if (this.fieldsLoaded()) {
                            levels.add(currLevel);
                            this.levelReset();
                            lineNumber = 0;
                            x = 0;
                            y = 0;
                        } else {
                            throw new RuntimeException("Level Reader: "
                                    + "Missing fields, loading failed.");
                        }
                    } else if (line.startsWith(START_BLOCKS)) {
                        isBlockSection = true;
                    } else {                //line contain values to store
                        //split lines and send key and value to Set-Level
                        String[] lineSplit = line.split(":");
                        if (lineSplit.length != 2) {
                            throw new RuntimeException("Wrong Usage."
                                    + "Loading Failed");
                        }
                        String key = lineSplit[0];
                        String value = lineSplit[1];
                        setNonBlockLevelInfo(key, value, currLevel);
                    }
                } else if (line.startsWith(END_BLOCKS)) {
                    isBlockSection = false;
                } else {
                    x = this.blockStartX;
                    for (int i = 0; i < line.length(); i++) {
                        String symbol = String.valueOf(line.charAt(i));
                        y = this.blockStartY + (this.rowHeight * lineNumber);
                        if (this.blockDef.isSpaceSymbol(symbol)) {
                            x += this.blockDef.getSpaceWidth(symbol);
                        } else if (this.blockDef.isBlockSymbol(symbol)) {
                            Block b = this.blockDef.getBlock(symbol,
                                    x, y);
                            currLevel.addBlock(b);
                            x += b.getWidth();
                        } else {
                            throw new RuntimeException("Error: Unknown block "
                                    + "symbol");
                        }

                    }
                    lineNumber++;
                }
                line = lineReader.readLine();
            }
            lineReader.close();
        } catch (IOException | NullPointerException e) {
            throw new RuntimeException(e.getMessage());
        }
        return levels;
    }

    /**
     * Level reset.
     */
    public void levelReset() {
        this.velocities = new ArrayList<>();
        this.background = null;
        this.blocks = new ArrayList<>();
        this.loadListStatus = newLoadListStatus();
        this.blockStartX = 0;
        this.blockStartY = 0;
        this.rowHeight = 0;
        this.blockDef = null;
    }

    /**
     * New load list status map.
     *
     * @return the map
     */
    public Map<String, Boolean> newLoadListStatus() {
        Map<String, Boolean> status = new TreeMap<>();
        status.put(LEVEL_NAME, false);
        status.put(BALL_VELOCITIES, false);
        status.put(BACKGROUND, false);
        status.put(PADDLE_SPEED, false);
        status.put(PADDLE_WIDTH, false);
        status.put(BLOCK_DEFINITIONS, false);
        status.put(BLOCKS_START_X, false);
        status.put(BLOCKS_START_Y, false);
        status.put(ROW_HEIGHT, false);
        status.put(NUM_BLOCKS, false);
        //loadListStatus.put(START_BLOCKS, false);
        //loadListStatus.put(END_BLOCKS, false);
        //might add END_LEVEL TO CHECK FOR ALL SECTIONS ?
        return status;

    }

    /**
     * Fields loaded boolean.
     *
     * @return the boolean
     */
    public boolean fieldsLoaded() {
        for (Map.Entry<String, Boolean> entry : this.loadListStatus.
                entrySet()) {
            boolean status = entry.getValue();
            if (!status) {
                return false;
            }
        }
        return true;
    }


    /**
     * Sets non block level info.
     *
     * @param key   the key
     * @param value the value
     * @param level the level
     */
    public void setNonBlockLevelInfo(String key, String value,
                                     LevelFactory level) {
        //check each key and its matching value
        if (key.equals(LEVEL_NAME)) {
            //set a level name
            level.setLevelName(value);
            //mark as loaded
            loadListStatus.put(LEVEL_NAME, true);
        } else if (key.equals(BALL_VELOCITIES)) {
            //split to multiple velocities
            String[] splitVelo = value.split(" ");
            //loop on given velocities
            for (String currVelo : splitVelo) {
                String[] speedAngle = currVelo.split(",");
                //not in the form of (a,s)
                if (speedAngle.length != 2) {
                    throw new RuntimeException("Wrong Usage."
                            + "Loading Failed");
                }
                double speed = 0;
                double angle = 0;
                //set speed and angle
                try {
                    angle = Double.parseDouble(speedAngle[0]);
                    speed = Double.parseDouble(speedAngle[1]);
                    Velocity v = Velocity.fromAngleAndSpeed(angle, speed);
                    this.velocities.add(v);
                } catch (Exception e) {
                    throw new RuntimeException("Ball velocities Error.");
                }
                //set level velocities
                level.setVelocities(this.velocities);
                level.setNumOfBalls(this.velocities.size());
            }
            //mark as loaded
            loadListStatus.put(BALL_VELOCITIES, true);
        } else if (key.equals(BACKGROUND)) {
            if (value.startsWith(COLOR_PRE) && value.endsWith(COLOR_POST)) {
                //extract value
                value = value.substring(COLOR_PRE.length(),
                        value.length() - COLOR_POST.length());
                //parse color
                ColorsParser cp = new ColorsParser();
                //set new background sprite
                Color color = cp.colorFromString(value);
                level.setBackground(new ColorBackground(color));
            } else if (value.startsWith(IMAGE_PRE)
                    && value.endsWith(IMAGE_POST)) {
                //extract value
                value = value.substring(IMAGE_PRE.length(), value.length()
                        - IMAGE_POST.length());
                //parse image
                Image image = ImageParser.imageFromString(value);
                //set new background sprite
                level.setBackground(new ImageBackground(image));
            } else {
                throw new RuntimeException("Background Error: Wrong usage.");
            }
            loadListStatus.put(BACKGROUND, true);
        } else if (key.equals(PADDLE_SPEED)) {
            try {
                level.setPaddleSpeed(Integer.parseInt(value));
                //mark as loaded
                loadListStatus.put(PADDLE_SPEED, true);
            } catch (Exception e) {
                throw new RuntimeException("Paddle speed Error.");
            }
        } else if (key.equals(PADDLE_WIDTH)) {
            try {
                level.setPaddleWidth(Integer.parseInt(value));
                //mark as loaded
                loadListStatus.put(PADDLE_WIDTH, true);
            } catch (Exception e) {
                throw new RuntimeException("Paddle width Error.");
            }
        } else if (key.equals(BLOCKS_START_X)) {
            try {
                this.blockStartX = Integer.parseInt(value);
                //mark as loaded
                loadListStatus.put(BLOCKS_START_X, true);
            } catch (Exception e) {
                throw new RuntimeException("Block start-X Error.");
            }
        } else if (key.equals(BLOCKS_START_Y)) {
            try {
                this.blockStartY = Integer.parseInt(value);
                //mark as loaded
                loadListStatus.put(BLOCKS_START_Y, true);
            } catch (Exception e) {
                throw new RuntimeException("Block start-Y Error.");
            }
        } else if (key.equals(ROW_HEIGHT)) {
            try {
                this.rowHeight = Integer.parseInt(value);
                //mark as loaded
                loadListStatus.put(ROW_HEIGHT, true);
            } catch (Exception e) {
                throw new RuntimeException("Row height Error.");
            }
        } else if (key.equals(NUM_BLOCKS)) {
            try {
                level.setNumberOfBlocksToRemove(Integer.parseInt(value));
                //mark as loaded
                loadListStatus.put(NUM_BLOCKS, true);
            } catch (Exception e) {
                throw new RuntimeException("Block number Error.");
            }
        } else if (key.equals(BLOCK_DEFINITIONS)) {
            try {
                this.blockDef = BlocksDefinitionReader.
                        fromReader(new FileReader(value));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else {
            throw new RuntimeException("Error.Unknown key.");
        }
    }

}
