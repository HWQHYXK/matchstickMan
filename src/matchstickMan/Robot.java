package matchstickMan;

import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Robot extends MatchstickMan implements ChangeListener<Boolean>
{
    public BooleanProperty upp = new SimpleBooleanProperty(),downn = new SimpleBooleanProperty(),leftt= new SimpleBooleanProperty(),rightt = new SimpleBooleanProperty(),
            switchh = new SimpleBooleanProperty(),attackk = new SimpleBooleanProperty(),movee= new SimpleBooleanProperty(),dropp = new SimpleBooleanProperty(), rotatee = new SimpleBooleanProperty(),
            shinningg= new SimpleBooleanProperty();
    protected Timeline transfere;
    ArrayList<BooleanProperty> dict = new ArrayList<>();
    public Robot(boolean facingRight, Color skin)
    {
        super(facingRight, skin);
        upp .set(false);
        downn .set(false);
        leftt .set(false);
        rightt.set(false);
        switchh .set(false);
        attackk .set(false);
        movee .set(false);
        shinningg .set(false);
        dropp.set(false);
        rotatee.set(false);


        dict.add(upp);
        dict.add(downn);
        dict.add(leftt);
        dict.add(rightt);
        dict.add(switchh);
        dict.add(attackk);
        dict.add(movee);
        dict.add(shinningg);
        dict.add(rotatee);


        upp.addListener(this::uppp);
        downn.addListener(this::downnn);
        leftt.addListener(this::lefttt);
        rightt.addListener(this::righttt);
        switchh.addListener(this::switchhh);
        attackk.addListener(this::attackkk);
        movee.addListener(this::moveee);
        dropp.addListener(this::droppp);
        rotatee.addListener(this::rotateee);
    }
    public void uppp(ObservableValue<? extends Boolean> up, Boolean oldpressed, Boolean newpressed)
    {
        if(!newpressed)this.upp(newpressed);
//        if(newpressed)
//        {
//            if(!this.frozen.get() && !this.isJumping)
//            {
//                this.isJumping = true;
//                this.jump(15 * MatchstickMan.ratio);
//            }
//        }
//        if(!newpressed)
//        {
//
//        }
    }
    public void downnn(ObservableValue<? extends Boolean> down, Boolean oldpressed, Boolean newpressed)
    {
        if(!newpressed)this.downn(newpressed);
//        if(newpressed)
//        {
////            System.out.println(" jkd here");
//            if(!this.frozen.get() && !this.isJumping)
//            {
//                if(this.facingRight.get())this.shield.right();
//                else this.shield.left();
//                if(!this.shield.showing)this.statusController.setFrozen(true);
//                this.shield.show();
//                this.down();
//                this.stick.up();
//            }
//        }
//        if(!newpressed)
//        {
//            this.up();
//            this.shield.disappear();
//            this.stick.down();
//        }
    }
    public void lefttt(ObservableValue<? extends Boolean> left, Boolean oldpressed, Boolean newpressed)
    {
        if(!newpressed)this.leftt(newpressed);
//        if(newpressed)
//        {
////            System.out.println("×ó");
//            if(!this.frozen.get() && !this.isMoving)
//            {
////                System.out.println("here");
//                if (this.isJumping) this.left(Duration.millis(150));
//                else this.left();
//            }
//        }
//        if(!newpressed)
//        {
//            this.stop();
//        }
    }
    public void righttt(ObservableValue<? extends Boolean> right, Boolean oldpressed, Boolean newpressed)
    {
        if(!newpressed)this.rightt(newpressed);
//        if(newpressed)
//        {
//            if(!this.frozen.get() && !this.isMoving)
//            {
//                if(this.isJumping) this.right(Duration.millis(150));
//                else this.right();
//            }
//        }
//        if(!newpressed)
//        {
//            this.stop();
//        }
    }
    public void switchhh(ObservableValue<? extends Boolean> switchh, Boolean oldpressed, Boolean newpressed)
    {
        if(!newpressed)this.switchh(newpressed);
//        if(newpressed)
//        {
//            if(!this.frozen.get())
//            {
//                boolean flag = true;
//                for(Ball ball:this.balls)
//                {
//                    if(ball.isAttacking || ball.isRecovering)flag = false;
//                }
//                for(Ball ball:this.balls)
//                {
//                    if(flag)
//                    {
//                        if (!this.getChildren().contains(ball) && !this.platform.getChildren().contains(ball))
//                            ball.play();
//                        else ball.stop();
//                    }
//                }
//            }
//        }
//        if(!newpressed)
//        {
//
//        }
    }
    public void attackkk(ObservableValue<? extends Boolean> attack, Boolean oldpressed, Boolean newpressed)
    {
        if(!newpressed)this.attackk(newpressed);
//        if(newpressed)
//        {
//            if(!this.frozen.get()&&!this.stick.frozen)
//            {
//                Ball.ballsAttack(this);
//                if(!this.isAttacking)this.attack();
//            }
//        }
//        if(!newpressed)
//        {
//
//        }
    }
    public void moveee(ObservableValue<? extends Boolean> move, Boolean oldpressed, Boolean newpressed)
    {
        if(!newpressed)this.movee(newpressed);
//        if(newpressed)
//        {
//            if(!this.frozen.get() && !this.isJumping)
//            {
//                this.shadowMove.play();
//            }
//        }
//        if(!newpressed)
//        {
//
//        }
    }
    public void droppp(ObservableValue<? extends Boolean> drop, Boolean oldpressed, Boolean newpressed)
    {
        if(!newpressed)this.dropp(newpressed);
//        if(newpressed)
//        {
//            if(!this.frozen.get() && !this.isJumping)
//            {
//                this.stick.fly();
//            }
//        }
//        if(!newpressed)
//        {
//
//        }
    }
    public void rotateee(ObservableValue<? extends Boolean> rotate, Boolean oldpressed, Boolean newpressed)
    {
        if(!newpressed)this.rotatee(newpressed);
//        if(newpressed)
//        {
//            if(!robot.frozen.get() && !robot.isJumping)
//            {
//                robot.stick.fly();
//            }
//        }
//        if(!newpressed)
//        {
//
//        }
    }
    public void transfere()
    {

    }
}


//        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(0.1), event ->
//        {
//            if(upp.get())
//            {
//                if(!frozen.get() && !isJumping)
//                {
//                    this.isJumping = true;
//                    this.jump(15 * MatchstickMan.ratio);
//                }
//            }
//            if(downn.get())
//            {
//                if(!frozen.get() && !isJumping)
//                {
//                    if(this.facingRight.get())
//                    {
//                        this.shield.right();
//                    }
//                    else
//                    {
//                        this.shield.left();
//                    }
//                    this.shield.show();
//                    this.down();
//                    this.frozen.set(true);
//                }
//            }
//            else
//            {
//                shield.disappear();
//                up();
//            }
//            if(leftt.get())
//            {
//                if(!frozen.get() && !this.isMoving)
//                {
//                    System.out.println("here");
//                    if (this.isJumping) this.left(Duration.millis(150));
//                    else this.left();
//                }
//            }
//            else
//            {
//                System.out.println("stop at "+getLayoutX());
//                this.legReposition();
//                this.right.stop();
//                this.left.stop();
//                this.isMoving = false;
//                this.isCanceled = true;
//                this.motionStatus = 0;
//            }
//            if(rightt.get())
//            {
//                if(!frozen.get() && !this.isMoving)
//                {
//                    if(this.isJumping) this.right(Duration.millis(150));
//                    else this.right();
//                }
//            }
//            else
//            {
//                System.out.println("stop at "+getLayoutX());
//                this.legReposition();
//                this.right.stop();
//                this.left.stop();
//                this.isMoving = false;
//                this.isCanceled = true;
//                this.motionStatus = 0;
//            }
//            if(switchh.get())
//            {
//
//            }
//            if(attackk.get())
//            {
//
//            }
//            if(movee.get())
//            {
//
//            }
//        }));
//        timeline.setCycleCount(Timeline.INDEFINITE);
//        timeline.play();

//public void upp(ObservableValue<? extends Boolean> up, Boolean oldpressed, Boolean newpressed)
//    {
//        if(newpressed)
//        {
//            if(!frozen.get() && !isJumping)
//            {
//                this.isJumping = true;
//                this.jump(15 * MatchstickMan.ratio);
//            }
//        }
//        if(!newpressed)
//        {
//            
//        }
//    }
//    public void downn(ObservableValue<? extends Boolean> down, Boolean oldpressed, Boolean newpressed)
//    {
//        if(newpressed)
//        {
////            System.out.println("here");
//            if(!frozen.get() && !isJumping)
//            {
//                if(this.facingRight.get())
//                {
//                    this.shield.right();
//                }
//                else
//                {
//                    this.shield.left();
//                }
//                this.shield.show();
//                this.down();
//                this.frozen.set(true);
//            }
//        }
//        if(!newpressed)
//        {
//            shield.disappear();
//            up();
//        }
//    }
//    public void leftt(ObservableValue<? extends Boolean> left, Boolean oldpressed, Boolean newpressed)
//    {
//        if(newpressed)
//        {
//            if(!frozen.get() && !this.isMoving)
//            {
//                System.out.println("here");
//                if (this.isJumping) this.left(Duration.millis(150));
//                else this.left();
//            }
//        }
//        if(!newpressed)
//        {
//            System.out.println("stop at "+getLayoutX());
//            this.legReposition();
//            this.right.stop();
//            this.left.stop();
//            this.isMoving = false;
//            this.isCanceled = true;
//            this.motionStatus = 0;
//        }
//    }
//    public void rightt(ObservableValue<? extends Boolean> right, Boolean oldpressed, Boolean newpressed)
//    {
//        if(newpressed)
//        {
//            if(!frozen.get() && !this.isMoving)
//            {
//                if(this.isJumping) this.right(Duration.millis(150));
//                else this.right();
//            }
//        }
//        if(!newpressed)
//        {
//            this.legReposition();
//            this.right.stop();
//            this.left.stop();
//            this.isMoving = false;
//            this.isCanceled = true;
//            this.motionStatus = 0;
//        }
//    }
//    public void switchh(ObservableValue<? extends Boolean> switchh, Boolean oldpressed, Boolean newpressed)
//    {
//        if(newpressed)
//        {
//            if(!frozen.get())
//            {
//                boolean flag = true;
//                for(Ball ball:this.totalBalls)
//                {
//                    if(ball.isAttacking || ball.isRecovering)flag = false;
//                }
//                for(Ball ball:this.totalBalls)
//                {
//                    if(flag)
//                    {
//                        if (!this.getChildren().contains(ball) && !this.platform.getChildren().contains(ball))
//                            ball.play();
//                        else ball.stop();
//                    }
//                }
//            }
//        }
//        if(!newpressed)
//        {
//
//        }
//    }
//    public void attackk(ObservableValue<? extends Boolean> attack, Boolean oldpressed, Boolean newpressed)
//    {
//        if(newpressed)
//        {
//            if(!frozen.get())
//            {
//                Ball.ballsAttack(this,0);
//            }
//        }
//        if(!newpressed)
//        {
//
//        }
//    }
//    public void movee(ObservableValue<? extends Boolean> move, Boolean oldpressed, Boolean newpressed)
//    {
//        if(newpressed)
//        {
//            if(!frozen.get() && !isJumping)
//            {
//                enable("ShadowMove");
//                shadowMove.play();
//            }
//        }
//        if(!newpressed)
//        {
//
//        }
//    }
