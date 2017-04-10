package eeit9212.model.dao;

import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import eeit9212.model.ReportBean;

public class ReportDAOHibernate implements ReportDAO {
	
	private SessionFactory sessionFactory;

	public ReportDAOHibernate(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("eeit9212/beansHibernate.xml");
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
		try{
		sessionFactory.getCurrentSession().beginTransaction();
		ReportDAO reportDAOHibernate=new ReportDAOHibernate(sessionFactory);
		ReportBean bean=new ReportBean();
		bean.setMemberNo(2);
		bean.setReportTypeNo(4);
		bean.setReportStatusNo(9201);
		bean.setReportTarget(3);
		bean.setReportContent("helladadadado");
//		ReportBean bean1=new ReportBean();
//		bean1.setReportNo(1);
//		bean1.setMemberNo(100);
//		bean1.setReportTypeNo(1);
//		bean1.setReportStatusNo(9202);
//		bean1.setReportTarget(3);
//		bean1.setReportContent("hello");
//		System.out.println(reportDAOHibernate.insertReport(bean));
		reportDAOHibernate.updateReport(bean);
//		System.out.println(reportDAOHibernate.selectReport(bean));
		
		sessionFactory.getCurrentSession().getTransaction().commit();
		}
		catch(Exception e){
			e.printStackTrace();
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		((ConfigurableApplicationContext) context).close();
	}
	

	public Serializable insertReport(ReportBean bean){
		Serializable result = this.getSession().save(bean);
		return result;
	}

	public void updateReport(ReportBean bean){
		String selectReport="update report set report_Content=? where member_No=? and reportType_No=? and report_Target=?";
		NativeQuery<?> query=this.getSession().createNativeQuery(selectReport);
		query.setParameter(1, bean.getReportContent());
		query.setParameter(2, bean.getMemberNo());
		query.setParameter(3, bean.getReportTypeNo());
		query.setParameter(4, bean.getReportTarget());
		query.executeUpdate();
	}

	public ReportBean selectReport(ReportBean bean){
		String selectReport="from ReportBean where member_No=? and reportType_No=? and report_Target=?";
		ReportBean result=null;	
		Query<ReportBean> query=this.getSession().createQuery(selectReport,ReportBean.class);
		query.setParameter(0, bean.getMemberNo());
		query.setParameter(1, bean.getReportTypeNo());
		query.setParameter(2, bean.getReportTarget());
		try{
		result=query.getSingleResult();
		
		}catch(Exception e){
			System.out.println("selectReport找不到該資料，正常insert。");
		}
		return result;
	}
	
	
	
}
