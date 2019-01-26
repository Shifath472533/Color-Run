package colourrun;

import java.util.*;
import java.io.*;

/**
 * This class takes all the scores, adds them in an array list, loads the scores and updates the scores
 * 
 * @author Mohammad Faiyaz Khan, Shifath
 */
public class HighMan {
    
    static int max = 5;
    private ArrayList<Scoring> scores;

    private static final String HIGHSCORE_FILE = "scores1.dat";

    ObjectOutputStream outputStream = null;
    ObjectInputStream inputStream = null;

    /**
     * This method initializes the Scoring array list
     */
    public HighMan() {
        scores = new ArrayList<Scoring>();
    }
  
    /**
     * 
     * @return an ArrayList with scores in it 
     */
    public ArrayList<Scoring> getScores() {
        loadScoreFile();
        sort();
        return scores;
    }
 
    /**
     * Sorts the Array list of scores
     */
    private void sort() {
        SortingScore comparator = new SortingScore();
        Collections.sort(scores, comparator);
}

/**
 * This function adds a new score to the arra
 * @param name name of the player
 * @param score score the player achieved
 * @param levelName level in which the game was played on
 */    
    public void addScore(String name, int score, String levelName) {
        loadScoreFile();
        scores.add(new Scoring(name, score, levelName));
        updateScoreFile();
}
    /**
     * this method loads the scores from the file
     */
    public void loadScoreFile() {
        try {
            inputStream = new ObjectInputStream(new FileInputStream(HIGHSCORE_FILE));
            scores = (ArrayList<Scoring>) inputStream.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("[Laad] FNF Error: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("[Laad] IO Error: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("[Laad] CNF Error: " + e.getMessage());
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.flush();
                    outputStream.close();
                }
            } catch (IOException e) {
                System.out.println("[Laad] IO Error: " + e.getMessage());
            }
        }
}
    /**
     * This method writes and updates the scores in the file 
     */
    public void updateScoreFile() {
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream(HIGHSCORE_FILE));
            outputStream.writeObject(scores);
        } catch (FileNotFoundException e) {
            System.out.println("[Update] FNF Error: " + e.getMessage() + ",the program will try and make a new file");
        } catch (IOException e) {
            System.out.println("[Update] IO Error: " + e.getMessage());
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.flush();
                    outputStream.close();
                }
            } catch (IOException e) {
                System.out.println("[Update] Error: " + e.getMessage());
            }
        }
}
    /**
     * This method concatenates the player name, score and level name
     * @return the concatenated String
     */
    public String getHighscoreString() {
        String highscoreString = "";

        ArrayList<Scoring> scores;
        scores = getScores();
        
        max = 10; 

        int i = 0;
        int x = scores.size();
        if (x > max) {
            x = max;
        }
        while (i < x) {
            highscoreString += (i + 1) + ".\t" + scores.get(i).getNaam() + "\t\t" + scores.get(i).getScore() + "\t\t" + scores.get(i).getLevelName()+"\n";
            i++;
        }
        return highscoreString;
}
}