package robot;

public class Calculator {
    private double ballSpeed=-1;
    private double moveTime=-1;
    private double moveDis=-1;

    private double t;
    private double pos1=-1,time1;
    private double pos2=-1,time2=-1;

    void import_time(long t) {
        this.t=t;// now time
    }
    void calc_speed(Info now) {
        // the speed of ball
        if(ballSpeed>0) return;
        if(pos1<0) {
            if(now.ball[0][0] > 3000) return;
            pos1=now.ball[0][0];
            time1=t;
        }
        else if(Math.abs(now.ball[0][0]-pos1) >100) {
            ballSpeed=Math.abs(pos1-now.ball[0][0])/(t-time1);
        }
    }
    void calc_time(Info now) {
        // the time of a move
        if(moveTime>0) return;
        if(time2<0 && now.B.hplocked) time2=t;
        else if(time2>0 && !now.B.hplocked) moveTime=t-time2;
    }
    void calc_dis(Info now) {
        // the dis of a move
        if(moveDis>0) return;
        if(pos2<0 && now.B.hplocked) pos2=now.B.x;
        else if(pos2>0 && !now.B.hplocked) moveDis=Math.abs(pos2-now.B.x);
    }
}
