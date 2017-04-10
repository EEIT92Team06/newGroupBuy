package model;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import model.dao.MemberDAOJdbc;

public class MemberService {
	public static void main(String[] args){
		ApplicationContext context=new ClassPathXmlApplicationContext("eeit9211/beans.config.xml");
		MemberService memberService=(MemberService)context.getBean("memberService");
//		memberService.updateMemberInfo(1, "8d7嫌", "87號");
//		memberDAOJdbc.updateMemberPassword(1, "12sf52");
//		memberDAOJdbc.updateMemberPic(1, "aaaaa//0a/a\\aaa");
		MemberBean ans=memberService.selectMemberInfo(1);
		
		System.out.println(ans);
		
		
		((ConfigurableApplicationContext)context).close();
	}
	private MemberDAO memberDAO;
	public MemberService(MemberDAO memberDAO) {
		this.memberDAO=memberDAO;
	}

	public MemberBean selectMemberInfo(int memberNo) {
		MemberBean result=memberDAO.selectMemberInfo(memberNo);
		return result;
	}

	public MemberBean updateMemberPassword(int memberNo, String memberPassword) {
		MemberBean result=memberDAO.updateMemberPassword(memberNo, memberPassword);
		return result;
	}

	public MemberBean updateMemberPic(int memberNo, String memberPic) {
		MemberBean result=null;
		return result;
	}

	public MemberBean updateMemberInfo(int memberNo, String memberNickName, String memberAddress) {
		MemberBean result=memberDAO.updateMemberInfo(memberNo, memberNickName, memberAddress);
		return result;
	}
}
