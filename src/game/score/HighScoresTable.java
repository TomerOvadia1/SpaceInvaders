package game.score;

import java.io.File;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.io.FileNotFoundException;

/**
 * HighScoresTable class.
 *
 * @author Tomer Ovadia.
 */
public class HighScoresTable implements java.io.Serializable {
    private int tableSize;
    private List<ScoreInfo> scoresList;

    /**
     * Create an empty high-scores table with the specified size.
     * The size means that the table holds up to size top scores.
     *
     * @param size the size
     */
    public HighScoresTable(int size) {
        this.tableSize = size;
        this.scoresList = new ArrayList<>();
    }


    /**
     * Add a high-score.
     *
     * @param score the score
     */
    public void add(ScoreInfo score) {
        //if score list is full
        if (this.scoresList.size() == this.size()) {
            //check if current score is higher than lowest score
            if (this.scoresList.get(this.size() - 1).getScore()
                    <= score.getScore()) {
                //remove lowest score
                this.scoresList.remove(this.size() - 1);
                //add new score
                this.scoresList.add(score);
            }
            //if score list is not full
        } else if (this.scoresList.size() < this.size()) {
            this.scoresList.add(score);
        }
        //sort score list using custom comparator
        this.scoresList.sort(new ScoreInfoComparator());

    }

    /**
     * Size int.
     *
     * @return table size.
     */
    public int size() {
        return this.tableSize;
    }

    /**
     * Return the current high scores.
     * The list is sorted such that the highest
     * scores come first.
     *
     * @return high scores
     */
    public List<ScoreInfo> getHighScores() {
        return this.scoresList;
    }

    /**
     * return the rank of the current score: where will it
     * be on the list if added?
     * Rank 1 means the score will be highest on the list.
     * Rank `size` means the score will be lowest.
     * Rank > `size` means the score is too low and will not
     * be added to the list.
     *
     * @param score the score
     * @return rank rank
     */
    public int getRank(int score) {
        //loop on scores to check if already exists
        for (ScoreInfo scoreInfo : this.scoresList) {
            if (scoreInfo.getScore() == score) {
                return this.scoresList.indexOf(scoreInfo) + 1;
            }
        }
        //if does not exist- return the matching rank by creating a copy
        //of the table and adding the current score
        List<ScoreInfo> listCopy = new ArrayList<>(this.scoresList);
        ScoreInfo newScore = new ScoreInfo("newScore", score);
        listCopy.add(newScore);
        listCopy.sort(new ScoreInfoComparator());
        return listCopy.indexOf(newScore) + 1;
    }

    /**
     * Clears the table.
     */
    public void clear() {
        this.tableSize = 0;
        this.scoresList.clear();
    }

    /**
     * Load table data from file.
     * Current table data is cleared.
     *
     * @param filename the filename
     * @throws IOException the io exception
     */
    public void load(File filename) throws IOException {
        //create a new input stream
        ObjectInputStream is = null;
        try {
            //read object
            is = new ObjectInputStream(new FileInputStream(filename));
            HighScoresTable newTable = (HighScoresTable) is.readObject();
            //clear current table
            this.clear();
            //set new parameters
            this.tableSize = newTable.size();
            this.scoresList = newTable.scoresList;
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFound Exception has occurred.");
        } catch (FileNotFoundException ex2) {
            this.tableSize = 5;
            this.scoresList = new ArrayList<>();
            this.save(filename);
        } finally {
            if (is != null) {
                is.close();
            }

        }

    }

    /**
     * Save table data to the specified file.
     *
     * @param filename the filename
     * @throws IOException the io exception
     */
    public void save(File filename) throws IOException {
        // Saving of object in a file
        ObjectOutputStream os = null;
        try {
            os = new ObjectOutputStream(new FileOutputStream(filename));
            os.writeObject(this);
        } finally {
            //cannot close a null output stream
            if (os != null) {
                os.close();
            }

        }
    }

    /**
     * Needs update boolean.
     *
     * @param score the score
     * @return the boolean
     */
    public boolean needsUpdate(int score) {
        return this.getRank(score) <= this.size();
    }

    /**
     * Read a table from file and return it.
     * If the file does not exist, or there is a problem with
     * reading it, an empty table is returned.
     *
     * @param filename the filename
     * @return high scores table
     */
    public static HighScoresTable loadFromFile(File filename) {
        //new input stream
        ObjectInputStream is = null;

        HighScoresTable newTable;
        try {
            is = new ObjectInputStream(new FileInputStream(filename));
            newTable = (HighScoresTable) is.readObject();
            is.close();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e3) {
            newTable = new HighScoresTable(5);
        }
        return newTable;

    }

    /**
     * Main.
     *
     * @param args the args
     */
//TESTING MAIN FUNCTION
    public static void main(String[] args) {
        HighScoresTable hst = new HighScoresTable(5);
        hst.add(new ScoreInfo("p3", 3));
        hst.add(new ScoreInfo("p1", 1));
        hst.add(new ScoreInfo("p2", 2));
        hst.add(new ScoreInfo("p4", 4));
        hst.add(new ScoreInfo("p5", 5));
        try {
            hst.save(new File("highscores.ser"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("ERROR");
        }
        HighScoresTable hst2 = new HighScoresTable(8);
        hst2.add(new ScoreInfo("p2", 2));
        hst2.add(new ScoreInfo("p2", 2));
        hst2.add(new ScoreInfo("p2", 2));
        hst2.add(new ScoreInfo("p2", 2));
        hst2.add(new ScoreInfo("p2", 2));
        hst2.add(new ScoreInfo("p2", 2));
        hst2.add(new ScoreInfo("p2", 2));
        hst2.add(new ScoreInfo("p2", 2));
        //used to check that only 'size' length is saved
        hst2.add(new ScoreInfo("p2", 3));
        hst2.add(new ScoreInfo("p2", 3));

        hst2.add(new ScoreInfo("p2", 3));

        hst2.add(new ScoreInfo("p2", 3));


        try {
            hst2.save(new File("test2.ser"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("ERROR2");
        }
        try {
            hst.load(new File("test2.ser"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("ERROR2");
        }
        //Should print only p2:2
        for (ScoreInfo si : hst.getHighScores()) {
            System.out.println(si.getName() + ":" + si.getScore());
        }
    }

}