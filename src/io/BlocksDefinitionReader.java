
package io;


import factories.BlockColorDrawer;
import factories.BlockCreator;
import factories.BlockDrawer;
import factories.BlockFactory;
import factories.BlockImageDrawer;
import factories.BlocksFromSymbolsFactory;
import factories.ColorsParser;
import factories.ImageParser;

import java.awt.Image;
import java.awt.Color;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * The type Blocks definition reader.
 *
 * @author Tomer Ovadia
 */
public class BlocksDefinitionReader {
    private static final String DEFAULT = "default";
    private static final String BLOCK_DEFINITION = "bdef";
    private static final String SPACER_DEFINITION = "sdef";
    private static final String SYMBOL = "symbol";
    private static final String WIDTH = "width";
    private static final String HEIGHT = "height";
    private static final String HIT_POINTS = "hit_points";
    private static final String FILL = "fill";
    private static final String FILL_K = "fill-";
    //private static final String RGB_PRE = "color(RGB(";
    //private static final String RGB_POST = "))";
    private static final String COLOR_PRE = "color(";
    private static final String COLOR_POST = ")";
    private static final String IMAGE_PRE = "image(";
    private static final String IMAGE_POST = ")";
    private static final String STROKE = "stroke";


    /**
     * From reader blocks from symbols factory.
     *
     * @param reader the reader
     * @return the blocks from symbols factory
     */
    public static BlocksFromSymbolsFactory fromReader(java.io.Reader reader) {
        Map<String, Integer> spacerWidths = new TreeMap<>();
        Map<String, BlockCreator> blockCreators = new TreeMap<>();
        //hold a default map
        Map<String, String> defaultSettings = new TreeMap<>();
        //hold a status map to check if all values needed were loaded
        Map<String, Boolean> blockStatus = blockStatusInitialize();
        LineNumberReader lineReader = null;
        try {
            lineReader = new LineNumberReader(reader);
            String line = null;
            line = lineReader.readLine();
            while (line != null) {
                //skip on empty lines or comments
                line = line.trim();
                while ((line.equals("") || line.trim().startsWith("#"))) {
                    line = lineReader.readLine();
                }
                //trim spaces
                line = line.trim();
                if (line.startsWith(DEFAULT)) {
                    line = line.substring(DEFAULT.length()).trim();
                    //split line
                    defaultSettings = lineSplitter(line);
                    for (String key : defaultSettings.keySet()) {
                        if (blockStatus.containsKey(key)) {
                            blockStatus.put(key, true);
                        }
                    }
                } else if (line.startsWith(BLOCK_DEFINITION)) {
                    String symbol;
                    blockStatus = blockStatusInitialize();
                    //remove bdef substring
                    line = line.substring(BLOCK_DEFINITION.length());
                    line = line.trim();
                    //create block info map , with default settings
                    Map<String, String> blockInfo =
                            new TreeMap<>(defaultSettings);
                    // add new values/settings from bdef specification -
                    // run over default settings if new settings are
                    // specified
                    blockInfo.putAll(lineSplitter(line));
                    //get symbol
                    symbol = getSymbol(blockInfo);
                    //update status map
                    blockStatus.put(SYMBOL, true);
                    blockInfo.remove(SYMBOL);
                    BlockCreator b = analyzeBlockInfo(blockInfo,
                            blockStatus);
                    blockCreators.put(symbol, b);
                } else if (line.startsWith(SPACER_DEFINITION)) {
                    //remove sdef tag
                    line = line.substring(SPACER_DEFINITION.length());
                    line = line.trim();
                    //create a new map
                    Map<String, String> spacerInfo = lineSplitter(line);
                    //if parameter is missing
                    if (!spacerInfo.containsKey(SYMBOL)
                            || !spacerInfo.containsKey(WIDTH)) {
                        throw new RuntimeException("Error: Spacer Info"
                                + "missing parameters.");
                    }
                    //get symbol
                    String symbol = getSymbol(spacerInfo);
                    Integer width;
                    try {
                        width = Integer.parseInt(spacerInfo.get(WIDTH));
                    } catch (Exception e) {
                        throw new RuntimeException("Width wrong usage."
                                + "Loading Failed");
                    }
                    if (width < 1) {
                        throw new RuntimeException("Width must be a"
                                + " positive number.");
                    }
                    spacerWidths.put(symbol, width);
                }

                line = lineReader.readLine();
            }
            lineReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
        return new BlocksFromSymbolsFactory(spacerWidths, blockCreators);
    }

    /**
     * Block status initialize map.
     *
     * @return the map
     */
    public static Map<String, Boolean> blockStatusInitialize() {
        Map<String, Boolean> status = new TreeMap<>();
        status.put(HEIGHT, false);
        status.put(SYMBOL, false);
        status.put(WIDTH, false);
        status.put(FILL, false);
        status.put(HIT_POINTS, false);
        return status;
    }

    /**
     * All fields loaded boolean.
     *
     * @param status the status
     * @return the boolean
     */
    public static boolean allFieldsLoaded(Map<String, Boolean> status) {
        boolean val = false;
        for (Map.Entry<String, Boolean> entry : status.entrySet()) {
            val = entry.getValue();
            if (!val) {
                return false;
            }
        }
        return true;
    }

    /**
     * Line splitter map.
     *
     * @param line the line
     * @return the map
     */
    public static Map<String, String> lineSplitter(String line) {
        Map<String, String> map = new TreeMap<>();
        String[] expression = line.split(" ");
        //split to key & value
        for (String exp : expression) {
            String[] keyValue = exp.split(":");
            if (keyValue.length != 2) {
                throw new RuntimeException("Wrong usage. Loading failed");
            }
            map.put(keyValue[0], keyValue[1]);
        }
        return map;
    }

    /**
     * Analyze block info block creator.
     *
     * @param blockInfo the block info
     * @param status    the status
     * @return the block creator
     */
    public static BlockCreator analyzeBlockInfo(Map<String, String> blockInfo,
                                                Map<String, Boolean>
                                                        status) {
        BlockFactory blockCreator = new BlockFactory();
        Color defaultColor = null;
        Image defaultImage = null;
        Color strokeColor = null;
        Map<String, String> fillK = new TreeMap<>();
        int hitPoints = 0;
        for (String key : blockInfo.keySet()) {
            //check for each parameter
            if (key.equals(HEIGHT)) {
                int height;
                try {
                    height = Integer.parseInt(blockInfo.get(HEIGHT));
                } catch (Exception e) {
                    throw new RuntimeException("Height wrong usage."
                            + "Loading failed");
                }
                if (height < 1) {
                    throw new RuntimeException("Height must be a positive"
                            + "number.");
                }
                blockCreator.setHeight(height);
                status.put(HEIGHT, true);
            } else if (key.equals(WIDTH)) {
                int width;
                try {
                    width = Integer.parseInt(blockInfo.get(WIDTH));
                } catch (Exception e) {
                    throw new RuntimeException("Width wrong usage."
                            + "Loading Failed");
                }
                if (width < 1) {
                    throw new RuntimeException("Width must be a positive"
                            + "number.");
                }
                blockCreator.setWidth(width);
                status.put(WIDTH, true);
            } else if (key.equals(HIT_POINTS)) {
                try {
                    hitPoints = Integer.parseInt(blockInfo.get(HIT_POINTS));
                } catch (Exception e) {
                    throw new RuntimeException("Wrong usage."
                            + "Loading failed");
                }
                if (hitPoints < 1) {
                    throw new RuntimeException("Hit Points must be a positive"
                            + "number.");
                }
                blockCreator.setHitPoints(hitPoints);
                status.put(HIT_POINTS, true);
            } else if (key.equals(FILL)) {
                String value = blockInfo.get(key);
                if (value.startsWith(COLOR_PRE) && value.endsWith(COLOR_POST)) {
                    //get color properties
                    value = value.substring(COLOR_PRE.length(),
                            value.length() - COLOR_POST.length());
                    ColorsParser cp = new ColorsParser();
                    defaultColor = cp.colorFromString(value);
                    status.put(FILL, true);
                } else if (value.startsWith(IMAGE_PRE)
                        && value.endsWith(IMAGE_POST)) {
                    value = value.substring(IMAGE_PRE.length(),
                            value.length() - IMAGE_POST.length());
                    defaultImage = ImageParser.imageFromString(value);
                    status.put(FILL, true);
                } else {
                    throw new RuntimeException("Unsupported fill value."
                            + "Loading failed");
                }
            } else if (key.equals(STROKE)) {
                String value = blockInfo.get(key);
                if (value.startsWith(COLOR_PRE) && value.endsWith(COLOR_POST)) {
                    //get color properties
                    value = value.substring(COLOR_PRE.length(),
                            value.length() - COLOR_POST.length());
                    ColorsParser cp = new ColorsParser();
                    strokeColor = cp.colorFromString(value);
                    blockCreator.setStroke(strokeColor);
                } else {
                    throw new RuntimeException("Unsupported stroke value."
                            + "Loading failed");
                }
            } else if (key.startsWith(FILL_K)) {
                String k = key.substring(FILL_K.length());
                fillK.put(k, blockInfo.get(key));

            } else {
                throw new RuntimeException("Unsupported key value."
                        + "Loading failed");
            }
        }
        if (fillK.size() == hitPoints) {
            status.put(FILL, true);
        }
        if (!allFieldsLoaded(status)) {
            throw new RuntimeException("Block Definition: Missing fields,"
                    + " loading failed.");
        }
        List<BlockDrawer> blockDrawerList = setBlockFill(hitPoints,
                defaultColor, defaultImage, fillK);
        blockCreator.setBlockDrawers(blockDrawerList);
        return blockCreator;
    }

    /**
     * Gets symbol.
     *
     * @param info the info
     * @return the symbol
     */
    public static String getSymbol(Map<String, String> info) {
        String symbol;
        if (!info.containsKey(SYMBOL)) {
            throw new RuntimeException("No block symbol.");
        }
        symbol = info.get(SYMBOL);
        if (symbol.length() != 1) {
            throw new RuntimeException("Symbol must be a single char.");

        }
        return symbol;
    }

    /**
     * Sets block fill.
     *
     * @param hitPoints    the hit points
     * @param defaultColor the default color
     * @param defaultImage the default image
     * @param fillK        the fill k
     * @return the block fill
     */
    public static List<BlockDrawer> setBlockFill(int hitPoints,
                                                 Color defaultColor,
                                                 Image defaultImage,
                                                 Map<String, String> fillK) {
        List<BlockDrawer> blockDrawerList = new ArrayList<>();
        BlockDrawer defaultBlockDrawer;
        if (defaultImage != null) {
            defaultBlockDrawer = new BlockImageDrawer(defaultImage);
        } else {
            defaultBlockDrawer = new BlockColorDrawer(defaultColor);
        }
        for (int i = 0; i < hitPoints; i++) {
            blockDrawerList.add(defaultBlockDrawer);
        }
        if (fillK == null) {
            return blockDrawerList;
        }
        for (String key : fillK.keySet()) {
            int k;
            try {
                k = Integer.parseInt(key);
            } catch (Exception e) {
                throw new RuntimeException("Fill-K: Wrong usage."
                        + "Loading failed");
            }
            if (k > hitPoints || k < 1) {
                throw new RuntimeException("Fill-K: OutOfBound-Exception."
                        + "Loading failed");
            }
            String value = fillK.get(key);
            if (value.startsWith(COLOR_PRE) && value.endsWith(COLOR_POST)) {
                //get color properties
                value = value.substring(COLOR_PRE.length(),
                        value.length() - COLOR_POST.length());
                ColorsParser cp = new ColorsParser();
                Color color = cp.colorFromString(value);
                blockDrawerList.set(k - 1, new BlockColorDrawer(color));
            } else if (value.startsWith(IMAGE_PRE)
                    && value.endsWith(IMAGE_POST)) {
                value = value.substring(IMAGE_PRE.length(),
                        value.length() - IMAGE_POST.length());
                Image image = ImageParser.imageFromString(value);
                blockDrawerList.set(k - 1, new BlockImageDrawer(image));

            } else {
                throw new RuntimeException("Fill-K: no color prefix/postfix"
                        + "Loading failed");
            }
        }
        return blockDrawerList;
    }

}