package robot;

import java.util.ArrayList;
import java.util.Random;

public class Warrior {
    private Info now;

    private long time;
    private boolean wait;
    private double point;

    private double state;
    private double situation;

    final double inf=1e30;
    final double eps=1e-10;

    final Random rand=new Random();
    final Calculator calculator=new Calculator();
    final String[] key={"up","left","right","down","switch","attack","move","drop","rotate"};

    public Warrior(){
        wait=false;
        point=1e30;
    }

    public ArrayList<String> operate(Info now) {
        this.now=now;

        ArrayList<String> s=new ArrayList<String>();

        if(now.B.isdrop) {
            if(!wait) {
                wait=true;
                time = System.currentTimeMillis();
            }
            else if(time+250>=System.currentTimeMillis()) {
                point=now.A.x;
                wait=false;
            }
        }
        else point=inf;

        judgeState();
        judgeSituation();

        if((s=naturalReact()).size()!=0) return s;
        if((s=attack()).size()!=0) return s;

        if(!now.A.isrotate) s.add("rotate");
        else s=move(rand.nextDouble()>(-0.233*state+situation)*1.5);

        return s;
    }
    private void judgeState() {
        int p1=-10,p2=10,p3=4,p4=-4,p5=10;
        double total=p1+p2+p3+p4+p5;
        double val=now.B.hp/100*p1+now.A.hp/100*p2+now.A.ballnumber/4*p3+now.B.ballnumber/4*p4+rand.nextDouble()*p5;
        //valԽ�������Խ�ֹ�
        state=val/total;
        state=Math.min(state,1);
        state=Math.max(state,1e-30);
    }
    private void judgeSituation() {
        int p1=-4,p2=4,p3=10,p4=-10,p5=5;
        double total=p1+p2+p3+p4+p5;
        double val=now.B.hp/100*p1+now.A.hp/100*p2+now.A.ballnumber/4*p3+now.B.ballnumber/4*p4+rand.nextDouble()*p5;
        //valԽ�������Խ�ֹ�
        situation=val/total;
        situation=Math.min(situation,1);
        situation=Math.max(situation,1e-30);
    }
    private ArrayList<String> naturalReact() {
        ArrayList<String> s=new ArrayList<>();

        int tt=(int)(0.618/situation+0.382/state);
        if(tt==0 || rand.nextInt(tt)==0) return s;

        boolean go=true,move=true,defend=true;

        if(now.B.isdrop && Math.abs(now.A.x-point)<50) defend=false;
        if(near()) defend=false;
        if(ball_danger()) go=false;
        if(be_rotated()) go=false;

        if(defend&go&move) return s;
        if(!defend&&go&&move)
            return rand.nextBoolean()?go(true):move(true);
        while(true) {
            int t=rand.nextInt(3);
            if(t==0&&go) return go(true);
            else if(t==1&&move) return move(true);
            else if(t==2&&defend) return defend();
        }
    }
    private ArrayList<String> attack() {
        ArrayList<String> s=new ArrayList<>();

        if(now.A.ballnumber==0||now.B.hplocked) return s;
        if(now.B.isdefending) {
            double dis=Math.abs(now.A.x-now.B.x);
            if(dis<200) {
                if(rand.nextInt(200)<dis) return ballattack();
                return go(false);
            }
            if(dis > 888+66*now.B.ballnumber+rand.nextInt((int)((1-state)*235)+1)) {
                s.add("drop");
                return s;
            }
        }
        if(now.B.isdrop) {
            double dis=Math.abs(now.A.x-now.B.x);
            if(dis < 450) return ballattack();
            if(dis < 600) return go(false);
            if(dis < 1200-66*now.B.ballnumber+rand.nextInt((int)((1-state)*255)+1)) return move(false);
            if(dis > 888+66*now.B.ballnumber+rand.nextInt((int)((1-state)*235)+1)) {
                s.add("drop");
                return s;
            }
        }
        if(now.B.ballnumber<=now.A.ballnumber && now.A.hp>=now.B.ballnumber*10) {
            int tt=0;//int tt=(int)(0.6618/situation+0.3382/state);
            if(tt==0 || rand.nextInt(tt)==0) {
                double dis=Math.abs(now.A.x-now.B.x);
                if(rand.nextDouble()<dis/1000 && !now.B.isdefending) return ballattack();
                if(dis > 700) return move(false);
                if(dis > 200) return go(false);
                if(rand.nextInt(200) >= dis) return ballattack();
                return go(false);
            }
        }
        return s;
    }
    private boolean near() {
        if(Math.abs(now.A.x-now.B.x)<120) return true;
        return false;
    }
    private boolean ball_danger() {
        double tt;
        tt=Math.abs(now.A.x-now.ball[0][0]);
        tt=Math.min(tt,Math.abs(now.A.x-now.ball[1][0]));
        tt=Math.min(tt,Math.abs(now.A.x-now.ball[2][0]));
        tt=Math.min(tt,Math.abs(now.A.x-now.ball[3][0]));
        return tt-225<-eps;
    }
    private boolean be_rotated() {
        if(!now.B.isrotate) return false;
        return Math.abs(now.A.x-now.B.x)<200;
    }
    private ArrayList<String> go(boolean away) {
        ArrayList<String> s=new ArrayList<>();
        boolean right=true;
        if(away) right=now.B.x<now.A.x;
        else right=now.B.x>now.A.x;
        if(right && now.A.x>1750) right^=true;
        else if(!right && now.A.x<50) right^=true;
        s.add(right?"right":"left");
        return s;
    }
    private ArrayList<String> move(boolean away) {
        ArrayList<String> s=new ArrayList<>();
        boolean right=true;
        if(away) right=now.B.x<now.A.x;
        else right=now.B.x>now.A.x;
        if(right && now.A.x>1450) right^=true;
        else if(!right && now.A.x<350) right^=true;
        s.add(right?"right":"left");
        s.add("move");
        return s;
    }
    private ArrayList<String> defend() {
        double a,b,t;
        for(int i=0;i<3;i++)
            for(int j=i+1;j<4;j++) {
                a=Math.abs(now.ball[i][0]-now.A.x);
                b=Math.abs(now.ball[j][0]-now.A.x);
                if(a>b) {
                    t=now.ball[i][0];now.ball[i][0]=now.ball[j][0];now.ball[j][0]=t;
                    t=now.ball[i][1];now.ball[i][1]=now.ball[j][1];now.ball[j][1]=t;
                }
            }
        ArrayList<String> s=new ArrayList<String>();
        boolean right=true;
        if(now.ball[0][0]>10000){
            if(now.B.x-now.A.x>eps) right=true;
            else right=false;
        }
        else {
            if (now.ball[0][0] < now.A.x) right=false;
            else right=true;
        }
        if(right&&!now.A.facingright) s.add("right");
        else if(!right&&now.A.facingright) s.add("left");
        s.add("down");
        return s;
    }
    private ArrayList<String> ballattack() {
        ArrayList<String> s=new ArrayList<>();
        boolean right=now.B.x>now.A.x;;
        if(!now.A.facingright && right) s.add("right");
        else if(now.A.facingright && !right) s.add("left");
        s.add("attack");
        return s;
    }
}