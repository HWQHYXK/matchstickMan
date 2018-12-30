package matchstickMan;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;



public class Stick extends Line implements ChangeListener<Boolean>
{
    private MatchstickMan host;
    public Stick(MatchstickMan man)
    {
        host = man;

        setStrokeWidth(2.5);
        setSmooth(true);
        if(host.facingRight.get())
        {
            right();
        }
        else
        {
            setStartX(host.rightForeArm.getEndX());
            setStartY(host.rightForeArm.getEndY());
            setEndX(host.leftForeArm.getEndX()*2);
            setEndY(host.leftForeArm.getEndY()*2);
        }
    }
    public void left()
    {
        setStartX(host.rightForeArm.getEndX());
        setStartY(host.rightForeArm.getEndY());
        setEndX(host.leftForeArm.getEndX()*2);
        setEndY(host.leftForeArm.getEndY()*2);
    }
    public void right()
    {
//        setStartX(host.leftForeArm.getEndX());
//        setStartY(host.leftForeArm.getEndY());
//        setEndX(host.rightForeArm.getEndX()*2);
//        setEndY(host.rightForeArm.getEndY()*2);
        setStartX(0);
        setStartY(4.5*MatchstickMan.ratio);
        setEndX(4*MatchstickMan.ratio);
        setEndY(-1.25*MatchstickMan.ratio);
    }
    public void show()
    {
        host.getChildren().add(this);
        ImageView magicstick = new ImageView(new Image("/matchStickMan/image/stick.png"));
        host.getChildren().add(magicstick);
    }
    public void disappear()
    {

    }

    @Override
    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue)
    {
        if(oldValue && !newValue)
        {
            left();
        }
        if(!oldValue && newValue)
        {
            right();
        }
    }
//    public
}
