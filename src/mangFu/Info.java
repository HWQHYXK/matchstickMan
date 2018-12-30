package mangFu;

public class Info
{
	final int N=67108864;
	final double eps=1e-10;
	Player A,B;//A is me
	double[][] ball=new double[4][2];
	public Info(Player A, Player B)
	{
		this.A=A;this.B=B;
	}
	public void init(double[][] ball)
	{
		this.ball=ball;
	}
}
