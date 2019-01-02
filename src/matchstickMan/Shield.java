package matchstickMan;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.effect.Bloom;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;



public class Shield extends Arc implements ChangeListener<Boolean>
{
    public MatchstickMan host;
    public boolean right;
    public boolean showing = false;
    public DoubleProperty hp = new SimpleDoubleProperty();
    public  Shield(MatchstickMan man)
    {
        super(0,2.75*MatchstickMan.ratio,4.5*MatchstickMan.ratio,4.5*MatchstickMan.ratio,90,-146.44269);
        host = man;
        System.out.println(180-Math.acos(0.8333333333333333333333333333333333333333)/Math.PI*180);
        this.setEffect(new Bloom(0.1));
        this.setStrokeWidth(4);
        if(man.skin.equals(Color.INDIANRED)||man.skin.equals(Color.DARKRED))this.setStroke(Color.ORANGE);
        else this.setStroke(Color.POWDERBLUE);
        this.setFill(Color.TRANSPARENT);
        this.setType(ArcType.OPEN);
    }
//    public void changeDirection(ObservableValue<? extends Number> value, Number oldValue, Number newValue)
//    {
//        this.setLength(-this.getLength());
//    }
    public void show()
    {
        if(!host.getChildren().contains(this))
        {
            showing = true;
            host.getChildren().add(this);
        }
    }
    public void disappear()
    {
        if(host.getChildren().contains(this))
        {
            showing = false;
            host.getChildren().remove(this);
        }
    }
    public void left()
    {
        this.setLength(146.44269);
        right = false;
    }
    public void right()
    {
        this.setLength(-146.44269);
        right = true;
    }

    @Override
    public void changed(ObservableValue<? extends Boolean> facingRight, Boolean oldValue, Boolean newValue)
    {
        if(oldValue && !newValue)
        {
            right = false;
            left();
        }
        if(!oldValue && newValue)
        {
            right = true;
            right();
        }
    }
}
