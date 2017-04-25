package sitemail.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import login.model.MemberBean;

public class SiteMailService {
	private SiteMailDAO siteMailDAO;

	public SiteMailService(SiteMailDAO siteMailDAO) {
		this.siteMailDAO = siteMailDAO;
	}

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.config.xml");
		SiteMailService siteMailService = (SiteMailService) context.getBean("siteMailService");
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
		sessionFactory.getCurrentSession().beginTransaction();
		MemberBean bean = new MemberBean();

		// bean.setMemberNo(1);
		// List<MailBean> results = siteMailService.selectMailByMemberNo(bean);
		// for (MailBean result : results) {
		// MailBean bean1 = new MailBean();
		// bean1.setSiteMailNo(result.getSiteMailNo());
		// bean1.setMemberNo(result.getMemberNo());
		// bean1.setSiteMailStatusNo(result.getSiteMailStatusNo());
		// bean1.setSiteMailTime(result.getSiteMailTime());
		// bean1.setSiteMailCanNo(result.getSiteMailCanNo());
		// bean1.setSiteMailCanTitle(result.getSiteMailCanTitle());
		// bean1.setSiteMailCanContent(result.getSiteMailCanContent());
		// System.out.println(bean1.getSiteMailCanContent());
		// }

		// List<MailBean> results = siteMailService.searchMail("開");
		// for (MailBean result : results) {
		// MailBean bean1 = new MailBean();
		// bean1.setSiteMailNo(result.getSiteMailNo());
		// bean1.setMemberNo(result.getMemberNo());
		// bean1.setSiteMailStatusNo(result.getSiteMailStatusNo());
		// bean1.setSiteMailTime(result.getSiteMailTime());
		// bean1.setSiteMailCanNo(result.getSiteMailCanNo());
		// bean1.setSiteMailCanTitle(result.getSiteMailCanTitle());
		// bean1.setSiteMailCanContent(result.getSiteMailCanContent());
		// System.out.println("bean1="+bean1);
		// }

		// int deleteNum=siteMailService.deleteMail(8);
		// System.out.println("deleteNum="+deleteNum);

		// MailBean temp=siteMailService.selectSpecificMail(2);
		// System.out.println("temp="+temp);

		List<AnnouncementBean> temp = siteMailService.selectAnnounceMail(3);
		System.out.println("temp=" + temp);
		sessionFactory.getCurrentSession().getTransaction().commit();
		((ConfigurableApplicationContext) context).close();
	}

	public List<MailBean> selectMailByMemberNo(login.model.MemberBean memberBean) {
		List<MailBean> list = null;
		if (memberBean != null) {
			List<Object[]> results = siteMailDAO.selectMailByMemberNo(memberBean.getMemberNo());
			list = new ArrayList<MailBean>();
			for (Object[] temp : results) {
				MailBean bean = new MailBean();
				bean.setSiteMailNo(Integer.parseInt(temp[0].toString()));
				bean.setMemberNo(Integer.parseInt(temp[1].toString()));
				bean.setSiteMailStatusNo(Integer.parseInt(temp[2].toString()));
				bean.setSiteMailTime((Timestamp) temp[3]);
				bean.setSiteMailCanNo(Integer.parseInt(temp[4].toString()));
				bean.setSiteMailCanTitle(temp[5].toString());
				bean.setSiteMailCanContent(temp[6].toString());
				list.add(bean);
			}
			return list;
		}
		return null;
	}

	public List<AnnouncementBean> searchAnnounceMail(String keyWord, int memberNo) {
		List<AnnouncementBean> list = null;
		if (keyWord != null) {
			list = new ArrayList<AnnouncementBean>();
			List<Object[]> result = siteMailDAO.searchAnnounceMail(keyWord, memberNo);
			for (Object[] ob : result) {
				AnnouncementBean bean = new AnnouncementBean();
				bean.setSiteMailNo(Integer.parseInt(ob[0].toString()));
				bean.setMemberNo(Integer.parseInt(ob[1].toString()));
				bean.setSiteMailStatusNo(Integer.parseInt(ob[2].toString()));
				bean.setSiteMailTitle(ob[3].toString());
				bean.setSiteMailTime((Timestamp) ob[4]);
				bean.setSiteMailContent(ob[5].toString());
				bean.setSiteMailType(ob[6].toString());
				list.add(bean);
			}
			return list;

		}
		return null;
	}

	public List<MailBean> searchMail(String keyWord, int memberNo) {
		List<MailBean> list = null;
		if (keyWord != null) {
			list = new ArrayList<MailBean>();
			List<Object[]> result = siteMailDAO.searchMail("%" + keyWord + "%", memberNo);
			for (Object[] ob : result) {
				MailBean bean = new MailBean();
				bean.setSiteMailNo(Integer.parseInt(ob[0].toString()));
				bean.setMemberNo(Integer.parseInt(ob[1].toString()));
				bean.setSiteMailStatusNo(Integer.parseInt(ob[2].toString()));
				bean.setSiteMailTime((Timestamp) ob[3]);
				bean.setSiteMailCanNo(Integer.parseInt(ob[4].toString()));
				bean.setSiteMailCanTitle(ob[5].toString());
				bean.setSiteMailCanContent(ob[6].toString());
				list.add(bean);
			}
			return list;
		}
		return null;
	}
//刪除狀態信
	public int deleteMail(Integer siteMailNo) {
		int deleteNum = 0;
		if (siteMailNo != null) {
			deleteNum = siteMailDAO.deleteMail(siteMailNo);
			deleteNum = 1;
		}
		return deleteNum;
	}
//刪除公告信
	public int deleteAnnounceMail(Integer siteMailNo) {
		int deleteNum = 0;
		if (siteMailNo != null) {
			deleteNum = siteMailDAO.deleteAnnounceMail(siteMailNo);
			deleteNum = 1;
		}
		return deleteNum;
	}
	//刪除未讀狀態信
	public int deleteUnReadMail(Integer siteMailNo){
		int deleteNum=0;
		if(siteMailNo!=null){
			deleteNum=siteMailDAO.deleteUnReadMail(siteMailNo);
			if(deleteNum==1){
				deleteNum=1;
			}
		}
		return deleteNum;
	}
	//刪除未讀公告信
	public int deleteUnReadAnnounceMail(Integer siteMailNo){
		int deleteNum=0;
		if(siteMailNo!=null){
			deleteNum=siteMailDAO.deleteUnReadAnnounce(siteMailNo);
			if(deleteNum==1){
				deleteNum=1;
			}
		}
		return deleteNum;
	}
	public MailBean selectSpecificMail(Integer siteMailNo) {
		MailBean mailBean = null;
		if (siteMailNo != null) {
			mailBean = siteMailDAO.selectSpecificMail(siteMailNo);
		}
		return mailBean;
	}

	public List<AnnouncementBean> selectAnnounceMail(Integer memberNo) {
		List<AnnouncementBean> result = null;
		if (memberNo != null) {
			result = new ArrayList<AnnouncementBean>();
			List<Object[]> temp = siteMailDAO.selectAnnounceMailByMemberNo(memberNo);
			for (Object[] ob : temp) {
				AnnouncementBean bean = new AnnouncementBean();
				bean.setSiteMailNo(Integer.parseInt(ob[0].toString()));
				bean.setMemberNo(Integer.parseInt(ob[1].toString()));
				bean.setSiteMailStatusNo(Integer.parseInt(ob[2].toString()));
				bean.setSiteMailTitle(ob[3].toString());
				bean.setSiteMailTime((Timestamp) ob[4]);
				bean.setSiteMailContent(ob[5].toString());
				result.add(bean);
			}
		}
		return result;
	}

	public AnnouncementBean selectAnnounceDetail(Integer siteMailNo) {
		AnnouncementBean announcementBean = null;
		if (siteMailNo != null) {
			announcementBean = siteMailDAO.selectAnnounceMail(siteMailNo);
		}
		return announcementBean;
	}

	public int sendMail(MemberBean memberBean, int siteMailCanNo) {
		int insertNum = 0;
		if (memberBean != null) {
			SiteMailBean bean = new SiteMailBean();
			bean.setMemberNo(memberBean.getMemberNo());
			bean.setSiteMailStatusNo(9301);
			bean.setSiteMailCanNo(siteMailCanNo);
			bean.setSiteMailTime(new Timestamp(new java.util.Date().getTime()));
			siteMailDAO.insertSiteMail(bean);
			insertNum = 1;
		}
		return insertNum;
	}

	public List<AnnouncementBean> selectUnReadAnnounceMail(Integer memberNo) {
		List<AnnouncementBean> result = null;
		if (memberNo != null) {
			result = new ArrayList<AnnouncementBean>();
			List<Object[]> temp = siteMailDAO.selectUnReadAnnounceMailByMemberNo(memberNo);
			for (Object[] ob : temp) {
				AnnouncementBean bean = new AnnouncementBean();
				bean.setSiteMailNo(Integer.parseInt(ob[0].toString()));
				bean.setMemberNo(Integer.parseInt(ob[1].toString()));
				bean.setSiteMailStatusNo(Integer.parseInt(ob[2].toString()));
				bean.setSiteMailTitle(ob[3].toString());
				bean.setSiteMailTime((Timestamp) ob[4]);
				bean.setSiteMailContent(ob[5].toString());
				result.add(bean);
			}
		}
		return result;
	}

	public List<MailBean> selectUnReadMailByMemberNo(login.model.MemberBean memberBean) {
		List<MailBean> list = null;
		if (memberBean != null) {
			List<Object[]> results = siteMailDAO.selectUnReadMailByMemberNo(memberBean.getMemberNo());
			list = new ArrayList<MailBean>();
			for (Object[] temp : results) {
				MailBean bean = new MailBean();
				bean.setSiteMailNo(Integer.parseInt(temp[0].toString()));
				bean.setMemberNo(Integer.parseInt(temp[1].toString()));
				bean.setSiteMailStatusNo(Integer.parseInt(temp[2].toString()));
				bean.setSiteMailTime((Timestamp) temp[3]);
				bean.setSiteMailCanNo(Integer.parseInt(temp[4].toString()));
				bean.setSiteMailCanTitle(temp[5].toString());
				bean.setSiteMailCanContent(temp[6].toString());
				list.add(bean);
			}
			return list;
		}
		return null;
	}

	// 將狀態信讀取狀態轉為已讀9302
	public int updateMailStatus(SiteMailBean bean) {
		int updateNum = 0;
		if (bean != null) {
			SiteMailBean temp = new SiteMailBean();
			temp.setSiteMailNo(bean.getSiteMailNo());
			temp.setMemberNo(bean.getMemberNo());
			temp.setSiteMailStatusNo(9302);
			temp.setSiteMailCanNo(bean.getSiteMailCanNo());
			temp.setSiteMailTime(bean.getSiteMailTime());
			updateNum = siteMailDAO.updateMailStatus(temp);
		}
		return updateNum;
	}

	// 將公告信讀取狀態轉為已讀9302
	public int updateAnnounceMailStatus(AnnouncementBean bean) {
		int updateNum = 0;
		if (bean != null) {
			AnnouncementBean temp = new AnnouncementBean();
			temp.setSiteMailNo(bean.getSiteMailNo());
			temp.setMemberNo(bean.getMemberNo());
			temp.setSiteMailStatusNo(9302);
			temp.setSiteMailTitle(bean.getSiteMailTitle());
			temp.setSiteMailTime(bean.getSiteMailTime());
			temp.setSiteMailContent(bean.getSiteMailContent());
			temp.setSiteMailType(bean.getSiteMailType());
			updateNum = siteMailDAO.updateAnnounceMailStatus(temp);
		}
		return updateNum;
	}
	//查詢狀態信已讀未讀
	public int getMailStatus(Integer siteMailNo){
		int statusNo=0;
		if(siteMailNo!=null){
			SiteMailBean bean=siteMailDAO.selectStatus(siteMailNo);
			statusNo=bean.getSiteMailStatusNo();
		}
		return statusNo;
	}
	//查詢公告信已讀未讀
	public int getAnnounceStatus(Integer siteMailNo){
		int statusNo=0;
		if(siteMailNo!=null){
			AnnouncementBean bean=siteMailDAO.selectAnnounceStatus(siteMailNo);
			statusNo=bean.getSiteMailStatusNo();
		}
		return statusNo;

	}
	//查詢個人總未讀信件
	public int getUnReadNum(MemberBean memberBean){
		int unReadNum=0;
		
		if(memberBean!=null){
			int unReadSiteNum=siteMailDAO.countSiteMail(memberBean.getMemberNo());
			int unReadAnnounceNum=siteMailDAO.countAnnounceMail(memberBean.getMemberNo());
			unReadNum=unReadSiteNum+unReadAnnounceNum;
		}
		return unReadNum;
	}
}
