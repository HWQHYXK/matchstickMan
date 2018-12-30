package matchstickMan;

import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Robot extends MatchstickMan implements ChangeListener<Boolean>
{
    public BooleanProperty upp = new SimpleBooleanProperty(),downn = new SimpleBooleanProperty(),leftt= new SimpleBooleanProperty(),rightt = new SimpleBooleanProperty(),
            switchh = new SimpleBooleanProperty(),attackk = new SimpleBooleanProperty(),movee= new SimpleBooleanProperty(),dropp = new SimpleBooleanProperty(), rotatee = new SimpleBooleanProperty(),
            shinningg= new SimpleBooleanProperty();
    protected Timeline transfere;
    ArrayList<BooleanProperty> dict = new ArrayList<>();
    public Robot(boolean facingRight, MotionController mc, Color skin)
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


        upp.addListener(mc::upp);
        downn.addListener(mc::downn);
        leftt.addListener(mc::leftt);
        rightt.addListener(mc::rightt);
        switchh.addListener(mc::switchh);
        attackk.addListener(mc::attackk);
        movee.addListener(mc::movee);
        dropp.addListener(mc::dropp);
        rotatee.addListener(mc::rotatee);
    }
    public void transfere(MotionController mc)
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
