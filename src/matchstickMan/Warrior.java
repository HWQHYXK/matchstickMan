package matchstickMan;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.ArrayList;

public class Warrior extends Robot
{
    private boolean strong;
    public Warrior(boolean facingRight, Color skin, boolean strong)
    {
        super(facingRight, skin);
        this.strong = strong;
    }
    public void transfere()
    {
        stick.setImage(new Image("matchstickMan/image/stick3.png"));
        if(strong)
        {
            double damage = stick.damage;
            hp.addListener((hp,pre,now)->
            {
                stick.damage = damage+(100-(Double)hp.getValue())/33.3;
//                ball0.damage[0] = ball0.damage[1]+(100-(Double)hp.getValue())/20;
//                ball1.damage[0] = ball1.damage[1]+(100-(Double)hp.getValue())/20;
//                ball2.damage[0] = ball2.damage[1]+(100-(Double)hp.getValue())/20;
//                ball3.damage[0] = ball3.damage[1]+(100-(Double)hp.getValue())/20;
            });
        }
        robot.Info info;
        robot.Warrior main;
        info = new robot.Info(this.player, opponent.player);
        main = new robot.Warrior();
        transfere = new Timeline(new KeyFrame(Duration.millis(8.88), event ->
        {
            if(opponent.hp.get()<=0&&opponent.frozen.get()||hp.get()<=0&&frozen.get())
            {
                transfere.stop();
                stop();
            }
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
            ArrayList<String> strings = main.operate(info);
//            if(!last.equals(strings)&&!strings.isEmpty())
//            {
//            for(int i=0;i<strings.size();i++)System.out.print(strings.get(i)+" ");
//            System.out.println();
//            }
            MotionController.auto(this, strings, 0);
        }));
        transfere.setCycleCount(Timeline.INDEFINITE);
        transfere.play();
    }
}
