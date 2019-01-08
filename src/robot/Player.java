package robot;

public class Player
{
    double x,y,hp;
    int ballnumber;
    boolean hplocked;
    boolean facingright;
    boolean isdefending;
    boolean isrotate;
    boolean isdrop;
    boolean isattacking;
    public void init(double x,double y,double hp,int ballnumber,boolean hplocked,boolean facingright,boolean isdefending,boolean isrotate,boolean isdrop, boolean isattacking) {
//        System.out.print(x);
        this.x=x;this.y=y;this.hp=hp;
        this.ballnumber=ballnumber;this.hplocked=hplocked;
        this.facingright=facingright;this.isdefending=isdefending;
        this.isrotate=isrotate;this.isdrop=isdrop;this.isattacking=isattacking;
    }
}
