package matchstickMan;


import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class DeathDetector implements ChangeListener<Number>
{
    public MatchstickMan man;
    @Override
    public void changed(ObservableValue<? extends Number> hp, Number oldHP, Number newHP)
    {
        if((double)oldHP >0 && (double)newHP <= 0)
        {
            man.stop();
            Image image = new Image("/matchStickMan/image/blood3.gif");
            ImageView imageView = new ImageView(image);
            imageView.setScaleX(1.8);
            imageView.setScaleY(1.8);
            imageView.setLayoutX(man.getLayoutX()-11.25*MatchstickMan.ratio);
            imageView.setLayoutY(MatchstickMan.field-9*MatchstickMan.ratio);
            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(500),event ->
            {
                man.platform.getChildren().add(0,imageView);
            }),new KeyFrame(Duration.millis(2500), event ->
            {
//                imageView.setVisible(false);
            }));
//            KeyFrame keyFrame = new KeyFrame(Duration.millis(500));
//            for(Line limb:man.limbs)
//            {
//                try
//                {
//                    limb.startXProperty().unbind();
//                    limb.startYProperty().unbind();
//                    limb.endXProperty().unbind();
//                    limb.endYProperty().unbind();
//                    limb.setStartX(limb.getStartX() + man.getLayoutX());
//                    limb.setStartY(limb.getStartY() + man.getLayoutY());
//                    limb.setEndX(limb.getEndX() + man.getLayoutX());
//                    limb.setEndY(limb.getEndX() + man.getLayoutY());
//                }finally
//                {
//                    man.getChildren().remove(limb);
//                    man.platform.getChildren().add(limb);
//                    keyFrame.getValues().add(new KeyValue(limb.startYProperty(), MatchstickMan.field));
//                    keyFrame.getValues().add(new KeyValue(limb.endYProperty(), MatchstickMan.field));
//                }
//            }
//            timeline.getKeyFrames().add(keyFrame);
            timeline.play();
            man.frozen.setValue(true);
        }
        if((double)newHP <= 0)man.frozen.setValue(true);
    }
    public DeathDetector(MatchstickMan man)
    {
        this.man = man;
    }
}
