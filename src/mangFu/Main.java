//package mangFu;
//
//import java.lang.reflect.Array;
//import java.util.ArrayList;
//
//public class Main {
//	boolean havek=false;
//	//up left right down switch attack move
//	public ArrayList<String> operate(Info now) {
//		ArrayList<String> s = new ArrayList<>();
//		if(!havek) {
//			havek=true;
//			s.add("switch");
//			return s;
//		}
//		return defence(now);
//	}
//	private ArrayList<String> defence(Info now) {
//		double a,b,t;
//		for(int i=0;i<3;i++)
//			for(int j=i+1;j<4;j++) {
//				a=Math.abs(now.ball[i][0]-now.A.x);
//				b=Math.abs(now.ball[j][0]-now.A.x);
//				if(a>b) {
//					t=now.ball[i][0];now.ball[i][0]=now.ball[j][0];now.ball[j][0]=t;
//					t=now.ball[i][1];now.ball[i][1]=now.ball[j][1];now.ball[j][1]=t;
//				}
//			}
//		ArrayList<String> s = new ArrayList<>();
//		if(now.ball[0][0]<now.A.x){
//			if(now.A.facingright==true&&now.A.isdefending==true)
//			{
////				s.add("left");
//				return s;
//			}
//			s.add("left");
//		}
//		else{
//			if(now.A.facingright==false&&now.A.isdefending==true)
//			{
////				s.add("right");
//				return s;
//			}
//			s.add("right");
//		}
//		s.add("down");
//		return s;
//	}
//}


/*-------------------------------------------------------------------------------------------*/

package mangFu;

import java.util.*;

public class Main {
	boolean havek=false;
	final double eps=1e-10;
	Random rand=new Random();
	ArrayList<String> last = new ArrayList<>();
	//up left right down switch attack move
	public ArrayList<String> operate(Info now) {
		//if(true) return moveto(now);
		ArrayList<String> s=new ArrayList<String>();
		if(!havek) {
			havek=true;
			s.add("switch");
		}
		else if(dangerous(now)) s=defence(now);
		else if(neardanger(now)) s=flee(now);
		else if((now.B.ballnumber==0 && now.A.ballnumber!=0) ||( now.A.ballnumber-2>=now.B.ballnumber && now.A.hp > eps+10*now.B.ballnumber)) s=superattack(now);
		else if((now.B.ballnumber<2 && now.B.ballnumber<now.A.ballnumber) || (now.B.hp<now.A.hp)) s=attack(now);
		else if(now.B.ballnumber>now.A.ballnumber && now.A.x>200 && now.A.x<1600) s=moveaway(now);
		else if(Math.abs(now.A.x-now.B.x)>1000 ) s=moveto(now);
		else
		{
			s=rand.nextInt(33)==1?attack(now):defence(now);
//			System.out.println("male");
		}
		if(!s.equals(last) && !last.isEmpty()) return last=new ArrayList<>();
		else return last=s;
	}
	private boolean neardanger(Info now) {
		if(Math.abs(now.A.x-now.B.x)>15*15+eps ) return false;
		if(now.B.ballnumber<now.A.ballnumber) return false;
		return true;
	}
	private ArrayList<String> flee(Info now) {
		if(now.A.x>200 && now.A.x<1600) return moveaway(now);
		ArrayList<String> s=new ArrayList<String>();
		s.add(now.A.x>900+eps?"left":"right");
		s.add("up");
		return s;
	}
	private boolean dangerous(Info now) {
		double tt;
		tt=Math.abs(now.A.x-now.ball[0][0]);
		tt=Math.min(tt,Math.abs(now.A.x-now.ball[1][0]));
		tt=Math.min(tt,Math.abs(now.A.x-now.ball[2][0]));
		tt=Math.min(tt,Math.abs(now.A.x-now.ball[3][0]));
		return tt-225<-eps;
	}
	private ArrayList<String> moveto(Info now) {
		ArrayList<String> s=new ArrayList<String>();
		if(!now.B.hplocked) s.add(now.B.x>now.A.x?"right":"left");
		return s;
	}
	private ArrayList<String> moveaway(Info now) {
		ArrayList<String> s=new ArrayList<String>();
		s.add(now.B.x<now.A.x?"right":"left");
		return s;
	}
	private ArrayList<String> attack(Info now) {
		if(Math.abs(now.A.x-now.B.x)>450) return moveto(now);
		if((Math.abs(now.A.x-now.B.x)<4.5*15-eps || now.B.isdefending==false )&& now.A.ballnumber!=0 && now.B.hplocked==false) {
			ArrayList<String> s=new ArrayList<String>();
			s.add(now.B.x>now.A.x?"right":"left");
			s.add("attack");return s;
		}
		return defence(now);
	}
	private ArrayList<String> superattack(Info now) {
		ArrayList<String> s=new ArrayList<String>();
		if(rand.nextInt(200)<200-Math.abs(now.A.x-now.B.x) && now.B.hplocked==false) s.add("attack");
		else s.add(now.B.x>now.A.x?"right":"left");
		return s;
	}
	private ArrayList<String> defence(Info now) {
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
		//if(now.A.isdefending && (now.ball[0][0]<now.A.x)==now.A.facingright) return new ArrayList<String>();
		if(Math.abs(now.ball[0][0]-now.A.x) < (7*15)) {
			ArrayList<String> s=new ArrayList<String>();
			s.add("right");
			s.add("move");return s;
		}
		ArrayList<String> s=new ArrayList<String>();
		if(now.ball[0][0]>10000){
			if(now.B.x-now.A.x>eps) s.add("right");
			else s.add("left");
		}
		else {
			if (now.ball[0][0] < now.A.x) s.add("left");
			else s.add("right");
		}
		s.add("down");
		return s;
	}
}
