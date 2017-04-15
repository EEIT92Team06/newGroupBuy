package searchgroup.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SearchService {
	private SearchDAO searchDAO;
	public SearchService(SearchDAO searchDAO) {
		this.searchDAO = searchDAO;
	}
	
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.cfg.xml");
		SessionFactory sessionFactory = (SessionFactory)context.getBean("sessionFactory");
		sessionFactory.getCurrentSession().beginTransaction();
		SearchService searchService = (SearchService) context.getBean("searchService");
		
//		List<Map<String, String>> result = searchService.select("");
//		System.out.println("result : " + result);
		// ============================================================
//		List<Map<String, String>> result = searchService.select(2);
//		System.out.println("result : " + result);
		// ============================================================		
//		List<Map<String, String>> result = searchService.selectGroupType();
//		System.out.println("result : " + result);
		// ============================================================
//		List<Map<String, String>> result = searchService.selectGroupType();
//		System.out.println("result : " + result);
		// ============================================================
//		List<Map<String, String>> result = searchService.selectTop2Group();
//		System.out.println("result : " + result);
		// ============================================================
		int result = searchService.selectRecommendTable(1);
		System.out.println("result ==> " + result);
		
		sessionFactory.getCurrentSession().getTransaction().commit();
		((ConfigurableApplicationContext) context).close();
		
	}
		
		
		
	public List<Map<String, String>> select(String groupName){
		List<Object[]> results;
		if(groupName == null || groupName.trim().length() ==0){
			results = searchDAO.select();
		}else{
			results = searchDAO.select(groupName);
		}
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		for(Object[] result : results){
			Map<String, String> map = new HashMap<String, String>();
			map.put("groupInfoNo", result[0].toString());
			map.put("groupInfoName", result[1].toString());
			map.put("productType", result[2].toString());
			map.put("groupStatus", result[3].toString());
			map.put("memberName", result[4].toString());
			if(result[5] == null){
				map.put("result", "無評分紀錄");
			}else{
				map.put("result", result[5].toString());
			}
			list.add(map);
		}
		return list;
	}
	
	public List<Map<String, String>> selectGroupType(){
		List<Object[]> results = searchDAO.selectGroupType();
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		for(Object[] result : results){
			Map<String, String>map = new HashMap<String, String>();
			map.put("productTypeNo", result[0].toString());
			map.put("productType", result[1].toString());
			list.add(map);
		}
		return list;
	}
	
	public List<Map<String, String>> select(int groupTypeNo){
		List<Object[]> results = searchDAO.select(groupTypeNo);
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		for(Object[] result : results){
			Map<String, String> map = new HashMap<String, String>();
			map.put("groupInfoNo", result[0].toString());
			map.put("groupInfoName", result[1].toString());
			map.put("productType", result[2].toString());
			map.put("groupStatus", result[3].toString());
			map.put("memberName", result[4].toString());
			if(result[5] == null){
				map.put("result", "無評分紀錄");
			}else{
				map.put("result", result[5].toString());
			}
			list.add(map);
		}
		return list;
	}
	
	public List<Map<String, String>> selectTop2Group(){
		List<Object[]> results = searchDAO.selectTop2Group();
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		for(Object[] result : results){
			Map<String, String> map = new HashMap<String, String>();
			map.put("groupInfoNo", result[0].toString());
			map.put("groupInfoName", result[1].toString());
			map.put("productType", result[2].toString());
			map.put("groupStatus", result[3].toString());
			map.put("memberName", result[4].toString());
			if(result[5] == null){
				map.put("result", "無評分紀錄");
			}else{
				map.put("result", result[5].toString());
			}
			list.add(map);
		}
		return list;
	}
	
	public int selectRecommendTable(int memberNo){
		Object[] result = searchDAO.selectRecommendTable(memberNo);
		int[] resultToint = new int[8];
		for(int i =1 ; i< result.length; i++){
			resultToint[i-1] = (int)result[i];
		}
		int[] index = {1,2,3,4,5,6,7,8};
		while(true){
			int counter = 0;
			for(int i = 0;i<resultToint.length-1;i++){
				int temp = 0;
				int temp1 = 0;
				if(resultToint[i]<resultToint[i+1]){
					temp = resultToint[i];
					temp1 = index[i];
					resultToint[i] = resultToint[i+1];
					index[i] = index[i+1];
					resultToint[i+1] = temp;
					index[i+1] = temp1;
					counter++;
				}
			}
			if(counter == 0)break;
		}
		for(int i =0 ; i< resultToint.length; i++){
			System.out.print("index : " + index[i]);
			System.out.println(" || result = " + resultToint[i]);
		}  
		return index[0];
	}
	
	public int insertClickTimes(String INSERT_RECOMMEND_CLICKTIME) {
		return searchDAO.insertClickTimes(INSERT_RECOMMEND_CLICKTIME);
	}
	
	public int insertRecommend(int memberNo){
		return searchDAO.insertRecommend(memberNo);
	}
	
}
