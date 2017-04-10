package test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class TestDate {

	public static void main(String[] args) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		java.util.Date date=new java.util.Date();
		java.sql.Date sqlDate=new java.sql.Date(date.getTime());
		System.out.println(sqlDate);
		System.out.println(format.format(sqlDate));
		
		java.util.Date date1=new java.util.Date();
		Timestamp ts=new Timestamp(date1.getTime());
		System.out.println(ts);
		System.out.println(new Timestamp(date1.getTime()+86400000*3));
		System.out.println(format.format(ts));

	}

}
