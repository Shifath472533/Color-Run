package colourrun;

import static colourrun.ColourRun.mediaPlayer1;
import static colourrun.Easy.flag;
import com.jfoenix.controls.JFXButton;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * This class contains the level "Medium" in the main game
 *
 * @author Mohammad Faiyaz Khan (2016331011)
 */
public class Medium extends Application {

    Stage window;
    Scene scene3;
    TextField txt;
    Button bt;
    Label label1;
    VBox vbox;
    private String st;

    Scene scene, scene1, scene2;
    private static final double KEYBOARD_MOVEMENT_DELTA = 10;
    private static final double RECT_WIDTH = 200;
    private static final double RECT_HEIGHT = 30;
    private static final double RECT_MAX_Y = 800;
    AudioClip Sound, col;

    static Timeline timeline3;
    static int W = 1200, H = 800;
    static double background = 0, background1 = 800;

    static int score = 0, life = 34, flag, flag1;
    Label lifelabel = new Label();
    static int f = 0, a = 1;
    public static Stage window1, window2, window3;
    //JFXButton newGameButton;
    public static Media media;
    public static MediaPlayer mediaPlayer;
    public static Media media1;
    public static MediaPlayer mediaPlayer1;

    private static double SCREEN_WIDTH = 1200;//maximum screen width
    static double sum = 0.0;

    /**
     * hashmap for collecting 4 rectangles each
     */
    HashMap< Integer, Rectangle[]> mp = new HashMap<Integer, Rectangle[]>();
    Random rand2 = new Random();
    static int prevlife = 34;

    /**
     * This method creates a rectangle with defined width and height
     *
     * @param x defines the x co-ordinate of x of the rectangle
     * @param idx It is used to dynamically change the width of the rectangles
     * @return a rectangle with random width
     */
    private Rectangle createRectangle(double x, int idx) {
        Random rand = new Random();
        Rectangle rect = new Rectangle();
        rect.setX(x);
        rect.setY(0);
        rect.setHeight(RECT_HEIGHT);

        //random width
        if (idx >= 5) {
            double width = SCREEN_WIDTH - sum;
            rect.setWidth(width);
        } else {
            int width = rand.nextInt(150) + 100;
            sum = sum + width;
            rect.setWidth(width);
        }
        rect.setStroke(Color.BLACK);
        rect.setArcWidth(0);
        rect.setArcHeight(0);
        return rect;
    }

    /**
     * This method creates a rectangle with defined width and height
     *
     * @param x defines the x co-ordinate of x of the rectangle
     * @return a Rectangle type object
     */
    public Rectangle type2(double x) {
        Random rand = new Random();
        Rectangle rect = new Rectangle();
        rect.setX(x);
        double y = 65.0;
        rect.setY(y);
        rect.setHeight(RECT_HEIGHT);
        rect.setWidth(50);
        //random width
        rect.setStroke(Color.BLACK);
        rect.setArcWidth(0);
        rect.setArcHeight(0);
        return rect;
    }

    /**
     * This method creates a rectangle with defined width and height
     *
     * @param x defines the x co-ordinate of x of the rectangle
     * @return a Rectangle type object
     */
    public Rectangle createSimpleRectangle(double x) {
        Rectangle rect = new Rectangle(x, 0, RECT_WIDTH, RECT_HEIGHT);
        rect.setStroke(Color.BLACK);
        rect.setArcWidth(0);
        rect.setArcHeight(0);
        return rect;
    }

    /**
     * This method checks the validity whether the width of all the rectangles
     * make up the width of the screen
     *
     * @param rects The width of the rectangles to check
     * @return true or false, validity of the set of rectangles
     */
    boolean valid(Rectangle[] rects) {
        double s = 0;
        for (int i = 0; i < 6; i++) {
            s = s + rects[i].getWidth();
        }
        if (Math.abs(s - 1200.00) < 0.000009) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * This method resets score, life when the restart button is clicked
     */
    void restartScore() {
        f = 0;
        score = 0;
        lifelabel.setText("***");
        life = 34;
    }

    Rectangle[] rectangles;
    private Random random = new Random();

    /**
     * This method randomizes different color of rectangle when it is called
     *
     * @param rects the array that contains randomized rectangles
     * @param circle the circle moving in the game
     * @param colors the list of colors of the rectangles and circle
     */
    private void randomizeColors6(Rectangle[] rects, Circle circle, List<Color> colors) {
        Collections.shuffle(colors, random);
        for (int i = 0; i < rects.length; i++) {
            rects[i].setFill(colors.get(i));
        }
        circle.setFill(colors.get(random.nextInt(colors.size())));
    }

    /**
     * This method randomizes different color of rectangle when it is called
     *
     * @param rects the array that contains randomized rectangles
     * @param circle the circle moving in the game
     * @param colors the list of colors of the rectangles and circle
     */
    private void randomizeColors7(Rectangle[] rects, Circle circle, List<Color> colors) {
        Collections.shuffle(colors, random);
        for (int i = 0; i < rects.length; i++) {
            rects[i].setFill(colors.get(i));
        }
        List<Color> ColorTemp = Arrays.asList(Color.DARKGOLDENROD, Color.LIGHTPINK, Color.DARKGREY);
        circle.setFill(ColorTemp.get(random.nextInt(ColorTemp.size())));
    }

    /**
     * This method starts the game on clicking the new game button
     *
     * @param stage the stage of the level
     */
    public void start(Stage stage) {

        media = new Media(getClass().getResource("hip.mp3").toString());
        mediaPlayer = new MediaPlayer(media);
        media1 = new Media(getClass().getResource("Game Over.mp3").toString());
        mediaPlayer1 = new MediaPlayer(media1);

        mediaPlayer.setOnEndOfMedia(new Runnable() {
            public void run() {
                mediaPlayer.seek(Duration.ZERO);
                mediaPlayer.setVolume(0.1);
            }
        });

        mediaPlayer.play();
        mediaPlayer.setVolume(0.1);

        stage.close();

        background = -800;
        background1 = 0;

        Image image1 = new Image(Medium.class.getResourceAsStream("Medium.png"));
        Image image2 = new Image(Medium.class.getResourceAsStream("Medium.png"));

        ImageView iv1 = new ImageView(image1);
        ImageView iv2 = new ImageView(image2);

        iv1.setFitWidth(W);
        iv1.setFitHeight(H);
        iv2.setFitWidth(W);
        iv2.setFitHeight(H);
        iv1.setTranslateX(0);
        iv1.setTranslateY(0);
        iv2.setTranslateX(0);
        iv2.setTranslateY(0);

        /**
         * This timeline animates the background
         */
        timeline3 = new Timeline(new KeyFrame(Duration.millis(6), e2 -> {
            background++;
            if (background >= 800) {
                background = -800;
            }
            iv1.setTranslateY(background);

            background1++;
            if (background1 >= 800) {
                background1 = -800;
            }
            iv2.setTranslateY(background1);
        }));
        timeline3.setCycleCount(Timeline.INDEFINITE);
        timeline3.play();
        window2 = new Stage();
        List<Color> colors6 = Arrays.asList(Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.CADETBLUE, Color.DARKCYAN);
        List<Color> colors7 = Arrays.asList(Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.CADETBLUE, Color.DARKCYAN, Color.AQUAMARINE);
        Circle circle = new Circle(650, 700, 20);

        /**
         * creating 100 rectangles in a hashmap
         */
        for (int j = 0; j <= 100; j += 5) {
            Rectangle[] rectangles = new Rectangle[6];
            for (int i = 0; i < rectangles.length; i++) {
                rectangles[i] = createSimpleRectangle(RECT_WIDTH * i);
            }
            mp.put(j, rectangles);
        }
        for (int j = 0; j <= 100; j++) {
            //do{
            if (j % 5 == 0) {
                continue;
            }
            sum = 0;
            Rectangle[] rectangles = new Rectangle[6];
            for (int i = 0; i < rectangles.length - 1; i++) {
                rectangles[i] = createRectangle(sum, i);
            }
            rectangles[5] = createRectangle(sum, 5);
            //}while(valid(rectangles)==false);

            mp.put(j, rectangles);
        }
        for (int j = 101; j <= 150; j++) {
            sum = 0;
            Rectangle[] rectangles = new Rectangle[7];
            rectangles[0] = createRectangle(sum, 0);
            rectangles[1] = type2(sum);
            sum += 150;
            rectangles[2] = type2(sum);
            sum += 50;
            for (int i = 3; i < rectangles.length; i++) {
                rectangles[i] = createRectangle(sum, i);
            }
            mp.put(j, rectangles);
        }
        for (int j = 151; j <= 200; j++) {
            sum = 0;
            Rectangle[] rectangles = new Rectangle[7];
            for (int i = 0; i < 4; i++) {
                rectangles[i] = createRectangle(sum, i);
            }

            rectangles[4] = type2(sum);
            sum += 150;
            rectangles[5] = type2(sum);
            sum += 50;
            rectangles[6] = createRectangle(sum, 6);
            mp.put(j, rectangles);
        }
        Label scorelabel = new Label("Score: ");
        scorelabel.setAlignment(Pos.TOP_RIGHT);
        scorelabel.setText("Score: 0");

        lifelabel.setAlignment(Pos.TOP_LEFT);
        //lifelabel.set
        lifelabel.setPadding(new Insets(0, 0, 820, 1050));
        scorelabel.setText("Score: 0");
        scorelabel.setStyle("-fx-text-fill: #FFFFFF;");
        lifelabel.setText("***");
        lifelabel.setStyle("-fx-text-fill: #FFFFFF;");

        Pane root = new Pane();
        root.getChildren().addAll(iv1, iv2);
        root.setPrefHeight(RECT_MAX_Y);

        rectangles = mp.get(rand2.nextInt(100));
        for (Rectangle rect : rectangles) {
            root.getChildren().add(rect);
        }
        root.getChildren().add(circle);

        window1 = new Stage();
        Label label = new Label("Game Over !");
        label.setPadding(new Insets(10, 10, 10, 10));
        label.setFont(new Font("Arial", 50));

        Label scrlabel = new Label("Your Score: ");
        scrlabel.setPadding(new Insets(10, 10, 10, 10));
        scrlabel.setFont(new Font("Arial", 30));

        Button restartButton = new Button("Play Again");
        restartButton.setPrefSize(170, 40);
        restartButton.setStyle("-fx-background-color:#1D316C; -fx-text-fill: #FFFFFF;");

        Button quitButton = new Button("Quit");
        quitButton.setPrefSize(170, 40);
        quitButton.setStyle("-fx-background-color:#1D316C; -fx-text-fill: #FFFFFF;");

        Button quittomainmenuButton = new Button("Quit to Main Menu");
        quittomainmenuButton.setPrefSize(170, 40);
        quittomainmenuButton.setStyle("-fx-background-color:#1D316C; -fx-text-fill: #FFFFFF;");

        VBox layout = new VBox();
        layout.getChildren().addAll(restartButton, quittomainmenuButton);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: linear-gradient( #2B577D ,  #CDC6CA)");
        layout.setSpacing(10);
        Scene scene1 = new Scene(layout, 500, 400);
        bt = new JFXButton("Enter");
        bt.setPrefSize(170, 40);
        bt.setStyle("-fx-background-color:#1D316C; -fx-text-fill: #FFFFFF;");
        label1 = new Label("     Enter Your Name\n(Maximum 8 character)");
        label.setTextAlignment(TextAlignment.CENTER);
        label1.setPadding(new Insets(10, 10, 10, 10));
        label1.setFont(new Font("Arial", 20));

        txt = new TextField();
        txt.setPromptText("Name");
        txt.setAlignment(Pos.CENTER);
        txt.setMaxSize(200, 20);
        vbox = new VBox(15);
        vbox.setAlignment(Pos.CENTER);
        vbox.setStyle("-fx-background-color: linear-gradient( #2B577D ,  #CDC6CA)");
        vbox.setSpacing(10);
        vbox.getChildren().addAll(label, scrlabel, label1, txt, bt);
        scene3 = new Scene(vbox);
        window1.setScene(scene3);
        window1.setResizable(false);
        window1.setHeight(430);
        window1.setWidth(500);
        window1.setOnCloseRequest(e -> {
            window2.close();
            window1.close();
        });

        double frameDuration = 16;
        double iterationDuration = 2100;
        int framesPerIteration = (int) (iterationDuration / frameDuration + 1);
        randomizeColors6(rectangles, circle, colors6);

        Timeline timeline = new Timeline();

        /**
         * The basic event handler class of the game It handles users key code
         * event and moves the circle Also it checks collision between objects
         * An object of this class is passed to the timeline
         */
        class FrameHandler implements EventHandler<ActionEvent> {

            private KeyCode code;
            private int frame = 1;
            public boolean lost = false;

            private void lostAction() {
                //mediaPlayer.setVolume(0);
                mediaPlayer1.play();
                mediaPlayer1.setVolume(0.3);
                timeline.stop();
                //score -= 10;
                scrlabel.setText("Score: " + score);
                window1.show();
                System.out.println(window1.getHeight() + "   " + window1.getWidth());
                Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
                window1.setX((primScreenBounds.getWidth() - window1.getWidth()) / 2);
                window1.setY((primScreenBounds.getHeight() - window1.getHeight()) / 4);

                HighMan hm = new HighMan();

                bt.setOnAction(e -> {
                    st = txt.getText();
                    if (st.length() > 8) {
                        st = st.substring(0, 8);
                    } else if (st.length() < 8) {
                        while ((st.length()) <= 8) {
                            st += " ";
                        }
                    }
                    hm.addScore(st, score, "Medium");
                    //System.out.println(st);
                    window1.setScene(scene1);
                    System.out.println(window1.getHeight() + "   " + window1.getWidth());
                });

                System.out.println(st);
            }

            @Override
            /**
             * This method is called when the game is lost It stops the timeline
             * and shows the Game Over window
             */
            public void handle(ActionEvent event) {
                if (frame == 0) {
                    if(flag == 0){
                        col = new AudioClip(getClass().getResource("click3.mp3").toString());
                        col.setVolume(1);
                        col.play();
                    }
                    flag = 0;
                    if (prevlife == life) {
                        score += 10;
                    } else {
                        prevlife = life;
                    }
                    scorelabel.setText("Score: " + score);
                }
                if (frame <= 10) {
                    /**
                     * when new frame is created, the existing rectangle is
                     * removed and a random rectangle from the hashmap is added
                     */
                    for (Rectangle rect : rectangles) {
                        root.getChildren().remove(rect);
                    }
                    int x = rand2.nextInt(200);
                    rectangles = mp.get(x);
                    for (Rectangle rect : rectangles) {
                        root.getChildren().add(rect);
                    }
                    //root.getChildren().add(circle);
                    if (x <= 100) {
                        randomizeColors6(rectangles, circle, colors6); // change colors when iteration is done
                    } else {
                        randomizeColors7(rectangles, circle, colors7);
                    }
                }

                /**
                 * move circle, if key is pressed
                 */
                if (code != null) {
                    switch (code) {
                        case RIGHT: {
                            if (circle.getCenterX() + circle.getRadius() < 1200) {
                                circle.setCenterX(circle.getCenterX() + KEYBOARD_MOVEMENT_DELTA);
                            }
                            break;
                        }
                        case LEFT: {
                            if (circle.getCenterX() + circle.getRadius() > 40) {
                                circle.setCenterX(circle.getCenterX() - KEYBOARD_MOVEMENT_DELTA);
                            }
                            break;
                        }
                    }
                }

                /**
                 * move rects & check intersection
                 */
                final Paint color = circle.getFill();
                final double cx = circle.getCenterX();
                final double cy = circle.getCenterY();
                final double r2 = circle.getRadius() * circle.getRadius();
                boolean lost = false;
                for (Rectangle rect : rectangles) {
                    rect.setY(frame * RECT_MAX_Y / framesPerIteration);
                    /**
                     * check for intersections with rects of wrong color
                     */
                    if (rect.getFill() != color) {

                        double dy = Math.min(Math.abs(rect.getY() - cy),
                                Math.abs(rect.getY() + rect.getHeight() - cy));
                        dy = dy * dy;

                        if (dy > r2) {
                            continue; // y-distance too great for intersection
                        }
                        if (cx >= rect.getX() && cx <= rect.getX() + rect.getWidth()) {
                            lost = true;
                        } else {
                            double dx = Math.min(Math.abs(rect.getX() - cx),
                                    Math.abs(rect.getX() + rect.getWidth() - cx));
                            if (dx * dx + dy <= r2) {
                                lost = true;
                            }
                        }
                    }
                }
                frame = (frame + 1) % framesPerIteration;
                /**
                 * Reseting the life
                 */
                if (lost) {
                    
                    flag = flag + 1;
                    if(flag == 1){
                        col = new AudioClip(getClass().getResource("wrong.mp3").toString());
                        col.setVolume(0.5); 
                        col.play();
                    }

                    --life;
                    System.out.println(life);
                    if (life >= 23 && life <= 34) {
                        f = 1;
                        //life = 23;
                        lifelabel.setText("**");
                    } else if (life < 23 && life >= 12) {
                        f = 1;
                        //life = 12;
                        lifelabel.setText("*");
                    } else if (life < 12 && life >= 1) {
                        f = 1;
                       // life = 1;
                        lifelabel.setText("");
                    } else if (life < 1) {
                        lostAction();
                    }
                }

            }
        }

        FrameHandler frameHandler = new FrameHandler();

        root.setStyle("-fx-background: url(../images/color ball.jpg);");
        root.setStyle("-fx-background-color: linear-gradient(#2B577D ,  #CDC6CA)");
        scorelabel.setFont(Font.font("Arial", 30));

        lifelabel.setFont(Font.font("Arial", 60));
        root.getChildren().addAll(scorelabel, lifelabel);
        Scene scene = new Scene(root);

        /**
         * keep track of the state of the arrow keys
         */
        scene.setOnKeyPressed(evt -> {
            KeyCode code = evt.getCode();
            switch (code) {
                case RIGHT:
                case LEFT:
                    frameHandler.code = code;
                    break;
            }
        });
        scene.setOnKeyReleased(evt -> {
            KeyCode code = evt.getCode();
            if (frameHandler.code == code) {
                frameHandler.code = null;
            }
        });

        /**
         * this button restarts the game
         */
        restartButton.setOnAction(event -> {
            frameHandler.lost = !frameHandler.lost;
            restartScore();
            frameHandler.frame = 0;
            frameHandler.code = null;
            mediaPlayer.stop();
            //mediaPlayer.play();
            //timeline.play();
            window1.close();
            Medium medium = new Medium();
            medium.start(window2);
        });

        /**
         * this button sets the scene to main menu
         */
        quittomainmenuButton.setOnAction((ActionEvent e) -> {
            ColourRun cr = new ColourRun();
            cr.setA(1);
            window1.close();
            window2.close();
            restartScore();
            ColourRun.w = 1;
            mediaPlayer.stop();
            ColourRun clr = new ColourRun();
            try {
                clr.start(window2);
            } catch (Exception ex) {

            }
        });

        /**
         * This button executes the exit from the game
         */
        quitButton.setOnAction(event -> {
            System.exit(0);
        });

        window2.setScene(scene);

        timeline.getKeyFrames()
                .add(new KeyFrame(Duration.millis(frameDuration), frameHandler));
        timeline.setCycleCount(Timeline.INDEFINITE);

        timeline.play();

        window2.setOnCloseRequest(e -> {
            window1.close();
            window2.close();
        });
        window2.show();
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        window2.setX((primScreenBounds.getWidth() - window2.getWidth()) / 2);
        window2.setY((primScreenBounds.getHeight() - window2.getHeight()) / 4);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
