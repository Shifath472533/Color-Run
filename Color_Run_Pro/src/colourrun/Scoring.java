package colourrun;

import java.io.Serializable;

/**
 * This  class contains the name, score and level of players
 * It can be sorted as it implements Serializable
 * @author Mohammad Faiyaz Khan (2016331011),shifath(2016331001)
 */
public class Scoring  implements Serializable {
    private int score;
    private String naam, levelName;

    /**
     * Getter method for score
     * @return score
     */
    public int getScore() {
        return score;
    }

    /**
     * Getter method for player name
     * @return name
     */
    public String getNaam() {
        return naam;
    }

/**
     * Getter method for level name
     * @return level name
     */    
    public String getLevelName() {
        return levelName;
    }

    public Scoring(String naam, int score, String levelName) {
        this.score = score;
        this.naam = naam;
        this.levelName = levelName;
    }
}
