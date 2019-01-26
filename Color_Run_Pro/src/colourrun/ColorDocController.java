package colourrun;

import static colourrun.ColourRun.b;
import static colourrun.ColourRun.k;
import static colourrun.ColourRun.mediaPlayer;
import static colourrun.ColourRun.window;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class This is the controller class of the starting FXML file
 * named 'ColorDoc.fxml '
 *
 * @author Shifath
 */
public class ColorDocController implements Initializable {

    @FXML
    private JFXButton newGameButton;
    @FXML
    private ImageView cross, cross1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (k == 0) {
            cross.setOpacity(1);
            mediaPlayer.setAutoPlay(false);
            k = 1;
        }
    }

    /**
     * This method works when the sound icon is clicked It makes the sound off
     * if it is pressed even times then it will make the sound on and vice versa
     */
    @FXML
    private void soundclicked(MouseEvent event) {
        if (k == 1) {

            ColourRun.mediaPlayer.stop();
            System.out.println("ha1");
            cross.setOpacity(1);
            k = 0;
        } else {
            System.out.println("ha2");
            ColourRun.mediaPlayer.play();
            mediaPlayer.setVolume(0.1);
            cross.setOpacity(0);
            k = 1;
        }
    }

    /**
     * this method starts the game i.e. this method works when ' new game ' It
     * starts the start method of the respective Game mode selected button is
     * clicked It also works according to the game mode
     */
    @FXML
    private void buttonclicked(ActionEvent event) {
        if (b == 1) {
            mediaPlayer.stop();
            Easy easy = new Easy();
            easy.start(window);
        } else if (b == 2) {
            Hard cr2 = new Hard();

            try {

                mediaPlayer.stop();
                Medium medium = new Medium();
                medium.start(window);
            } catch (Exception ex) {

            }
        } else {
            mediaPlayer.stop();
            Hard hard = new Hard();
            hard.start(window);
        }
    }

    /**
     * This method works when ' High Score ' button is clicked It shows the
     * window where the High scores of the game is listed
     */
    @FXML
    private void highscoreclicked(ActionEvent event) {
        ScoringWindow sw = new ScoringWindow();
        try {
            //mediaPlayer.stop();
            sw.start(window);
        } catch (Exception ex) {

        }
    }

    /**
     * This method works when One of the game mode is selected
     */
    @FXML
    private void menuclicked1(ActionEvent event) {
        b = 1;
    }

    /**
     * This method works when One of the game mode is selected
     */
    @FXML
    private void menuclicked2(ActionEvent event) {
        b = 2;
    }

    /**
     * This method works when One of the game mode is selected
     */
    @FXML
    private void menuclicked3(ActionEvent event) {
        b = 3;
    }

    /**
     * This method works when ' How To Play ' button is clicked It shows the
     * window where the rules and procedure of the game is written
     */
    @FXML
    private void howtoplayclicked(ActionEvent event) {
        HowToPlay htp = new HowToPlay();
        try {
            //mediaPlayer.stop();
            htp.start(window);
        } catch (Exception ex) {

        }
    }

    /**
     * this method works when the ' Exit ' button is clicked It exits the system
     */
    @FXML
    private void quitclicked(ActionEvent event) {
        System.exit(0);
    }

}
