package test;

import java.sql.Timestamp;
import java.util.Timer;
import java.util.TimerTask;

public class Test extends TimerTask{

	
	@Override
	public void run() {
	System.out.println("現在時間="+new java.util.Date());
		
	}
	
	
	public static void main(String[] args) {
		Timer timer =new Timer();
		Timestamp date1=Timestamp.valueOf("2017-03-27 10:30:45");
		java.util.Date date=new java.util.Date(new java.util.Date().getTime()+5000);
		timer.schedule(new Test(), date1);
		
		
	}



}
