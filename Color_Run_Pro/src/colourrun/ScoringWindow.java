package colourrun;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
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
//import javafx.scene.media.Media;
//import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * This class works when 'High Score ' Button is clicked It displays all the
 * high scores in sorted order
 *
 * @author Shifath
 */
public class ScoringWindow extends Application {

    Label label1, label2, label3;
    //Stage window;
    Button button;
//    public static Media media;
//    public static MediaPlayer mediaPlayer;

    /**
     * It starts the display of high score window
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

        Image image = new Image(ScoringWindow.class.getResourceAsStream("scoring.jpg"));
        ImageView iv = new ImageView(image);

        HighMan hm = new HighMan();

        label1 = new Label();
        label1.setText("Highscores:\n");
        label1.setPadding(new Insets(30, 0, 0, 50));
        label1.setFont(new Font("courier new", 10));
        label1.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 1.5em;");

        label2 = new Label();
        label2.setText("        Name       " + "      Score    " + "    Level\n" + hm.getHighscoreString());
        label2.setPadding(new Insets(0, 0, 0, 50));
        label2.setFont(new Font("courier new", 10));
        label2.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 1.5em;");

        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(label1, label2);

        button = new Button("Main Menu");
        button.setPrefSize(170, 40);
        button.setStyle("-fx-background-color:#1d316c; -fx-text-fill: #5b12ff;");
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
        root.getChildren().addAll(pane, vbox, button);

        Scene scene = new Scene(root);

        window.setTitle("High Scores");
        window.setScene(scene);
        window.setHeight(597);
        window.setWidth(998);
        window.show();

        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        window.setX((primScreenBounds.getWidth() - window.getWidth()) / 2);
        window.setY((primScreenBounds.getHeight() - window.getHeight()) / 4);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
