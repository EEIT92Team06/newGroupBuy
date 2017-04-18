package test;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

public class TestDate {

	public static void main(String[] args) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date date=new java.util.Date();
		java.sql.Date sqlDate=new java.sql.Date(date.getTime());
		System.out.println(sqlDate);
		System.out.println(format.format(sqlDate));
		
		java.util.Date date1=new java.util.Date();
		Timestamp ts=new Timestamp(date1.getTime());

		System.out.println(ts);
		System.out.println(new Timestamp(date1.getTime()+86400000*3));
		System.out.println(format.format(ts));
		Double a=3.455465;
		DecimalFormat dddd=new DecimalFormat("0.0");
		String formatt=dddd.format(a);
		Double dd=Double.parseDouble(formatt);
		System.out.println(dd);
		
	}

}
