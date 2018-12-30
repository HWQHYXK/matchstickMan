package matchstickMan;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Test extends Application
{
    public static final double width = 1800;
    public static final double height = 1000;
    public BorderPane border = new BorderPane();
    public Scene scene = new Scene(border, width, height);
    public MatchstickMan man = new MatchstickMan(true, Color.BLACK);
    public Timeline right = new Timeline();
    public Timeline left = new Timeline();
    public void start(Stage primaryStage) throws Exception
    {
        scene.setOnKeyPressed(this::handler);
        scene.setOnKeyReleased(this::handler2);
        border.getChildren().add(man);
        man.setLayoutX(30);
        man.setLayoutY(height/8*4);
        man.init();
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args)
    {
        launch(args);
    }
    public void handler(KeyEvent e)
    {
        if(e.getCode() == KeyCode.D && !man.isMoving )
        {
            man.changeStatus(0);
            man.left.stop();
            man.right();
            man.isMoving = true;
        }
        if(e.getCode() == KeyCode.A && !man.isMoving)
        {
            man.changeStatus(0);
            man.right.stop();
//            left = new Timeline();
//            KeyValue keyValue1 = new KeyValue(man.layoutXProperty(), 0);
//            KeyFrame keyFrame1 = new KeyFrame(Duration.millis(man.getLayoutX()*2), keyValue1);
//            left.getKeyFrames().addAll(keyFrame1);
//            left.play();
            man.left();
            man.isMoving = true;
        }
    }
    public void handler2(KeyEvent e)
    {
        man.legReposition();
        man.right.stop();
        man.left.stop();
        man.isMoving = false;
    }
}
