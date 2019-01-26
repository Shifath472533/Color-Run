package colourrun;

import java.util.Comparator;

/**
 * This class compares two scores
 * @author Mohammad Faiyaz Khan & Shifath
 */
public class SortingScore implements Comparator<Scoring> {
    
        /**
         * This function compares two scores
         * @param score1 first score
         * @param score2 second score
         * @return boolean value
         */
        public int compare(Scoring score1, Scoring score2) {

            int sc1 = score1.getScore();
            int sc2 = score2.getScore();

            if (sc1 > sc2){
                return -1;
            }else if (sc1 < sc2){
                return +1;
            }else{
                return 0;
            }
        }
}