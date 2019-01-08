package robot;

public class Info
{
    Player A,B;//A is me
    double[][] ball=new double[4][2];
    public Info(Player A, Player B)
    {
        this.A = A;
        this.B = B;
    }
    public void init(double[][] ball)
    {
        this.ball=ball;
    }
}