package matchstickMan;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.effect.Bloom;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.Random;

public class ShadowMove implements Skill
{
    public boolean isMoving = false;
    public boolean enabled = false;
    MatchstickMan man;
    Timeline timeline ;
    Timeline timeline2 ;

    public ShadowMove()
    {
        timeline = new Timeline();
        timeline2 = new Timeline();
    }

    @Override
    public void changeStatus()
    {
        man.statusController.reverseFrozen();
        man.statusController.reverseHPLocked();
    }
    private void preAnimation()
    {
        Image image = new Image("/matchStickMan/image/attack1.gif");
        ImageView imageView = new ImageView(image);
        imageView.setPreserveRatio(true);
        imageView.setLayoutX(man.getLayoutX()-2*MatchstickMan.ratio);
        imageView.setLayoutY(man.getLayoutY()-0.5*MatchstickMan.ratio);
        if(!man.facingRight.get())imageView.setRotate(180);
        man.platform.getChildren().add(0,imageView);
        new Timeline(new KeyFrame(Duration.millis(500), event ->
        {
            man.platform.getChildren().remove(imageView);
        })).play();
    }
    @Override
    public void play()
    {
        isMoving = true;
        double x = man.getLayoutX();
        KeyValue keyValue;
        double distance = 20*MatchstickMan.ratio;
        if(man.motionStatus == 1)
        {
//            preAnimation();
            man.setEffect(new Bloom(0.1));
            man.statusController.setHPLocked(true);
            man.statusController.setFrozen(true);
//            man.setLayoutX(man.leftBorder);
            if(x+distance<man.rightBorder)keyValue = new KeyValue(man.layoutXProperty(), x+distance);
            else keyValue = new KeyValue(man.layoutXProperty(), man.rightBorder);
            timeline = new Timeline(new KeyFrame(Duration.millis(20), keyValue));
            timeline.setCycleCount(10);
            timeline.setOnFinished(event ->
            {
//                man.setLayoutX((man.rightBorder-man.leftBorder)*new Random().nextDouble()+man.leftBorder);
//                if(x+distance<man.rightBorder)man.setLayoutX(x+distance);
//                else man.setLayoutX(man.rightBorder);
                man.statusController.setHPLocked(false);
                man.statusController.setFrozen(false);
                man.setEffect(null);
                isMoving = false;
//                if(man.shining.enabled)man.shining.stop();
            });
            timeline.setDelay(Duration.millis(50));
            timeline.play();
        }
        else if(man.motionStatus == -1)
        {
//            preAnimation();
            man.setEffect(new Bloom(0.1));
            man.statusController.setHPLocked(true);
            man.statusController.setFrozen(true);
//            man.setLayoutX(man.rightBorder);
            if(x-distance>man.leftBorder)keyValue = new KeyValue(man.layoutXProperty(), x-distance);
            else keyValue = new KeyValue(man.layoutXProperty(), man.leftBorder);
            timeline2 = new Timeline(new KeyFrame(Duration.millis(20), keyValue));
            timeline2.setCycleCount(10);
            timeline2.setOnFinished(event ->
            {
//                man.setLayoutX((man.rightBorder-man.leftBorder)*new Random().nextDouble()+man.leftBorder);
//                if(x-distance>man.leftBorder)man.setLayoutX(x-distance);
//                else man.setLayoutX(man.leftBorder);
//                if(man.shining.enabled)man.shining.stop();
                man.setEffect(null);
                man.statusController.setHPLocked(false);
                man.statusController.setFrozen(false);
                isMoving = false;
            });
            timeline2.setDelay(Duration.millis(50));
            timeline2.play();
        }
    }

    @Override
    public void stop()
    {
        isMoving = false;
        timeline.stop();
        timeline2.stop();
    }
    @Override
    public void setMan(MatchstickMan man)
    {
        this.man = man;
    }
}



//                    event ->
//        {
//                System.out.println("here");
//                KeyValue keyValue1 = new KeyValue(man.layoutXProperty(), man.leftBorder);
//                KeyFrame keyFrame1 = new KeyFrame(Duration.millis(100),keyValue1);
//                timeline2.getKeyFrames().add(keyFrame1);
//                timeline2.setCycleCount(20);
//                timeline2.setOnFinished( event1 ->
//                {
//                    System.out.println("hello");
//                    changeStatus();
//                });
//                timeline2.play();
//            }