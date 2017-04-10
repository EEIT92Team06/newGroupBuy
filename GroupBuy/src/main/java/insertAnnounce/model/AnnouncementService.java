package insertAnnounce.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AnnouncementService {
	private AnnounceDAO announceDAO;
	public AnnouncementService(AnnounceDAO announceDAO) {
		this.announceDAO = announceDAO;
	}

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.cfg.xml");
		SessionFactory sessionFactory = (SessionFactory)context.getBean("sessionFactory");
		sessionFactory.getCurrentSession().beginTransaction();
		AnnouncementService announceService = (AnnouncementService) context.getBean("announceService");
//		List<Map<String, String>> results = announceService.select();
//		System.out.println("results : " + results);
		
//		Timestamp time = new Timestamp(new Date().getTime());
//		System.out.println("time : " + time);
//		AnnouncementBean announce = new AnnouncementBean(1, 9301, "xxx", time, "nono");
//		System.out.println("announce : " + announce);
//		announceService.insertMsg(announce);
		
//		int result = announceService.modifyMemStatus(2);
//		System.out.println("result : "+ result);
		
//		announceService.modifyGroupAndOrder(4);
		
		List<Map<String, String>> results = announceService.selectReports();
		System.out.println(results);
		
		
		sessionFactory.getCurrentSession().getTransaction().commit();
		((ConfigurableApplicationContext) context).close();
	}
	
	public void insertMsg(AnnouncementBean announcementBean){
		announceDAO.insert(announcementBean);
	}
	
	public List<Map<String,String>> select(){
		List<Object[]> results = announceDAO.select();
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		for(Object[] result : results){
			Map<String,String> map = new HashMap<String,String>();
			map.put("memberNo", result[0].toString());
			map.put("memberName", result[1].toString());
			list.add(map);
		}
		return list;
	}
	
	public List<Map<String,String>> selectPartMem(){
		List<Object[]> results = announceDAO.selectPartMem();
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		for(Object[] result : results){
			Map<String,String> map = new HashMap<String,String>();
			map.put("memberNo", result[0].toString());
			map.put("memberName", result[1].toString());
			list.add(map);
		}
		return list;
	}
	
	public int modifyMemStatus(int memberNo){
		if(announceDAO.update(memberNo) == 1){
			return announceDAO.insertBan(memberNo);
		}
		return 0;
	}
	
	public void modifyGroupAndOrder(int groupNo){
		announceDAO.updateGroupStatus(groupNo);
		announceDAO.updateOrderInfoStatusByGroupInfoNo(groupNo);
	}
	
	public List<Map<String,String>> selectReports(){
		List<Object[]> results = announceDAO.selectReports();
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		for(Object[] result : results){
			Map<String,String> map = new HashMap<String,String>();
			map.put("reportNo", result[0].toString());
			map.put("memberNo", result[1].toString());
			map.put("memberName", result[2].toString());
			map.put("reportTypeNo", result[3].toString());
			map.put("reportType", result[4].toString());
			map.put("reportStatusNo", result[5].toString());
			map.put("reportTarget", result[6].toString());
			map.put("reportContent", result[7].toString());
			list.add(map);
		}
		return list;
	}
	

}
