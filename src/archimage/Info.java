package archimage;

public class Info extends mangFu.Info
{
    Player A,B;//A is me
    double[][] ball=new double[4][2];
    public Info(Player A, Player B)
    {
        super(A,B);
        this.A = A;
        this.B = B;
    }
    @Override
    public void init(double[][] ball)
    {
        this.ball=ball;
    }
}