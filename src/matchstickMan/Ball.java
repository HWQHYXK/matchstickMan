package matchstickMan;

import javafx.animation.*;
import javafx.scene.effect.Bloom;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

import javafx.event.ActionEvent;

import java.util.Random;

public class Ball extends Circle implements Skill
{
    public ImageView skinImage;
    public PathTransition pathTransition;
//    public Timeline tt = new Timeline();
//    public Timeline ttt = new Timeline();
    public Timeline timeline,attack;
    public Judge judge;
//    private KeyFrame keyFrame;
//    private double now = 0;
//    private double length;
    private Color skin;
    public double[] damage = new double[]{10, 10};
    public boolean aimed = false;
    public boolean isRecovering = false;
    public boolean isAttacking = false;
    MatchstickMan host;
    Random random = new Random();
    double x,y;
    @Override
    public void changeStatus()
    {

    }

    public void play()
    {
        if(aimed)
        {
            pathTransition.play();
            new Timeline(new KeyFrame(Duration.seconds(5),event ->
            {
                aimed = false;
                setColor(skin);
            })).play();
        }
        else
        {
            setColor(skin);
            if (host.platform.getChildren().contains(this)) host.platform.getChildren().remove(this);
            if(!host.getChildren().contains(this))
            {
                host.getChildren().add(this);
//                host.getChildren().add(skinImage);
            }
            this.setLayoutX(x);
            this.setLayoutY(y);
            pathTransition.play();
        }

    }
    @Override
    public void stop()
    {
        pathTransition.pause();
        host.getChildren().remove(this);
    }

    @Override
    public void setMan(MatchstickMan man)
    {
        this.host = man;
        if(man.skin.equals(Color.INDIANRED)||man.skin.equals(Color.DARKRED))skin = Color.ORANGERED;
        else if(man.skin.equals(Color.MEDIUMPURPLE))skin = Color.PURPLE;
        else skin = Color.DODGERBLUE;
        setEffect(new Bloom(0.1));
    }
    public void init(double x, double y)
    {
//        this.setLayoutX(x+host.getLayoutX()-MatchstickMan.ratio);
//        this.layoutXProperty().bind(host.layoutXProperty().add(x-MatchstickMan.ratio));
//        this.setLayoutY(y+host.getLayoutY());
//        this.layoutYProperty().bind(host.layoutYProperty().add(y));
        this.x = x;
        this.y = y;
//        pathTransition = new Timeline(new KeyFrame(Duration.millis(800+random.nextInt(10)*20),new KeyValue(layoutYProperty(), y+MatchstickMan.ratio)));
//        pathTransition.setCycleCount(Timeline.INDEFINITE);
//        pathTransition.setAutoReverse(true);
//        pathTransition.play();
        Path path = new Path();
//        MoveTo moveTo = new MoveTo(this.getLayoutX(),host.getLayoutY()+y-33*MatchstickMan.ratio);
        pathTransition = new PathTransition();
        MoveTo moveTo = new MoveTo(x,y);
        moveTo.setAbsolute(true);
        path.getElements().add(moveTo);
//        LineTo lineTo = new LineTo(this.getLayoutX(),host.getLayoutY()+y-30*MatchstickMan.ratio);
        LineTo lineTo = new LineTo(x,y+MatchstickMan.ratio);
        path.getElements().add(lineTo);
        pathTransition.setDuration(Duration.millis(400+random.nextInt(10)*20));
        pathTransition.setAutoReverse(true);
        pathTransition.setPath(path);
        pathTransition.setNode(this);
        pathTransition.setCycleCount(PathTransition.INDEFINITE);
    }

    public Ball(MatchstickMan host, double radius)
    {
//        super(host.getLayoutX()+(new Random().nextInt(1)*2-1)*1.5*MatchstickMan.ratio+new Random().nextInt((int)MatchstickMan.ratio), host.getLayoutY(), radius);
        super(radius);
        setMan(host);
//        skinImage = new ImageView("/matchstickMan/image/fireBall.png");
//        skinImage.setScaleX(0.1);
//        skinImage.setScaleY(0.1);
//        skinImage.setVisible(false);
    }
    public void setColor(Color color)
    {
        this.setFill(color);
    }
    public void attack(double length)
    {
        if((host.getChildren().contains(this))&&!aimed&&this.isVisible()&&!isAttacking)
        {
            isAttacking = true;
            if(host.skin.equals(Color.INDIANRED)||host.skin.equals(Color.DARKRED))setColor(Color.RED);
            else setColor(Color.BLUE);
            pathTransition.pause();
            host.getChildren().remove(this);
            setLayoutX(host.getLayoutX() + x);
            setLayoutY(host.getLayoutY() + y);
            if(!host.platform.getChildren().contains(this))
            {
                host.platform.getChildren().add(this);
//                host.platform.getChildren().add(skinImage);
            }
//        attackTransition = new TranslateTransition(Duration.millis(400),this);
//        attackTransition.setByX(length);
//        attackTransition.setCycleCount(2);
//        attackTransition.setAutoReverse(true);
            judge = new Judge(host.opponent);
            KeyValue keyValue = new KeyValue(this.layoutXProperty(), this.getLayoutX() + length);
            KeyFrame keyFrame = new KeyFrame(Duration.millis(400), keyValue);
            timeline = new Timeline(keyFrame);
            timeline.setCycleCount(2);
            timeline.setAutoReverse(true);
            attack = new Timeline(new KeyFrame(Duration.millis(0.1), this::onFinished));
            attack.setCycleCount(Timeline.INDEFINITE);
//            timeline.setOnFinished(this::frame);
            timeline.play();
            timeline.setOnFinished(event ->
            {
                isAttacking = false;
                play();
            });
            attack.play();
        }
//        attackTransition.setOnFinished(event ->
//        {
//            play();
//            attack.stop();
//        });
//        attackTransition.play();
    }
    public void onFinished(ActionEvent e)
    {
        switch (judge.calc(this.getLayoutX(), this.getLayoutY(), this.getRadius(),25,30,50)) {
            case -1:
                host.opponent.statusController.damageHP(0);
                timeline.stop();
                host.platform.getChildren().remove(this);
                if(!host.getChildren().contains(this))host.getChildren().add(this);
//                host.getChildren().add(skinImage);
                new Timeline(new KeyFrame(Duration.millis(1), event ->
                {
                    this.setLayoutX(x);
                    this.setLayoutY(y);
                })).play();
                aimed = true;
                isAttacking = false;
                setColor(Color.GREY);
                play();
                break;
            case 0:
                break;
            case 1:
            case 2:
                if(host.opponent.statusController.damageHP(damage[0])>0)
                {
                    isAttacking = false;
                    timeline.pause();
                    attack.stop();
//                host.balls.remove(this);
                    this.setVisible(false);
                    this.setLayoutY(-100);
                    isRecovering = true;
                    new Timeline(new KeyFrame(Duration.seconds(10), event ->
                    {
                        host.platform.getChildren().remove(this);
                        this.setVisible(true);
                        isRecovering = false;
                        init(random.nextDouble() * 5 * MatchstickMan.ratio - 2.5 * MatchstickMan.ratio, random.nextDouble() * 2.5 * MatchstickMan.ratio - 1.25 * MatchstickMan.ratio);
                        play();
                    })).play();
                }
                else
                {
                    new Timeline(new KeyFrame(Duration.millis(1), event ->
                    {
                        timeline.pause();
                        attack.stop();
                        isAttacking = false;
                        host.platform.getChildren().remove(this);
                        this.setVisible(true);
                        isRecovering = false;
                        this.setLayoutX(x);
                        this.setLayoutY(y);
                        play();
                    })).play();
                }
        }
    }
//    public static void ballsAttack(MatchstickMan man,int i)
//    {
//
//        if (i >= man.balls.size()) return;
//        else if (man.getChildren().contains(man.balls.get(i))) {
//            if (i == 0)
//            {
//                if (man.facingRight.get())
//                    man.balls.get(i).attack(new Random().nextInt(3) * 5 * MatchstickMan.ratio + 30 * MatchstickMan.ratio);
//                else
//                    man.balls.get(i).attack(-new Random().nextInt(3) * 5 * MatchstickMan.ratio - 30 * MatchstickMan.ratio);
//                ballsAttack(man,  1);
//            } else new Timeline(new KeyFrame(Duration.millis(200), event1 ->
//            {
//                try {
//                    if (man.facingRight.get())
//                        man.balls.get(i).attack(new Random().nextInt(3) * 5 * MatchstickMan.ratio + 30 * MatchstickMan.ratio);
//                    else
//                        man.balls.get(i).attack(-new Random().nextInt(3) * 5 * MatchstickMan.ratio - 30 * MatchstickMan.ratio);
//                    ballsAttack(man, i + 1);
//                }catch (Exception e)
//                {
//                    System.out.println("haha");
//                }
//            })).play();
//        }
//    }
    public static void ballsAttack(MatchstickMan man)
    {
        if (man.facingRight.get())
            man.ball0.attack(new Random().nextInt(3) * 5 * MatchstickMan.ratio + 30 * MatchstickMan.ratio);
        else
            man.ball0.attack(-new Random().nextInt(3) * 5 * MatchstickMan.ratio - 30 * MatchstickMan.ratio);
        KeyFrame keyFrame = new KeyFrame(Duration.millis(50), event1 ->
        {
            try {
                if (man.facingRight.get())
                    man.ball1.attack(new Random().nextInt(3) * 5 * MatchstickMan.ratio + 30 * MatchstickMan.ratio);
                else
                    man.ball1.attack(-new Random().nextInt(3) * 5 * MatchstickMan.ratio - 30 * MatchstickMan.ratio);
            }catch (Exception e)
            {

            }
        });
        KeyFrame keyFrame1 = new KeyFrame(Duration.millis(100), event1 ->
        {
            try {
                if (man.facingRight.get())
                    man.ball2.attack(new Random().nextInt(3) * 5 * MatchstickMan.ratio + 30 * MatchstickMan.ratio);
                else
                    man.ball2.attack(-new Random().nextInt(3) * 5 * MatchstickMan.ratio - 30 * MatchstickMan.ratio);
            }catch (Exception e)
            {

            }
        });
        KeyFrame keyFrame2 = new KeyFrame(Duration.millis(150), event1 ->
        {
            try {
                if (man.facingRight.get())
                    man.ball3.attack(new Random().nextInt(3) * 5 * MatchstickMan.ratio + 30 * MatchstickMan.ratio);
                else
                    man.ball3.attack(-new Random().nextInt(3) * 5 * MatchstickMan.ratio - 30 * MatchstickMan.ratio);
            }catch (Exception e)
            {

            }
        });
        Timeline t1 = new Timeline(keyFrame, keyFrame1, keyFrame2);
        t1.play();
        }
    }

//    public void frame(ActionEvent event)
//    {
//        if(now <= length)
//        {
//            System.out.println(now);
//            now+=length/4000;
//            keyFrame.getValues().add(new KeyValue(this.layoutXProperty(), this.getLayoutX()+now));
//        }
//        else
//        {
//            isAttacking = false;
//            play();
//            attack.stop();
//        }
//    }
//}


//    public void attack(double length)
//    {
//        System.out.println("here");
//        pathTransition.pause();
//        host.getChildren().remove(this);
//        setLayoutX(host.getLayoutX()+x);
//        setLayoutY(host.getLayoutY()+y);
//        attackTransition = new PathTransition();
//        Path path = new Path();
//        MoveTo moveTo = new MoveTo(this.getLayoutX(),this.getLayoutY());
//        path.getElements().add(moveTo);
//        LineTo lineTo = new LineTo(this.getLayoutX()+length,this.getLayoutY());
//        path.getElements().add(lineTo);
//        host.platform.getChildren().add(path);
//        attackTransition.setDuration(Duration.millis(100*length/MatchstickMan.ratio));
//        attackTransition.setAutoReverse(true);
//        attackTransition.setCycleCount(2);
//        attackTransition.setPath(path);
//        attackTransition.setNode(this);
//        host.platform.getChildren().add(this);
//        attackTransition.setOnFinished(event ->
//        {
//            play();
//        });
//        attackTransition.play();
//    }


//now = 0;
//            tt = new Timeline(new KeyFrame(Duration.millis(0.1), event ->
//            {
//                if(host.facingRight.get() && now <= this.length || !host.facingRight.get() && now >= this.length)
//                {
//                    KeyValue keyValue = new KeyValue(this.layoutXProperty(), host.getLayoutX()+ now + this.length / 4000);
//                    now += this.length / 4000;
//                    keyFrame = new KeyFrame(Duration.millis(0.1), keyValue);
//                    timeline = new Timeline(keyFrame);
//                    timeline.play();
//                }
//            }));
//            tt.setCycleCount(Timeline.INDEFINITE);
//            ttt = new Timeline(new KeyFrame(Duration.millis(400), event ->
//            {
//                tt.stop();
//                KeyValue keyValue = new KeyValue(layoutXProperty(), host.getLayoutX()+x);
//                keyFrame = new KeyFrame(Duration.millis(400), keyValue);
//                timeline = new Timeline(keyFrame);
//                timeline.setOnFinished(event1 ->
//                {
//                    isAttacking = false;
//                    play();
//                });
//                timeline.play();
//            }));
//            ttt.play();
//            tt.play();