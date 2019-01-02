package matchstickMan;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.value.ObservableValue;
import javafx.scene.effect.Bloom;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class MagicStick extends ImageView
{
    public double damage = 1;
    public boolean isRotating = false;
    public boolean isCanceled = true;
    public static double X=-40*MatchstickMan.ratio,Y=-22.5*MatchstickMan.ratio;
    public MatchstickMan host;
    public boolean frozen;
    public Timeline down = new Timeline();
    public Timeline up = new Timeline();
    public Timeline rotate;
    private Bloom bloom = new Bloom(0.1);
    public MagicStick(MatchstickMan man)
    {
        super();
        setVisible(false);
        if(man.skin.equals(Color.INDIANRED)||man.skin.equals(Color.DARKRED))setImage(new Image("matchstickMan/image/stick1.png"));
        else setImage(new Image("matchstickMan/image/stick2.png"));
        this.setScaleX(0.3);
        this.setScaleY(0.3);
//        setRotate(15);
//        this.setX(-16.75*MatchstickMan.ratio);//-14.5
        this.setY(Y);
        if(man.facingRight.get())
        {
            right();
        }
        else
        {
            left();
        }
        host = man;
        man.getChildren().add(this);
    }
    public void right()
    {
        if(!frozen)
        {
            setX(X+2.5*MatchstickMan.ratio);
            KeyFrame keyFrame = new KeyFrame(Duration.millis(61.8), new KeyValue(this.rotateProperty(), 15));
            new Timeline(keyFrame).play();
        }
    }
    public void left()
    {
        if(!frozen)
        {
            setX(X-2.5*MatchstickMan.ratio);
            KeyFrame keyFrame = new KeyFrame(Duration.millis(61.8), new KeyValue(this.rotateProperty(), -15));
            new Timeline(keyFrame).play();
        }
    }
    public void up()
    {
        if(!frozen)
        {
            if(isRotating)
            {
                stop();
            }
            setEffect(bloom);
            KeyFrame keyFrame = new KeyFrame(Duration.millis(250), new KeyValue(this.rotateProperty(), 0),
                    new KeyValue(this.xProperty(), X),
                    new KeyValue(this.yProperty(), Y-10*MatchstickMan.ratio));
//        up.stop();
            up = new Timeline(keyFrame);
            up.play();
        }
    }
    public void down()
    {
        if(!frozen)
        {
            this.setEffect(null);
            int i;
            if (host.facingRight.get()) i = 1;
            else i = -1;
            setRotate(i*15);
            setX(X+i*2.5*MatchstickMan.ratio);
            setY(Y);
            up.stop();
//            KeyFrame keyFrame = new KeyFrame(Duration.millis(250), new KeyValue(this.rotateProperty(), i * 15),
//                    new KeyValue(this.xProperty(), (-16.75 + i * 2.25) * MatchstickMan.ratio),
//                    new KeyValue(this.yProperty(), -25 * MatchstickMan.ratio));
//            down = new Timeline(keyFrame);
//            down.play();
        }
    }
    public void fly()
    {
        if(!frozen)
        {
            frozen = true;
//        host.statusController.setFrozen(true);
            setEffect(bloom);
            zoom();
            KeyFrame keyFrame = new KeyFrame(Duration.millis(250), event ->
            {
                host.getChildren().remove(this);
                host.platform.getChildren().add(this);
                setX(host.getLayoutX()+getX() + host.opponent.getLayoutX() - host.getLayoutX());
                drop(host.opponent.getLayoutX());
            }
                    , new KeyValue(this.rotateProperty(), 0),
                    new KeyValue(this.xProperty(), X),
                    new KeyValue(this.yProperty(), -250 * MatchstickMan.ratio));
            up = new Timeline(keyFrame);
            up.setDelay(Duration.millis(250));
            up.play();
        }
    }
    public void drop(double position)
    {
        setEffect(null);
        KeyFrame keyFrame = new KeyFrame(Duration.millis(500),event ->
        {
            if(Math.abs(host.opponent.getLayoutX()-position)<=2*MatchstickMan.ratio)
            {
                host.opponent.statusController.damageHP(15);
                ImageView imageView;
                if(host.opponent.skin.equals(Color.INDIANRED)|| host.opponent.skin.equals(Color.DARKRED))imageView = new ImageView("/matchstickMan/image/red.gif");
                else imageView = new ImageView("/matchstickMan/image/blue.gif");
                imageView.setScaleX(1.5);
                imageView.setScaleY(1.5);
                imageView.setLayoutX(-6*MatchstickMan.ratio);
                imageView.setLayoutY(-MatchstickMan.ratio);
                host.opponent.getChildren().add(0, imageView);
                new Timeline(new KeyFrame(Duration.millis(250), event1 ->
                    host.opponent.getChildren().remove(imageView)
                )).play();
            }
            new Timeline(new KeyFrame(Duration.millis(300), event1 ->
            {
                frozen = false;
                host.platform.getChildren().remove(this);
                host.getChildren().add(this);
                this.setY(Y);
                shrink();
                if(host.facingRight.get())
                {
                    right();
                }
                else
                {
                    left();
                }
            })).play();
        }, new KeyValue(this.yProperty(),host.getLayoutY()+Y-4*MatchstickMan.ratio));
        down = new Timeline(keyFrame);
        down.play();

    }
    public void rotate()
    {
        if(!isRotating)
        {
            ImageView stickEffect;
            if(host.skin.equals(Color.INDIANRED)||host.skin.equals(Color.DARKRED))stickEffect = new ImageView("matchstickMan/image/stickEffect2.gif");
            else stickEffect = new ImageView("matchstickMan/image/stickEffect1.gif");
            stickEffect.setScaleX(2.2);
            stickEffect.setScaleY(2.2);
            new Timeline(new KeyFrame(Duration.seconds(3), event ->
            {
                host.getChildren().remove(stickEffect);
            })).play();
            isRotating = true;
            rotate = new Timeline(new KeyFrame(Duration.millis(200), event ->
            {
                int i;
                if (host.facingRight.get())
                {
                    stickEffect.layoutXProperty().bind(layoutXProperty().add(-30));
                    stickEffect.layoutYProperty().bind(layoutYProperty().add(-110));
                    if(!host.getChildren().contains(stickEffect))host.getChildren().add(stickEffect);
                    i = 1;
                }
                else
                {
                    stickEffect.layoutXProperty().bind(layoutXProperty().add(-180));
                    stickEffect.layoutYProperty().bind(layoutYProperty().add(-110));
                    if(!host.getChildren().contains(stickEffect))host.getChildren().add(stickEffect);
                    i = -1;
                }
                //System.out.println(host.opponent.getLayoutX()+i*2*MatchstickMan.ratio-host.getLayoutX());
                if (Math.abs(host.opponent.getLayoutX() - i * 2.5 * MatchstickMan.ratio - host.getLayoutX()) < 6 * MatchstickMan.ratio)
                {
                    if(host.opponent.shield.showing)
                    {
                        if(host.opponent.shield.right)
                        {
                            if(host.getLayoutX()<host.opponent.getLayoutX())
                            {
                                host.opponent.statusController.damageHP(damage);
//                                if (host.opponent.statusController.damageHP(damage)>0 && host instanceof Warrior) host.statusController.increaseHP(damage * 0.5);
                            }
                            else
                            {
//                                host.shadowMove.timeline.pause();
//                                host.shadowMove.timeline2.pause();
                                stop();
//                                host.shadowMove.stop();
//                                host.setEffect(null);
//                                host.statusController.setFrozen(false);
//                                host.statusController.setHPLocked(false);
                                host.setLayoutX(host.getLayoutX()+5*MatchstickMan.ratio);
                                host.stop();
                            }
                        }
                        else
                        {
                            if(host.getLayoutX()>host.opponent.getLayoutX())
                            {
                                host.opponent.statusController.damageHP(damage);
//                                if (host.opponent.statusController.damageHP(damage)>0 && host instanceof Warrior) host.statusController.increaseHP(damage * 0.5);
                            }
                            else
                            {
//                                host.shadowMove.timeline.pause();
//                                host.shadowMove.timeline2.pause();
                                stop();
//                                host.shadowMove.stop();
//                                host.setEffect(null);
//                                host.statusController.setFrozen(false);
//                                host.statusController.setHPLocked(false);
                                host.setLayoutX(host.getLayoutX()-5*MatchstickMan.ratio);
                                host.stop();
                            }
                        }
                    }
                    else
                    {
                        host.opponent.statusController.damageHP(damage);
//                        if (host.opponent.statusController.damageHP(damage)>0 && host instanceof Warrior) host.statusController.increaseHP(damage * 0.5);
                    }
                }
            }
            , new KeyValue(rotateProperty(), 360)
            ));
            rotate.setCycleCount(10);
            rotate.play();
            rotate.setOnFinished(event ->
            {
                isRotating = false;
                host.getChildren().remove(stickEffect);
                this.setY(Y);
                if (host.facingRight.get())
                {
                    right();
                } else
                {
                    left();
                }
            });
        }
    }
    public void stop()
    {
        rotate.stop();
        isRotating = false;
        this.setY(Y);
        if (host.facingRight.get())
        {
            right();
        } else
        {
            left();
        }
    }
    public void zoom()
    {
        new Timeline(new KeyFrame(Duration.millis(250), event ->
        {
        }, new KeyValue(scaleXProperty(), 0.5), new KeyValue(scaleYProperty(), 0.5))).play();
    }
    public void shrink()
    {
        new Timeline(new KeyFrame(Duration.millis(250), event ->
        {
        }, new KeyValue(scaleXProperty(), 0.3), new KeyValue(scaleYProperty(), 0.3))).play();
    }
    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue)
    {
        if(oldValue && !newValue)
        {
            if(host.stickAttackRight != null)
            {
                host.stickAttackRight.stop();
                host.isAttacking = false;
                setY(Y);
            }
            left();
        }
        if(!oldValue && newValue)
        {
            if(host.stickAttackLeft != null)
            {
                host.stickAttackLeft.stop();
                host.isAttacking = false;
                setY(Y);
            }
            right();
        }
    }
}
