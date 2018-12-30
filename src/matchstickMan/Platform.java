package matchstickMan;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;

public class Platform extends Application
{
    ArrayList<String> last = new ArrayList<>();
    public static final double width = 1800;
    public static final double height = 1000;
    public static final double field = height*0.75;
    public BorderPane border = new BorderPane();
    public Scene scene = new Scene(border, width, height);
    public MatchstickMan man = new MatchstickMan(true, Color.ROYALBLUE);
    public MatchstickMan man2;
    public Robot robot;
    public MotionController mc = new MotionController(man);
    public ProgressBar hp = new ProgressBar();
    public ProgressBar hp2 = new ProgressBar();
    public static Stage stage;
    /*--------------------------------------------------------------------------------------------------*/
//    public Line hor = new Line(0,6.5*MatchstickMan.ratio+height/2,width,6.5*MatchstickMan.ratio+height/2);
    public void start(Stage primaryStage)
    {
        display(primaryStage, "normal");
    }
    public void display(Stage primaryStage, String mode)
    {
        stage = primaryStage;
        primaryStage.centerOnScreen();
        scene.setOnKeyPressed(mc::pressedHandler);
        scene.setOnKeyReleased(mc::releasedHandler);
        initMan();
        Label label = new Label("3");
        label.setTextFill(Color.DARKRED);
        label.setStyle("-fx-font-size: 100");
        border.setCenter(label);
        switch (mode)
        {
            case "simple":
                count(label,true);
                break;
            case "normal":
                archimage();
                count(label,true);
                break;
            case "hard":
                count(label,true);
                break;
            case "fuck":
                count(label,true);
                break;
            case "PVP":
                PVP();
                count(label,false);

        }
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args)
    {
        launch(args);
    }
    public void initMan()
    {
//        Button start = new Button("Press or Enter!");
//        start.setPrefWidth(100);
//        start.setPrefHeight(100);
        hp.setProgress(1);
        hp.setStyle("-fx-accent: BLUE");
        hp.setPrefSize(400,30);
        hp2.setStyle("-fx-accent: RED");
        hp2.setPrefSize(400,30);
        HBox ghp = new HBox(hp, hp2);
        ghp.setAlignment(Pos.TOP_CENTER);
        border.setBackground(new Background(new BackgroundImage(new Image("/matchstickMan/image/background1.gif"),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                new BackgroundSize(1,1,true,true,false,false))));
        ghp.setSpacing(width/5);
        border.setTop(ghp);
        man.setLayoutX(30);
        man.setLayoutY(field);
//        PVP();
//        archimage();
        border.getChildren().add(man);
        man.leftBorder = 0;
        man.rightBorder = width;
        man.setPlatform(border);
        hp.progressProperty().bind(man.hp.divide(100));
        man.enable("Shield");
        man.enable("Shining");
        man.enable("Ball");
        man.enable("ShadowMove");
        man.enable("Stick");
//        border.setBottom(new ImageView(new Image("/matchStickMan/image/stick.png")));
    }
    public void count(Label label, boolean pve)
    {
        new Timeline(new KeyFrame(Duration.seconds(1), new KeyValue(label.textProperty(), "2"))
                ,new KeyFrame(Duration.seconds(2), new KeyValue(label.textProperty(), "1"))
                ,new KeyFrame(Duration.seconds(3), event ->
        {

            new Timeline(new KeyFrame(Duration.millis(1000), event1 ->
            {
                border.getChildren().remove(label);
            }, new KeyValue(label.scaleXProperty(), 1.5), new KeyValue(label.scaleYProperty(), 1.5))).play();
            man.init();
            if(pve)
            {
                robot.init();
                ArrayList<String> arrayList = new ArrayList<>();
                arrayList.add("switch");
                mc.auto(arrayList, 0);
            }
            else
            {
                man2.init();
            }

        },new KeyValue(label.textProperty(), "Fight"))).play();

    }
    public void test()
    {
        Bullet bullet = new Bullet(width,height);
        border.getChildren().add(bullet);
        bullet.release(man.opponent);
        bullet.release(man);
    }
    public void  PVP()
    {
        man2 = new MatchstickMan(false, Color.PINK);
        mc.setMan2(man2);
        man2.setLayoutX(width-30);
        man2.setLayoutY(field);
        man2.enable("Stick");
        border.getChildren().add(man2);
        man2.setPlatform(border);
        man2.leftBorder = 0;
        man2.rightBorder = width;
        man2.enable("Shield");
        man2.enable("Shining");
        man2.enable("Ball");
        man2.enable("ShadowMove");
        man.setOpponent(man2);
        man2.setOpponent(man);
        hp2.progressProperty().bind(man2.hp.divide(100));
    }
    public void PVE(Robot r)
    {
        robot = r;
        mc.setRobot(robot);
        robot.setLayoutX(width-30);
        robot.setLayoutY(field);
        border.getChildren().add(robot);
        robot.setPlatform(border);
        robot.leftBorder = 0;
        robot.rightBorder = width;
        robot.enable("Stick");
        robot.enable("Shield");
        robot.enable("Shining");
        robot.enable("Ball");
        robot.enable("ShadowMove");
        man.setOpponent(robot);
        robot.setOpponent(man);
        hp2.progressProperty().bind(robot.hp.divide(100));
        man.toPlayer(!(r instanceof Warrior));
        robot.toPlayer(!(r instanceof Warrior));
        robot.transfere(mc);
    }
    public void normal()
    {
        PVE(new Robot(false, mc, Color.INDIANRED));
//        robot.shining.play(robot.skin);
    }
    public void archimage()
    {
        PVE(new Warrior(false,mc,Color.INDIANRED));
    }
}
