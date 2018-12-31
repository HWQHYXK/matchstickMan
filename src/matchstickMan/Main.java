package matchstickMan;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Main extends Application
{
//    public static Stage stage;
    private int i = 0;
    @Override
    public void start(Stage primaryStage)
    {
//        Parent parent = FXMLLoader.load(getClass().getResource("prompt.fxml"));
//        primaryStage.initStyle(StageStyle.TRANSPARENT);
//        primaryStage.setResizable(false);
//        primaryStage.setScene(new Scene(parent));
//        stage = primaryStage;
//        primaryStage.show();
        Stage stage = primaryStage;
        MediaPlayer mediaPlayer = new MediaPlayer(new Media(new File("src/matchstickMan/media/PointG.mp3").toURI().toString()));
        MediaView mediaView = new MediaView(mediaPlayer);
        mediaPlayer.setStartTime(Duration.ZERO);
        mediaPlayer.setStopTime(Duration.minutes(3));
        mediaPlayer.setCycleCount(-1);
        mediaPlayer.setOnEndOfMedia(mediaPlayer::play);
        mediaPlayer.play();
        BorderPane borderPane = new BorderPane();
        borderPane.getChildren().add(mediaView);
        FlowPane hBox = new FlowPane(200,MatchstickMan.ratio);
        borderPane.setCenter(hBox);
        for(int i=1;i<=9;i++)
        {
            if(i==5)
            {
                Light.Point light = new Light.Point();
                light.setX(2*MatchstickMan.ratio);
                light.setY(2*MatchstickMan.ratio);
                light.setZ(50);
                Lighting lighting = new Lighting();
                lighting.setLight(light);
                lighting.setSurfaceScale(5.0);
                Button button = new Button("Start");
                button.setTextFill(Color.ORANGERED);
                button.setStyle("-fx-background-color: LIGHTGREEN;-fx-border-radius: 25;-fx-background-radius: 25;");
                button.setEffect(lighting);
                button.setPrefWidth(4*MatchstickMan.ratio);
                button.setPrefHeight(4*MatchstickMan.ratio);
                TranslateTransition translateTransition = new TranslateTransition(Duration.millis(1020),button);
                translateTransition.setByY(10);
                translateTransition.setAutoReverse(true);
                translateTransition.setCycleCount(TranslateTransition.INDEFINITE);
                translateTransition.play();
                button.setOnAction(event ->
                {
                    borderPane.getChildren().clear();
                    primaryStage.close();
                    try {
                        Runtime.getRuntime().exec("java -jar restart.jar");
                    }catch (IOException e)
                    {
                        Chooser.display();
                    }
                });
                hBox.getChildren().add(button);
                continue;
            }
            MatchstickMan man;
            if(i<5) man = new MatchstickMan(true, Color.rgb(new Random().nextInt(255),new Random().nextInt(255),new Random().nextInt(255)));
            else man =  new MatchstickMan(false, Color.rgb(new Random().nextInt(255),new Random().nextInt(255),new Random().nextInt(255)));
            man.setVisible(false);
            hBox.getChildren().add(man);
            man.init();
            man.leftBorder = 0;
            man.rightBorder = 1;
            man.setPlatform(borderPane);
            man.enable("Shield");
            man.enable("Ball");
            man.enable("ShadowMove");
            man.upp(true);
            Timeline animation = new Timeline(new KeyFrame(Duration.millis(1020), event1 ->
            {
                man.upp(true);
            }));
            animation.setCycleCount(Timeline.INDEFINITE);
            animation.play();
        }
        Timeline t = new Timeline(new KeyFrame(Duration.seconds(5), event ->hBox.getChildren().get(i++).setVisible(true)));
        t.setCycleCount(9);
        t.play();
        borderPane.setStyle("-fx-background-color: transparent");
        Scene scene = new Scene(borderPane, 800, 800);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        scene.setOnMouseDragged(event ->
        {
            primaryStage.setX(event.getX());
            primaryStage.setY(event.getY());
        });
        primaryStage.show();

//        mediaPlayer.play();
    }
    public static void main(String[] args) {
        launch(args);
    }
}