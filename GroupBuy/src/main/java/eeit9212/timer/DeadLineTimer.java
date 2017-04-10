package eeit9212.timer;

import java.util.TimerTask;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import eeit9212.model.GroupInfoDAO;
import eeit9212.model.GroupInfoService;
import eeit9212.model.OrderInfoDAO;
import eeit9212.model.OrderInfoService;

public class DeadLineTimer extends TimerTask{

	private int groupInfoNo;
	private GroupInfoDAO groupInfo;
	private OrderInfoDAO orderInfo;

	public DeadLineTimer(int groupInfoNo, GroupInfoDAO groupInfo, OrderInfoDAO orderInfo) {
		super();
		this.groupInfoNo = groupInfoNo;
		this.groupInfo = groupInfo;
		this.orderInfo = orderInfo;
	}

	@Override
	public void run() {
		groupInfo.updateGroupStatus(groupInfoNo, 2);
		System.out.println("更新時間:"+new java.util.Date());
	}
	

	public static void main(String[] args) {
		
	}


 
}
