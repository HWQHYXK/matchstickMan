package matchstickMan;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Random;

public class Archimage extends Robot
{
    private boolean strong;
    private BooleanProperty superFrozen = new SimpleBooleanProperty();
    public Archimage(boolean facingRight, Color skin, boolean strong)
    {
        super(facingRight, skin);
        superFrozen.set(false);
        superFrozen.addListener((frozen, old, now) ->
        {
            if(now)transfere.stop();
            else transfere.play();
        });
        this.strong = strong;
    }
    private class OnFinished implements EventHandler<ActionEvent>
    {
        ImageView imageView;
        boolean flag = true;
        @Override
        public void handle(ActionEvent event)
        {
            if(flag&&!opponent.shadowMove.isMoving&&Math.abs(opponent.getLayoutX()-imageView.getLayoutX()-1.9*MatchstickMan.ratio)<2*MatchstickMan.ratio && Platform.field-opponent.getLayoutY()<MatchstickMan.ratio)
            {
                flag = false;
                imageView.setLayoutX(0x3fffffff);
                platform.getChildren().remove(imageView);
                opponent.statusController.damageHP(5);
            }
        }
    }
    public void summon(int step)
    {
        if(step == 1)
        {
            superFrozen.set(true);
            statusController.setFrozen(true);
            statusController.setHPLocked(true);
            ImageView effect, wing;
            if(!facingRight.get())
            {
                wing = new ImageView("matchstickMan/image/wing4.gif");
                wing.setScaleX(1.5);
                wing.setScaleY(1.5);
                wing.setLayoutX(-49);
                wing.setLayoutY(-15);
                effect = new ImageView("matchstickMan/image/summon.gif");
                effect.setLayoutX(-200);
            }
            else
            {
                wing = new ImageView("matchstickMan/image/wing3.gif");
                wing.setScaleX(1.5);
                wing.setScaleY(1.5);
                wing.setLayoutX(-100);
                effect = new ImageView("matchstickMan/image/summon1.gif");
            }
            effect.setLayoutY(-60);
            getChildren().addAll(effect);
            getChildren().add(0,wing);
            new Timeline(new KeyFrame(Duration.millis(800),new KeyValue(layoutYProperty(), field-20*ratio))).play();
            new Timeline(new KeyFrame(Duration.seconds(5), event ->
            {
                getChildren().remove(effect);
            })).play();
        }
        if(step == 5)
        {
            new Timeline(new KeyFrame(Duration.millis(500),event ->
            {
                superFrozen.set(false);
            }, new KeyValue(layoutYProperty(), field-6.5*ratio))).play();
            return;
        }
        Random random = new Random();
        int k = random.nextInt(2)+1;
        ImageView imageView = new ImageView("matchstickMan/image/monster"+ k+".gif");
        imageView.setLayoutY(field-100);
        OnFinished onFinished = new OnFinished();
        onFinished.imageView = imageView;
        Timeline judge = new Timeline(new KeyFrame(Duration.millis(1), onFinished));
        judge.setCycleCount(Timeline.INDEFINITE);
        judge.play();

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.5+random.nextDouble()),event ->
        {

            if(k%2==1)//facingRight
            {
                imageView.setLayoutX(leftBorder);
                new Timeline(new KeyFrame(Duration.seconds(random.nextDouble()+5),event1 ->
                {
                    platform.getChildren().remove(imageView);
                }, new KeyValue(imageView.layoutXProperty(), rightBorder))).play();
            }
            else
            {
                imageView.setLayoutX(rightBorder);
                new Timeline(new KeyFrame(Duration.seconds(random.nextDouble()+5),event1 ->
                {
                    platform.getChildren().remove(imageView);
                }, new KeyValue(imageView.layoutXProperty(), leftBorder))).play();
            }
            platform.getChildren().add(imageView);
            summon(step+1);
        }));
        timeline.play();
    }
    public void tornado()
    {

    }
    public void transfere()
    {
        robot.Info info;
        robot.Archimage main;
        info = new robot.Info(this.player, opponent.player);
        main = new robot.Archimage();
        if(strong)
        {
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5), event ->
            {
                if(hp.get()>50)summon(0);
            }));
            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.play();
        }
        transfere = new Timeline(new KeyFrame(Duration.millis(10), event ->
        {
            if(opponent.hp.get()<=0&&opponent.frozen.get())transfere.stop();
            double[][] ball=new double[4][2];
            for(int i=0;i<opponent.balls.size();i++)for(int j=0;j<2;j++)
            {
                if(opponent.balls.get(i).isAttacking&&!opponent.balls.get(i).aimed&&opponent.balls.get(i).isVisible())
                {
                    try {
                        ball[i][0] = opponent.balls.get(i).getLayoutX();
                        ball[i][1] = opponent.balls.get(i).getLayoutY();
                    }catch (Exception e)
                    {
                        ball[i][0] = 0x7fffffff;
                        ball[i][1] = 0x7fffffff;
                    }
                }
                else
                {
                    ball[i][0] = 0x7fffffff;
                    ball[i][1] = 0x7fffffff;
                }
            }
            info.init(ball);
            ArrayList<String> strings = main.operate(info, strong);
//            if(!last.equals(strings)&&!strings.isEmpty())
//            {
            for(int i=0;i<strings.size();i++)System.out.print(strings.get(i)+" ");
            System.out.println();
//            }
            MotionController.auto(this, strings, 0);
        }));
        transfere.setCycleCount(Timeline.INDEFINITE);
        transfere.play();
    }
}
