package mangFu;

public class Player
{
	double x,y,hp;
	int ballnumber;
	boolean hplocked;
	boolean facingright;
	boolean isdefending;
	public void init(double x,double y,double hp,int ballnumber,boolean hplocked,boolean facingright,boolean isdefending) {
		this.x=x;this.y=y;this.hp=hp;
		this.ballnumber=ballnumber;this.hplocked=hplocked;
		this.facingright=facingright;this.isdefending=isdefending;
	}
}
