package matchstickMan;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class Init
{
    public static void init(MatchstickMan man, boolean right)
    {
        Timeline timeline = new Timeline();
        if(right)
        {
            KeyValue keyValue = new KeyValue(man.leftForeArm.endXProperty(), 2*man.leftForeArm.getStartX()-man.leftForeArm.getEndX());
            KeyFrame keyFrame = new KeyFrame(Duration.millis(200), keyValue);
            KeyValue keyValue1 = new KeyValue(man.rightForeArm.endYProperty(), 2*man.rightForeArm.getStartY()-man.rightForeArm.getEndY());
            KeyFrame keyFrame1 = new KeyFrame(Duration.millis(200),keyValue1);
            KeyValue keyValue2 = new KeyValue(man.leftBackLeg.endYProperty(), man.leftForeLeg.getEndY()-1.2*MatchstickMan.ratio);
            KeyFrame keyFrame2 = new KeyFrame(Duration.millis(300),keyValue2);
            KeyValue keyValue3 = new KeyValue(man.leftBackLeg.endXProperty(), man.leftForeLeg.getEndX());
            KeyFrame keyFrame3 = new KeyFrame(Duration.millis(300),keyValue3);
            KeyValue keyValue4 = new KeyValue(man.rightBackLeg.endYProperty(), man.rightForeLeg.getEndY()-1.2*MatchstickMan.ratio);
            KeyFrame keyFrame4 = new KeyFrame(Duration.millis(300),keyValue4);
            KeyValue keyValue5 = new KeyValue(man.rightBackLeg.endXProperty(), man.rightForeLeg.getEndX());
            KeyFrame keyFrame5 = new KeyFrame(Duration.millis(300),keyValue5);

//            KeyValue keyValue6 = new KeyValue(man.body.endYProperty(), man.body.getEndY()-0.5*MatchstickMan.ratio);
//            KeyFrame keyFrame6 = new KeyFrame(Duration.millis(300), keyValue6);
            timeline.getKeyFrames().addAll(keyFrame, keyFrame1, keyFrame2, keyFrame3, keyFrame4, keyFrame5);
            timeline.play();
        }
    }
}
