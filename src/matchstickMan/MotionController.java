package matchstickMan;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ObservableValue;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import javafx.util.Duration;



public class MotionController 
{
    public MatchstickMan man;
    public MatchstickMan man2;
//    public Robot robot,robot1;
    public HashMap<String, KeyCode> map1 = new HashMap<>();
    public HashMap<String, KeyCode> map2 = new HashMap<>();
    static boolean[] b = new boolean[]{false, false, false, false, false, false, false, false, false};
    public void initMap1()
    {
        map1.put("up", KeyCode.W);
        map1.put("left", KeyCode.A);
        map1.put("right", KeyCode.D);
        map1.put("down", KeyCode.S);
        map1.put("switch", KeyCode.K);
        map1.put("attack", KeyCode.J);
        map1.put("move", KeyCode.L);
        map1.put("shinning", KeyCode.I);
        map1.put("drop", KeyCode.U);
        map1.put("rotate",KeyCode.O);
    }
    public void initMap2()
    {
        map2.put("up", KeyCode.UP);
        map2.put("left", KeyCode.LEFT);
        map2.put("right", KeyCode.RIGHT);
        map2.put("down", KeyCode.DOWN);
        map2.put("switch", KeyCode.ALT);
        map2.put("attack", KeyCode.CONTROL);
        map2.put("move", KeyCode.SLASH);
        map2.put("shinning", KeyCode.SHIFT);
        map2.put("drop", KeyCode.ENTER);
        map2.put("rotate", KeyCode.BACK_SLASH);
    }
    public void controller1(KeyEvent event, MatchstickMan man, HashMap<String, KeyCode> map)
    {
        if(!man.frozen.getValue())
        {
            if (event.getCode() == map.get("right")) {
                man.rightt(true);
            }
            if (event.getCode() == map.get("left")) {
                man.leftt(true);
            }
            if (event.getCode() == map.get("up")) {
                man.upp(true);
            }
            if (event.getCode() == map.get("move"))
            {
                man.movee(true);
            }
            if(event.getCode() == map.get("down"))
            {
                man.downn(true);
            }
            if(event.getCode() == map.get("shinning"))
            {
                if(!man.isShining) man.shining.play(man.skin);
                else man.shining.stop();
            }
            if(event.getCode() == map.get("rotate"))
            {
                man.rotatee(true);
            }
            if(event.getCode() == map.get("switch"))
            {
                man.switchh(true);
            }
            if(event.getCode() == map.get("attack"))
            {
                man.attackk(true);
            }
            if(event.getCode() == map.get("drop"))
            {
                man.dropp(true);
            }
        }
    }
    public void controller2(KeyEvent event, MatchstickMan man, HashMap<String, KeyCode> map)
    {
        if(event.getCode() == map.get("left"))
        {
            man.leftt(false);
        }
        if(event.getCode() == map.get("right"))
        {
            man.rightt(false);
        }
        if(event.getCode() == map.get("down"))
        {
            man.downn(false);
        }
//        if(event.getCode() == map.get("move"))
//        {
//            man.right.stop();
//            man.left.stop();
//            man.changeStatus(0);
//        }
        if(event.getCode() == map.get("rotate"))
        {
            man.rotatee(false);
        }
//        if(event.getCode() == map.get("attack"))
//        {
//            man.isAttacking = false;
//        }
    }
    public MotionController(MatchstickMan man)
    {
        this.man = man;
        initMap1();
        initMap2();
    }
//    public void setRobot(Robot robot)
//    {
//        this.robot = robot;
//    }
//    public void setRobot1(Robot robot1){this.robot1 = robot1;}
    public static void auto(Robot robot,ArrayList<String> commands, int i) {
        if (i < commands.size()) new Timeline(new KeyFrame(Duration.millis(3), event ->
//        if (i < commands.size())
        {
            switch (commands.get(i)) {
                case "up":
//                    b[0] = true;
//                    if(!robot.frozen.get())robot.upp.set(true);
//                    robot.upp(true);
                    break;
                case "down":
                    b[1] = true;
                    robot.downn.set(true);
                    robot.downn(true);
                    break;
                case "left":
                    b[2] = true;
                    robot.leftt.set(true);
                    robot.leftt(true);
                    break;
                case "right":
                    b[3] = true;
                    robot.rightt.set(true);
                    robot.rightt(true);
                    break;
                case "switch":
                    b[4] = true;
                    if(!robot.frozen.get())robot.switchh.set(true);
                    robot.switchh(true);
                    break;
                case "attack":
                    b[5] = true;
                    if(!robot.frozen.get())robot.attackk.set(true);
                    robot.attackk(true);
                    break;
                case "move":
                    b[6] = true;
                    if(!robot.frozen.get())robot.movee.set(true);
                    robot.movee(true);
                    break;
                case "drop":
                    b[7] = true;
                    if(!robot.frozen.get())robot.dropp.set(true);
                    robot.dropp(true);
                    break;
                case "rotate":
                    b[8] = true;
                    robot.rotatee.set(true);
                    robot.rotatee(true);
            }
            auto(robot, commands, i + 1);
        }
        )).play();
        else
        {
//            if(!b[0])robot.upp(false);
//            if(!b[1])robot.downn(false);
//            if(!b[2])robot.leftt(false);
//            if(!b[3])robot.rightt(false);
//            if(!b[4])robot.switchh(false);
//            if(!b[5])robot.attackk(false);
//            if(!b[6])robot.movee(false);
//            if(!b[7])robot.dropp(false);
            for (int j = 0; j < b.length; j++)
            {
                if (!b[j]) robot.dict.get(j).set(false);
            }
            Arrays.fill(b, false);
        }
    }
//public void auto(ArrayList<String> commands)
//{
//    boolean[] b = new boolean[]{false, false, false, false, false, false, false};
//    for(String command:commands)
//    {
//        switch (command)
//        {
//            case "up":
//                b[0] = true;
//                robot.upp.set(true);
//                break;
//            case "down":
//                b[1] = true;
//                robot.downn.set(true);
//                break;
//            case "left":
//                b[2] = true;
//                robot.leftt.set(true);
//                break;
//            case "right":
//                b[3] = true;
//                robot.rightt.set(true);
//                break;
//            case "switch":
//                b[4] = true;
//                robot.switchh.set(true);
//                break;
//            case "attack":
//                b[5] = true;
//                robot.attackk.set(true);
//                break;
//            case "move":
//                b[6] = true;
//                robot.movee.set(true);
//                break;
//            case "drop":
//                System.out.println("drop");
//                b[7] = true;
//                robot.dropp.set(true);
//        }
//    }
//    for (int j = 0; j < b.length; j++)
//    {
//        if(!b[j]) robot.dict.get(j).set(false);
//    }
//}
    public void pressedHandler(KeyEvent event)
    {
        controller1(event, man, map1);
        if(man2!=null)controller1(event, man2, map2);
    }
    public void releasedHandler(KeyEvent event)
    {
        controller2(event, man, map1);
        if(man2 != null)
        {
            controller2(event, man2, map2);
        }
    }
    public void setMan2(MatchstickMan man2)
    {
        this.man2 = man2;
//        statusController2 = new StatusController(man2);
    }

}


//if(!man2.frozen.getValue())
//        {
//            if (event.getCode() == KeyCode.RIGHT && !man2.isMoving) {
//                if(man2.isJumping) man2.right(Duration.millis(150));
//                else man2.right();
//            }
//            if (event.getCode() == KeyCode.LEFT && !man2.isMoving) {
//                if (man2.isJumping) man2.left(Duration.millis(150));
//                else man2.left();
//            }
//            if (event.getCode() == KeyCode.UP && !man2.isJumping) {
//                man2.isJumping = true;
//                man2.jump(15 * MatchstickMan.ratio);
//            }
//            if(event.getCode() == KeyCode.DOWN)
//            {
//                if(man2.facingRight.get())
//                {
//                    man2.shield.right();
//                    man2.shield.show();
//                }
//                else
//                {
//                    man2.shield.left();
//                    man2.shield.show();
//                }
//            }
//            if (event.getCode() == KeyCode.SLASH && !man2.isJumping)
//            {
//                man2.enable("ShadowMove");
//                man2.shadowMove.play();
//            }
//            if(event.getCode() == KeyCode.PERIOD)
//            {
//                if(!man2.isShining) man2.shining.play();
//                else man2.shining.stop();
//            }
//            if(event.getCode() == KeyCode.V)
//            {
//                man2.shield.left();
//                man2.shield.show();
//            }
//            if(event.getCode() == KeyCode.B)
//            {
//                man2.shield.right();
//                man2.shield.show();
//            }
//            if(event.getCode() == KeyCode.N)
//            {
//                boolean flag = true;
//                for(Ball ball:man2.totalBalls)
//                {
//                    if(ball.isAttacking||ball.isRecovering)flag = false;
//                }
//                for(Ball ball:man2.totalBalls)
//                {
//                    if(flag)
//                    {
//                        if (!man2.getChildren().contains(ball) && !man2.platform.getChildren().contains(ball))
//                            ball.play();
//                        else ball.stop();
//                    }
//                }
//            }
//            if(event.getCode() == KeyCode.M)
//            {
//                Ball.ballsAttack(man2, 0);
//            }
//        }