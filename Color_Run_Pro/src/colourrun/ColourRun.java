package colourrun;

import com.jfoenix.controls.JFXButton;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import static javafx.scene.media.MediaPlayer.INDEFINITE;
import javafx.stage.Screen;

/**
 * This class displays the starting window From this class other class are be
 * accessed this class is displayed first and from this other class are accessed
 * through buttons
 *
 * @author Shifath
 */
public class ColourRun extends Application {

    public static Stage window;
    Scene scene;
    public static int b = 1, w = 0, k = 1;
    private static int a;
    public static Media media1;
    public static MediaPlayer mediaPlayer1;

    public static Media media;
    public static MediaPlayer mediaPlayer;

    @FXML
    ImageView cross, cross1;
    @FXML
    private JFXButton newGameButton;

    public void setA(int p) {
        a = p;
    }

    /**
     * This method starts the starting window to display This method works when
     * 'New Game ' button is clicked
     *
     * @param stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        media = new Media(getClass().getResource("hip.mp3").toString());
        mediaPlayer = new MediaPlayer(media);

        if (w == 1) {
            w = 0;
        } else {
            window = stage;
        }

        mediaPlayer.setCycleCount(INDEFINITE);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setVolume(0.1);

        Parent root = FXMLLoader.load(getClass().getResource("ColorDoc.fxml"));

        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("color.css").toString());
        window.setScene(scene);
        window.setResizable(false);
        window.setFullScreen(false);
        window.setOnCloseRequest(e -> {
            window.close();
        });
        window.show();

        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        window.setX((primScreenBounds.getWidth() - window.getWidth()) / 2);
        window.setY((primScreenBounds.getHeight() - window.getHeight()) / 4);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
