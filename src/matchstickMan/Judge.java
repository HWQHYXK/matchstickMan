package matchstickMan;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.shape.Line;

import java.util.ArrayList;

public class Judge
{
    public MatchstickMan man;
    public ArrayList<Line> limbs = new ArrayList<Line>();
    public Judge()
    {
    }
    public Judge(MatchstickMan man)
    {
        this.man = man;
        limbs = man.limbs;
//        limbs.add(man.leftForeArm);
//        limbs.add(man.leftBackArm);
//        limbs.add(man.body);
//        limbs.add(man.rightBackArm);
//        limbs.add(man.rightForeArm);
//        limbs.add(man.leftForeLeg);
//        limbs.add(man.leftBackLeg);
//        limbs.add(man.rightBackLeg);
//        limbs.add(man.rightForeLeg);
    }

    public int calc(double x, double y, double radius,double toShield,double toHead,double toLimbs)
    {
        double squareDistanceToHead = (x-man.getLayoutX())*(x-man.getLayoutX())+(y-man.getLayoutY())*(y-man.getLayoutY());
//        System.out.println(x-man.getLayoutX());
//        System.out.println(y-man.getLayoutY());
//        System.out.println(squareDistanceToHead);
        double squareDistanceToLimb;
        double squareDistanceToShield;
        if(man.getChildren().contains(man.shield))
        {
            double x1 = man.getLayoutX();
            double y1 = man.getLayoutY()+2.75*MatchstickMan.ratio;
            squareDistanceToShield = (x-x1)*(x-x1)+(y-y1)*(y-y1);
//            System.out.println(Math.abs(squareDistanceToShield -4.5*4.5*MatchstickMan.ratio*MatchstickMan.ratio));
//            if(man.getChildren().contains(man.shield))System.out.println(Math.sqrt(squareDistanceToShield) -(4.5*MatchstickMan.ratio+radius));
            if(Math.abs(Math.sqrt(squareDistanceToShield) -(4.5*MatchstickMan.ratio+radius)) <= toShield)
            {
                if(man.shield.right)
                {
//                    System.out.println("here");
                    if(x>=x1&&y<=man.getLayoutY()+6.5*MatchstickMan.ratio)return -1;
                }
                else
                {
                    if(x<=x1&&y<=man.getLayoutY()+6.5*MatchstickMan.ratio)return -1;
                }
            }
        }
        if(Math.abs(Math.sqrt(squareDistanceToHead) - (MatchstickMan.ratio+radius)) <= toHead)
        {
            System.out.println(Math.sqrt(squareDistanceToHead));
            return 2;
        }
        else
        {
            for(Line limb: limbs)
            {
                double x1 = man.getLayoutX()+limb.getStartX();
                double y1 = man.getLayoutY()+limb.getStartY();
                double x2 = man.getLayoutX()+limb.getEndX();
                double y2 = man.getLayoutY()+limb.getEndY();
                if((y-y1)*(y-y2)<0)
                {
                    squareDistanceToLimb = (((y1 - y2) * x - (x1 - x2) * y + x1 * y2 - x2 * y1) * ((y1 - y2) * x - (x1 - x2) * y + x1 * y2 - x2 * y1)) / ((y1 - y2) * (y1 - y2) + (x1 - x2) * (x1 - x2));
                    if (squareDistanceToLimb <= toLimbs) return 1;
                }
            }
        }
        return 0;
    }
}
//    public  int calc(double x, double y)
//    {
//        double squareDistanceToHead = (x-man.getLayoutX())*(x-man.getLayoutX())+(y-man.getLayoutY())*(y-man.getLayoutY());
////        System.out.println(x-man.getLayoutX());
////        System.out.println(y-man.getLayoutY());
////        System.out.println(squareDistanceToHead);
//        double squareDistanceToLimb;
//        if(man.getChildren().contains(man.shield))
//        {
//            double x1 = man.getLayoutX();
//            double y1 = man.getLayoutY()+2.75*MatchstickMan.ratio;
////            System.out.println(Math.abs(squareDistanceToShield -4.5*4.5*MatchstickMan.ratio*MatchstickMan.ratio));
//            if(man.shield.right&&Math.abs(x-man.getLayoutX()-4.5*MatchstickMan.ratio)<=3)
//            {
////                    System.out.println("here");
//                if(x>=x1&&y<=man.getLayoutY()+6.5*MatchstickMan.ratio)return -1;
//            }
//            else if(Math.abs(x-man.getLayoutX()+4.5*MatchstickMan.ratio)<=3)
//            {
//                if(x<=x1&&y<=man.getLayoutY()+6.5*MatchstickMan.ratio)return -1;
//            }
//        }
//        if(Math.abs(Math.sqrt(squareDistanceToHead) - (MatchstickMan.ratio)) <= 10)
//        {
//            System.out.println(Math.sqrt(squareDistanceToHead));
//            return 2;
//        }
//        else
//        {
//            for(Line limb: limbs)
//            {
//                double x1 = man.getLayoutX()+limb.getStartX();
//                double y1 = man.getLayoutY()+limb.getStartY();
//                double x2 = man.getLayoutX()+limb.getEndX();
//                double y2 = man.getLayoutY()+limb.getEndY();
//                if((y-y1)*(y-y2)<0)
//                {
//                    squareDistanceToLimb = (((y1 - y2) * x - (x1 - x2) * y + x1 * y2 - x2 * y1) * ((y1 - y2) * x - (x1 - x2) * y + x1 * y2 - x2 * y1)) / ((y1 - y2) * (y1 - y2) + (x1 - x2) * (x1 - x2));
//                    if (squareDistanceToLimb <= 50) return 1;
//                }
//            }
//        }
//        return 0;
//    }
