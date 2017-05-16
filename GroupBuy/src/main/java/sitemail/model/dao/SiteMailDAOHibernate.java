package sitemail.model.dao;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import sitemail.model.AnnouncementBean;
import sitemail.model.MailBean;
import sitemail.model.SiteMailBean;
import sitemail.model.SiteMailDAO;

public class SiteMailDAOHibernate implements SiteMailDAO {
	private SessionFactory sessionFactory;

	public SiteMailDAOHibernate(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public static void main(String[] args) {

		ApplicationContext context = new ClassPathXmlApplicationContext("beans.config.xml");
		SiteMailDAO siteMailDAOHibernate = (SiteMailDAO) context.getBean("siteMailDAOHibernate");
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
		sessionFactory.getCurrentSession().beginTransaction();
//		List<Object[]> result = siteMailDAOHibernate.selectMailByMemberNo(3);
//		for(Object[] temp:result){
//			MailBean bean=new MailBean();
//			bean.setSiteMailNo(Integer.parseInt(temp[0].toString())); 
//			bean.setMemberNo(Integer.parseInt(temp[1].toString()));
//			bean.setSiteMailStatusNo(Integer.parseInt(temp[2].toString()));
//			bean.setSiteMailTime((Timestamp)temp[3]);
//			bean.setSiteMailCanNo(Integer.parseInt(temp[4].toString()));
//			bean.setSiteMailCanTitle(temp[5].toString());
//			bean.setSiteMailCanContent(temp[6].toString());
//			System.out.println(bean.getSiteMailCanContent());
//		}
		
//		int deleteNum=siteMailDAOHibernate.deleteMail(1);
//		System.out.println("deleteNum="+deleteNum);
		
//	    List<Object[]> result = siteMailDAOHibernate.searchMail("%"+"寄"+"%");
//	    for(Object[] ob:result){
//	    	MailBean bean=new MailBean();
//			bean.setSiteMailNo(Integer.parseInt(ob[0].toString())); 
//			bean.setMemberNo(Integer.parseInt(ob[1].toString()));
//			bean.setSiteMailStatusNo(Integer.parseInt(ob[2].toString()));
//			bean.setSiteMailTime((Timestamp)ob[3]);
//			bean.setSiteMailCanNo(Integer.parseInt(ob[4].toString()));
//			bean.setSiteMailCanTitle(ob[5].toString());
//			bean.setSiteMailCanContent(ob[6].toString());
//            System.out.println(bean.getSiteMailCanTitle());
//	    }
		
//		MailBean temp = siteMailDAOHibernate.selectSpecificMail(2);
//		System.out.println("temp="+temp);
		
//		SiteMailCanBean siteMailCanBean=(SiteMailCanBean)siteMailDAOHibernate.selectMailContent(1);
//		System.out.println("siteMailCanBean="+siteMailCanBean);
		
//		List<Object[]> results=siteMailDAOHibernate.selectAnnounceMailByMemberNo(1);
//		for(Object[]result:results){
//		  AnnouncementBean bean=new AnnouncementBean();
//		  bean.setSiteMailNo(Integer.parseInt(result[0].toString()));
//		  bean.setMemberNo(Integer.parseInt(result[1].toString()));
//		  bean.setSiteMailStatusNo(Integer.parseInt(result[2].toString()));
//		  bean.setSiteMailTitle(result[3].toString());
//		  bean.setSiteMailTime((Timestamp)result[4]);
//		  bean.setSiteMailContent(result[5].toString());
//		  System.out.println(bean.getSiteMailContent());
//		}
		SiteMailBean bean=new SiteMailBean();
		bean.setMemberNo(1);
		bean.setSiteMailStatusNo(9301);
		bean.setSiteMailCanNo(1);
		bean.setSiteMailTime((Timestamp)new java.util.Date());
		int insertNum=siteMailDAOHibernate.insertSiteMail(bean);
		System.out.println("insertNum="+insertNum);
		
		sessionFactory.getCurrentSession().getTransaction().commit();
		((ConfigurableApplicationContext) context).close();
	}	

	//查詢狀態信
	private static final String selectMail = "select *from mail  where member_No=? order by siteMail_Time desc";
	@Override
	public List<Object[]> selectMailByMemberNo(int memberNo) {
		Query query = sessionFactory.getCurrentSession().createNativeQuery(selectMail);
		query.setParameter(1, memberNo);
		return query.getResultList();
	}
	//查詢狀態信(未讀)
	private static final String selectUnReadMail = "select *from mail  where siteMailStatus_No=9301 and member_No=? order by siteMail_Time desc";
	@Override
	public List<Object[]> selectUnReadMailByMemberNo(int memberNo) {
		Query query = sessionFactory.getCurrentSession().createNativeQuery(selectUnReadMail);
		query.setParameter(1, memberNo);
		return query.getResultList();
	}
	//查詢公告信
	private static final String selectAnnounceMail = "select *from annoucement  where member_No=? order by siteMail_Time desc";
	@Override
	public List<Object[]> selectAnnounceMailByMemberNo(Integer memberNo) {
	
		return temp(memberNo, selectAnnounceMail);
	}
	//查詢公告信(未讀)
	private static final String queryStr = "select *from annoucement  where member_No=? and sitemailStatus_No=9301 order by siteMail_Time desc";
	@Override
	public List<Object[]> selectUnReadAnnounceMailByMemberNo(Integer memberNo) {
		return temp(memberNo, queryStr);
	}

	private List<Object[]> temp(Integer memberNo, String queryStr) {
		Query query = sessionFactory.getCurrentSession().createNativeQuery(queryStr);
		query.setParameter(1, memberNo);
		return query.getResultList();
	}


	//刪除狀態信
	@Override
	public int deleteMail(int siteMailNo){
	  int deleteNum=0;
	  Session session=sessionFactory.getCurrentSession();
	  SiteMailBean siteMailBean=(SiteMailBean)session.get(SiteMailBean.class, siteMailNo);
	  if(siteMailBean!=null){
	  session.delete(siteMailBean);
	  deleteNum=1;
	  }
	  return deleteNum;  
	}
	//刪除公告信
	@Override
	public int deleteAnnounceMail(int siteMailNo){
		  int deleteNum=0;
		  Session session=sessionFactory.getCurrentSession();
		  AnnouncementBean announcementBean=(AnnouncementBean)session.get(AnnouncementBean.class, siteMailNo);
		  if(announcementBean!=null){
		  session.delete(announcementBean);
		  deleteNum=1;
		  }
		  return deleteNum;  
		}
	//刪除未讀狀態信
	private static final String deleteUnReadMail = " delete  from  SiteMail  where siteMailStatus_No=9301 and siteMail_No=?";
	@Override
	public int deleteUnReadMail(int siteMailNo){
	  int deleteNum=0;
	  Query query = sessionFactory.getCurrentSession().createNativeQuery(deleteUnReadMail);
	  query.setParameter(1, siteMailNo);
	  deleteNum=query.executeUpdate();
	  if(deleteNum==1){
		  deleteNum=1;
	  }
	  return deleteNum;  
	}
	//刪除未讀公告信
	private static final String deleteUnReadAnnounce = " delete  from  annoucement  where siteMailStatus_No=9301 and siteMail_No=?";
	@Override
	public int deleteUnReadAnnounce(int siteMailNo){
	  int deleteNum=0;
	  Query query = sessionFactory.getCurrentSession().createNativeQuery(deleteUnReadAnnounce);
	  query.setParameter(1, siteMailNo);
	  deleteNum=query.executeUpdate();
	  if(deleteNum==1){
		  deleteNum=1;
	  }
	  return deleteNum;  
	}



	//搜尋引擎(狀態信)
	private static String serchEngine ="select * from mail where siteMailCan_Title like ?  and member_No=?";
	@Override
	public List<Object[]>searchMail(String keyWord,int memberNo){
		Query query=sessionFactory.getCurrentSession().createNativeQuery(serchEngine);
		query.setParameter(1, keyWord);
		query.setParameter(2, memberNo);
		return query.getResultList();
	}
	//搜尋引擎(公告信)
	private static String serchAnnounce ="  select * from annoucement where siteMail_Type like '%系統公告%'and member_No=?";
	@Override
	public List<Object[]>searchAnnounceMail(String keyWord,int memberNo){
		Query query=sessionFactory.getCurrentSession().createNativeQuery(serchAnnounce);
		query.setParameter(1, memberNo);
		return query.getResultList();
	}
	//查詢狀態信內容
	@Override
	public MailBean selectSpecificMail(Integer siteMailNo){
		MailBean mailBean=null;
		if(siteMailNo!=null){
			Session session=sessionFactory.getCurrentSession();
			mailBean=(MailBean)session.get(MailBean.class, siteMailNo);
		}
		return mailBean;
	}
	//查詢公告信內容
	@Override
	public AnnouncementBean selectAnnounceMail(Integer siteMailNo){
		AnnouncementBean mailBean=null;
		if(siteMailNo!=null){
			Session session=sessionFactory.getCurrentSession();
			mailBean=(AnnouncementBean)session.get(AnnouncementBean.class, siteMailNo);
		}
		return mailBean;
	}

	//新增狀態信
	@Override
	public int insertSiteMail(SiteMailBean siteMailBean){
		int insertNum=0;
		if(siteMailBean!=null){
			Session session=sessionFactory.getCurrentSession();
			session.saveOrUpdate(siteMailBean);
			insertNum=1;
		}
		
		return insertNum;
	}
	//更新狀態信狀態
	@Override
	public int updateMailStatus(SiteMailBean siteMailBean) {
		int updateNum = 0;
		if (siteMailBean != null) {
			Session session = sessionFactory.getCurrentSession();
			session.saveOrUpdate(siteMailBean);
			updateNum = 1;
		}
		return updateNum;
	}
	//更新公告信狀態
	@Override
	public int updateAnnounceMailStatus(AnnouncementBean announcementBean) {
		int updateNum = 0;
		if (announcementBean != null) {
			Session session = sessionFactory.getCurrentSession();
			//不加這行會出現 錯誤 a different object with the same identifier value was already associated with the session。
			session.clear();
			session.saveOrUpdate(announcementBean);
			updateNum = 1;
		}
		return updateNum;
	}
	//查詢狀態信已讀未讀
	@Override
	public SiteMailBean selectStatus(Integer siteMailNo){
		SiteMailBean bean=null;
		if(siteMailNo!=null){
		Session session=sessionFactory.getCurrentSession();
		bean=(SiteMailBean)session.get(SiteMailBean.class, siteMailNo);
		}
		return bean;
	}
	//查詢公告信已讀未讀
	@Override
	public AnnouncementBean selectAnnounceStatus(Integer siteMailNo){
		AnnouncementBean bean=null;
		if(siteMailNo!=null){
		Session session=sessionFactory.getCurrentSession();
		bean=(AnnouncementBean)session.get(AnnouncementBean.class, siteMailNo);
		}
		return bean;

	}
	//查詢未讀狀態信
	private static final String countSiteMail ="  select count(*) from siteMail where siteMailStatus_No=9301 and member_No=?";
	@Override
	public int countSiteMail(Integer memberNo){
		int unReadNum=0;
		if(memberNo!=null){
			Session session=sessionFactory.getCurrentSession();
			Query query=session.createNativeQuery(countSiteMail);
			query.setParameter(1,memberNo);
			unReadNum=(int)query.getSingleResult();
		}
		return unReadNum;
	}
	
	//查詢未讀公告信
	private static final String countAnnounceMail ="  select count(*) from annoucement where siteMailStatus_No=9301 and member_No=?";
	@Override
	public int countAnnounceMail(Integer memberNo){
		int unReadNum=0;
		if(memberNo!=null){
			Session session=sessionFactory.getCurrentSession();
			Query query=session.createNativeQuery(countAnnounceMail);
			query.setParameter(1,memberNo);
			unReadNum=(int)query.getSingleResult();
		}
		return unReadNum;
	}
}
