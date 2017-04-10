package searchgroup.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class GroupMsgService {
	private GroupMsgDAO groupMsgDAO;

	public GroupMsgService(GroupMsgDAO groupMsgDAO) {
		this.groupMsgDAO = groupMsgDAO;
	}
	
	public static void main(String args[]){
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.cfg.xml");
		SessionFactory sessionFactory = (SessionFactory)context.getBean("sessionFactory");
		sessionFactory.getCurrentSession().beginTransaction();
		GroupMsgService groupMsgService = (GroupMsgService) context.getBean("groupMsgService");
		List<Map<String, Object>> results = groupMsgService.selectMSG(1);
		System.out.println("results : " + results);
		
		
		sessionFactory.getCurrentSession().getTransaction().commit();
		((ConfigurableApplicationContext) context).close();
	}
	public List<Map<String,Object>> selectMSG(int groupInfoNo){
		List<Object[]> results = groupMsgDAO.selectMsg(groupInfoNo);
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		for(Object[] result : results){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("groupMsgNo", result[0].toString());
			map.put("memberName", result[1].toString());
			map.put("groupMsgContent", result[2].toString());
			List<Object[]> replyResults = groupMsgDAO.selectReplyMsg(Integer.parseInt(result[0].toString()));
			List<Map<String,String>> replylist = new ArrayList<Map<String,String>>();
			for(Object[] replyResult : replyResults){
				Map<String, String>replymap = new HashMap<String, String>();
				replymap.put("memberName", replyResult[0].toString());
				replymap.put("groupMsgReplyContent", replyResult[1].toString());
				replylist.add(replymap);
			}
			map.put("groupReplyMsg", replylist);
			list.add(map);
		}
		return list;
	}
	public void insertMsg(GroupMsgBean groupMsgBean){
		groupMsgDAO.insert(groupMsgBean);
	}
	public void insertreplyMsg(GroupMsgReplyBean groupMsgReplyBean){
		groupMsgDAO.insertReply(groupMsgReplyBean);
	}
	
}
