package searchgroup.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SearchDetailsService {
	
	private SearchDetailsDAO searchDetailsDAO;
	public SearchDetailsService(SearchDetailsDAO searchDetailsDAO) {
		this.searchDetailsDAO = searchDetailsDAO;
	}
	public static void main(String args[]){
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.cfg.xml");
		SessionFactory sessionFactory = (SessionFactory)context.getBean("sessionFactory");
		sessionFactory.getCurrentSession().beginTransaction();
		SearchDetailsService searchDetailsService = (SearchDetailsService) context.getBean("searchDetailsService");
		
//		Map<String, String> result = searchDetailsService.selectDetails(2);
//		System.out.println(result);
		
//		List<Map<String, String>> result = searchDetailsService.selectGroupProdsDetails(1);
//		System.out.println(result);
		
//		List<Map<String, String>> result = searchDetailsService.selectDetailsPicNo(1);
//		System.out.println(result);
		
		String result = searchDetailsService.selectmemberPic(1);
		System.out.println("result : " + result);
		
//		int result = searchDetailsService.insertClickTimes(1);
//		System.out.println("update "+result+"筆資料成功");
		
		sessionFactory.getCurrentSession().getTransaction().commit();
		((ConfigurableApplicationContext) context).close();
	}
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	public Map<String, String> selectDetails(int groupInfoNo){
		Object[] result = searchDetailsDAO.selectDetails(groupInfoNo);
		Map<String, String>map = new HashMap<String, String>();
		map.put("memberNo", result[0].toString());
		map.put("groupInfoName", result[1].toString());
		map.put("memberName", result[2].toString());
		String formatStartDate = format.format((Date)result[3]);
		map.put("groupInfoStartDate", formatStartDate);
		String formatDeadLine = format.format((Date)result[4]);
		map.put("groupInfoDeadLine", formatDeadLine);
		if(result[5] == null){
			map.put("groupInfoContent", "");
		}else{
			map.put("groupInfoContent", result[5].toString());
		}
		map.put("groupInfoShippingWay", result[6].toString());
		map.put("productType", result[7].toString());
		map.put("groupStatus", result[8].toString());
		map.put("groupInfoMinProductQt", result[9].toString());
		if(result[10] == null){
			map.put("result", "無評分紀錄");
		}else{
			map.put("result", result[10].toString());
		}
		
		return map;
	}
	
	public List<Map<String, String>> selectDetailsPicNo(int groupInfoNo) {
		List<Object> results = searchDetailsDAO.selectDetailsPicNo(groupInfoNo);
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		for(Object result : results){
			Map<String, String>map = new HashMap<String, String>();
			map.put("groupInfoPicNo", result.toString());
			list.add(map);
		}
		return list;
	}
	
	public List<Map<String, String>> selectGroupProdsDetails(int groupInfoNo) {
		List<Object[]> results = searchDetailsDAO.selectGroupProdsDetails(groupInfoNo);
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		for(Object[] result : results){
			Map<String, String>map = new HashMap<String, String>();
			map.put("groupInfoDetailsNo", result[0].toString());
			map.put("groupInfoDetailsProdcutName", result[1].toString());
			map.put("groupInfoDetailsProductPrice", result[2].toString());
			list.add(map);
		}
		return list;
	}
	
	public String selectmemberPic(int memberNo) {
		Object result = searchDetailsDAO.selectmemberPic(memberNo);
		return result.toString();
	}
	
	
	public int insertClickTimes(int groupInfoNo){
		return searchDetailsDAO.insertClickTimes(groupInfoNo);
	}
}
