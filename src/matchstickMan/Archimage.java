package matchstickMan;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.ArrayList;

public class Archimage extends Robot
{
    private boolean strong;
    public Archimage(boolean facingRight, Color skin, boolean strong)
    {
        super(facingRight, skin);
        this.strong = strong;
    }
    public void transfere()
    {
        robot.Info info;
        robot.Archimage main;
        info = new robot.Info(this.player, opponent.player);
        main = new robot.Archimage();
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
