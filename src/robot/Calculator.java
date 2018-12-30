package robot;

public class Calculator {
    private Info now;

    private double ballSpeed=-1;
    private double moveTime=-1;
    private double moveDis=-1;

    private double t;
    private double pos1=-1,time1;
    private double pos2=-1,time2=-1;

    void import_time(long t) {
        this.t=t;// now time
    }
    void import_info(Info now) {
        this.now=now;
    }
    void calc_speed() {
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
    void calc_time() {
        // the time of a move
        if(moveTime>0) return;
        if(time2<0 && now.B.hplocked) time2=t;
        else if(time2>0 && !now.B.hplocked) moveTime=t-time2;
    }
    void calc_dis() {
        // the dis of a move
        if(moveDis>0) return;
        if(pos2<0 && now.B.hplocked) pos2=now.B.x;
        else if(pos2>0 && !now.B.hplocked) moveDis=Math.abs(pos2-now.B.x);
    }

    private double pos=-1,time;
    void calc_move() {
        if(!now.B.hplocked){pos=-1;return;}
        if(pos>0) return;
        if(now.B.facingright) pos=now.B.x+moveDis;
        else pos=now.B.x-moveDis;
        time=t+moveTime;
    }
    double get_pos(){return pos;}
    double get_time(){return time;}
    double get_ballSpeed(){return ballSpeed;}
}
