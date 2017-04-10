package eeit9212.model;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CreditAttendanceService {
	private GrouperCreditDAO grouperCreditDAO;
	private GroupAttendanceDAO groupAttendanceDAO;

	public CreditAttendanceService(GrouperCreditDAO grouperCreditDAO,GroupAttendanceDAO groupAttendanceDAO) {
		this.grouperCreditDAO = grouperCreditDAO;
		this.groupAttendanceDAO = groupAttendanceDAO;
	}

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("eeit9212/beans.xml");
		CreditAttendanceService creditAttendanceService = (CreditAttendanceService) context.getBean("creditAttendanceService");

		System.out.println(creditAttendanceService.updateGrouperCredit(4, 5));
		System.out.println(creditAttendanceService.updateGroupAttendance(1, 1));
		((ConfigurableApplicationContext) context).close();
	}

	public boolean updateGrouperCredit(int groupInfoMemberNo, int score) {
		boolean result = false;
		int rs = -1;
		if (grouperCreditDAO.selectGrouperCredit(groupInfoMemberNo)) {
			rs = grouperCreditDAO.updateGrouperCredit(groupInfoMemberNo, score);
		} else {
			rs = grouperCreditDAO.insertGrouperCredit(groupInfoMemberNo, score);
		}
		if (rs == -1) {
			result = false;
		} else {
			result = true;
		}

		return result;
	}
	public boolean updateGroupAttendance(int memberNo,int success) {
		boolean result = false;
		int rs = -1;
		if (groupAttendanceDAO.selectGroupAttendance(memberNo)) {
			rs = groupAttendanceDAO.updateGroupAttendance(memberNo, success);
		} else {
			rs = groupAttendanceDAO.insertGroupAttendance(memberNo, success);
		}
		if (rs == -1) {
			result = false;
		} else {
			result = true;
		}

		return result;
	}

}
