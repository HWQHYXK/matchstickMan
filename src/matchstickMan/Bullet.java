package matchstickMan;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class Bullet extends Circle
{
    private Timeline timeline;
    private int[] damage = new int[]{10, 20};
    public Bullet(double width, double height)
    {
        super(0,0,3);
        KeyValue keyValue = new KeyValue(this.centerYProperty(), width);
        KeyValue keyValue1 = new KeyValue(this.centerXProperty(), height);
        KeyFrame keyFrame = new KeyFrame(Duration.millis(5000), keyValue, keyValue1);

        timeline = new Timeline(keyFrame);
        timeline.setCycleCount(Timeline.INDEFINITE);
    }
    public void release(MatchstickMan man)
    {
        timeline.play();
        Judge judge = new Judge(man);
        Timeline t = new Timeline(new KeyFrame(Duration.millis(0.1),event ->
        {
//            if(man.head.getLayoutX()-MatchstickMan.ratio <= this.getCenterX() && this.getCenterX() <= man.head.getLayoutX()+MatchstickMan.ratio && man.head.getLayoutY()-MatchstickMan.ratio<=this.getCenterY() && this.getCenterY()<= man.head.getLayoutY()+MatchstickMan.ratio)
//            {
//                System.out.println("");
//                timeline.stop();
//            }
            switch (judge.calc(this.getCenterX(), this.getCenterY(), this.getRadius(),10,10,50))
            {
                case -1:
                    System.out.println("shield");
                    man.statusController.damageHP(0);
                    timeline.pause();
                    timeline.playFrom(Duration.millis(20));
                    break;
                case 0:
                    break;
                case 1:
                    System.out.println("胳膊");
                    man.statusController.damageHP(damage[0]);
                    timeline.pause();
                    timeline.playFrom(Duration.millis(20));
                    break;
                case 2:
                    System.out.println("头");
                    man.statusController.damageHP(damage[1]);
                    timeline.pause();
                    timeline.playFrom(Duration.millis(20));
            }

        }));
        t.setCycleCount(Timeline.INDEFINITE);
        t.play();
//        while (timeline.getCurrentRate() != 0.0)
//        {
//            if(man.getLayoutX()-20 <= this.getCenterX() && this.getCenterX() <= man.getLayoutX()+20 && man.getLayoutY()-200<=this.getCenterY() && this.getCenterY()<= man.getLayoutY()+20)
//            System.out.println("attack done!");
//        }
    }
}
