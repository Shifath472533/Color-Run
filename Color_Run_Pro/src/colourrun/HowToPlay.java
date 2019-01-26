package colourrun;


import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * This class works when 'How To Play ' Button is clicked It shows the window
 * where the rules and procedure of the game is written
 *
 * @author Shifath
 */
public class HowToPlay extends Application {

    Label label;
    Stage window;
    Button button;
    public static Media media;
    public static MediaPlayer mediaPlayer;

    /**
     * This method starts to display the stage of rules and procedures
     *
     * @param window
     * @throws Exception
     */
    @Override
    public void start(Stage window) throws Exception {

//        media = new Media(getClass().getResource("hip.mp3").toString());
//        mediaPlayer = new MediaPlayer(media);
//
//        mediaPlayer.setOnEndOfMedia(new Runnable() {
//            public void run() {
//                mediaPlayer.seek(Duration.ZERO);
//                mediaPlayer.setVolume(0.1);
//            }
//        });
//
//        mediaPlayer.play();
//        mediaPlayer.setVolume(0.1);
        this.window = window;

        Image image = new Image(HowToPlay.class.getResourceAsStream("ball.jpg"));
        ImageView iv = new ImageView(image);

        label = new Label();
        label.setText("1. Move the circle and it's corresponding colour. If you don't find the matching colour then\n    navigate through the vacant area.\n"
                + "2. Use Left and Right arrow key to move the circle left and right.                  \n"
                + "3. If you want to change the difficulty level the click the 'Game Mode' button and select the \n    difficulty level.");
        label.setPadding(new Insets(20, 20, 200, 20));
        label.setFont(new Font("courier new", 10));
        label.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 1.5em;");

        button = new Button("Main Menu");
        button.setPrefSize(170, 40);
        button.setStyle("-fx-background-color:#1D316C; -fx-text-fill: #FFFFFF;");
        button.setLayoutX(660);
        button.setLayoutY(420);
        button.setOnAction(e -> {
            Parent root;
            try {

                root = FXMLLoader.load(getClass().getResource("ColorDoc.fxml"));
                Scene scene = new Scene(root);
                scene.getStylesheets().add(getClass().getResource("color.css").toString());
                window.setScene(scene);
                window.setResizable(false);
                window.setFullScreen(false);
                window.setOnCloseRequest(ex -> {
                    window.close();
                });
                window.show();
            } catch (IOException ex) {
                Logger.getLogger(ScoringWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        Pane pane = new VBox();

        pane.getChildren().addAll(iv);

        Group root = new Group();
        root.getChildren().addAll(pane, label, button);

        Scene scene = new Scene(root);

        window.setTitle("How To Play");
        window.setScene(scene);
        window.setMaxHeight(597);
        window.setMaxWidth(998);
        window.show();

        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        window.setX((primScreenBounds.getWidth() - window.getWidth()) / 2);
        window.setY((primScreenBounds.getHeight() - window.getHeight()) / 4);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
