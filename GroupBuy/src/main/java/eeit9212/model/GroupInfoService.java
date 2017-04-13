package eeit9212.model;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class GroupInfoService {
	private GroupInfoPicDAO groupInfoPicDAO;
	private GroupInfoDAO groupInfoDAO;
	private GroupInfoDetailsDAO groupInfoDetailsDAO;
	private OrderInfoDAO orderInfoDAO;
	private CreditAttendanceService creditAttendanceService;

	public GroupInfoService(GroupInfoPicDAO groupInfoPicDAO, GroupInfoDAO groupInfoDAO,
			GroupInfoDetailsDAO groupInfoDetailsDAO, OrderInfoDAO orderInfoDAO,
			CreditAttendanceService creditAttendanceService) {
		super();
		this.groupInfoPicDAO = groupInfoPicDAO;
		this.groupInfoDAO = groupInfoDAO;
		this.groupInfoDetailsDAO = groupInfoDetailsDAO;
		this.orderInfoDAO = orderInfoDAO;
		this.creditAttendanceService = creditAttendanceService;
	}


	public static void main(String args[]) {

		ApplicationContext context = new ClassPathXmlApplicationContext("eeit9212/beans.xml");
		GroupInfoService groupInfoService = (GroupInfoService) context.getBean("groupInfoService");
		List<CreateGroupInfoBean> select = groupInfoService.selectMyCreatedGroupInfo(1);
		System.out.println(select);
		List<AttendGroupInfoBean> select1 = groupInfoService.selectMyAttendedGroupInfo(1);
		System.out.println(select1);
		List<GroupInfoPicBean> select2 = groupInfoService.selectGroupInfoPic(1);
		System.out.println(select2);
		List<GroupInfoDetailsBean> select3 = groupInfoService.selectGroupInfoDetail(1);
		System.out.println(select3);
		CreateGroupInfoBean select4 = groupInfoService.selectGroupInfoByGroupInfoNo(1);
		System.out.println(select4);
		AttendGroupInfoBean select5 = groupInfoService.selectMyAttendedByGroupInfoNo(1, 3);
		System.out.println(select5);

		((ConfigurableApplicationContext) context).close();
	}

	public int updateGroupInfoDeadLine(int groupInfoNo, Timestamp deadLine) {
		int result = -1;
		result = groupInfoDAO.updateGroupInfoDeadLine(groupInfoNo, new Timestamp(new java.util.Date().getTime() + 60000));
		return result;

	}

	public int updateGroupStatus(int groupInfoNo, int groupStatusNo) {
		int result = -1;
		result = groupInfoDAO.updateGroupStatus(groupInfoNo, groupStatusNo);
		return result;

	}

	public List<GroupInfoDetailsBean> selectGroupInfoDetail(int groupInfoNo) {
		List<GroupInfoDetailsBean> result = null;
		result = groupInfoDetailsDAO.selectGroupInfoDetail(groupInfoNo);

		return result;
	}

	public List<GroupInfoPicBean> selectGroupInfoPic(int groupInfoNo) {
		List<GroupInfoPicBean> result = null;
		result = groupInfoPicDAO.selectGroupInfoPic(groupInfoNo);
		return result;
	}

	public GroupInfoPicBean selectGroupInfoPicByNo(int groupInfoPicNo) {
		GroupInfoPicBean result = null;
		result = groupInfoPicDAO.selectGroupInfoPicByNo(groupInfoPicNo);
		return result;
	}

	public CreateGroupInfoBean selectGroupInfoByGroupInfoNo(int groupInfoNo) {
		CreateGroupInfoBean result = null;
		result = groupInfoDAO.selectGroupInfoByGroupInfoNo(groupInfoNo);
		boolean flag=false;
		int count = 0;
		int checkCount = 0;
		if (result.getGroupStatusNo() == 9) {
			for (OrderInfoBean orderBean : orderInfoDAO.selectMyGroupOrderInfo(result.getGroupInfoNo())) {
				if (orderBean.getOrderInfoStatusNo() != 1004 && orderBean.getOrderInfoStatusNo() != 1002) {
					count++;

				}

				if (orderBean.getOrderInfoStatusNo() == 1202) {
					checkCount++;
				}

			}
			if (count == checkCount) {
				groupInfoDAO.updateGroupStatus(result.getGroupInfoNo(), 11);
				System.out.println("已匯款的買家都已收貨+評分，團狀態改成11(成功結束)");
				flag = true;
			}
		}
		if(flag){
			result = groupInfoDAO.selectGroupInfoByGroupInfoNo(groupInfoNo);
		}
		return result;
	}

	public AttendGroupInfoBean selectMyAttendedByGroupInfoNo(int memberNo, int groupInfoNo) {
		AttendGroupInfoBean result = null;
		result = groupInfoDAO.selectMyAttendedByGroupInfoNo(memberNo, groupInfoNo);
		return result;
	}

	public List<CreateGroupInfoBean> selectMyCreatedGroupInfo(int memberNo) {
		List<CreateGroupInfoBean> result = groupInfoDAO.selectMyCreatedGroupInfo(memberNo);
		if (this.checkCreateGroupInfo(result)) {
			result = groupInfoDAO.selectMyCreatedGroupInfo(memberNo);
		}

		return result;
	}

	public List<AttendGroupInfoBean> selectMyAttendedGroupInfo(int memberNo) {
		List<AttendGroupInfoBean> result = groupInfoDAO.selectMyAttendedGroupInfo(memberNo);
		boolean flag = false;
		if (this.checkAttendGroupInfo(result)) {
			result = groupInfoDAO.selectMyAttendedGroupInfo(memberNo);
		}

		for (AttendGroupInfoBean bean : result) {
			if (bean.getGroupStatusNo() == 8 && bean.getOrderStatusNo() == 1101) {
				orderInfoDAO.updateOrderInfoStatus(1004, bean.getOrderInfoNo());
				creditAttendanceService.updateGroupAttendance(memberNo, 0);
				flag = true;
			}
		}
		if (flag) {
			result = groupInfoDAO.selectMyAttendedGroupInfo(memberNo);

		}
		return result;
	}

	public boolean checkAttendGroupInfo(List<AttendGroupInfoBean> result) {
		boolean flag = false;

		for (AttendGroupInfoBean bean : result) {
			System.out.println(bean.getGroupInfoNo()+"號團截止日期的LONG為:"+bean.getGroupInfoDeadLine().getTime());
			System.out.println("現在時間的LONG為:"+new java.util.Date().getTime());
			System.out.println("時間差為:"+(bean.getGroupInfoDeadLine().getTime()-new java.util.Date().getTime()));
			if (bean.getGroupStatusNo() == 7
					&& bean.getGroupInfoDeadLine().getTime() - new java.util.Date().getTime() <= 86400000) {
				orderInfoDAO.updateOrderInfoStatusByOrderStatusNo(bean.getGroupInfoNo(), 1104, 1101);
				System.out.println("匯款時間剩不到24H，"+bean.getGroupInfoNo()+"團還未匯款的改成1104");
				flag = true;
			}
			
			if (bean.getGroupInfoDeadLine().getTime() - new java.util.Date().getTime() <= 0
					&& bean.getGroupStatusNo() <= 8) {
				if (bean.getGroupStatusNo() == 1) {
					groupInfoDAO.updateGroupStatus(bean.getGroupInfoNo(), 2);
					System.out.println("第一次時間到，"+bean.getGroupInfoNo()+"團狀態改成2");
					flag = true;
				} else if (bean.getGroupStatusNo() == 3) {
					groupInfoDAO.updateGroupStatus(bean.getGroupInfoNo(), 6);
					System.out.println("延期時間到，"+bean.getGroupInfoNo()+"團狀態改成6");
					flag = true;
				}
				if (bean.getGroupStatusNo() == 7) {
					groupInfoDAO.updateGroupStatus(bean.getGroupInfoNo(), 8);
					orderInfoDAO.updateOrderInfoStatusByOrderStatusNo(bean.getGroupInfoNo(), 1004, 1104);
					for (OrderInfoBean orderBean : orderInfoDAO.selectMyGroupOrderInfo(bean.getGroupInfoNo())) {
						if (orderBean.getOrderInfoStatusNo() == 1004) {
							creditAttendanceService.updateGroupAttendance(orderBean.getMemberNo(), 0);
							System.out.println("匯款時間到，"+orderBean.getMemberNo()+"會員狀態，1104改成1004，出席率少1");
						}
					}
					flag = true;
				}

			}
			if (new java.util.Date().getTime() - bean.getGroupInfoDeadLine().getTime() > 86400000) {
				if (bean.getGroupStatusNo() == 2 || bean.getGroupStatusNo() == 6) {
					groupInfoDAO.updateGroupStatus(bean.getGroupInfoNo(), 5);
					orderInfoDAO.updateOrderInfoStatusByGroupInfoNo(bean.getGroupInfoNo(), 1005);
					// 可能要呼叫發站內信的功能
					System.out.println(bean.getGroupStatusNo() + "團超過24H未處理，狀態改成5，所有該團買家狀態改成1005");
					flag = true;
				}

			}
			int count = 0;
			int checkCount = 0;
			if (bean.getGroupStatusNo() == 9) {
				for (OrderInfoBean orderBean : orderInfoDAO.selectMyGroupOrderInfo(bean.getGroupInfoNo())) {
					if (orderBean.getOrderInfoStatusNo() != 1004 && orderBean.getOrderInfoStatusNo() != 1002) {
						count++;

					}

					if (orderBean.getOrderInfoStatusNo() == 1202) {
						checkCount++;
					}

				}
				if (count == checkCount) {
					groupInfoDAO.updateGroupStatus(bean.getGroupInfoNo(), 11);
					System.out.println(bean.getGroupInfoNo()+"團已匯款的買家都已收貨+評分，團狀態改成11(成功結束)");
					
					flag = true;
				}
			}
		}

		return flag;
	}

	public boolean checkCreateGroupInfo(List<CreateGroupInfoBean> result) {
		boolean flag = false;

		for (CreateGroupInfoBean bean : result) {
			System.out.println(bean.getGroupInfoNo()+"號團截止日期的LONG為:"+bean.getGroupInfoDeadLine().getTime());
			System.out.println("現在時間的LONG為:"+new java.util.Date().getTime());
			System.out.println("時間差為:"+(bean.getGroupInfoDeadLine().getTime()-new java.util.Date().getTime()));
			if (bean.getGroupStatusNo() == 7
					&& bean.getGroupInfoDeadLine().getTime() - new java.util.Date().getTime() <= 86400000) {
				orderInfoDAO.updateOrderInfoStatusByOrderStatusNo(bean.getGroupInfoNo(), 1104, 1101);
				System.out.println("匯款時間剩不到24H，還未匯款的改成1104");
				flag = true;
			}

			if (bean.getGroupInfoDeadLine().getTime() - new java.util.Date().getTime() <= 0
					&& bean.getGroupStatusNo() <= 8) {
				if (bean.getGroupStatusNo() == 1) {
					groupInfoDAO.updateGroupStatus(bean.getGroupInfoNo(), 2);
					System.out.println("第一次時間到，狀態改成2");
					flag = true;
				} else if (bean.getGroupStatusNo() == 3) {
					groupInfoDAO.updateGroupStatus(bean.getGroupInfoNo(), 6);
					System.out.println("延期時間到，狀態改成6");
					flag = true;
				}
				if (bean.getGroupStatusNo() == 7) {
					groupInfoDAO.updateGroupStatus(bean.getGroupInfoNo(), 8);
					orderInfoDAO.updateOrderInfoStatusByOrderStatusNo(bean.getGroupInfoNo(), 1004, 1104);
					for (OrderInfoBean orderBean : orderInfoDAO.selectMyGroupOrderInfo(bean.getGroupInfoNo())) {
						if (orderBean.getOrderInfoStatusNo() == 1004) {
							creditAttendanceService.updateGroupAttendance(orderBean.getMemberNo(), 0);
						}
					}
					System.out.println("匯款時間到，1104改成1004，該會員出席率少1");
					flag = true;
				}

			}
			if (new java.util.Date().getTime() - bean.getGroupInfoDeadLine().getTime() > 86400000) {
				if (bean.getGroupStatusNo() == 2 || bean.getGroupStatusNo() == 6) {
					System.out.println("getGroupStatusNo=" + bean.getGroupStatusNo() + "超過24H未處理");
					System.out.println("狀態=" + bean.getGroupStatusNo());
					groupInfoDAO.updateGroupStatus(bean.getGroupInfoNo(), 5);
					orderInfoDAO.updateOrderInfoStatusByGroupInfoNo(bean.getGroupInfoNo(), 1005);
					// 可能要呼叫發站內信的功能
					System.out.println("超過24H未處理，狀態改成5，所有該團買家狀態改成1005");
					flag = true;
				}

			}
			int count = 0;
			int checkCount = 0;
			if (bean.getGroupStatusNo() == 9) {
				for (OrderInfoBean orderBean : orderInfoDAO.selectMyGroupOrderInfo(bean.getGroupInfoNo())) {
					if (orderBean.getOrderInfoStatusNo() != 1004 && orderBean.getOrderInfoStatusNo() != 1002) {
						count++;

					}

					if (orderBean.getOrderInfoStatusNo() == 1202) {
						checkCount++;
					}

				}
				if (count == checkCount) {
					groupInfoDAO.updateGroupStatus(bean.getGroupInfoNo(), 11);
					System.out.println("已匯款的買家都已收貨+評分，團狀態改成11(成功結束)");
					flag = true;
				}
			}
		}
		return flag;
	}
}
