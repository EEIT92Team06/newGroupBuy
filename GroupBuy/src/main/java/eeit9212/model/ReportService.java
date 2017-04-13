package eeit9212.model;

import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import eeit9212.model.dao.ReportDAO;

public class ReportService {

	private ReportDAO reportDAO;
	
	public ReportService(ReportDAO reportDAO) {
		this.reportDAO = reportDAO;
	}

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("eeit9212/beansHibernate.xml");
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
		ReportService reportService = (ReportService) context.getBean("reportService");
		try{
		sessionFactory.getCurrentSession().beginTransaction();
		ReportBean bean=new ReportBean();
		bean.setMemberNo(20);
		bean.setReportTypeNo(3);
		bean.setReportStatusNo(9201);
		bean.setReportTarget(10);
		bean.setReportContent("hello");
		System.out.println(reportService.report(bean));
		
		
		sessionFactory.getCurrentSession().getTransaction().commit();
		}catch(Exception e){
			System.out.println("HI");
			e.printStackTrace();
		}
		((ConfigurableApplicationContext) context).close();
	}
	public boolean report(ReportBean bean){
		boolean result=false;
		if(reportDAO.selectReport(bean)==null){
			reportDAO.insertReport(bean);
			result=true;
		}
		else{
			System.out.println("selectReport該資料存在，正常update。");
			reportDAO.updateReport(bean);	
		}
		return result;
	}
	
	
}
