package matchstickMan;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.effect.Glow;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.util.Duration;

import java.util.ArrayList;

public class MatchstickMan extends Group implements ChangeListener<Boolean>
{
    public MatchstickMan opponent;
    /*---------------------------------------------------------------------------------------------*/
    public mangFu.Player player1;
    public archimage.Player player2;
    public StatusController statusController;
    public BooleanProperty frozen = new SimpleBooleanProperty();
    public BooleanProperty hpLocked = new SimpleBooleanProperty();
    public DoubleProperty hp = new SimpleDoubleProperty();
    public ShadowMove shadowMove;
    public Shining shining;
    public MagicStick stick;
    public Shield shield;
    public ArrayList<Ball> balls = new ArrayList<>();
    public Ball ball0;
    public Ball ball1;
    public Ball ball2;
    public Ball ball3;
//    public ArrayList<Ball> totalBalls = new ArrayList<>();
    /*-------------------------------------------------------------------------------------------*/
    public Timeline right;
    public Timeline left;
    public Timeline jump;
    public Timeline down;
    public Timeline up;
    public Timeline stickAttackRight;
    public Timeline stickAttackLeft;
    public int motionStatus = 0;// 0静止 1向右 -1向左
    public BooleanProperty facingRight = new SimpleBooleanProperty();
    public boolean isAttacking = false;
    public boolean isMoving = false;
    public boolean isJumping = false;
    public boolean isShining = false;
    public boolean isCanceled;
    /*-------------------------------------------------------------------------------------------*/
    public static final double ratio = 25;
    public Color skin;
    public Circle core = new Circle(0,1.5*ratio, 1);
    public Circle top = new Circle(0,-ratio,5, Color.TRANSPARENT);
    public Circle head = new Circle(0,0,ratio);
    public Circle eye;
    public Line body = new Line(0,0,0,3.5*ratio);
    public Line leftBackArm = new Line(0,1.5*ratio,-1*ratio, 3*ratio);
    public Line leftForeArm = new Line(-1*ratio,3*ratio,-2*ratio, 4.5*ratio);
    public Line rightBackArm = new Line(0,1.5*ratio,ratio,3*ratio);
    public Line rightForeArm = new Line(ratio,3*ratio,2*ratio,4.5*ratio);
    public Group leftArm = new Group(leftBackArm,leftForeArm);
    public Group rightArm = new Group(rightBackArm, rightForeArm);
    public Line leftBackLeg = new Line(0, 3.5*ratio, -0.75*ratio, 5*ratio);
    public Line leftForeLeg = new Line(-0.75*ratio, 5*ratio, -1.5*ratio, 6.5*ratio);
    public Line rightBackLeg = new Line(0,3.5*ratio,0.75*ratio,5*ratio);
    public Line rightForeLeg = new Line(0.75*ratio,5*ratio,1.5*ratio,6.5*ratio);
    public Group leftLeg = new Group(leftBackLeg,leftForeLeg);
    public Group rightLeg = new Group(rightBackLeg,rightForeLeg);
    public ArrayList<Line> limbs = new ArrayList<>();
    /*-------------------------------------------------------------------------------------------*/
    public double leftBorder = 0;
    public double rightBorder = 500;
    public BorderPane platform;
    public static double field = Platform.field+6.5*ratio;
    public MatchstickMan(boolean facingRight, Color skin)
    {
        this.facingRight.set(facingRight);
        this.skin = skin;
        if(facingRight)eye = new Circle(ratio/2,0,ratio/5, Color.WHITE);
        else eye = new Circle(-ratio/2,0,ratio/5, Color.WHITE);
        leftBackArm.setStrokeWidth(3);
        rightBackArm.setStrokeWidth(3);
        leftForeArm.setStrokeWidth(3);
        rightForeArm.setStrokeWidth(3);
        leftBackLeg.setStrokeWidth(3);
        rightBackLeg.setStrokeWidth(3);
        leftForeLeg.setStrokeWidth(3);
        rightForeLeg.setStrokeWidth(3);
        body.setStrokeWidth(4);

        head.centerXProperty().bind(body.startXProperty());
        head.centerYProperty().bind(body.startYProperty());

        leftForeLeg.startXProperty().bind(leftBackLeg.endXProperty());
        leftForeLeg.startYProperty().bind(leftBackLeg.endYProperty());
        rightForeLeg.startXProperty().bind(rightBackLeg.endXProperty());
        rightForeLeg.startYProperty().bind(rightBackLeg.endYProperty());

        leftForeArm.startXProperty().bind(leftBackArm.endXProperty());
        leftForeArm.startYProperty().bind(leftBackArm.endYProperty());
        rightForeArm.startXProperty().bind(rightBackArm.endXProperty());
        rightForeArm.startYProperty().bind(rightBackArm.endYProperty());


        leftBackLeg.startXProperty().bind(rightBackLeg.startXProperty());
        leftBackLeg.startYProperty().bind(rightBackLeg.startYProperty());
        rightBackLeg.startXProperty().bind(body.endXProperty());
        rightBackLeg.startYProperty().bind(body.endYProperty());

        leftBackArm.startXProperty().bind(rightBackArm.startXProperty());
        leftBackArm.startYProperty().bind(rightBackArm.startYProperty());
        rightBackArm.startXProperty().bind(core.centerXProperty());
        rightBackArm.startYProperty().bind(core.centerYProperty());

        right = new Timeline(new KeyFrame(Duration.ZERO));
        left = new Timeline(new KeyFrame(Duration.ZERO));
        jump = new Timeline();
        down = new Timeline();
        up = new Timeline();

        this.getChildren().addAll(top,core,head,eye,body,leftArm,rightArm,leftLeg,rightLeg);
        limbs.add(this.leftForeArm);
        limbs.add(this.leftBackArm);
        limbs.add(this.body);
        limbs.add(this.rightBackArm);
        limbs.add(this.rightForeArm);
        limbs.add(this.leftForeLeg);
        limbs.add(this.leftBackLeg);
        limbs.add(this.rightBackLeg);
        limbs.add(this.rightForeLeg);
//        this.body.setStroke(Color.YELLOW);

        DeathDetector dd = new DeathDetector(this);
        hp.set(100);
        frozen.set(true);
        hp.addListener(dd::changed);
        frozen.addListener(this::changed);
    }
    public void enable(String skill)
    {
        switch (skill)
        {
            case "Stick":
//                stick = new Stick(this);
//                stick.show();
//                facingRight.addListener(stick::changed);
                stick = new MagicStick(this);
                facingRight.addListener(stick::changed);
            break;
            case "Shield":
                shield = new Shield(this);
                facingRight.addListener(shield::changed);
//                this.eye.centerXProperty().addListener(shield::changeDirection);
            break;
            case "Ball":
                if(facingRight.get())
                {
                    balls.add(ball0 = new Ball(this, ratio / 3));
                    balls.get(0).init(ratio, 0);
                    balls.add(ball1 = new Ball(this, ratio / 3));
                    balls.get(1).init(-ratio, 0);
                    balls.add(ball2 = new Ball(this, ratio / 3));
                    balls.get(2).init(2 * ratio, -ratio);
                    balls.add(ball3 = new Ball(this, ratio / 3));
                    balls.get(3).init(-3 * ratio, 1.5 * ratio);
                }
                else
                {
                    balls.add(ball0 = new Ball(this, ratio / 3));
                    balls.get(0).init(1.5*ratio, 0.5*ratio);
                    balls.add(ball1 = new Ball(this, ratio / 3));
                    balls.get(1).init(-2*ratio, 0);
                    balls.add(ball2 = new Ball(this, ratio / 3));
                    balls.get(2).init(1.33* ratio, -ratio);
                    balls.add(ball3 = new Ball(this, ratio / 3));
                    balls.get(3).init(-2 * ratio, 1.5 * ratio);
                }
//                for(Ball ball:balls)totalBalls.add(ball);
            break;
            case "Shining":
                shining = new Shining();
                shining.setMan(this);
                shining.enabled = true;
            break;
            case "ShadowMove":
                shadowMove = new ShadowMove();
                shadowMove.setMan(this);
                shadowMove.enabled = true;
            break;
        }
    }
    public void init()
    {
        setColor(skin);
        Timeline timeline = new Timeline();
        KeyFrame keyFrame, keyFrame1;
        if(facingRight.get())
        {
            KeyValue keyValue = new KeyValue(this.leftForeArm.endXProperty(), 2 * this.leftForeArm.getStartX() - this.leftForeArm.getEndX());
            keyFrame = new KeyFrame(Duration.millis(200), keyValue);
            KeyValue keyValue1 = new KeyValue(this.rightForeArm.endYProperty(), 2 * this.rightForeArm.getStartY() - this.rightForeArm.getEndY());
            keyFrame1 = new KeyFrame(Duration.millis(200), keyValue1);
        }
        else
        {
            KeyValue keyValue = new KeyValue(this.rightForeArm.endXProperty(), 2 * this.rightForeArm.getStartX() - this.rightForeArm.getEndX());
            keyFrame = new KeyFrame(Duration.millis(200), keyValue);
            KeyValue keyValue1 = new KeyValue(this.leftForeArm.endYProperty(), 2 * this.leftForeArm.getStartY() - this.leftForeArm.getEndY());
            keyFrame1 = new KeyFrame(Duration.millis(200), keyValue1);
        }
        KeyValue keyValue2 = new KeyValue(this.leftBackLeg.endYProperty(), this.leftForeLeg.getEndY()-2.25*MatchstickMan.ratio);
        KeyFrame keyFrame2 = new KeyFrame(Duration.millis(300),keyValue2);
        KeyValue keyValue3 = new KeyValue(this.leftBackLeg.endXProperty(), this.leftForeLeg.getEndX());
        KeyFrame keyFrame3 = new KeyFrame(Duration.millis(300),keyValue3);
        KeyValue keyValue4 = new KeyValue(this.rightBackLeg.endYProperty(), this.rightForeLeg.getEndY()-2.25*MatchstickMan.ratio);
        KeyFrame keyFrame4 = new KeyFrame(Duration.millis(300),keyValue4);
        KeyValue keyValue5 = new KeyValue(this.rightBackLeg.endXProperty(), this.rightForeLeg.getEndX());
        KeyFrame keyFrame5 = new KeyFrame(Duration.millis(300),keyValue5);

//            KeyValue keyValue6 = new KeyValue(this.body.endYProperty(), this.body.getEndY()+0.25*MatchstickMan.ratio);
//            KeyFrame keyFrame6 = new KeyFrame(Duration.millis(300), keyValue6);
        timeline.getKeyFrames().addAll(keyFrame, keyFrame1, keyFrame2, keyFrame3, keyFrame4, keyFrame5);
        timeline.setOnFinished(event -> frozen.set(false));
        timeline.play();
        statusController = new StatusController(this);
        statusController.setFrozen(false);
        statusController.setHPLocked(false);
    }
    public void  right(Duration time)
    {
        isCanceled = false;
        new Timeline(new KeyFrame(time, event ->
        {
           if(!isCanceled)right();
        })).play();
    }
    public  void left(Duration time)
    {
        isCanceled = false;
        new Timeline(new KeyFrame(time, event ->
        {
            if(!isCanceled)left();
        })).play();
    }
    public void right()
    {
        isMoving = true;
        facingRight.set(true);
        left.pause();
        if(motionStatus!=1)changeStatus(1);
        this.leftForeLeg.setEndX(2*this.leftForeLeg.getStartX()-body.getEndX());
        this.leftForeLeg.setEndY(2*this.leftForeLeg.getStartY()-body.getEndY());
        this.rightForeLeg.setEndX(this.rightForeLeg.getStartX()-2.236*ratio);
        this.rightForeLeg.setEndY(this.rightForeLeg.getEndY()-1.5*ratio);
        KeyValue kv1 = new KeyValue(this.layoutXProperty(), rightBorder);
        KeyFrame kf1 = new KeyFrame(Duration.millis((rightBorder-this.getLayoutX())*2), kv1);
        right = new Timeline(kf1);
        right.play();
    }
    public void left()
    {
        isMoving = true;
        facingRight.set(false);
        right.pause();
        if(motionStatus!=-1)changeStatus(-1);
        this.rightForeLeg.setEndX(2*this.rightForeLeg.getStartX()-body.getEndX());
        this.rightForeLeg.setEndY(2*this.rightForeLeg.getStartY()-body.getEndY());
        this.leftForeLeg.setEndX(this.leftForeLeg.getStartX()+2.236*ratio);
        this.leftForeLeg.setEndY(this.leftForeLeg.getEndY()-1.5*ratio);
        KeyValue keyValue1 = new KeyValue(this.layoutXProperty(), leftBorder);
        KeyFrame keyFrame1 = new KeyFrame(Duration.millis((this.getLayoutX()-leftBorder)*2), keyValue1);
        left = new Timeline(keyFrame1);
        left.play();
    }
    public void stop()
    {
        this.legReposition();
        this.right.pause();
        this.left.pause();
//        new Timeline(new KeyFrame(Duration.millis(3), event ->
//        {
            this.isMoving = false;
            this.isCanceled = true;
            this.motionStatus = 0;
//        })).play();

    }
    public void jump(double height)//9*ratio
    {
        jump = new Timeline();
        KeyValue keyValue;
        if(isMoving)keyValue = new KeyValue(this.layoutYProperty(),field-6.5*ratio+ratio);
        else
        {
            keyValue = new KeyValue(this.body.endYProperty(),3.5*ratio+2*ratio);
        }
        KeyFrame keyFrame = new KeyFrame(Duration.millis(50), event ->
        {
            KeyValue keyValue5 = new KeyValue(this.layoutYProperty(), field -6.5*ratio- height);
            KeyFrame keyFrame5 = new KeyFrame(Duration.millis(200), event1 ->
            {
                KeyValue keyValue6 = new KeyValue(this.layoutYProperty(), field-6.5*ratio);
                KeyFrame keyFrame6 = new KeyFrame(Duration.millis(200),
                        event2 ->
                        {
                            isJumping = false;
                        }
                        ,keyValue6);
                jump=new Timeline(keyFrame6);
                jump.play();
            }, keyValue5);
            jump = new Timeline(keyFrame5);
            KeyValue keyValue1;
            KeyFrame keyFrame1;
            if(!isMoving)
            {
                keyValue1 = new KeyValue(this.body.endYProperty(), 3.5*ratio);
                KeyValue keyValue2 = new KeyValue(this.eye.centerYProperty(), 0);
                KeyValue keyValue7 = new KeyValue(core.centerYProperty(), 1.5*ratio);
                KeyValue keyValue8 = new KeyValue(body.startYProperty(),  0);
                keyFrame1 = new KeyFrame(Duration.millis(50),keyValue1, keyValue2, keyValue7, keyValue8);
                jump.getKeyFrames().add(keyFrame1);
                jump.play();
            }
            jump.play();
        }, keyValue);
        jump.getKeyFrames().add(keyFrame);
        if(!isMoving)
        {
//            System.out.println(leftBackLeg.getEndY()/ratio);
//            System.out.println(body.getEndY()/ratio);
//            System.out.println(core.getCenterY()/ratio);
//            System.out.println(body.getStartY()/ratio);
            KeyValue keyValue1 = new KeyValue(core.centerYProperty(), 1.5*ratio+2*ratio);
            KeyValue keyValue2 = new KeyValue(body.startYProperty(), 2*ratio);
            KeyValue keyValue3 = new KeyValue(eye.centerYProperty(), 2*ratio);
            KeyFrame keyFrame1 = new KeyFrame(Duration.millis(50), keyValue1, keyValue2, keyValue3);
            jump.getKeyFrames().add(keyFrame1);
        }
//        if(!isMoving)
//        {
////          KeyValue keyValue1 = new KeyValue(rightForeLeg.startYProperty(),field-2.25*ratio);
////          KeyFrame keyFrame1 = new KeyFrame(Duration.millis(100), keyValue1);
////          KeyValue keyValue2 = new KeyValue(rightForeLeg.endYProperty(), field);
////          KeyFrame keyFrame2 = new KeyFrame(Duration.millis(100), keyValue2);
////          KeyValue keyValue3 = new KeyValue(leftForeLeg.startYProperty(), field-2.25*ratio);
////          KeyFrame keyFrame3 = new KeyFrame(Duration.millis(100), keyValue3);
////          KeyValue keyValue4 = new KeyValue(leftForeLeg.endYProperty(), field);
////          KeyFrame keyFrame4 = new KeyFrame(Duration.millis(100), keyValue4);
////          jump.getKeyFrames().addAll(keyFrame1, keyFrame2, keyFrame3, keyFrame4);
//            KeyValue keyValue1 = new KeyValue(this.layoutXProperty(),30);
//            KeyFrame keyFrame1 = new KeyFrame(Duration.millis(100), keyValue1);
//            jump.getKeyFrames().add(keyFrame1);
//        }
        jump.play();
    }
    public void down()
    {
        KeyValue keyValue = new KeyValue(this.body.endYProperty(),3.5*ratio+1.5*ratio);
        KeyValue keyValue1 = new KeyValue(core.centerYProperty(), 1.5*ratio+1.5*ratio);
        KeyValue keyValue2 = new KeyValue(body.startYProperty(), 0+1.5*ratio);
        KeyValue keyValue3 = new KeyValue(eye.centerYProperty(), 0+1.5*ratio);
        KeyValue keyValue4 = new KeyValue(leftBackArm.endYProperty(), 4*ratio);
        KeyValue keyValue5 = new KeyValue(rightBackArm.endYProperty(), 4*ratio);
        KeyValue keyValue6;
        KeyValue keyValue7;
        if(facingRight.get())
        {
            keyValue6 = new KeyValue(leftForeArm.endYProperty(), 2.5*ratio);
            keyValue7 = new KeyValue(rightForeArm.endYProperty(), 5.5*ratio);
        }
        else
        {
            keyValue6 = new KeyValue(leftForeArm.endYProperty(), 5.5*ratio);
            keyValue7 = new KeyValue(rightForeArm.endYProperty(), 2.5*ratio);
        }
        KeyFrame keyFrame = new KeyFrame(Duration.millis(100),keyValue, keyValue1, keyValue2, keyValue3, keyValue4, keyValue5, keyValue6, keyValue7);
        down = new Timeline(keyFrame);
        down.play();
        shield.showing = true;
    }
    public void up()
    {
        KeyValue keyValue = new KeyValue(this.body.endYProperty(), 3.5*ratio);
        KeyValue keyValue1 = new KeyValue(this.eye.centerYProperty(), 0);
        KeyValue keyValue2 = new KeyValue(core.centerYProperty(), 1.5*ratio);
        KeyValue keyValue3 = new KeyValue(body.startYProperty(), 0);
        KeyValue keyValue4 = new KeyValue(leftBackArm.endYProperty(), 3*ratio);
        KeyValue keyValue5 = new KeyValue(rightBackArm.endYProperty(), 3*ratio);
        KeyValue keyValue6;
        KeyValue keyValue7;
        if(facingRight.get())
        {
            keyValue6 = new KeyValue(leftForeArm.endYProperty(), 4.5*ratio);
            keyValue7 = new KeyValue(rightForeArm.endYProperty(), 1.5*ratio);
        }
        else
        {
            keyValue6 = new KeyValue(leftForeArm.endYProperty(), 1.5*ratio);;
            keyValue7 = new KeyValue(rightForeArm.endYProperty(), 4.5*ratio);
        }
        KeyFrame keyFrame;
        if(shield.showing)keyFrame = new KeyFrame(Duration.millis(100), event ->
        {
            if(hp.get()>=0)this.frozen.set(false);
        },keyValue, keyValue1, keyValue2, keyValue3, keyValue4, keyValue5, keyValue6, keyValue7);
        else keyFrame = new KeyFrame(Duration.millis(100),keyValue, keyValue1, keyValue2, keyValue3, keyValue4, keyValue5, keyValue6, keyValue7);
        up = new Timeline(keyFrame);
        up.play();
    }
    public void attack()
    {
        if(facingRight.get())
        {
            stick.setEffect(new Glow(0.1));
            isAttacking = true;
            KeyFrame keyFrame = new KeyFrame(Duration.millis(200),event ->
                    stick.setEffect(null)
                    ,new KeyValue(stick.rotateProperty(), 60),
                    new KeyValue(stick.yProperty(), MagicStick.Y+3*ratio),
                    new KeyValue(rightForeArm.endYProperty(), 3*ratio));
            stickAttackRight = new Timeline(keyFrame);
            stickAttackRight.setAutoReverse(true);
            stickAttackRight.setCycleCount(2);
            stickAttackRight.setOnFinished(event ->
            {
                isAttacking = false;
            });
            stickAttackRight.play();
        }
        else
        {
            stick.setEffect(new Glow((0.1)));
            isAttacking = true;
            KeyFrame keyFrame = new KeyFrame(Duration.millis(200),event ->
                    stick.setEffect(null)
                    ,new KeyValue(stick.rotateProperty(), -60),
                    new KeyValue(stick.yProperty(), MagicStick.Y+3*ratio),
                    new KeyValue(leftForeArm.endYProperty(), 3*ratio));
            stickAttackLeft = new Timeline(keyFrame);
            stickAttackLeft.setAutoReverse(true);
            stickAttackLeft.setCycleCount(2);
            stickAttackLeft.setOnFinished(event ->
            {
                isAttacking = false;
            });
            stickAttackLeft.play();
        }
    }
    public void changeStatus(int toStatus)
    {
        if(motionStatus != toStatus)
        {
            if(toStatus == 1)
            {
                legReposition();
                Timeline timeline = new Timeline();
                KeyValue keyValue = new KeyValue(this.leftForeArm.endXProperty(), 0);
                KeyValue keyValue3 = new KeyValue(this.leftForeArm.endYProperty(),4.5*ratio);
                KeyFrame keyFrame = new KeyFrame(Duration.millis(100), keyValue, keyValue3);
                KeyValue keyValue2 = new KeyValue(this.eye.centerXProperty(), ratio/2);
                KeyFrame keyFrame2 = new KeyFrame(Duration.millis(50),keyValue2);
                KeyValue keyValue1 = new KeyValue(this.rightForeArm.endYProperty(), 1.5*ratio);
                KeyValue keyValue4 = new KeyValue(this.rightForeArm.endXProperty(),2*ratio);
                KeyFrame keyFrame1 = new KeyFrame(Duration.millis(100),keyValue1, keyValue4);
                timeline.getKeyFrames().addAll(keyFrame, keyFrame1, keyFrame2);
                timeline.play();
            }
            else if(toStatus == -1)
            {
                legReposition();
                Timeline timeline = new Timeline();
                KeyValue keyValue = new KeyValue(this.rightForeArm.endXProperty(), 0);
                KeyValue keyValue3 = new KeyValue(this.rightForeArm.endYProperty(), 4.5*ratio);
                KeyFrame keyFrame = new KeyFrame(Duration.millis(100), keyValue, keyValue3);
                KeyValue keyValue1 = new KeyValue(this.leftForeArm.endYProperty(), 1.5*ratio);
                KeyValue keyValue4 = new KeyValue(this.leftForeArm.endXProperty(), -2*ratio);
                KeyFrame keyFrame1 = new KeyFrame(Duration.millis(100),keyValue1, keyValue4);
                KeyValue keyValue2 = new KeyValue(this.eye.centerXProperty(), -ratio/2);
                KeyFrame keyFrame2 = new KeyFrame(Duration.millis(50),keyValue2);
                timeline.getKeyFrames().addAll(keyFrame, keyFrame1, keyFrame2);
                timeline.play();
            }
            else if(toStatus == 0)
            {
                legReposition();
                Timeline timeline = new Timeline();
                KeyValue keyValue = new KeyValue(this.leftForeArm.endXProperty(), -2*ratio);
                KeyValue keyValue3 = new KeyValue(this.leftForeArm.endYProperty(),4.5*ratio);
                KeyFrame keyFrame = new KeyFrame(Duration.millis(100), keyValue, keyValue3);
//                KeyValue keyValue2 = new KeyValue(this.eye.centerXProperty(), ratio/2);
//                KeyFrame keyFrame2 = new KeyFrame(Duration.millis(50),keyValue2);
                KeyValue keyValue1 = new KeyValue(this.rightForeArm.endYProperty(), 4.5*ratio);
                KeyValue keyValue4 = new KeyValue(this.rightForeArm.endXProperty(),2*ratio);
                KeyFrame keyFrame1 = new KeyFrame(Duration.millis(100),keyValue1, keyValue4);
                timeline.getKeyFrames().addAll(keyFrame, keyFrame1);
                timeline.play();
            }
            motionStatus = toStatus;
        }
    }
    public void legReposition()
    {
        this.leftForeLeg.setEndX(-1.5*ratio);
        this.leftForeLeg.setEndY(6.5*ratio);
        this.rightForeLeg.setEndX(1.5*ratio);
        this.rightForeLeg.setEndY(6.5*ratio);
    }
    public void setColor(Color color)
    {
        for(Line limb:limbs)
        {
            limb.setStroke(color);
        }
        head.setFill(color);
        core.setFill(color);
    }
    public void setOpponent(MatchstickMan opponent)
    {
        this.opponent = opponent;
    }
    public void setPlatform(BorderPane bp)
    {
        platform = bp;
    }
    public void toPlayer(boolean mang)
    {
        if(mang)
        {
            player1 = new mangFu.Player();
            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(0.1), event ->
            {
                int count = balls.size();
                for(Ball ball:balls)
                {
                    if(ball.aimed||!ball.isVisible())count--;
                }
                player1.init(getLayoutX(), getLayoutY(), hp.get(), count, hpLocked.get(), facingRight.get(), this.getChildren().contains(shield));
//            else mangFu.init(getLayoutX(), getLayoutY(), hp.get(), balls.size(), hpLocked.get(), facingRight.get(), this.getChildren().contains(shield));
            }));
            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.play();
        }
        else
        {
            player2 = new archimage.Player();
            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(0.1), event ->
            {
//            boolean flag = false;
                int count = balls.size();
                for(Ball ball:balls)
                {
                    if(ball.aimed||!ball.isVisible())count--;
                }
                player2.init(getLayoutX(), getLayoutY(), hp.get(), count, hpLocked.get(), facingRight.get(), this.getChildren().contains(shield), stick.isRotating, stick.frozen);
//            else archimage.init(getLayoutX(), getLayoutY(), hp.get(), balls.size(), hpLocked.get(), facingRight.get(), this.getChildren().contains(shield));
            }));
            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.play();
        }

    }
    public void clearStatus() {
//        System.out.println("modifying...");
//        core = new Circle(0,1.5*ratio, 1);
//        head = new Circle(0,0,ratio);
//        coreX = core.centerXProperty();
//        coreY = core.centerYProperty();
//        body = new Line(0,0,0,3.5*ratio);
//        leftBackArm = new Line(0,1.5*ratio,-1*ratio, 3*ratio);
//        leftForeArm = new Line(-1*ratio,3*ratio,-2*ratio, 4.5*ratio);
//        rightBackArm = new Line(0,1.5*ratio,ratio,3*ratio);
//        rightForeArm = new Line(ratio,3*ratio,2*ratio,4.5*ratio);
//        leftArm = new Group(leftBackArm,leftForeArm);
//        rightArm = new Group(rightBackArm, rightForeArm);
//        leftBackLeg = new Line(0, 3.5*ratio, -0.75*ratio, 5*ratio);
//        leftForeLeg = new Line(-0.75*ratio, 5*ratio, -1.5*ratio, 6.5*ratio);
//        rightBackLeg = new Line(0,3.5*ratio,0.75*ratio,5*ratio);
//        rightForeLeg = new Line(0.75*ratio,5*ratio,1.5*ratio,6.5*ratio);
//        leftLeg = new Group(leftBackLeg,leftForeLeg);
//        rightLeg = new Group(rightBackLeg,rightForeLeg);
    }
    public void upp(boolean pressed)
    {
        if(pressed)
        {
            if(!this.frozen.get() && !this.isJumping)
            {
                this.isJumping = true;
                this.jump(15 * MatchstickMan.ratio);
            }
        }
        if(!pressed)
        {

        }
    }
    public void downn(boolean pressed)
    {
        if(pressed)
        {
            if(!this.frozen.get() && !this.isJumping)
            {
                if(this.facingRight.get())this.shield.right();
                else this.shield.left();
                if(!this.shield.showing)this.statusController.setFrozen(true);
                this.shield.show();
                this.down();
                this.stick.up();
            }
        }
        if(!pressed)
        {
            this.up();
            this.shield.disappear();
            this.stick.down();
        }
    }
    public void leftt(boolean pressed)
    {
        if(pressed)
        {
            if(!this.frozen.get() && !this.isMoving)
            {
                if (this.isJumping) this.left(Duration.millis(150));
                else this.left();
            }
        }
        if(!pressed)
        {
            this.stop();
        }
    }
    public void rightt(boolean pressed)
    {
        if(pressed)
        {
            if(!this.frozen.get() && !this.isMoving)
            {
                if(this.isJumping) this.right(Duration.millis(150));
                else this.right();
            }
        }
        if(!pressed)
        {
            this.stop();
        }
    }
    public void switchh(boolean pressed)
    {
        if(pressed)
        {
            if(!this.frozen.get())
            {
                boolean flag = true;
                for(Ball ball:this.balls)
                {
                    if(ball.isAttacking || ball.isRecovering)flag = false;
                }
                for(Ball ball:this.balls)
                {
                    if(flag)
                    {
                        if (!this.getChildren().contains(ball) && !this.platform.getChildren().contains(ball))
                            ball.play();
                        else ball.stop();
                    }
                }
            }
        }
    }
    public void attackk(boolean pressed)
    {
        if(pressed&&opponent!=null)
        {
            if(!this.frozen.get()&&!this.stick.frozen)
            {
                Ball.ballsAttack(this);
                if(!this.isAttacking)this.attack();
            }
        }
    }
    public void movee(boolean pressed)
    {
        if(pressed)
        {
            if(!this.frozen.get() && !this.isJumping)
            {
                this.shadowMove.play();
            }
        }
    }
    public void dropp(boolean pressed)
    {
        if(pressed&&opponent!=null)
        {
            if(!this.frozen.get())
            {
                this.stick.fly();
            }
        }
        if(!pressed)
        {

        }
    }
    public void rotatee(boolean pressed)
    {
        if(pressed)
        {
            if(opponent!=null)
                if(!frozen.get()&&stick.isCanceled)
                {
                    this.stick.isCanceled = false;
                    this.stick.rotate();
                }
        }
        else
        {
//            new Timeline(new KeyFrame(Duration.millis(0.9), event ->
//            {
                this.stick.isCanceled = true;
//            })).play();
        }
    }
    @Override
    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) 
    {
       stop();
    }
}

//  ,event1 ->
//          {
//          KeyValue keyValue6 = new KeyValue(rightForeLeg.startYProperty(),rightForeLeg.getStartX()-2*ratio);
//          KeyFrame keyFrame6 = new KeyFrame(Duration.millis(100), keyValue1);
//          KeyValue keyValue2 = new KeyValue(rightForeLeg.endYProperty(), rightForeLeg.getEndY()-2*ratio);
//          KeyFrame keyFrame2 = new KeyFrame(Duration.millis(100), keyValue2);
//          KeyValue keyValue3 = new KeyValue(leftForeLeg.startYProperty(), leftForeLeg.getStartY()-2*ratio);
//          KeyFrame keyFrame3 = new KeyFrame(Duration.millis(100), keyValue3);
//          KeyValue keyValue4 = new KeyValue(leftForeLeg.endYProperty(), leftForeLeg.getEndY()-2*ratio);
//          KeyFrame keyFrame4 = new KeyFrame(Duration.millis(100), keyValue4);
//          jump.getKeyFrames().addAll(keyFrame2, keyFrame3, keyFrame4, keyFrame6);
//          KeyValue keyValue7 = new KeyValue(core.centerYProperty(), core.getCenterY()-2*ratio);
//          KeyValue keyValue8 = new KeyValue(body.startYProperty(), body.getStartY()-2*ratio);
//          KeyFrame keyFrame7 = new KeyFrame(Duration.millis(100), keyValue7, keyValue8);
//          jump.getKeyFrames().add(keyFrame7);
//          jump.play();
//          }