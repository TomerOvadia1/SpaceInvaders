package io;

import game.levels.LevelSet;

import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Level set reader.
 *
 * @author Tomer Ovadia.
 */
public class LevelSetReader {
    /**
     * From reader list.
     *
     * @param reader the reader
     * @return the list
     */
    public static List<LevelSet> fromReader(java.io.Reader reader) {
        List<LevelSet> levelSetList = new ArrayList<>();
        LineNumberReader lineReader = null;
        LevelSet currLevelSet = null;
        try {
            //line reader
            lineReader = new LineNumberReader(reader);
            String line = null;
            line = lineReader.readLine();
            //set line number as 1 to match requirements
            lineReader.setLineNumber(1);
            while (line != null) {
                //skip on empty lines or comments
                line = line.trim();
                while ((line.equals("") || line.trim().startsWith("#"))) {
                    line = lineReader.readLine();
                }
                //if line number is odd
                if (lineReader.getLineNumber() % 2 == 1) {
                    currLevelSet = new LevelSet();
                    String[] keyValue = line.split(":");
                    if (keyValue.length != 2) {
                        throw new RuntimeException("Level Set Reader Error:"
                                + "Line should only contain level key and "
                                + "description.");
                    }
                    if (keyValue[0].length() != 1) {
                        throw new RuntimeException("Level key must be a"
                                + "single character.");
                    }
                    currLevelSet.setKey(keyValue[0]);
                    currLevelSet.setDescription(keyValue[1]);
                } else {
                    currLevelSet.setPath(line);
                    levelSetList.add(currLevelSet);
                }
                line = lineReader.readLine();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return levelSetList;
    }


}
